package hamud.mod.entity.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class HamudVillagerZombiePanicGoal extends Goal {

    private final AbstractVillager villager;
    private final double speed;
    private final double detectRange;

    private Zombie nearestZombie;
    private int panicTime;

    public HamudVillagerZombiePanicGoal(AbstractVillager villager, double speed, double detectRange) {
        this.villager = villager;
        this.speed = speed;
        this.detectRange = detectRange;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.villager.isAlive()) {
            return false;
        }

        if (this.villager.isTrading()) {
            return false;
        }

        List<Zombie> zombies = this.villager.level().getEntitiesOfClass(
                Zombie.class,
                this.villager.getBoundingBox().inflate(this.detectRange),
                zombie -> zombie.isAlive() && !zombie.isBaby()
        );

        if (zombies.isEmpty()) {
            return false;
        }

        this.nearestZombie = zombies.stream()
                .min(Comparator.comparingDouble(zombie -> zombie.distanceToSqr(this.villager)))
                .orElse(null);

        return this.nearestZombie != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.panicTime <= 0) {
            return false;
        }

        if (this.nearestZombie == null || !this.nearestZombie.isAlive()) {
            return false;
        }

        return this.villager.distanceToSqr(this.nearestZombie) <= this.detectRange * this.detectRange * 1.8D;
    }

    @Override
    public void start() {
        this.panicTime = 120;

        if (this.villager.isSleeping()) {
            this.villager.stopSleeping();
        }

        this.villager.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.nearestZombie = null;
        this.panicTime = 0;
        this.villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.panicTime--;

        if (this.nearestZombie == null) {
            return;
        }

        if (!this.nearestZombie.isAlive()) {
            return;
        }

        this.villager.getLookControl().setLookAt(
                this.nearestZombie.getX(),
                this.nearestZombie.getEyeY(),
                this.nearestZombie.getZ()
        );

        if (this.villager.tickCount % 10 != 0 && !this.villager.getNavigation().isDone()) {
            return;
        }

        Vec3 away = this.villager.position().subtract(this.nearestZombie.position());

        if (away.lengthSqr() < 0.001D) {
            away = new Vec3(
                    this.villager.getRandom().nextDouble() - 0.5D,
                    0.0D,
                    this.villager.getRandom().nextDouble() - 0.5D
            );
        }

        away = away.normalize();

        double x = this.villager.getX()
                + away.x * 10.0D
                + (this.villager.getRandom().nextDouble() - 0.5D) * 4.0D;

        double y = this.villager.getY();

        double z = this.villager.getZ()
                + away.z * 10.0D
                + (this.villager.getRandom().nextDouble() - 0.5D) * 4.0D;

        this.villager.getNavigation().moveTo(x, y, z, this.speed);
    }
}