package com.tm.krayscandles.block;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.block.base.BlockBase;
import com.tm.krayscandles.blockentity.BlockEntityStoneAltarTile;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockStoneAltarTile extends BlockBase implements EntityBlock {

    /**
     * The state of the direction the Block is facing.
     */
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1, 16);

    public BlockStoneAltarTile() {
        super(Block.Properties.copy(Blocks.STONE));
    }

    /**
     * Used to place or take items from the Altar when a Player right clicks it.
     */
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        Location location = new Location(level, pos);
        ItemStack heldStack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntityStoneAltarTile alterTile) {

            if (alterTile.getRitualStack().isEmpty() && !heldStack.isEmpty()) {

                ItemStack ritualStack = heldStack.copy();
                ritualStack.setCount(1);

                alterTile.placeRitualStack(ritualStack);
                heldStack.shrink(1);

                SoundHelper.playAtLocation(location, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1, 1);
                return InteractionResult.SUCCESS;
            }

            else if (!alterTile.getRitualStack().isEmpty()) {

                ItemStack copiedStack = alterTile.getRitualStack().copy();
                copiedStack.setCount(1);

                ItemHelper.spawnStackAtLocation(level, location, copiedStack);
                alterTile.takeRitualStack();

                SoundHelper.playAtLocation(location, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1, 1);
                return InteractionResult.SUCCESS;
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {

        if (!state.is(newState.getBlock())) {

            BlockEntity blockentity = level.getBlockEntity(pos);

            if (blockentity instanceof BlockEntityStoneAltarTile alterTile) {
                ItemHelper.spawnStackAtLocation(level, alterTile.getLocation(), alterTile.getRitualStack());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return InitBlockEntityTypes.STONE_ALTAR_TILE.get().create(pos, state);
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

