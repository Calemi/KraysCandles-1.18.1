package com.tm.krayscandles.block;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.krayscandles.block.base.BlockBase;
import com.tm.krayscandles.block.base.KCBlockStates;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockWaxMelter extends BlockBase implements WorldlyContainerHolder {

    public static final IntegerProperty LEVEL = KCBlockStates.WAX_MELTER_LEVEL;

    private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), INSIDE), BooleanOp.ONLY_FIRST);

    public BlockWaxMelter() {
        super(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion());
    }

    public static boolean fillWax(LevelAccessor level, BlockState state, BlockPos pos) {

        int wax_level = state.getValue(LEVEL);

        if (wax_level < 3) {

            if (wax_level == 2) {
                level.scheduleTick(pos, state.getBlock(), 40);
            }

            level.setBlock(pos, state.setValue(LEVEL, wax_level + 1), 2);

            return true;
        }

        return false;
    }

    public static void empty(LevelAccessor level, BlockState state, BlockPos pos) {
        level.setBlock(pos, state.setValue(LEVEL, 0), 2);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        new Location(level, pos).setBlock(state.setValue(LEVEL, 4));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        Location location = new Location(level, pos);
        ItemStack heldStack = player.getItemInHand(hand);

        if (location.getBlockState().getValue(LEVEL) < 4) {

            if (heldStack.getItem() == InitItems.WAX_CHUNK_SOY_RAW.get()) {

                if (fillWax(level, state, pos)) {
                    heldStack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        else {
            empty(level, state, pos);
            ItemHelper.spawnStackAtEntity(level, player, new ItemStack(InitItems.WAX_CHUNK_SOY.get(), 5));
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return INSIDE;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(LEVEL);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public WorldlyContainer getContainer(BlockState state, LevelAccessor level, BlockPos pos) {
        int wax_level = state.getValue(LEVEL);

        if (wax_level >= 4 && wax_level <= 8) {
            return new BlockWaxMelter.OutputContainer(state, level, pos, new ItemStack(InitItems.WAX_CHUNK_SOY.get()));
        }

        else {
            return wax_level < 3 ? new InputContainer(state, level, pos) : new EmptyContainer();
        }
    }

    static class EmptyContainer extends SimpleContainer implements WorldlyContainer {

        public EmptyContainer() {
            super(0);
        }

        public int[] getSlotsForFace(Direction pSide) {
            return new int[0];
        }

        /**
         * Returns true if automation can insert the given item in the given slot from the given side.
         */
        public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction dir) {
            return false;
        }

        /**
         * Returns true if automation can extract the given item in the given slot from the given side.
         */
        public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction dir) {
            return false;
        }
    }

    static class InputContainer extends SimpleContainer implements WorldlyContainer {

        private final BlockState state;
        private final LevelAccessor level;
        private final BlockPos pos;
        private boolean changed;

        public InputContainer(BlockState state, LevelAccessor level, BlockPos pos) {
            super(1);
            this.state = state;
            this.level = level;
            this.pos = pos;
        }

        /**
         * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
         */
        public int getMaxStackSize() {
            return 1;
        }

        public int[] getSlotsForFace(Direction side) {
            return side == Direction.UP ? new int[]{0} : new int[0];
        }

        /**
         * Returns true if automation can insert the given item in the given slot from the given side.
         */
        public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
            return !this.changed && direction == Direction.UP && stack.getItem() == InitItems.WAX_CHUNK_SOY_RAW.get();
        }

        /**
         * Returns true if automation can extract the given item in the given slot from the given side.
         */
        public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction dir) {
            return false;
        }

        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void setChanged() {

            ItemStack stack = this.getItem(0);

            if (!stack.isEmpty()) {
                this.changed = true;
                BlockWaxMelter.fillWax(this.level, this.state, this.pos);
                this.level.levelEvent(1500, this.pos, 0);
                this.removeItemNoUpdate(0);
            }
        }
    }

    static class OutputContainer extends SimpleContainer implements WorldlyContainer {

        private final BlockState state;
        private final LevelAccessor level;
        private final BlockPos pos;
        private boolean changed;

        public OutputContainer(BlockState state, LevelAccessor level, BlockPos pos, ItemStack stack) {
            super(stack);
            this.state = state;
            this.level = level;
            this.pos = pos;
        }

        /**
         * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
         */
        public int getMaxStackSize() {
            return 5;
        }

        public int[] getSlotsForFace(Direction side) {
            return side == Direction.DOWN ? new int[]{0} : new int[0];
        }

        /**
         * Returns true if automation can insert the given item in the given slot from the given side.
         */
        public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction dir) {
            return false;
        }

        /**
         * Returns true if automation can extract the given item in the given slot from the given side.
         */
        public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction dir) {
            return !this.changed && dir == Direction.DOWN && stack.is(InitItems.WAX_CHUNK_SOY.get());
        }

        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void setChanged() {

            int wax_level = state.getValue(LEVEL);

            if (wax_level < 8) {
                this.level.setBlock(this.pos, state.setValue(LEVEL, wax_level + 1), 2);
            }

            else BlockWaxMelter.empty(this.level, this.state, this.pos);

            this.changed = true;
        }
    }
}
