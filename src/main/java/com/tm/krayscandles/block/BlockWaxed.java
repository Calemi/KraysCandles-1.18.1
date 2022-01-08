package com.tm.krayscandles.block;

import com.tm.krayscandles.block.base.BlockBase;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class BlockWaxed extends BlockBase {

    public BlockWaxed() {
        super(Block.Properties.copy(Blocks.SAND).sound(SoundType.SLIME_BLOCK));
    }
}
