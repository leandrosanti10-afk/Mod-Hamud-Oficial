package hamud.mod.mixin;

import hamud.mod.entity.HenriqueEntity;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MerchantMenu.class)
public abstract class MerchantMenuAnyMusicDiscMixin {

    @Redirect(
            method = "moveFromInventoryToPaymentSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isSameItemSameTags(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private boolean hamudmod$acceptAnyMusicDiscWhenClickingTrade(ItemStack requiredStack, ItemStack inventoryStack) {
        if (isMarkedAnyMusicDiscTrade(requiredStack)) {
            return isAnyMusicDisc(inventoryStack)
                    && inventoryStack.getCount() >= requiredStack.getCount();
        }

        return ItemStack.isSameItemSameTags(requiredStack, inventoryStack);
    }

    private static boolean isMarkedAnyMusicDiscTrade(ItemStack stack) {
        return stack.hasTag()
                && stack.getOrCreateTag().getBoolean(HenriqueEntity.ANY_MUSIC_DISC_TAG);
    }

    private static boolean isAnyMusicDisc(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        return stack.is(ItemTags.MUSIC_DISCS)
                || stack.getItem() instanceof RecordItem;
    }
}