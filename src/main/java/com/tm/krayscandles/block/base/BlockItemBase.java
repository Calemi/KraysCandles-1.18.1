package com.tm.krayscandles.block.base;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * The base class for Items that place Blocks.
 */
public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block, CreativeModeTab tab) {
        super(block, new Item.Properties().tab(tab));
    }

    public BlockItemBase(Block block) {
        super(block, new Properties());
    }
}


