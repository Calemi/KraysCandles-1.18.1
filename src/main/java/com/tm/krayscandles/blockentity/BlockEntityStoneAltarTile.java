package com.tm.krayscandles.blockentity;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.blockentity.base.BlockEntityBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityStoneAltarTile extends BlockEntityBase {

    private ItemStack ritualStack;

    public BlockEntityStoneAltarTile(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.STONE_ALTAR_TILE.get(), pos, state);
        ritualStack = ItemStack.EMPTY;
    }

    /**
     * @return The floating Item Stack the Stone Altar Tile is holding.
     */
    public ItemStack getRitualStack() {
        return ritualStack;
    }

    /**
     * Sets the floating Item Stack the Stone Altar Tile is holding.
     * @param stack The Item Stack to set.
     */
    public void placeRitualStack(ItemStack stack) {
        ritualStack = stack;
        if (!getLevel().isClientSide()) markUpdated();
    }

    /**
     * Sets the floating Item Stack to EMPTY.
     */
    public void takeRitualStack() {
        ritualStack = ItemStack.EMPTY;
        if (!getLevel().isClientSide()) markUpdated();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag stackTag = tag.getCompound("Stack");
        ritualStack = ItemStack.of(stackTag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        CompoundTag stackTag = new CompoundTag();
        ritualStack.save(stackTag);
        tag.put("Stack", stackTag);
    }
}
