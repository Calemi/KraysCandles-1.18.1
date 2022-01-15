package com.tm.krayscandles.blockentity;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.item.ItemCrystal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleSoyMount extends BlockEntityCandleBase {

    private ItemStack candleStack;

    public BlockEntityCandleSoyMount(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_SOY_MOUNT.get(), pos, state);
        candleStack = ItemStack.EMPTY;
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {};
    }

    public ItemStack getCandleStack() {
        return candleStack;
    }

    public void placeCandle(int colorID) {

        ItemStack newStack = new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
        newStack.setDamageValue(colorID);

        candleStack = newStack;

        if (!getLevel().isClientSide()) markUpdated();
    }

    public void takeCandle() {
        candleStack = ItemStack.EMPTY;
    }

    @Override
    public int getMaxCrystalCountOfType(ItemCrystal.CrystalType type) {
        return 0;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag stackTag = tag.getCompound("Stack");
        candleStack = ItemStack.of(stackTag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        CompoundTag stackTag = new CompoundTag();
        candleStack.save(stackTag);
        tag.put("Stack", stackTag);
    }
}
