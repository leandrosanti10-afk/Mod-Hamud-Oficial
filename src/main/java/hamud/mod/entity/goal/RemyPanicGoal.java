package hamud.mod.entity.goal;

import hamud.mod.entity.RemyEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class RemyPanicGoal extends Goal {

    private final RemyEntity remy;
    private final double speed;

    private int panicTime;

    public RemyPanicGoal(RemyEntity remy, double speed) {
        this.remy = remy;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity attacker = this.remy.getLastHurtByMob();

        if (attacker == null) {
            return false;
        }

        if (!attacker.isAlive()) {
            return false;
        }

        return this.remy.tickCount - this.remy.getLastHurtByMobTimestamp() < 120;
    }

    @Override
    public boolean canContinueToUse() {
        return this.panicTime > 0 && this.remy.getLastHurtByMob() != null;
    }

    @Override
    public void start() {
        this.panicTime = 100;
    }

    @Override
    public void stop() {
        this.remy.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.panicTime--;

        LivingEntity attacker = this.remy.getLastHurtByMob();

        if (attacker == null) {
            return;
        }

        Vec3 away = this.remy.position().subtract(attacker.position()).normalize();

        double x = this.remy.getX() + away.x * 8.0D + (this.remy.getRandom().nextDouble() - 0.5D) * 4.0D;
        double y = this.remy.getY();
        double z = this.remy.getZ() + away.z * 8.0D + (this.remy.getRandom().nextDouble() - 0.5D) * 4.0D;

        this.remy.getNavigation().moveTo(x, y, z, this.speed);

        this.remy.getLookControl().setLookAt(
                attacker.getX(),
                attacker.getEyeY(),
                attacker.getZ()
        );
    }
}