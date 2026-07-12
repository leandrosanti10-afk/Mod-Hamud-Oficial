package hamud.mod.entity;

import hamud.mod.ModEntities;
import hamud.mod.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArpaoProjectileEntity extends AbstractArrow {

    public ArpaoProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public ArpaoProjectileEntity(Level level, LivingEntity owner) {
        super(ModEntities.ARPAO_PROJECTILE, owner, level);
        this.setNoGravity(true);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.ARPAO);
    }

    @Override
    protected float getWaterInertia() {
        /*
         * Vanilla normalmente freia projéteis dentro da água.
         * 0.99F faz o arpão praticamente manter a velocidade.
         */
        return 0.99F;
    }

    @Override
    public void tick() {
        super.tick();

        /*
         * Garante que ele continue sem gravidade,
         * inclusive depois de carregar/sincronizar.
         */
        this.setNoGravity(true);
    }
}