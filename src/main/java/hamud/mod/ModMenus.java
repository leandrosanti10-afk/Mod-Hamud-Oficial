package hamud.mod;

import hamud.mod.menu.CoroaMasterMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {

    public static final MenuType<CoroaMasterMenu> COROA_MASTER_MENU = Registry.register(
            BuiltInRegistries.MENU,
            new ResourceLocation(HamudMod.MOD_ID, "coroa_master_menu"),
            new MenuType<>(CoroaMasterMenu::new, FeatureFlags.VANILLA_SET)
    );

    public static void registerMenus() {
        System.out.println("Registrando menus do Hamud Mod...");
    }
}