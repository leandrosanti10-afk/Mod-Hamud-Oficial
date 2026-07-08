package hamud.mod;

import hamud.mod.entity.ChabanEntity;
import hamud.mod.entity.RemyEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<RemyEntity> REMY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "remy"),
            EntityType.Builder.of(RemyEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 2.05f)
                    .build("remy")
    );

    public static final EntityType<ChabanEntity> CHABAN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "chaban"),
            EntityType.Builder.of(ChabanEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 2.05f)
                    .build("chaban")
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(REMY, RemyEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CHABAN, ChabanEntity.createAttributes());

        System.out.println("Registrando entidades do Hamud Mod...");
    }
}