package hamud.mod.entity;

import hamud.mod.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RemyEntity extends AbstractVillager {

    private int remyTradeLevel = 1;
    private int remyTradeXp = 0;

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
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        int targetTradeCount = getTradeCountForLevel();

        // Se por algum motivo ele tiver trades demais, remove as extras.
        // Isso corrige Remys antigos que já nasceram com 10 trocas.
        while (offers.size() > targetTradeCount) {
            offers.remove(offers.size() - 1);
        }

        List<MerchantOffer> allTrades = createRemyTrades();

        // Adiciona apenas as trocas permitidas pelo nível atual
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

    private List<MerchantOffer> createRemyTrades() {
        List<MerchantOffer> trades = new ArrayList<>();

        // 1 — trigo -> moeda
        trades.add(new MerchantOffer(
                new ItemStack(Items.WHEAT, 20),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                2,
                0.05f
        ));

        // 2 — moeda -> pão
        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                new ItemStack(Items.BREAD, 6),
                16,
                1,
                0.05f
        ));

        // 3 — cenoura -> moeda
        trades.add(new MerchantOffer(
                new ItemStack(Items.CARROT, 22),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05f
        ));

        // 4 — batata -> moeda
        trades.add(new MerchantOffer(
                new ItemStack(Items.POTATO, 26),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05f
        ));

        // 5 — beterraba -> moeda
        trades.add(new MerchantOffer(
                new ItemStack(Items.BEETROOT, 15),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                10,
                0.05f
        ));

        // 6 — moeda -> torta de abóbora
        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.PUMPKIN_PIE, 4),
                12,
                10,
                0.05f
        ));

        // 7 — abóbora -> moeda
        trades.add(new MerchantOffer(
                new ItemStack(Items.PUMPKIN, 10),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12,
                15,
                0.05f
        ));

        // 8 — melancia -> moeda
        trades.add(new MerchantOffer(
                new ItemStack(Items.MELON, 4),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12,
                15,
                0.05f
        ));

        // 9 — moeda -> cookies
        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.COOKIE, 18),
                12,
                20,
                0.05f
        ));

        // 10 — moeda -> cenoura dourada
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

        if (this.getOffers().isEmpty()) {
            this.updateTrades();
        }

        System.out.println("Remy clicado. Trades carregadas: " + this.getOffers().size());

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
        // XP interno do Remy para liberar novas trocas
        this.remyTradeXp += 5;

        int oldLevel = this.remyTradeLevel;
        this.remyTradeLevel = calculateRemyLevel();

        // Se subiu de nível, adiciona novas trocas
        if (this.remyTradeLevel > oldLevel) {
            this.updateTrades();

            System.out.println("Remy subiu para o nível " + this.remyTradeLevel);
        }

        // XP visual vanilla
        if (offer.shouldRewardExp()) {
            int xp = 3 + this.random.nextInt(4);

            this.level().addFreshEntity(
                    new net.minecraft.world.entity.ExperienceOrb(
                            this.level(),
                            this.getX(),
                            this.getY() + 0.5,
                            this.getZ(),
                            xp
                    )
            );
        }
    }

    private int calculateRemyLevel() {
        if (this.remyTradeXp >= 120) {
            return 5;
        }

        if (this.remyTradeXp >= 70) {
            return 4;
        }

        if (this.remyTradeXp >= 30) {
            return 3;
        }

        if (this.remyTradeXp >= 10) {
            return 2;
        }

        return 1;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            this.hamudmod$spawnCigaretteSmoke();
        }

        if (!this.level().isClientSide && this.isTrading()) {
            this.getNavigation().stop();
        }
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

        if (this.remyTradeLevel <= 0) {
            this.remyTradeLevel = 1;
        }

        this.remyTradeLevel = calculateRemyLevel();

        this.updateTrades();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putInt("RemyTradeLevel", this.remyTradeLevel);
        tag.putInt("RemyTradeXp", this.remyTradeXp);
    }
    }