package hamud.mod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.RecordItem;
import hamud.mod.item.ArpaoItem;

public class ModItems {
    public static final Item MOEDA_HAMUD = registerItem(
            "moeda_hamud",
            new Item(new Item.Properties())
    );

    public static final Item REMY_SPAWN_EGG = registerItem(
            "remy_spawn_egg",
            new SpawnEggItem(ModEntities.REMY, 0xE6C58A, 0x6B3F1D, new Item.Properties())
    );

    public static final Item CHABAN_SPAWN_EGG = registerItem(
            "chaban_spawn_egg",
            new SpawnEggItem(ModEntities.CHABAN, 0xD8B078, 0x4A2A16, new Item.Properties())
    );

    public static final Item VELOCIRAPTOR_SPAWN_EGG = registerItem(
            "velociraptor_spawn_egg",
            new SpawnEggItem(ModEntities.VELOCIRAPTOR, 0x6F7A3A, 0x2E2418, new Item.Properties())
    );

    public static final Item BORGES_SPAWN_EGG = registerItem(
            "borges_spawn_egg",
            new SpawnEggItem(ModEntities.BORGES, 0x4FC97A, 0xD6B14A, new Item.Properties())
    );

    public static final Item BRANO_SPAWN_EGG = registerItem(
            "brano_spawn_egg",
            new SpawnEggItem(ModEntities.BRANO, 0xE8E8E8, 0x2B1A12, new Item.Properties())
    );

    public static final Item ARPAO = registerItem(
            "arpao",
            new ArpaoItem(new Item.Properties().durability(384))
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
            entries.accept(CHABAN_SPAWN_EGG);
            entries.accept(VELOCIRAPTOR_SPAWN_EGG);
            entries.accept(BORGES_SPAWN_EGG);
            entries.accept(BRANO_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(MUSIC_DISC_O_VOVIS_TA_INDECISO);
            entries.accept(MUSIC_DISC_O_VOVIS_VAI_COMER_A_EMILIA);
            entries.accept(MUSIC_DISC_O_VOVIS_E_HETERO);
            entries.accept(MUSIC_DISC_O_VOVIS_CARREGA_BOLSA);
            entries.accept(MUSIC_DISC_VOVIS_E_GAY);
            entries.accept(MUSIC_DISC_E_ANIVERSARIO_BAFORA);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.accept(ARPAO);
        });
        System.out.println("Registrando itens do Hamud Mod...");
    }
}