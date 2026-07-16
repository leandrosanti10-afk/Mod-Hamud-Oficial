package hamud.mod.entity;

import hamud.mod.entity.goal.HamudVillagerZombiePanicGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import hamud.mod.entity.goal.HenriquePanicGoal;

public class HenriqueEntity extends AbstractVillager {

    private BlockPos henriqueSleepTarget;
    private BlockPos henriqueWorkTarget;

    private int henriqueSleepSearchCooldown = 0;
    private int henriqueWorkSearchCooldown = 0;

    public HenriqueEntity(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 24.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new HamudVillagerZombiePanicGoal(this, 1.35D, 8.0D));
        this.goalSelector.addGoal(1, new HenriquePanicGoal(this, 1.35D));
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.85D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        while (!offers.isEmpty()) {
            offers.remove(offers.size() - 1);
        }

        /*
         * Henrique ainda não tem trocas.
         * Quando formos adicionar as trocas do explorador,
         * elas entram aqui com MerchantOffer.
         */
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.level().isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResult.PASS;
        }

        if (player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        if (!this.isAlive() || this.isBaby()) {
            return InteractionResult.PASS;
        }

        if (this.isSleeping()) {
            this.stopSleeping();
        }

        this.henriqueSleepTarget = null;
        this.henriqueWorkTarget = null;

        this.getNavigation().stop();
        this.setDeltaMovement(0.0D, 0.0D, 0.0D);

        this.updateTrades();

        if (this.getOffers().isEmpty()) {
            player.displayClientMessage(Component.literal("Henrique ainda não possui trocas."), true);
            return InteractionResult.CONSUME;
        }

        player.awardStat(Stats.TALKED_TO_VILLAGER);

        this.setTradingPlayer(player);

        this.openTradingScreen(
                serverPlayer,
                Component.literal("Henrique Explorador"),
                1
        );

        return InteractionResult.CONSUME;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            return;
        }

        if (this.isSleeping()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0D, 0.0D, 0.0D);

            this.henriqueSleepTarget = null;
            this.henriqueWorkTarget = null;

            if (this.level().isDay()) {
                this.stopSleeping();
            }

            return;
        }

        if (this.isTrading()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0D, 0.0D, 0.0D);

            this.henriqueSleepTarget = null;
            this.henriqueWorkTarget = null;

            return;
        }

        if (!this.level().isDay()) {
            this.hamudmod$handleSleeping();
            return;
        }

        this.henriqueSleepTarget = null;
        this.hamudmod$handleWorkAffinity();
    }

    private void hamudmod$handleSleeping() {
        if (this.isSleeping() || this.isTrading()) {
            return;
        }

        if (this.henriqueSleepSearchCooldown > 0) {
            this.henriqueSleepSearchCooldown--;
        }

        if (this.henriqueSleepTarget == null && this.henriqueSleepSearchCooldown <= 0) {
            this.henriqueSleepTarget = this.hamudmod$findNearbyBed();
            this.henriqueSleepSearchCooldown = 100;
        }

        if (this.henriqueSleepTarget != null) {
            BlockState bedState = this.level().getBlockState(this.henriqueSleepTarget);

            if (!bedState.is(BlockTags.BEDS)) {
                this.henriqueSleepTarget = null;
                return;
            }

            double distance = this.distanceToSqr(
                    this.henriqueSleepTarget.getX() + 0.5D,
                    this.henriqueSleepTarget.getY(),
                    this.henriqueSleepTarget.getZ() + 0.5D
            );

            if (distance > 2.5D) {
                this.getNavigation().moveTo(
                        this.henriqueSleepTarget.getX() + 0.5D,
                        this.henriqueSleepTarget.getY(),
                        this.henriqueSleepTarget.getZ() + 0.5D,
                        0.6D
                );
            } else {
                this.getNavigation().stop();
                this.setDeltaMovement(0.0D, 0.0D, 0.0D);

                this.henriqueWorkTarget = null;
                this.startSleeping(this.henriqueSleepTarget);
            }
        }
    }

    private BlockPos hamudmod$findNearbyBed() {
        BlockPos origin = this.blockPosition();
        int radius = 16;

        BlockPos closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-radius, -3, -radius),
                origin.offset(radius, 3, radius)
        )) {
            BlockState state = this.level().getBlockState(pos);

            if (state.is(BlockTags.BEDS)) {
                if (state.hasProperty(BedBlock.OCCUPIED) && state.getValue(BedBlock.OCCUPIED)) {
                    continue;
                }

                double distance = pos.distSqr(origin);

                if (distance < closestDistance) {
                    closestDistance = distance;
                    closest = pos.immutable();
                }
            }
        }

        return closest;
    }

    private void hamudmod$handleWorkAffinity() {
        if (this.isTrading() || this.isSleeping()) {
            return;
        }

        if (this.henriqueWorkSearchCooldown > 0) {
            this.henriqueWorkSearchCooldown--;
        }

        if (this.henriqueWorkTarget == null && this.henriqueWorkSearchCooldown <= 0) {
            this.henriqueWorkTarget = this.hamudmod$findNearbyWorkBlock();
            this.henriqueWorkSearchCooldown = 120;
        }

        if (this.henriqueWorkTarget != null) {
            if (!this.level().getBlockState(this.henriqueWorkTarget).is(Blocks.CARTOGRAPHY_TABLE)) {
                this.henriqueWorkTarget = null;
                return;
            }

            double distance = this.distanceToSqr(
                    this.henriqueWorkTarget.getX() + 0.5D,
                    this.henriqueWorkTarget.getY(),
                    this.henriqueWorkTarget.getZ() + 0.5D
            );

            if (distance > 6.0D) {
                this.getNavigation().moveTo(
                        this.henriqueWorkTarget.getX() + 0.5D,
                        this.henriqueWorkTarget.getY(),
                        this.henriqueWorkTarget.getZ() + 0.5D,
                        0.45D
                );
            } else {
                this.getNavigation().stop();

                this.getLookControl().setLookAt(
                        this.henriqueWorkTarget.getX() + 0.5D,
                        this.henriqueWorkTarget.getY() + 0.8D,
                        this.henriqueWorkTarget.getZ() + 0.5D
                );
            }
        }
    }

    private BlockPos hamudmod$findNearbyWorkBlock() {
        BlockPos origin = this.blockPosition();
        int radius = 16;

        BlockPos closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-radius, -3, -radius),
                origin.offset(radius, 3, radius)
        )) {
            BlockState state = this.level().getBlockState(pos);

            if (state.is(Blocks.CARTOGRAPHY_TABLE)) {
                double distance = pos.distSqr(origin);

                if (distance < closestDistance) {
                    closestDistance = distance;
                    closest = pos.immutable();
                }
            }
        }

        return closest;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }
}