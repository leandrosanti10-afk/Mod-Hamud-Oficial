package hamud.mod;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

public class ModVillagerTrades {
    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(
                VillagerProfession.FARMER,
                1,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(Items.WHEAT, 20),
                            new ItemStack(ModItems.MOEDA_HAMUD, 1),
                            16,
                            2,
                            0.05f
                    ));
                }
        );

        TradeOfferHelper.registerVillagerOffers(
                VillagerProfession.WEAPONSMITH,
                1,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemStack(ModItems.MOEDA_HAMUD, 5),
                            new ItemStack(Items.IRON_SWORD, 1),
                            8,
                            5,
                            0.05f
                    ));
                }
        );
    }
}