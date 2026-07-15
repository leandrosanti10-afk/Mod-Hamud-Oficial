package hamud.mod.entity;

import hamud.mod.ModItems;
import hamud.mod.entity.goal.BranoPanicGoal;
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
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import hamud.mod.entity.goal.HamudVillagerZombiePanicGoal;
import java.util.ArrayList;
import java.util.List;

public class BranoEntity extends AbstractVillager {

    private int branoTradeLevel = 1;
    private int branoTradeXp = 0;

    private BlockPos branoSleepTarget;
    private BlockPos branoWorkTarget;

    private int branoSleepSearchCooldown = 0;
    private int branoWorkSearchCooldown = 0;

    public BranoEntity(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 24.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new HamudVillagerZombiePanicGoal(this, 1.35D, 8.0D));
        this.goalSelector.addGoal(0, new BranoPanicGoal(this, 1.35D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        int targetTradeCount = this.getTradeCountForLevel();

        while (offers.size() > targetTradeCount) {
            offers.remove(offers.size() - 1);
        }

        List<MerchantOffer> allTrades = this.createBranoTrades();

        for (int i = offers.size(); i < targetTradeCount && i < allTrades.size(); i++) {
            offers.add(allTrades.get(i));
        }
    }

    private int getTradeCountForLevel() {
        return switch (this.branoTradeLevel) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 6;
            case 4 -> 8;
            case 5 -> 10;
            default -> 2;
        };
    }

    private int getXpNeededForNextLevel() {
        return switch (this.branoTradeLevel) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            case 4 -> 60;
            default -> Integer.MAX_VALUE;
        };
    }

    private List<MerchantOffer> createBranoTrades() {
        List<MerchantOffer> trades = new ArrayList<>();

        // =========================
        // NÍVEL 1 — 2 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.RAW_COPPER, 8),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                2,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                new ItemStack(Items.REDSTONE, 4),
                16,
                2,
                0.05F
        ));

        // =========================
        // NÍVEL 2 — 4 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.IRON_INGOT, 4),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                new ItemStack(Items.REDSTONE_TORCH, 2),
                16,
                5,
                0.05F
        ));

        // =========================
        // NÍVEL 3 — 6 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.COPPER_INGOT, 8),
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                16,
                10,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.LIGHTNING_ROD, 1),
                12,
                10,
                0.05F
        ));

        // =========================
        // NÍVEL 4 — 8 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.REDSTONE, 8),
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                16,
                15,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 4),
                new ItemStack(Items.REPEATER, 1),
                12,
                15,
                0.05F
        ));

        // =========================
        // NÍVEL 5 — 10 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.COPPER_BLOCK, 2),
                new ItemStack(ModItems.MOEDA_HAMUD, 5),
                8,
                20,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 8),
                new ItemStack(Items.OBSERVER, 1),
                8,
                30,
                0.05F
        ));

        return trades;
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

        this.branoSleepTarget = null;
        this.branoWorkTarget = null;

        this.getNavigation().stop();
        this.setDeltaMovement(0.0D, 0.0D, 0.0D);

        this.updateTrades();

        System.out.println(
                "Brano clicado. Nível: " + this.branoTradeLevel +
                        " | XP atual: " + this.branoTradeXp +
                        " | XP para próximo nível: " + this.getXpNeededForNextLevel() +
                        " | Trades: " + this.getOffers().size()
        );

        if (this.getOffers().isEmpty()) {
            player.displayClientMessage(Component.literal("Brano ainda não possui trocas."), true);
            return InteractionResult.CONSUME;
        }

        player.awardStat(Stats.TALKED_TO_VILLAGER);

        this.setTradingPlayer(player);

        this.openTradingScreen(
                serverPlayer,
                Component.literal("Brano Cientista Elétrico"),
                this.branoTradeLevel
        );

        return InteractionResult.CONSUME;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (this.branoTradeLevel < 5) {
            int xpGanho = 5;

            this.branoTradeXp += xpGanho;

            int xpNecessario = this.getXpNeededForNextLevel();

            if (this.branoTradeXp >= xpNecessario) {
                this.branoTradeLevel++;
                this.branoTradeXp = 0;

                this.updateTrades();

                System.out.println("Brano subiu para o nível " + this.branoTradeLevel);
            }
        }

        if (offer.shouldRewardExp()) {
            int xp = 3 + this.random.nextInt(4);

            this.level().addFreshEntity(
                    new ExperienceOrb(
                            this.level(),
                            this.getX(),
                            this.getY() + 0.5D,
                            this.getZ(),
                            xp
                    )
            );
        }
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

            this.branoSleepTarget = null;
            this.branoWorkTarget = null;

            if (this.level().isDay()) {
                this.stopSleeping();
            }

            return;
        }

        if (this.isTrading()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0D, 0.0D, 0.0D);

            this.branoSleepTarget = null;
            this.branoWorkTarget = null;

            return;
        }

        if (!this.level().isDay()) {
            this.hamudmod$handleSleeping();
            return;
        }

        this.branoSleepTarget = null;
        this.hamudmod$handleWorkAffinity();
    }

    private void hamudmod$handleSleeping() {
        if (this.isSleeping()) {
            return;
        }

        if (this.isTrading()) {
            return;
        }

        if (this.branoSleepSearchCooldown > 0) {
            this.branoSleepSearchCooldown--;
        }

        if (this.branoSleepTarget == null && this.branoSleepSearchCooldown <= 0) {
            this.branoSleepTarget = this.hamudmod$findNearbyBed();
            this.branoSleepSearchCooldown = 100;
        }

        if (this.branoSleepTarget != null) {
            BlockState bedState = this.level().getBlockState(this.branoSleepTarget);

            if (!bedState.is(BlockTags.BEDS)) {
                this.branoSleepTarget = null;
                return;
            }

            double distance = this.distanceToSqr(
                    this.branoSleepTarget.getX() + 0.5D,
                    this.branoSleepTarget.getY(),
                    this.branoSleepTarget.getZ() + 0.5D
            );

            if (distance > 2.5D) {
                this.getNavigation().moveTo(
                        this.branoSleepTarget.getX() + 0.5D,
                        this.branoSleepTarget.getY(),
                        this.branoSleepTarget.getZ() + 0.5D,
                        0.6D
                );
            } else {
                this.getNavigation().stop();
                this.setDeltaMovement(0.0D, 0.0D, 0.0D);

                this.branoWorkTarget = null;

                this.startSleeping(this.branoSleepTarget);
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

        if (this.branoWorkSearchCooldown > 0) {
            this.branoWorkSearchCooldown--;
        }

        if (this.branoWorkTarget == null && this.branoWorkSearchCooldown <= 0) {
            this.branoWorkTarget = this.hamudmod$findNearbyWorkBlock();
            this.branoWorkSearchCooldown = 120;
        }

        if (this.branoWorkTarget != null) {
            if (!this.level().getBlockState(this.branoWorkTarget).is(Blocks.LIGHTNING_ROD)) {
                this.branoWorkTarget = null;
                return;
            }

            double distance = this.distanceToSqr(
                    this.branoWorkTarget.getX() + 0.5D,
                    this.branoWorkTarget.getY(),
                    this.branoWorkTarget.getZ() + 0.5D
            );

            if (distance > 6.0D) {
                this.getNavigation().moveTo(
                        this.branoWorkTarget.getX() + 0.5D,
                        this.branoWorkTarget.getY(),
                        this.branoWorkTarget.getZ() + 0.5D,
                        0.45D
                );
            } else {
                this.getNavigation().stop();

                double lookX = this.branoWorkTarget.getX() + 0.5D;
                double lookY = this.branoWorkTarget.getY() + 0.8D;
                double lookZ = this.branoWorkTarget.getZ() + 0.5D;

                this.getLookControl().setLookAt(lookX, lookY, lookZ);
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

            if (state.is(Blocks.LIGHTNING_ROD)) {
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

        this.branoTradeLevel = tag.getInt("BranoTradeLevel");
        this.branoTradeXp = tag.getInt("BranoTradeXp");

        if (this.branoTradeLevel < 1) {
            this.branoTradeLevel = 1;
        }

        if (this.branoTradeLevel > 5) {
            this.branoTradeLevel = 5;
        }

        if (this.branoTradeXp < 0) {
            this.branoTradeXp = 0;
        }

        this.updateTrades();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("BranoTradeLevel", this.branoTradeLevel);
        tag.putInt("BranoTradeXp", this.branoTradeXp);
    }
}