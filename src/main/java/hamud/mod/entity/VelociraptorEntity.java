package hamud.mod.entity;

import hamud.mod.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class VelociraptorEntity extends TamableAnimal {

    public VelociraptorEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 24.0)
                .add(Attributes.MOVEMENT_SPEED, 0.34)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.15, true));

        this.goalSelector.addGoal(3, new FollowOwnerGoal(
                this,
                1.1,
                10.0f,
                2.0f,
                false
        ));

        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.9));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // =========================
        // CRESCER COM CARNE CRUA
        // =========================
        // Se ele for bebê, qualquer carne crua acelera o crescimento.
        if (this.isBaby() && this.isRawMeat(stack)) {
            if (!this.level().isClientSide) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                /*
                 * 60 = adianta 60 segundos de crescimento.
                 * Se quiser crescer mais rápido, aumente para 120, 300, etc.
                 */
                this.ageUp(60, true);

                this.level().broadcastEntityEvent(this, (byte) 7); // corações
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        // =========================
        // DOMAR COM CARNE CRUA
        // =========================
        if (!this.isTame() && this.isRawMeat(stack)) {
            if (!this.level().isClientSide) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                // 1 em 3 de chance de domar
                if (this.random.nextInt(3) == 0) {
                    this.tame(player);
                    this.setOrderedToSit(true);
                    this.getNavigation().stop();
                    this.level().broadcastEntityEvent(this, (byte) 7); // corações
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6); // fumaça
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        // =========================
        // CURAR COM CARNE CRUA
        // =========================
        if (this.isTame()
                && this.isOwnedBy(player)
                && this.isRawMeat(stack)
                && this.getHealth() < this.getMaxHealth()) {

            if (!this.level().isClientSide) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                this.heal(4.0f);
                this.level().broadcastEntityEvent(this, (byte) 7);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        // =========================
        // SENTAR / LEVANTAR
        // =========================
        if (this.isTame() && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
                this.jumping = false;
                this.getNavigation().stop();
                this.setTarget(null);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    private boolean isRawMeat(ItemStack stack) {
        return stack.is(Items.BEEF)
                || stack.is(Items.PORKCHOP)
                || stack.is(Items.CHICKEN)
                || stack.is(Items.MUTTON)
                || stack.is(Items.RABBIT)
                || stack.is(Items.COD)
                || stack.is(Items.SALMON)
                || stack.is(Items.TROPICAL_FISH);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return this.isRawMeat(stack);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        VelociraptorEntity baby = ModEntities.VELOCIRAPTOR.create(level);

        if (baby != null) {
            baby.setBaby(true);
        }

        return baby;
    }
}