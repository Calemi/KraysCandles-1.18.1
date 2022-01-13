package com.tm.krayscandles.block.candle;

import com.mojang.math.Vector3d;
import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockCandleCavern extends BlockCandleBase {

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(1, 0, 1, 15, 11, 15));

    @Override
    public BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType() {
        return InitBlockEntityTypes.CANDLE_CAVERN.get();
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.CANDLE_MINING;
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE;
    }

    @Override
    public void renderFlame(Level level, BlockPos pos, BlockState state, Vector3d particlePos) {
        CandleParticleHelper.renderFlame(level, pos, particlePos.x + 0.2D, particlePos.y + 0.3D, particlePos.z - 0.3D);
        CandleParticleHelper.renderFlame(level, pos, particlePos.x, particlePos.y + 0.2D, particlePos.z);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x + 0.3D, particlePos.y + 0.15D, particlePos.z + 0.125D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x - 0.25D, particlePos.y + 0.1D, particlePos.z + 0.2D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x - 0.375D, particlePos.y, particlePos.z - 0.375D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x - 0.35D, particlePos.y, particlePos.z + 0.4D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x + 0.4D, particlePos.y, particlePos.z + 0.35D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x - 0.1D, particlePos.y, particlePos.z - 0.4D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x + 0.35D, particlePos.y - 0.1D, particlePos.z - 0.15D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x + 0.025D, particlePos.y - 0.1D, particlePos.z + 0.35D);
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x - 0.35D, particlePos.y - 0.15D, particlePos.z - 0.075D);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity pPlacer, ItemStack stack) {
        SoundHelper.playAtLocation(new Location(level, pos), SoundEvents.AMBIENT_CAVE, SoundSource.BLOCKS, 1, 3);
    }
}
