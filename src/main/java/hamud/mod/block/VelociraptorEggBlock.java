package hamud.mod.block;

import hamud.mod.ModEntities;
import hamud.mod.entity.VelociraptorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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

    /*
     * Tempo entre cada estágio.
     * 20 ticks = 1 segundo.
     *
     * 1200 ticks = 60 segundos.
     *
     * Como são 3 etapas:
     * hatch 0 -> hatch 1
     * hatch 1 -> hatch 2
     * hatch 2 -> nasce
     *
     * Tempo total aproximado: 3 minutos.
     */
    private static final int HATCH_TIME = 20 * 60;

    private static final VoxelShape SHAPE = Block.box(
            1.0D, 0.0D, 1.0D,
            15.0D, 8.0D, 15.0D
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
    public void onPlace(
            BlockState state,
            Level level,
            BlockPos pos,
            BlockState oldState,
            boolean movedByPiston
    ) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            level.scheduleTick(pos, this, HATCH_TIME);
        }
    }

    @Override
    public void tick(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            RandomSource random
    ) {
        int hatch = state.getValue(HATCH);

        if (hatch < 2) {
            level.setBlock(
                    pos,
                    state.setValue(HATCH, hatch + 1),
                    2
            );

            level.levelEvent(2005, pos, 0);

            level.scheduleTick(pos, this, HATCH_TIME);
        } else {
            level.destroyBlock(pos, false);

            VelociraptorEntity velociraptor = ModEntities.VELOCIRAPTOR.spawn(
                    level,
                    pos.above(),
                    MobSpawnType.BREEDING
            );

            if (velociraptor != null) {
                velociraptor.setBaby(true);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH);
    }
}