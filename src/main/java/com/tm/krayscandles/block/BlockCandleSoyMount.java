package com.tm.krayscandles.block;

import com.mojang.math.Vector3d;
import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.blockentity.BlockEntityCandleSoyMount;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockCandleSoyMount extends BlockCandleBase {

    /**
     * The state of the direction the Block is facing.
     */
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape SHAPE_N = Block.box(5.5D, 2.0D, 11.0D, 10.5D, 12.0D, 16.0D);
    public static final VoxelShape SHAPE_E = Block.box(0.0D, 2.0D, 5.5D, 5.0D, 12.0D, 10.5D);
    public static final VoxelShape SHAPE_S = Block.box(5.5D, 2.0D, 0.0D, 10.5D, 12.0D, 5.0D);
    public static final VoxelShape SHAPE_W = Block.box(11.0D, 2.0D, 5.5D, 16.0D, 12.0D, 10.5D);

    public BlockCandleSoyMount() {
        super(Block.Properties.copy(Blocks.LANTERN).lightLevel(LIGHT_EMISSION));
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_E;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            default -> SHAPE_N;
        };
    }

    @Override
    public BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType() {
        return InitBlockEntityTypes.CANDLE_SOY_MOUNT.get();
    }

    @Override
    public void renderFlame(Level level, BlockPos pos, BlockState state, Vector3d particlePos) {

        double xOffset = 0.0D;
        double zOffset = 0.2D;

        switch (state.getValue(FACING)) {
            case EAST -> {
                xOffset = -0.2D;
                zOffset = 0.0D;
            }
            case SOUTH -> {
                xOffset = 0.0D;
                zOffset = -0.2D;
            }
            case WEST -> {
                xOffset = 0.2D;
                zOffset = 0.0D;
            }
        }

        CandleParticleHelper.renderFlame(level, pos,  particlePos.x + xOffset, particlePos.y + 0.3D, particlePos.z + zOffset);
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        Location location = new Location(level, pos);
        ItemStack heldStack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntityCandleSoyMount mount) {

            if (!mount.getCandleStack().isEmpty()) {

                if (heldStack.isEmpty()) {
                    ItemHelper.spawnStackAtLocation(level, location, mount.getCandleStack());
                    mount.takeCandle();
                    setLit(location, false);
                    mount.removeSoul();
                    SoundHelper.playAtLocation(location, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1, 1);
                    return InteractionResult.SUCCESS;
                }

                return super.use(state, level, pos, player, hand, hit);
            }

            else if (mount.getCandleStack().isEmpty() && heldStack.getItem() == InitItems.CANDLE_SOY_COLORED_ITEM.get()) {
                mount.placeCandle(heldStack.getDamageValue());
                heldStack.shrink(1);
                SoundHelper.playAtLocation(location, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1, 1);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {

        if (!state.is(newState.getBlock())) {

            BlockEntity blockentity = level.getBlockEntity(pos);

            if (blockentity instanceof BlockEntityCandleSoyMount mount) {
                ItemHelper.spawnStackAtLocation(level, mount.getLocation(), mount.getCandleStack());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
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
}
