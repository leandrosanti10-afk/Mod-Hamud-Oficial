package hamud.mod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item MOEDA_HAMUD = registerItem(
            "moeda_hamud",
            new Item(new Item.Properties())
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                new ResourceLocation(HamudMod.MOD_ID, name),
                item
        );
    }

    public static void registerItems() {
        System.out.println("Registrando itens do Hamud Mod...");
    }
}