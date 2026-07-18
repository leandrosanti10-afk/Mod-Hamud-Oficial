package hamud.mod.mixin;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MerchantOffer.class)
public abstract class MerchantOfferAnyMusicDiscMixin {

    private static final String ANY_MUSIC_DISC_TAG = "HamudAnyMusicDiscTrade";

    @Inject(method = "satisfiedBy", at = @At("HEAD"), cancellable = true)
    private void hamudmod$acceptAnyMusicDisc(
            ItemStack costA,
            ItemStack costB,
            CallbackInfoReturnable<Boolean> cir
    ) {
        MerchantOffer offer = (MerchantOffer) (Object) this;
        ItemStack offerCostA = offer.getCostA();

        if (!isMarkedAnyMusicDiscTrade(offerCostA)) {
            return;
        }

        boolean validDisc = isAnyMusicDisc(costA)
                && costA.getCount() >= offerCostA.getCount();

        if (!validDisc) {
            cir.setReturnValue(false);
            return;
        }

        ItemStack offerCostB = offer.getCostB();

        if (offerCostB.isEmpty()) {
            cir.setReturnValue(true);
            return;
        }

        boolean validCostB = ItemStack.isSameItemSameTags(costB, offerCostB)
                && costB.getCount() >= offerCostB.getCount();

        cir.setReturnValue(validCostB);
    }

    @Inject(method = "take", at = @At("HEAD"), cancellable = true)
    private void hamudmod$takeAnyMusicDisc(
            ItemStack costA,
            ItemStack costB,
            CallbackInfoReturnable<Boolean> cir
    ) {
        MerchantOffer offer = (MerchantOffer) (Object) this;
        ItemStack offerCostA = offer.getCostA();

        if (!isMarkedAnyMusicDiscTrade(offerCostA)) {
            return;
        }

        if (!isAnyMusicDisc(costA) || costA.getCount() < offerCostA.getCount()) {
            cir.setReturnValue(false);
            return;
        }

        ItemStack offerCostB = offer.getCostB();

        if (!offerCostB.isEmpty()) {
            boolean validCostB = ItemStack.isSameItemSameTags(costB, offerCostB)
                    && costB.getCount() >= offerCostB.getCount();

            if (!validCostB) {
                cir.setReturnValue(false);
                return;
            }
        }

        costA.shrink(offerCostA.getCount());

        if (!offerCostB.isEmpty()) {
            costB.shrink(offerCostB.getCount());
        }

        cir.setReturnValue(true);
    }

    private static boolean isMarkedAnyMusicDiscTrade(ItemStack stack) {
        return stack.hasTag()
                && stack.getOrCreateTag().getBoolean(ANY_MUSIC_DISC_TAG);
    }

    private static boolean isAnyMusicDisc(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        return stack.is(ItemTags.MUSIC_DISCS)
                || stack.getItem() instanceof RecordItem;
    }
}