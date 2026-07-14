package hamud.mod.entity;

import hamud.mod.ModItems;
import hamud.mod.entity.goal.BorgesPanicGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
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
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import hamud.mod.entity.goal.HamudVillagerZombiePanicGoal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class BorgesEntity extends AbstractVillager {

    private int borgesTradeLevel = 1;
    private int borgesTradeXp = 0;

    public BorgesEntity(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FOLLOW_RANGE, 24.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new HamudVillagerZombiePanicGoal(this, 1.35D, 8.0D));
        /*
         * Prioridade 0:
         * Quando apanha, ele foge desesperado.
         * Na terra corre.
         * Na água nada desesperado.
         */
        this.goalSelector.addGoal(0, new BorgesPanicGoal(this, 1.35D, 0.075D));

        /*
         * Não usar FloatGoal.
         * FloatGoal faz o mob subir para respirar.
         * Borges respira embaixo d'água.
         */

        /*
         * Dentro da água:
         * nada, para, volta a nadar, sobe e desce.
         */
        this.goalSelector.addGoal(1, new BorgesSwimAroundGoal(this));

        /*
         * Fora da água:
         * anda normalmente.
         */
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.9D));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        int targetTradeCount = this.getTradeCountForLevel();

        while (offers.size() > targetTradeCount) {
            offers.remove(offers.size() - 1);
        }

        List<MerchantOffer> allTrades = this.createBorgesTrades();

        for (int i = offers.size(); i < targetTradeCount && i < allTrades.size(); i++) {
            offers.add(allTrades.get(i));
        }
    }

    private int getTradeCountForLevel() {
        return switch (this.borgesTradeLevel) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 6;
            case 4 -> 8;
            case 5 -> 10;
            default -> 2;
        };
    }

    private int getXpNeededForNextLevel() {
        return switch (this.borgesTradeLevel) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            case 4 -> 60;
            default -> Integer.MAX_VALUE;
        };
    }

    private List<MerchantOffer> createBorgesTrades() {
        List<MerchantOffer> trades = new ArrayList<>();

        // =========================
        // NÍVEL 1 — 2 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.COD, 10),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                2,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(Items.KELP, 32),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                2,
                0.05F
        ));

        // =========================
        // NÍVEL 2 — 4 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.INK_SAC, 8),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                new ItemStack(Items.TROPICAL_FISH_BUCKET, 1),
                8,
                5,
                0.05F
        ));

        // =========================
        // NÍVEL 3 — 6 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.PRISMARINE_SHARD, 4),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                10,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 4),
                new ItemStack(Items.SEA_LANTERN, 4),
                8,
                10,
                0.05F
        ));

        // =========================
        // NÍVEL 4 — 8 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(Items.NAUTILUS_SHELL, 1),
                new ItemStack(ModItems.MOEDA_HAMUD, 4),
                8,
                15,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 6),
                new ItemStack(Items.TURTLE_HELMET, 1),
                4,
                15,
                0.05F
        ));

        // =========================
        // NÍVEL 5 — 10 TROCAS
        // =========================

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 10),
                this.createShipwreckMap(),
                4,
                20,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 20),
                new ItemStack(ModItems.ARPAO, 1),
                3,
                30,
                0.05F
        ));

        return trades;
    }

    private ItemStack createShipwreckMap() {
        /*
         * Por enquanto é um mapa nomeado.
         * Depois dá para fazer ele apontar para uma estrutura específica,
         * mas isso exige buscar estrutura no ServerLevel.
         */
        ItemStack map = new ItemStack(Items.MAP, 1);
        map.setHoverName(Component.literal("Mapa de Naufrágio"));
        return map;
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

        this.getNavigation().stop();
        this.setDeltaMovement(0.0D, 0.0D, 0.0D);

        this.updateTrades();

        System.out.println(
                "Borges clicado. Nível: " + this.borgesTradeLevel +
                        " | XP atual: " + this.borgesTradeXp +
                        " | XP para próximo nível: " + this.getXpNeededForNextLevel() +
                        " | Trades: " + this.getOffers().size()
        );

        if (this.getOffers().isEmpty()) {
            player.displayClientMessage(Component.literal("Borges ainda não possui trocas."), true);
            return InteractionResult.CONSUME;
        }

        player.awardStat(Stats.TALKED_TO_VILLAGER);

        this.setTradingPlayer(player);

        this.openTradingScreen(
                serverPlayer,
                Component.literal("Borges"),
                this.borgesTradeLevel
        );

        return InteractionResult.CONSUME;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (this.borgesTradeLevel < 5) {
            int xpGanho = 5;

            this.borgesTradeXp += xpGanho;

            int xpNecessario = this.getXpNeededForNextLevel();

            if (this.borgesTradeXp >= xpNecessario) {
                this.borgesTradeLevel++;
                this.borgesTradeXp = 0;

                this.updateTrades();

                System.out.println("Borges subiu para o nível " + this.borgesTradeLevel);
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

        if (this.level().isClientSide && this.isInWater()) {
            this.spawnSnorkelBubbles();
        }

        if (!this.level().isClientSide && this.isTrading()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0D, 0.0D, 0.0D);
        }
    }

    private void spawnSnorkelBubbles() {
        if (this.tickCount % 2 != 0) {
            return;
        }

        RandomSource random = this.getRandom();

        float yawRad = this.getYRot() * ((float) Math.PI / 180F);

        double forwardX = -Math.sin(yawRad);
        double forwardZ = Math.cos(yawRad);

        double rightX = Math.cos(yawRad);
        double rightZ = Math.sin(yawRad);

        /*
         * Valores ajustáveis da bolha.
         */
        double headForwardOffset = 1.45D;
        double sideOffset = -0.42D;
        double heightOffset = 0.82D;

        double x = this.getX()
                + forwardX * headForwardOffset
                + rightX * sideOffset
                + (random.nextDouble() - 0.5D) * 0.04D;

        double y = this.getY()
                + heightOffset
                + (random.nextDouble() - 0.5D) * 0.04D;

        double z = this.getZ()
                + forwardZ * headForwardOffset
                + rightZ * sideOffset
                + (random.nextDouble() - 0.5D) * 0.04D;

        BlockPos bubblePos = BlockPos.containing(x, y, z);

        if (this.level().getFluidState(bubblePos).isEmpty()) {
            return;
        }

        double motionX = (random.nextDouble() - 0.5D) * 0.012D;
        double motionY = 0.040D + random.nextDouble() * 0.015D;
        double motionZ = (random.nextDouble() - 0.5D) * 0.012D;

        this.level().addParticle(
                ParticleTypes.BUBBLE,
                x,
                y,
                z,
                motionX,
                motionY,
                motionZ
        );

        if (random.nextFloat() < 0.35F) {
            this.level().addParticle(
                    ParticleTypes.BUBBLE,
                    x + (random.nextDouble() - 0.5D) * 0.05D,
                    y + (random.nextDouble() - 0.5D) * 0.05D,
                    z + (random.nextDouble() - 0.5D) * 0.05D,
                    motionX * 0.6D,
                    motionY * 0.8D,
                    motionZ * 0.6D
            );
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.move(MoverType.SELF, this.getDeltaMovement());

            /*
             * Atrito da água.
             */
            this.setDeltaMovement(this.getDeltaMovement().scale(0.86D));

            return;
        }

        super.travel(travelVector);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.borgesTradeLevel = tag.getInt("BorgesTradeLevel");
        this.borgesTradeXp = tag.getInt("BorgesTradeXp");

        if (this.borgesTradeLevel < 1) {
            this.borgesTradeLevel = 1;
        }

        if (this.borgesTradeLevel > 5) {
            this.borgesTradeLevel = 5;
        }

        if (this.borgesTradeXp < 0) {
            this.borgesTradeXp = 0;
        }

        this.updateTrades();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("BorgesTradeLevel", this.borgesTradeLevel);
        tag.putInt("BorgesTradeXp", this.borgesTradeXp);
    }

    static class BorgesSwimAroundGoal extends Goal {
        private final BorgesEntity borges;

        private double targetX;
        private double targetY;
        private double targetZ;

        private int swimTime;
        private int restTime;

        public BorgesSwimAroundGoal(BorgesEntity borges) {
            this.borges = borges;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return this.borges.isInWater();
        }

        @Override
        public boolean canContinueToUse() {
            return this.borges.isInWater();
        }

        @Override
        public void start() {
            this.restTime = 20 + this.borges.getRandom().nextInt(40);
            this.swimTime = 0;
            this.pickNewTarget();
        }

        @Override
        public void tick() {
            if (!this.borges.isInWater()) {
                return;
            }

            if (this.borges.getLastHurtByMob() != null
                    && this.borges.tickCount - this.borges.getLastHurtByMobTimestamp() < 120) {
                return;
            }

            if (this.restTime > 0) {
                this.restTime--;
                this.borges.setDeltaMovement(this.borges.getDeltaMovement().scale(0.65D));
                return;
            }

            this.swimTime++;

            double dx = this.targetX - this.borges.getX();
            double dy = this.targetY - this.borges.getY();
            double dz = this.targetZ - this.borges.getZ();

            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (distance < 0.8D || this.swimTime > 80) {
                this.restTime = 25 + this.borges.getRandom().nextInt(50);
                this.swimTime = 0;
                this.pickNewTarget();
                return;
            }

            double nx = dx / distance;
            double ny = dy / distance;
            double nz = dz / distance;

            float targetYaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
            this.borges.setYRot(this.rotlerp(this.borges.getYRot(), targetYaw, 8.0F));
            this.borges.yBodyRot = this.borges.getYRot();
            this.borges.yHeadRot = this.borges.getYRot();

            float targetPitch = (float) -(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * 180.0D / Math.PI);
            this.borges.setXRot(this.rotlerp(this.borges.getXRot(), targetPitch, 5.0F));

            double speed = 0.026D;

            this.borges.setDeltaMovement(
                    this.borges.getDeltaMovement().add(
                            nx * speed,
                            ny * speed,
                            nz * speed
                    )
            );
        }

        private void pickNewTarget() {
            RandomSource random = this.borges.getRandom();

            for (int i = 0; i < 12; i++) {
                double x = this.borges.getX() + (random.nextDouble() * 10.0D - 5.0D);
                double y = this.borges.getY() + (random.nextDouble() * 4.0D - 2.0D);
                double z = this.borges.getZ() + (random.nextDouble() * 10.0D - 5.0D);

                BlockPos pos = BlockPos.containing(x, y, z);

                if (!this.borges.level().getFluidState(pos).isEmpty()) {
                    this.targetX = x;
                    this.targetY = y;
                    this.targetZ = z;
                    return;
                }
            }

            this.targetX = this.borges.getX();
            this.targetY = this.borges.getY();
            this.targetZ = this.borges.getZ();
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
}