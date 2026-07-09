package hamud.mod.block;

import hamud.mod.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VelociraptorEggBlock extends Block {

    public static final IntegerProperty HATCH = TurtleEggBlock.HATCH;

    private static final VoxelShape SHAPE = Block.box(
            5.0D, 0.0D, 5.0D,
            11.0D, 9.0D, 11.0D
    );

    public VelociraptorEggBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0));
    }

    @Override
    public VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }

    @Override
    public void randomTick(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            RandomSource random
    ) {
        int hatch = state.getValue(HATCH);

        // Chance de evoluir o ovo.
        // Quanto maior o número, mais demorado.
        if (random.nextInt(8) != 0) {
            return;
        }

        if (hatch < 2) {
            level.setBlock(
                    pos,
                    state.setValue(HATCH, hatch + 1),
                    2
            );

            // Partícula parecida com crescimento/rachadura
            level.levelEvent(2005, pos, 0);
        } else {
            level.destroyBlock(pos, false);

            ModEntities.VELOCIRAPTOR.spawn(
                    level,
                    pos,
                    MobSpawnType.BREEDING
            );
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH);
    }
}
