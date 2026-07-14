package hamud.mod.entity.goal;

import hamud.mod.entity.BranoEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class BranoPanicGoal extends Goal {

    private final BranoEntity brano;
    private final double speed;

    private int panicTime;

    public BranoPanicGoal(BranoEntity brano, double speed) {
        this.brano = brano;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity attacker = this.brano.getLastHurtByMob();

        if (attacker == null) {
            return false;
        }

        if (!attacker.isAlive()) {
            return false;
        }

        return this.brano.tickCount - this.brano.getLastHurtByMobTimestamp() < 120;
    }

    @Override
    public boolean canContinueToUse() {
        return this.panicTime > 0 && this.brano.getLastHurtByMob() != null;
    }

    @Override
    public void start() {
        this.panicTime = 100;
    }

    @Override
    public void stop() {
        this.brano.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.panicTime--;

        LivingEntity attacker = this.brano.getLastHurtByMob();

        if (attacker == null) {
            return;
        }

        Vec3 away = this.brano.position().subtract(attacker.position()).normalize();

        double x = this.brano.getX() + away.x * 8.0D + (this.brano.getRandom().nextDouble() - 0.5D) * 4.0D;
        double y = this.brano.getY();
        double z = this.brano.getZ() + away.z * 8.0D + (this.brano.getRandom().nextDouble() - 0.5D) * 4.0D;

        this.brano.getNavigation().moveTo(x, y, z, this.speed);

        this.brano.getLookControl().setLookAt(
                attacker.getX(),
                attacker.getEyeY(),
                attacker.getZ()
        );
    }
}