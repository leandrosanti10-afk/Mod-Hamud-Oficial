package hamud.mod.item;

import hamud.mod.entity.ArpaoProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ArpaoItem extends BowItem {

    private static final int COOLDOWN_TICKS = 40; // 2 segundos
    private static final float VELOCIDADE = 3.2F;
    private static final float IMPRECISAO = 0.6F;

    public ArpaoItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack arpao = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(arpao);
        }

        boolean creative = player.getAbilities().instabuild;
        boolean infinity = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, arpao) > 0;

        ItemStack ammo = player.getProjectile(arpao);

        /*
         * Sem infinidade ou creative, precisa ter flecha.
         * Com infinidade, pode atirar sem consumir flecha.
         */
        if (!creative && !infinity && ammo.isEmpty()) {
            return InteractionResultHolder.fail(arpao);
        }

        if (!level.isClientSide) {
            ArpaoProjectileEntity projectile = new ArpaoProjectileEntity(level, player);

            projectile.setBaseDamage(6.0D);
            projectile.setNoGravity(true);
            projectile.pickup = AbstractArrow.Pickup.DISALLOWED;

            projectile.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,
                    VELOCIDADE,
                    IMPRECISAO
            );

            level.addFreshEntity(projectile);

            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.TRIDENT_THROW,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            arpao.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

            if (!creative && !infinity) {
                if (!ammo.isEmpty() && ammo.is(Items.ARROW)) {
                    ammo.shrink(1);
                }
            }

            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        }

        player.swing(hand);

        return InteractionResultHolder.sidedSuccess(arpao, level.isClientSide);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 20;
    }
}