package hamud.mod.mixin;

import hamud.mod.entity.BorgesEntity;
import hamud.mod.entity.BranoEntity;
import hamud.mod.entity.ChabanEntity;
import hamud.mod.entity.RemyEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public abstract class ZombieTargetMixin extends PathfinderMob {

    protected ZombieTargetMixin(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void hamudmod$addCustomVillagerTargets(CallbackInfo ci) {
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
                this,
                RemyEntity.class,
                true
        ));

        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
                this,
                ChabanEntity.class,
                true
        ));

        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
                this,
                BorgesEntity.class,
                true
        ));

        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
                this,
                BranoEntity.class,
                true
        ));
    }
}