package com.tm.krayscandles.block;

import com.tm.krayscandles.block.base.BlockBase;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockStatue extends BlockBase {

    /**
     * The state of the direction the Block is facing.
     */
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(1, 0, 1, 15, 4, 15),
            Block.box(2, 4, 2, 14, 6, 14));

    public BlockStatue() {
        super(Block.Properties.copy(Blocks.STONE).lightLevel(value -> 14));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {

        double xOffset = 0.25D;
        double zOffset = -0.4D;

        switch (state.getValue(FACING)) {
            case EAST -> {
                xOffset = 0.4D;
                zOffset = 0.25D;
            }
            case SOUTH -> {
                xOffset = -0.25D;
                zOffset = 0.4D;
            }
            case WEST -> {
                xOffset = -0.4D;
                zOffset = -0.25D;
            }
        }

        CandleParticleHelper.renderFlame(level, pos,pos.getX() + 0.5D + xOffset, pos.getY() + 1.7D, pos.getZ() + 0.5D + zOffset);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
