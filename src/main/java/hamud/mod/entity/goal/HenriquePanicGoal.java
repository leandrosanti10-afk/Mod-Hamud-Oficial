package hamud.mod.entity.goal;

import hamud.mod.entity.HenriqueEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class HenriquePanicGoal extends Goal {

    private final HenriqueEntity henrique;
    private final double speed;

    private int panicTime;

    public HenriquePanicGoal(HenriqueEntity henrique, double speed) {
        this.henrique = henrique;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity attacker = this.henrique.getLastHurtByMob();

        if (attacker == null) {
            return false;
        }

        if (!attacker.isAlive()) {
            return false;
        }

        return this.henrique.tickCount - this.henrique.getLastHurtByMobTimestamp() < 120;
    }

    @Override
    public boolean canContinueToUse() {
        return this.panicTime > 0 && this.henrique.getLastHurtByMob() != null;
    }

    @Override
    public void start() {
        this.panicTime = 100;

        if (this.henrique.isSleeping()) {
            this.henrique.stopSleeping();
        }
    }

    @Override
    public void stop() {
        this.henrique.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.panicTime--;

        LivingEntity attacker = this.henrique.getLastHurtByMob();

        if (attacker == null) {
            return;
        }

        Vec3 away = this.henrique.position().subtract(attacker.position());

        if (away.lengthSqr() < 0.001D) {
            away = new Vec3(
                    this.henrique.getRandom().nextDouble() - 0.5D,
                    0.0D,
                    this.henrique.getRandom().nextDouble() - 0.5D
            );
        }

        away = away.normalize();

        double x = this.henrique.getX()
                + away.x * 8.0D
                + (this.henrique.getRandom().nextDouble() - 0.5D) * 4.0D;

        double y = this.henrique.getY();

        double z = this.henrique.getZ()
                + away.z * 8.0D
                + (this.henrique.getRandom().nextDouble() - 0.5D) * 4.0D;

        this.henrique.getNavigation().moveTo(x, y, z, this.speed);

        this.henrique.getLookControl().setLookAt(
                attacker.getX(),
                attacker.getEyeY(),
                attacker.getZ()
        );
    }
}