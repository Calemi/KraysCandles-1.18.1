package com.tm.krayscandles.tab;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class KCToolTab extends CreativeModeTab {

    public KCToolTab() {
        super(KCReference.MOD_ID + ".tabTool");
    }

    @Override
    public ItemStack makeIcon () {
        return new ItemStack(InitItems.CANDLE_SOY.get());
    }
}
