package hamud.mod;

import hamud.mod.entity.ArpaoProjectileEntity;
import hamud.mod.entity.BorgesEntity;
import hamud.mod.entity.ChabanEntity;
import hamud.mod.entity.RemyEntity;
import hamud.mod.entity.VelociraptorEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import hamud.mod.entity.BranoEntity;
import hamud.mod.entity.HenriqueEntity;

public class ModEntities {

    public static final EntityType<RemyEntity> REMY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "remy"),
            EntityType.Builder.of(RemyEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 2.05F)
                    .build("remy")
    );

    public static final EntityType<ChabanEntity> CHABAN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "chaban"),
            EntityType.Builder.of(ChabanEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 2.05F)
                    .build("chaban")
    );

    public static final EntityType<VelociraptorEntity> VELOCIRAPTOR = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "velociraptor"),
            EntityType.Builder.of(VelociraptorEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.25F)
                    .build("velociraptor")
    );

    public static final EntityType<BorgesEntity> BORGES = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "borges"),
            EntityType.Builder.of(BorgesEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.9F, 2.0F)
                    .build("borges")
    );

    public static final EntityType<BranoEntity> BRANO = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "brano"),
            EntityType.Builder.of(BranoEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 2.05F)
                    .build("brano")
    );

    public static final EntityType<HenriqueEntity> HENRIQUE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "henrique"),
            EntityType.Builder.of(HenriqueEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 2.05F)
                    .build("henrique")
    );

    public static final EntityType<ArpaoProjectileEntity> ARPAO_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(HamudMod.MOD_ID, "arpao_projectile"),
            EntityType.Builder.<ArpaoProjectileEntity>of(ArpaoProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("arpao_projectile")
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(REMY, RemyEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CHABAN, ChabanEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(VELOCIRAPTOR, VelociraptorEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BORGES, BorgesEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BRANO, BranoEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(HENRIQUE, HenriqueEntity.createAttributes());

        /*
         * Não registrar atributo para ARPAO_PROJECTILE.
         * Projétil não usa FabricDefaultAttributeRegistry.
         */

        System.out.println("Registrando entidades do Hamud Mod...");
    }
}