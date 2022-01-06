package com.tm.krayscandles.tab;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class KCCandleTab extends CreativeModeTab {

    public KCCandleTab() {
        super(KCReference.MOD_ID + ".tabCandle");
    }

    @Override
    public ItemStack makeIcon () {
        ItemStack stack = new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
        stack.setDamageValue(14);
        return stack;
    }
}
