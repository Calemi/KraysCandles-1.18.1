package com.tm.krayscandles.tab;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class KCMainTab extends CreativeModeTab {

    public KCMainTab() {
        super(KCReference.MOD_ID + ".tabMain");
    }

    @Override
    public ItemStack makeIcon () {
        return new ItemStack(InitItems.CANDLE_SOY.get());
    }
}
