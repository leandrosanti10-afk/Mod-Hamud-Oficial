package hamud.mod.entity;

import hamud.mod.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
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
import hamud.mod.entity.goal.RemyPanicGoal;
import hamud.mod.entity.goal.HamudVillagerZombiePanicGoal;

import java.util.ArrayList;
import java.util.List;

public class RemyEntity extends AbstractVillager {

    private int remyTradeLevel = 1;
    private int remyTradeXp = 0;

    private BlockPos remySleepTarget;
    private BlockPos remyComposterTarget;

    private int remySleepSearchCooldown = 0;
    private int remyComposterSearchCooldown = 0;

    public RemyEntity(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new HamudVillagerZombiePanicGoal(this, 1.35D, 8.0D));
        this.goalSelector.addGoal(0, new RemyPanicGoal(this, 1.35D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        int targetTradeCount = getTradeCountForLevel();

        while (offers.size() > targetTradeCount) {
            offers.remove(offers.size() - 1);
        }

        List<MerchantOffer> allTrades = createRemyTrades();

        for (int i = offers.size(); i < targetTradeCount && i < allTrades.size(); i++) {
            offers.add(allTrades.get(i));
        }
    }

    private int getTradeCountForLevel() {
        return switch (this.remyTradeLevel) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 6;
            case 4 -> 8;
            case 5 -> 10;
            default -> 2;
        };
    }

    private int getXpNeededForNextLevel() {
        return switch (this.remyTradeLevel) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            case 4 -> 60;
            default -> Integer.MAX_VALUE;
        };
    }

    private List<MerchantOffer> createRemyTrades() {
        List<MerchantOffer> trades = new ArrayList<>();

        // NÍVEL 1 — 2 TROCAS

        trades.add(new MerchantOffer(
                new ItemStack(Items.WHEAT, 20),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                2,
                0.05f
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                new ItemStack(Items.BREAD, 6),
                16,
                1,
                0.05f
        ));

        // NÍVEL 2 — 4 TROCAS

        trades.add(new MerchantOffer(
                new ItemStack(Items.CARROT, 22),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05f
        ));

        trades.add(new MerchantOffer(
                new ItemStack(Items.POTATO, 26),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05f
        ));

        // NÍVEL 3 — 6 TROCAS

        trades.add(new MerchantOffer(
                new ItemStack(Items.BEETROOT, 15),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                10,
                0.05f
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.PUMPKIN_PIE, 4),
                12,
                10,
                0.05f
        ));

        // NÍVEL 4 — 8 TROCAS

        trades.add(new MerchantOffer(
                new ItemStack(Items.PUMPKIN, 10),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12,
                15,
                0.05f
        ));

        trades.add(new MerchantOffer(
                new ItemStack(Items.MELON, 4),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12,
                15,
                0.05f
        ));

        // NÍVEL 5 — 10 TROCAS

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.COOKIE, 18),
                12,
                20,
                0.05f
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 6),
                new ItemStack(Items.GOLDEN_CARROT, 3),
                8,
                30,
                0.05f
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

        this.remySleepTarget = null;
        this.remyComposterTarget = null;
        this.getNavigation().stop();
        this.setDeltaMovement(0.0, 0.0, 0.0);

        this.updateTrades();

        System.out.println(
                "Remy clicado. Nível: " + this.remyTradeLevel +
                        " | XP atual: " + this.remyTradeXp +
                        " | XP para próximo nível: " + getXpNeededForNextLevel() +
                        " | Trades: " + this.getOffers().size()
        );

        if (this.getOffers().isEmpty()) {
            return InteractionResult.PASS;
        }

        player.awardStat(Stats.TALKED_TO_VILLAGER);

        this.setTradingPlayer(player);

        this.openTradingScreen(
                serverPlayer,
                Component.literal("Remy Fazendeiro"),
                this.remyTradeLevel
        );

        return InteractionResult.CONSUME;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (this.remyTradeLevel < 5) {
            int xpGanho = 1;

            this.remyTradeXp += xpGanho;

            int xpNecessario = getXpNeededForNextLevel();

            if (this.remyTradeXp >= xpNecessario) {
                this.remyTradeLevel++;
                this.remyTradeXp = 0;

                this.updateTrades();

                System.out.println("Remy subiu para o nível " + this.remyTradeLevel);
            }
        }

        if (offer.shouldRewardExp()) {
            int xp = 3 + this.random.nextInt(4);

            this.level().addFreshEntity(
                    new ExperienceOrb(
                            this.level(),
                            this.getX(),
                            this.getY() + 0.5,
                            this.getZ(),
                            xp
                    )
            );
        }
    }

    @Override
    public void tick() {
        super.tick();

        // Client: só partículas
        if (this.level().isClientSide) {
            this.hamudmod$spawnCigaretteSmoke();
            return;
        }

        // Se está dormindo, trava completamente
        if (this.isSleeping()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0, 0.0, 0.0);
            this.remySleepTarget = null;
            this.remyComposterTarget = null;

            if (this.level().isDay()) {
                this.stopSleeping();
            }

            return;
        }

        // Se está negociando, não anda, não dorme, não trabalha
        if (this.isTrading()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0, 0.0, 0.0);
            this.remySleepTarget = null;
            this.remyComposterTarget = null;
            return;
        }

        // Noite: procurar cama e dormir
        if (!this.level().isDay()) {
            this.hamudmod$handleSleeping();
            return;
        }

        // Dia: procurar composteira
        this.remySleepTarget = null;
        this.hamudmod$handleComposterAffinity();
    }

    private void hamudmod$handleSleeping() {
        if (this.isSleeping()) {
            return;
        }

        if (this.isTrading()) {
            return;
        }

        if (this.remySleepSearchCooldown > 0) {
            this.remySleepSearchCooldown--;
        }

        if (this.remySleepTarget == null && this.remySleepSearchCooldown <= 0) {
            this.remySleepTarget = this.hamudmod$findNearbyBed();
            this.remySleepSearchCooldown = 100;
        }

        if (this.remySleepTarget != null) {
            BlockState bedState = this.level().getBlockState(this.remySleepTarget);

            if (!bedState.is(BlockTags.BEDS)) {
                this.remySleepTarget = null;
                return;
            }

            double distance = this.distanceToSqr(
                    this.remySleepTarget.getX() + 0.5,
                    this.remySleepTarget.getY(),
                    this.remySleepTarget.getZ() + 0.5
            );

            if (distance > 2.5) {
                this.getNavigation().moveTo(
                        this.remySleepTarget.getX() + 0.5,
                        this.remySleepTarget.getY(),
                        this.remySleepTarget.getZ() + 0.5,
                        0.6
                );
            } else {
                this.getNavigation().stop();
                this.setDeltaMovement(0.0, 0.0, 0.0);
                this.remyComposterTarget = null;
                this.startSleeping(this.remySleepTarget);
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

    private void hamudmod$handleComposterAffinity() {
        if (this.isTrading() || this.isSleeping()) {
            return;
        }

        if (this.remyComposterSearchCooldown > 0) {
            this.remyComposterSearchCooldown--;
        }

        if (this.remyComposterTarget == null && this.remyComposterSearchCooldown <= 0) {
            this.remyComposterTarget = this.hamudmod$findNearbyComposter();
            this.remyComposterSearchCooldown = 120;
        }

        if (this.remyComposterTarget != null) {
            if (!this.level().getBlockState(this.remyComposterTarget).is(Blocks.COMPOSTER)) {
                this.remyComposterTarget = null;
                return;
            }

            double distance = this.distanceToSqr(
                    this.remyComposterTarget.getX() + 0.5,
                    this.remyComposterTarget.getY(),
                    this.remyComposterTarget.getZ() + 0.5
            );

            if (distance > 6.0) {
                this.getNavigation().moveTo(
                        this.remyComposterTarget.getX() + 0.5,
                        this.remyComposterTarget.getY(),
                        this.remyComposterTarget.getZ() + 0.5,
                        0.45
                );
            } else {
                this.getNavigation().stop();

                double lookX = this.remyComposterTarget.getX() + 0.5;
                double lookY = this.remyComposterTarget.getY() + 0.8;
                double lookZ = this.remyComposterTarget.getZ() + 0.5;

                this.getLookControl().setLookAt(lookX, lookY, lookZ);
            }
        }
    }

    private BlockPos hamudmod$findNearbyComposter() {
        BlockPos origin = this.blockPosition();
        int radius = 16;

        BlockPos closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-radius, -3, -radius),
                origin.offset(radius, 3, radius)
        )) {
            BlockState state = this.level().getBlockState(pos);

            if (state.is(Blocks.COMPOSTER)) {
                double distance = pos.distSqr(origin);

                if (distance < closestDistance) {
                    closestDistance = distance;
                    closest = pos.immutable();
                }
            }
        }

        return closest;
    }

    private void hamudmod$spawnCigaretteSmoke() {
        if (this.isSleeping()) {
            return;
        }

        if (this.random.nextFloat() > 0.22f) {
            return;
        }

        float yawRad = this.yHeadRot * ((float) Math.PI / 180F);

        double forwardX = -Mth.sin(yawRad);
        double forwardZ = Mth.cos(yawRad);

        double rightX = Mth.cos(yawRad);
        double rightZ = Mth.sin(yawRad);

        double forward = 0.72;
        double side = -0.12;
        double height = 1.63;

        double x = this.getX() + forwardX * forward + rightX * side;
        double y = this.getY() + height;
        double z = this.getZ() + forwardZ * forward + rightZ * side;

        double velocityX = (this.random.nextDouble() - 0.5) * 0.015;
        double velocityY = 0.035 + this.random.nextDouble() * 0.025;
        double velocityZ = (this.random.nextDouble() - 0.5) * 0.015;

        this.level().addParticle(
                ParticleTypes.SMOKE,
                x,
                y,
                z,
                velocityX,
                velocityY,
                velocityZ
        );
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.remyTradeLevel = tag.getInt("RemyTradeLevel");
        this.remyTradeXp = tag.getInt("RemyTradeXp");

        if (this.remyTradeLevel < 1) {
            this.remyTradeLevel = 1;
        }

        if (this.remyTradeLevel > 5) {
            this.remyTradeLevel = 5;
        }

        if (this.remyTradeXp < 0) {
            this.remyTradeXp = 0;
        }

        this.updateTrades();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("RemyTradeLevel", this.remyTradeLevel);
        tag.putInt("RemyTradeXp", this.remyTradeXp);
    }
}