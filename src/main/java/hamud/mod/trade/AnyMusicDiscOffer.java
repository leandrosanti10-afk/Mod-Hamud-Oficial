package hamud.mod.trade;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.trading.MerchantOffer;

public class AnyMusicDiscOffer extends MerchantOffer {

    public AnyMusicDiscOffer(
            ItemStack displayCost,
            ItemStack result,
            int maxUses,
            int xp,
            float priceMultiplier
    ) {
        super(displayCost, result, maxUses, xp, priceMultiplier);
    }

    @Override
    public boolean satisfiedBy(ItemStack costA, ItemStack costB) {
        return this.isAnyMusicDisc(costA)
                && costA.getCount() >= this.getCostA().getCount()
                && (
                this.getCostB().isEmpty()
                        || (
                        ItemStack.isSameItemSameTags(costB, this.getCostB())
                                && costB.getCount() >= this.getCostB().getCount()
                )
        );
    }

    @Override
    public boolean take(ItemStack costA, ItemStack costB) {
        if (!this.satisfiedBy(costA, costB)) {
            return false;
        }

        costA.shrink(this.getCostA().getCount());

        if (!this.getCostB().isEmpty()) {
            costB.shrink(this.getCostB().getCount());
        }

        return true;
    }

    private boolean isAnyMusicDisc(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        return stack.is(ItemTags.MUSIC_DISCS)
                || stack.getItem() instanceof RecordItem;
    }
}