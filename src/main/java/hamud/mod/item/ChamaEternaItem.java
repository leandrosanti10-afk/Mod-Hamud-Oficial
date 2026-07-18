package hamud.mod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChamaEternaItem extends Item {

    public ChamaEternaItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(
            ItemStack stack,
            Level level,
            Entity entity,
            int slot,
            boolean selected
    ) {
        if (level.isClientSide) {
            return;
        }

        if (!(entity instanceof Player player)) {
            return;
        }

        boolean holdingInMainHand = player.getMainHandItem() == stack;
        boolean holdingInOffHand = player.getOffhandItem() == stack;

        if (!holdingInMainHand && !holdingInOffHand) {
            return;
        }

        player.addEffect(new MobEffectInstance(
                MobEffects.REGENERATION,
                40,
                0,
                true,
                false,
                true
        ));
    }
}