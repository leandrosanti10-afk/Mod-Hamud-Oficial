package hamud.mod.entity.goal;

import hamud.mod.entity.BorgesEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class BorgesPanicGoal extends Goal {

    private final BorgesEntity borges;
    private final double landSpeed;
    private final double waterSpeed;

    private int panicTime;

    public BorgesPanicGoal(BorgesEntity borges, double landSpeed, double waterSpeed) {
        this.borges = borges;
        this.landSpeed = landSpeed;
        this.waterSpeed = waterSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity attacker = this.borges.getLastHurtByMob();

        if (attacker == null) {
            return false;
        }

        if (!attacker.isAlive()) {
            return false;
        }

        return this.borges.tickCount - this.borges.getLastHurtByMobTimestamp() < 120;
    }

    @Override
    public boolean canContinueToUse() {
        return this.panicTime > 0 && this.borges.getLastHurtByMob() != null;
    }

    @Override
    public void start() {
        this.panicTime = 120;
    }

    @Override
    public void stop() {
        this.borges.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.panicTime--;

        LivingEntity attacker = this.borges.getLastHurtByMob();

        if (attacker == null) {
            return;
        }

        this.borges.getLookControl().setLookAt(
                attacker.getX(),
                attacker.getEyeY(),
                attacker.getZ()
        );

        if (this.borges.isInWater()) {
            this.swimAway(attacker);
        } else {
            this.runAway(attacker);
        }
    }

    private void runAway(LivingEntity attacker) {
        Vec3 away = this.borges.position().subtract(attacker.position()).normalize();

        double x = this.borges.getX() + away.x * 9.0D + (this.borges.getRandom().nextDouble() - 0.5D) * 4.0D;
        double y = this.borges.getY();
        double z = this.borges.getZ() + away.z * 9.0D + (this.borges.getRandom().nextDouble() - 0.5D) * 4.0D;

        this.borges.getNavigation().moveTo(x, y, z, this.landSpeed);
    }

    private void swimAway(LivingEntity attacker) {
        Vec3 away = this.borges.position().subtract(attacker.position()).normalize();

        /*
         * Varia altura para ele fugir mergulhando,
         * não ficar só na mesma camada.
         */
        double vertical = (this.borges.getRandom().nextDouble() - 0.35D) * 0.45D;

        Vec3 motion = new Vec3(
                away.x * this.waterSpeed,
                away.y * this.waterSpeed + vertical,
                away.z * this.waterSpeed
        );

        this.borges.setDeltaMovement(this.borges.getDeltaMovement().add(motion));

        float targetYaw = (float) (Math.atan2(away.z, away.x) * 180.0D / Math.PI) - 90.0F;
        this.borges.setYRot(this.rotlerp(this.borges.getYRot(), targetYaw, 16.0F));
        this.borges.yBodyRot = this.borges.getYRot();
        this.borges.yHeadRot = this.borges.getYRot();

        BlockPos pos = this.borges.blockPosition();

        /*
         * Se estiver quase saindo da água por cima,
         * força um pouco para baixo.
         */
        if (this.borges.level().getFluidState(pos.above()).isEmpty()) {
            this.borges.setDeltaMovement(
                    this.borges.getDeltaMovement().add(0.0D, -0.08D, 0.0D)
            );
        }
    }

    private float rotlerp(float currentAngle, float targetAngle, float maxIncrease) {
        float f = targetAngle - currentAngle;

        while (f < -180.0F) {
            f += 360.0F;
        }

        while (f >= 180.0F) {
            f -= 360.0F;
        }

        if (f > maxIncrease) {
            f = maxIncrease;
        }

        if (f < -maxIncrease) {
            f = -maxIncrease;
        }

        return currentAngle + f;
    }
}