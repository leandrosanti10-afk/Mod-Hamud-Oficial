package hamud.mod;

import net.fabricmc.api.ModInitializer;

public class HamudMod implements ModInitializer {
	public static final String MOD_ID = "hamud-mod";

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModVillagerTrades.registerTrades();

		System.out.println("Hamud Mod carregado!");
	}
}