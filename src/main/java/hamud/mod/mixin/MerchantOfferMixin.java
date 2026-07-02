package hamud.mod.mixin;

import hamud.mod.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MerchantOffer.class)
public class MerchantOfferMixin {

    @Inject(method = "getCostA", at = @At("RETURN"), cancellable = true)
    private void hamudmod$replaceCostA(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack original = cir.getReturnValue();

        if (original.is(Items.EMERALD)) {
            cir.setReturnValue(new ItemStack(ModItems.MOEDA_HAMUD, original.getCount()));
        }
    }

    @Inject(method = "getCostB", at = @At("RETURN"), cancellable = true)
    private void hamudmod$replaceCostB(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack original = cir.getReturnValue();

        if (original.is(Items.EMERALD)) {
            cir.setReturnValue(new ItemStack(ModItems.MOEDA_HAMUD, original.getCount()));
        }
    }

    @Inject(method = "getResult", at = @At("RETURN"), cancellable = true)
    private void hamudmod$replaceResult(CallbackInfoReturnable<ItemStack> cir) {
        ItemStack original = cir.getReturnValue();

        if (original.is(Items.EMERALD)) {
            cir.setReturnValue(new ItemStack(ModItems.MOEDA_HAMUD, original.getCount()));
        }
    }
}