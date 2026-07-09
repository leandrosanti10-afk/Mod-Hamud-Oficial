package hamud.mod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.RecordItem;

public class ModItems {
    public static final Item MOEDA_HAMUD = registerItem(
            "moeda_hamud",
            new Item(new Item.Properties())
    );

    public static final Item REMY_SPAWN_EGG = registerItem(
            "remy_spawn_egg",
            new SpawnEggItem(ModEntities.REMY, 0x8B5A2B, 0xF5D76E, new Item.Properties())
    );

    public static final Item MUSIC_DISC_O_VOVIS_TA_INDECISO = registerItem("music_disc_o_vovis_ta_indeciso",
            new RecordItem(14, ModSounds.O_VOVIS_TA_INDECISO, new Item.Properties().stacksTo(1), 1200));
    public static final Item MUSIC_DISC_O_VOVIS_VAI_COMER_A_EMILIA = registerItem("music_disc_o_vovis_vai_comer_a_emilia",
            new RecordItem(14, ModSounds.O_VOVIS_VAI_COMER_A_EMILIA, new Item.Properties().stacksTo(1), 1200));
    public static final Item MUSIC_DISC_O_VOVIS_E_HETERO = registerItem("music_disc_o_vovis_e_hetero_ele_nao_gosta_de_pau",
            new RecordItem(14, ModSounds.O_VOVIS_E_HETERO_ELE_NAO_GOSTA_DE_PAU, new Item.Properties().stacksTo(1), 1200));
    public static final Item MUSIC_DISC_O_VOVIS_CARREGA_BOLSA = registerItem("music_disc_o_vovis_carrega_tua_bolsa",
            new RecordItem(14, ModSounds.O_VOVIS_CARREGA_TUA_BOLSA, new Item.Properties().stacksTo(1), 1200));
    public static final Item MUSIC_DISC_VOVIS_E_GAY = registerItem("music_disc_vovis_e_gay_e_ele_quica_no_pau",
            new RecordItem(14, ModSounds.VOVIS_E_GAY_E_ELE_QUICA_NO_PAU, new Item.Properties().stacksTo(1), 1200));
    public static final Item MUSIC_DISC_E_ANIVERSARIO_BAFORA = registerItem("music_disc_e_aniversario_do_bafora",
            new RecordItem(14, ModSounds.E_ANIVERSARIO_DO_BAFORA, new Item.Properties().stacksTo(1), 1200));

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
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(MUSIC_DISC_O_VOVIS_TA_INDECISO);
            entries.accept(MUSIC_DISC_O_VOVIS_VAI_COMER_A_EMILIA);
            entries.accept(MUSIC_DISC_O_VOVIS_E_HETERO);
            entries.accept(MUSIC_DISC_O_VOVIS_CARREGA_BOLSA);
            entries.accept(MUSIC_DISC_VOVIS_E_GAY);
            entries.accept(MUSIC_DISC_E_ANIVERSARIO_BAFORA);
        });
        System.out.println("Registrando itens do Hamud Mod...");
    }
}