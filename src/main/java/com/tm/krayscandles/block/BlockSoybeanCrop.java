package com.tm.krayscandles.block;

import com.tm.krayscandles.block.base.BlockCropBase;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.world.level.ItemLike;

public class BlockSoybeanCrop extends BlockCropBase {

    @Override
    protected ItemLike getBaseSeedId() {
        return InitItems.SOYBEAN.get();
    }
}
