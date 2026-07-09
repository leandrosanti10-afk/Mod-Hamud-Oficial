package hamud.mod;

import hamud.mod.block.VelociraptorEggBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {

    public static final Block VELOCIRAPTOR_EGG = registerBlock(
            "velociraptor_egg",
            new VelociraptorEggBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.5f)
                    .randomTicks()
                    .noOcclusion()
                    .sound(SoundType.WOOD)
            )
    );

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);

        return Registry.register(
                BuiltInRegistries.BLOCK,
                new ResourceLocation(HamudMod.MOD_ID, name),
                block
        );
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                new ResourceLocation(HamudMod.MOD_ID, name),
                new BlockItem(block, new Item.Properties())
        );
    }

    public static void registerBlocks() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.accept(VELOCIRAPTOR_EGG.asItem());
        });

        System.out.println("Registrando blocos do Hamud Mod...");
    }
}
