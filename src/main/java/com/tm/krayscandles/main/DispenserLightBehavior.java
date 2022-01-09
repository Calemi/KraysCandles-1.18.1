package com.tm.krayscandles.main;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.block.base.BlockCandleBase;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

public class DispenserLightBehavior implements DispenseItemBehavior {

    @Override
    public ItemStack dispense(BlockSource source, ItemStack stack) {

        Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
        Location location = new Location(source.getLevel(), source.getPos().relative(direction));

        if (location.getBlock() instanceof BlockCandleBase) {

            if (location.getBlockState().getValue(BlockCandleBase.LIT)) {
                BlockCandleBase.extinguishCandle(location);
            }

            else BlockCandleBase.lightCandle(location, null, stack);
        }

        return stack;
    }
}
