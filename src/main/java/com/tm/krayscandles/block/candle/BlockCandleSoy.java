package com.tm.krayscandles.block.candle;

import com.mojang.math.Vector3d;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockCandleSoy extends BlockCandleBase {

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(7.75, 8, 7.75, 8.25, 10, 8.25),
            Block.box(7, 0, 7, 9, 8, 9));

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE;
    }

    @Override
    public void renderFlame(Level level, BlockPos pos, BlockState state, Vector3d particlePos) {
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x, particlePos.y + 0.2D, particlePos.z);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, BlockEntityType.BEACON, BeaconBlockEntity::tick);
    }
}
