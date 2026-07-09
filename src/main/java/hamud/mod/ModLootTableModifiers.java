package hamud.mod;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModLootTableModifiers {

    // IDs dos baús originais do Minecraft que vamos injetar os itens
    private static final ResourceLocation DUNGEON_CHEST_ID = new ResourceLocation("minecraft", "chests/simple_dungeon");
    private static final ResourceLocation MINESHAFT_CHEST_ID = new ResourceLocation("minecraft", "chests/abandoned_mineshaft");
    private static final ResourceLocation DESERT_PYRAMID_CHEST_ID = new ResourceLocation("minecraft", "chests/desert_pyramid");
    private static final ResourceLocation JUNGLE_TEMPLE_CHEST_ID = new ResourceLocation("minecraft", "chests/jungle_temple");
    private static final ResourceLocation ANCIENT_CITY_CHEST_ID = new ResourceLocation("minecraft", "chests/ancient_city");
    private static final ResourceLocation WOODLAND_MANSION_CHEST_ID = new ResourceLocation("minecraft", "chests/woodland_mansion");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            
            // 1. Dungeon
            if (DUNGEON_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        // 10% de chance de spawnar o disco (0.10f)
                        .when(LootItemRandomChanceCondition.randomChance(0.10f))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_O_VOVIS_TA_INDECISO));
                tableBuilder.withPool(poolBuilder);
            }

            // 2. Minas Abandonadas
            if (MINESHAFT_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.10f))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_O_VOVIS_VAI_COMER_A_EMILIA));
                tableBuilder.withPool(poolBuilder);
            }

            // 3. Pirâmide do Deserto
            if (DESERT_PYRAMID_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.15f))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_O_VOVIS_E_HETERO));
                tableBuilder.withPool(poolBuilder);
            }

            // 4. Templo da Selva
            if (JUNGLE_TEMPLE_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.15f))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_O_VOVIS_CARREGA_BOLSA));
                tableBuilder.withPool(poolBuilder);
            }

            // 5. Cidade Ancestral
            if (ANCIENT_CITY_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        // 25% de chance porque é uma estrutura mais difícil e tem muitos baús
                        .when(LootItemRandomChanceCondition.randomChance(0.25f))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_VOVIS_E_GAY));
                tableBuilder.withPool(poolBuilder);
            }

            // 6. Mansão das Sombras (Woodland Mansion)
            if (WOODLAND_MANSION_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.30f))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_E_ANIVERSARIO_BAFORA));
                tableBuilder.withPool(poolBuilder);
            }
        });
        
        System.out.println("Tabelas de Loot do Hamud Mod registradas!");
    }
}
