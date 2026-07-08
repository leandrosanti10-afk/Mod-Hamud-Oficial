package hamud.mod.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
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
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ChabanEntity extends AbstractVillager {

    public ChabanEntity(EntityType<? extends AbstractVillager> entityType, Level level) {
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

        // Chaban ainda não possui trocas.
        // Quando quiser adicionar trades, vamos colocar aqui.
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
        this.setDeltaMovement(0.0, 0.0, 0.0);

        this.updateTrades();

        System.out.println("Chaban clicado. Trades carregadas: " + this.getOffers().size());

        if (this.getOffers().isEmpty()) {
            player.displayClientMessage(Component.literal("Chaban ainda não possui trocas."), true);
            return InteractionResult.CONSUME;
        }

        player.awardStat(Stats.TALKED_TO_VILLAGER);

        this.setTradingPlayer(player);

        this.openTradingScreen(
                serverPlayer,
                Component.literal("Chaban"),
                1
        );

        return InteractionResult.CONSUME;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
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

        if (!this.level().isClientSide && this.isTrading()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0.0, 0.0, 0.0);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }
}