package com.tm.krayscandles.block.candle;

import com.mojang.math.Vector3d;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.block.base.KCBlockStates;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockCandleSoyColored extends BlockCandleBase {

    public static final EnumProperty<DyeColor> COLOR = KCBlockStates.COLOR;

    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(7.75, 8, 7.75, 8.25, 10, 8.25),
            Block.box(7, 0, 7, 9, 8, 9));

    public BlockCandleSoyColored() {
        registerDefaultState(getStateDefinition().any().setValue(LIT, false).setValue(COLOR, DyeColor.WHITE));
    }

    @Override
    public BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType() {
        return InitBlockEntityTypes.CANDLE_SOY_COLORED.get();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return InitBlockEntityTypes.CANDLE_SOY_COLORED.get().create(pos, state);
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
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos, state.setValue(COLOR, DyeColor.byId(stack.getDamageValue())), 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COLOR);
    }
}
