package com.tm.krayscandles.block.candle;

import com.mojang.math.Vector3d;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockCandleFire extends BlockCandleBase {

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(7, 0, 7, 9, 22, 9),
            Block.box(7.825D, 22, 7.825D, 8.225D, 23.75D, 8.225D));

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE;
    }

    @Override
    public BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType() {
        return InitBlockEntityTypes.CANDLE_FIRE.get();
    }

    @Override
    public void renderFlame(Level level, BlockPos pos, BlockState state, Vector3d particlePos) {
        double midY = particlePos.y + 0.35D;
        double topY = particlePos.y + 1.125D;

        double midOffset = 0.1D;

        CandleParticleHelper.renderFlame(level, pos, particlePos.x, topY, particlePos.z);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x + midOffset, midY, particlePos.z);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x - midOffset, midY, particlePos.z);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x, midY, particlePos.z + midOffset);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x, midY, particlePos.z - midOffset);
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.CANDLE_FIRE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return InitBlockEntityTypes.CANDLE_FIRE.get().create(pos, state);
    }
}
