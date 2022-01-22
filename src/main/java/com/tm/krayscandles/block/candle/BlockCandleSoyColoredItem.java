package com.tm.krayscandles.block.candle;

import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

public class BlockCandleSoyColoredItem extends BlockItem {

    public BlockCandleSoyColoredItem(Block block) {
        super(block, new Item.Properties());
    }

    @Override
    public Component getName(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return new TranslatableComponent(this.getDescriptionId(stack) + "_" + DyeColor.byId(stack.getDamageValue()).getName());
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {

        if (tab == KraysCandles.TAB_CANDLE) {

            for (byte i = 0; i < DyeColor.values().length; i++) {
                ItemStack stack = new ItemStack(this);
                stack.setDamageValue(i);
                items.add(stack);
            }
        }
    }
}
