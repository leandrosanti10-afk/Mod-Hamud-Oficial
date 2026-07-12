package hamud.mod.entity;

import hamud.mod.ModBlocks;
import hamud.mod.ModEntities;
import hamud.mod.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChabanEntity extends AbstractVillager {

    private int chabanTradeLevel = 1;
    private int chabanTradeXp = 0;

    private UUID guardOneUuid;
    private UUID guardTwoUuid;

    private int guardCheckCooldown = 0;

    public ChabanEntity(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.34D)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.45D);
    }

    @Override
    protected void registerGoals() {
        /*
         * Quando tiver alvo, ele corre atrás e bate com o porrete.
         */
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.25D, true));

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        /*
         * Ao apanhar, ele define quem bateu nele como alvo.
         */
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        int targetTradeCount = getTradeCountForLevel();

        while (offers.size() > targetTradeCount) {
            offers.remove(offers.size() - 1);
        }

        List<MerchantOffer> allTrades = createChabanTrades();

        for (int i = offers.size(); i < targetTradeCount && i < allTrades.size(); i++) {
            offers.add(allTrades.get(i));
        }
    }

    private int getTradeCountForLevel() {
        return switch (this.chabanTradeLevel) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 6;
            case 4 -> 8;
            case 5 -> 10;
            default -> 2;
        };
    }

    private int getXpNeededForNextLevel() {
        return switch (this.chabanTradeLevel) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            case 4 -> 60;
            default -> Integer.MAX_VALUE;
        };
    }

    private List<MerchantOffer> createChabanTrades() {
        List<MerchantOffer> trades = new ArrayList<>();

        trades.add(new MerchantOffer(
                new ItemStack(Items.BONE, 8),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                2,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                new ItemStack(Items.TORCH, 8),
                16,
                1,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(Items.LEATHER, 8),
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                16,
                5,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                new ItemStack(Items.COOKED_BEEF, 4),
                16,
                5,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(Items.STONE, 16),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                10,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.ARROW, 16),
                16,
                10,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(Items.COAL, 8),
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                16,
                15,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 5),
                new ItemStack(Items.BOW, 1),
                8,
                15,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 8),
                new ItemStack(Items.GOAT_HORN, 1),
                6,
                20,
                0.05F
        ));

        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 40),
                new ItemStack(ModBlocks.VELOCIRAPTOR_EGG.asItem(), 1),
                3,
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

        /*
         * Se ele estiver agressivo, não abre troca.
         */
        if (this.getTarget() != null && this.getTarget().isAlive()) {
            player.displayClientMessage(Component.literal("Chaban está agressivo!"), true);
            return InteractionResult.CONSUME;
        }

        this.getNavigation().stop();
        this.setDeltaMovement(0.0D, 0.0D, 0.0D);

        this.updateTrades();

        System.out.println(
                "Chaban clicado. Nível: " + this.chabanTradeLevel +
                        " | XP atual: " + this.chabanTradeXp +
                        " | XP para próximo nível: " + getXpNeededForNextLevel() +
                        " | Trades: " + this.getOffers().size()
        );

        if (this.getOffers().isEmpty()) {
            player.displayClientMessage(Component.literal("Chaban ainda não possui trocas."), true);
            return InteractionResult.CONSUME;
        }

        player.awardStat(Stats.TALKED_TO_VILLAGER);

        this.setTradingPlayer(player);

        this.openTradingScreen(
                serverPlayer,
                Component.literal("Chaban"),
                this.chabanTradeLevel
        );

        return InteractionResult.CONSUME;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (this.chabanTradeLevel < 5) {
            int xpGanho = 5;

            this.chabanTradeXp += xpGanho;

            int xpNecessario = getXpNeededForNextLevel();

            if (this.chabanTradeXp >= xpNecessario) {
                this.chabanTradeLevel++;
                this.chabanTradeXp = 0;

                this.updateTrades();

                System.out.println("Chaban subiu para o nível " + this.chabanTradeLevel);
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
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);

        if (!this.level().isClientSide && result) {
            Entity attacker = source.getEntity();

            if (!(attacker instanceof LivingEntity)) {
                attacker = source.getDirectEntity();
            }

            if (attacker instanceof LivingEntity livingAttacker && livingAttacker != this) {
                System.out.println("Chaban foi atacado por: " + livingAttacker.getName().getString());

                /*
                 * Chaban fica agressivo e ataca quem bateu nele.
                 */
                this.setTarget(livingAttacker);
                this.setAggressive(true);
                this.getNavigation().stop();
                this.getNavigation().moveTo(livingAttacker, 1.25D);

                /*
                 * Os velociraptors também atacam.
                 */
                this.commandVelociraptorsToAttack(livingAttacker);
            }
        }

        return result;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        /*
         * Isso faz ele balançar o braço do porrete ao atacar.
         */
        this.swing(InteractionHand.MAIN_HAND);

        return super.doHurtTarget(target);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            return;
        }

        LivingEntity target = this.getTarget();

        if (target != null && target.isAlive()) {
            this.setAggressive(true);

            if (this.distanceToSqr(target) > 4.0D) {
                this.getNavigation().moveTo(target, 1.25D);
            }

            this.getLookControl().setLookAt(
                    target.getX(),
                    target.getEyeY(),
                    target.getZ()
            );
        } else {
            this.setAggressive(false);
        }

        if (this.isTrading()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0D, 0.0D, 0.0D);
        }

        this.handleVelociraptorGuards();
    }

    private void handleVelociraptorGuards() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (this.guardCheckCooldown > 0) {
            this.guardCheckCooldown--;
            return;
        }

        this.guardCheckCooldown = 40;

        VelociraptorEntity guardOne = this.getGuard(serverLevel, this.guardOneUuid);
        VelociraptorEntity guardTwo = this.getGuard(serverLevel, this.guardTwoUuid);

        if (guardOne == null || !guardOne.isAlive()) {
            guardOne = this.spawnGuard(serverLevel, -1.5D, 0.0D);
            if (guardOne != null) {
                this.guardOneUuid = guardOne.getUUID();
            }
        }

        if (guardTwo == null || !guardTwo.isAlive()) {
            guardTwo = this.spawnGuard(serverLevel, 1.5D, 0.0D);
            if (guardTwo != null) {
                this.guardTwoUuid = guardTwo.getUUID();
            }
        }

        this.keepGuardLeashedAndClose(guardOne, -1.5D, 0.0D);
        this.keepGuardLeashedAndClose(guardTwo, 1.5D, 0.0D);
    }

    @Nullable
    private VelociraptorEntity getGuard(ServerLevel serverLevel, @Nullable UUID uuid) {
        if (uuid == null) {
            return null;
        }

        Entity entity = serverLevel.getEntity(uuid);

        if (entity instanceof VelociraptorEntity velociraptor) {
            return velociraptor;
        }

        return null;
    }

    @Nullable
    private VelociraptorEntity spawnGuard(ServerLevel serverLevel, double offsetX, double offsetZ) {
        VelociraptorEntity guard = ModEntities.VELOCIRAPTOR.create(serverLevel);

        if (guard == null) {
            return null;
        }

        guard.moveTo(
                this.getX() + offsetX,
                this.getY(),
                this.getZ() + offsetZ,
                this.getYRot(),
                0.0F
        );

        guard.setPersistenceRequired();
        guard.setBaby(false);

        serverLevel.addFreshEntity(guard);

        guard.setLeashedTo(this, true);

        return guard;
    }

    private void keepGuardLeashedAndClose(@Nullable VelociraptorEntity guard, double offsetX, double offsetZ) {
        if (guard == null || !guard.isAlive()) {
            return;
        }

        if (!guard.isLeashed() || guard.getLeashHolder() != this) {
            guard.setLeashedTo(this, true);
        }

        LivingEntity target = guard.getTarget();

        if (target != null && target.isAlive()) {
            guard.setAggressive(true);

            double distanceToTarget = guard.distanceToSqr(target);
            double distanceToChaban = guard.distanceToSqr(this);

            if (this.distanceToSqr(target) > 36.0D) {
                this.getNavigation().moveTo(target, 0.85D);
            }

            if (distanceToTarget > 2.5D) {
                guard.getNavigation().moveTo(target, 1.35D);
            }

            if (distanceToChaban > 64.0D) {
                guard.getNavigation().moveTo(this, 1.25D);
            }

            return;
        }

        guard.setAggressive(false);

        double distance = guard.distanceToSqr(this);

        if (distance > 100.0D) {
            guard.teleportTo(
                    this.getX() + offsetX,
                    this.getY(),
                    this.getZ() + offsetZ
            );
            guard.getNavigation().stop();

            if (!guard.isLeashed() || guard.getLeashHolder() != this) {
                guard.setLeashedTo(this, true);
            }

            return;
        }

        if (distance > 9.0D) {
            guard.getNavigation().moveTo(this, 1.1D);
        }
    }

    private void commandVelociraptorsToAttack(LivingEntity target) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        VelociraptorEntity guardOne = this.getGuard(serverLevel, this.guardOneUuid);
        VelociraptorEntity guardTwo = this.getGuard(serverLevel, this.guardTwoUuid);

        this.commandGuardToAttack(guardOne, target);
        this.commandGuardToAttack(guardTwo, target);

        if (this.distanceToSqr(target) > 16.0D) {
            this.getNavigation().moveTo(target, 0.85D);
        }
    }

    private void commandGuardToAttack(@Nullable VelociraptorEntity guard, LivingEntity target) {
        if (guard == null || !guard.isAlive()) {
            return;
        }

        if (target == this || target == guard) {
            return;
        }

        if (!guard.isLeashed() || guard.getLeashHolder() != this) {
            guard.setLeashedTo(this, true);
        }

        guard.setOrderedToSit(false);
        guard.setTarget(target);
        guard.setAggressive(true);
        guard.setLastHurtByMob(target);

        guard.getNavigation().stop();
        guard.getNavigation().moveTo(target, 1.35D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.chabanTradeLevel = tag.getInt("ChabanTradeLevel");
        this.chabanTradeXp = tag.getInt("ChabanTradeXp");

        if (tag.hasUUID("ChabanGuardOne")) {
            this.guardOneUuid = tag.getUUID("ChabanGuardOne");
        }

        if (tag.hasUUID("ChabanGuardTwo")) {
            this.guardTwoUuid = tag.getUUID("ChabanGuardTwo");
        }

        if (this.chabanTradeLevel < 1) {
            this.chabanTradeLevel = 1;
        }

        if (this.chabanTradeLevel > 5) {
            this.chabanTradeLevel = 5;
        }

        if (this.chabanTradeXp < 0) {
            this.chabanTradeXp = 0;
        }

        this.updateTrades();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("ChabanTradeLevel", this.chabanTradeLevel);
        tag.putInt("ChabanTradeXp", this.chabanTradeXp);

        if (this.guardOneUuid != null) {
            tag.putUUID("ChabanGuardOne", this.guardOneUuid);
        }

        if (this.guardTwoUuid != null) {
            tag.putUUID("ChabanGuardTwo", this.guardTwoUuid);
        }
    }
}