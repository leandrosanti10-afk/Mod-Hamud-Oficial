package hamud.mod;

import net.fabricmc.api.ModInitializer;

public class HamudMod implements ModInitializer {
	public static final String MOD_ID = "hamud-mod";

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModEntities.registerEntities();
//		ModBlocks.registerBlocks();
		ModSounds.registerSounds();
		ModLootTableModifiers.modifyLootTables();

		System.out.println("Hamud Mod carregado!");
	}
}