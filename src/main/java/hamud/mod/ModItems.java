package hamud.mod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {
    public static final Item MOEDA_HAMUD = registerItem(
            "moeda_hamud",
            new Item(new Item.Properties())
    );

    public static final Item REMY_SPAWN_EGG = registerItem(
            "remy_spawn_egg",
            new SpawnEggItem(ModEntities.REMY, 0x8B5A2B, 0xF5D76E, new Item.Properties())
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                new ResourceLocation(HamudMod.MOD_ID, name),
                item
        );
    }

    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.accept(MOEDA_HAMUD);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.accept(REMY_SPAWN_EGG);
        });
        System.out.println("Registrando itens do Hamud Mod...");
    }
}