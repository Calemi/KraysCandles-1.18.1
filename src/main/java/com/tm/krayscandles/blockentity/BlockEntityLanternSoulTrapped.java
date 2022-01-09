package com.tm.krayscandles.blockentity;

import com.tm.krayscandles.blockentity.base.BlockEntityBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.soul.BlockEntitySoulHolder;
import com.tm.krayscandles.soul.Soul;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityLanternSoulTrapped extends BlockEntityBase implements BlockEntitySoulHolder {

    /**
     * The trapped Soul within the Candle.
     */
    private Soul trappedSoul;

    public BlockEntityLanternSoulTrapped(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.LANTERN_SOUL_TRAPPED.get(), pos, state);
        removeSoul();
    }

    /**
     * @return The trapped Soul within the Candle.
     */
    @Override
    public Soul getSoul() {
        return trappedSoul;
    }

    /**
     * Sets the current trapped soul.
     * @param soul The soul to set
     */
    @Override
    public void setSoul(Soul soul) {
        this.trappedSoul = soul;
    }

    /**
     * Removes the current trapped soul.
     */
    @Override
    public void removeSoul() {
        this.trappedSoul = new Soul(null);
    }

    /**
     * Creates this Block Entity from a CompoundTag when loaded.
     * @param tag The CompoundTag to load from.
     */
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        trappedSoul = Soul.load(tag);
    }

    /**
     * Saves this Block Entity to a CompoundTag when saved.
     * @param tag The CompoundTag to save to.
     */
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        trappedSoul.save(tag);
    }
}
