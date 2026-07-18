package hamud.mod;

import net.fabricmc.api.ModInitializer;

public class HamudMod implements ModInitializer {
	public static final String MOD_ID = "hamud-mod";

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModEntities.registerEntities();
		ModMenus.registerMenus();
		ModVillagerTrades.registerTrades();
		ModSounds.registerSounds();
		ModLootTableModifiers.modifyLootTables();

		System.out.println("Hamud Mod carregado!");
	}
}