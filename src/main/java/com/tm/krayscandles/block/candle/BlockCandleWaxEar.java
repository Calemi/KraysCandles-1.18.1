package com.tm.krayscandles.block.candle;

import com.mojang.math.Vector3d;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockCandleWaxEar extends BlockCandleBase {

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 7.5, 9));

    @Override
    public BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType() {
        return InitBlockEntityTypes.CANDLE_WAX_EAR.get();
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return null;
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE;
    }

    @Override
    public void renderFlame(Level level, BlockPos pos, BlockState state, Vector3d particlePos) {
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x, particlePos.y + 0.1D, particlePos.z);
    }
}
