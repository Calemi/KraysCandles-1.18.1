package com.tm.krayscandles.block.candle;

import com.mojang.math.Vector3d;
import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.blockentity.candle.BlockEntityCandleNull;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockCandleNull extends BlockCandleBase {

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(7.75, 8, 7.75, 8.25, 10, 8.25),
            Block.box(7, 0, 7, 9, 8, 9));

    @Override
    public BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType() {
        return InitBlockEntityTypes.CANDLE_NULL.get();
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, getBlockEntityType(), BlockEntityCandleNull::tick);
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
        CandleParticleHelper.renderFlame(level, pos,  particlePos.x, particlePos.y + 0.2D, particlePos.z);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
        super.animateTick(state, level, pos, rand);

        for (int i = 0; i < 10; i++) {
            CandleParticleHelper.renderSmoke(level, pos.getX() + ((rand.nextDouble() / 2) + 0.25D), pos.getY() + (rand.nextDouble() / 2), pos.getZ() + ((rand.nextDouble() / 2) + 0.25D));
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity pPlacer, ItemStack stack) {
        SoundHelper.playAtLocation(new Location(level, pos), SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS, 1, 12);
    }
}
