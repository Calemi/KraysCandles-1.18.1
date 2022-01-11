package com.tm.krayscandles.events;

import com.tm.krayscandles.block.candle.BlockCandleSoyColored;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockDropEvents {

    @SubscribeEvent
    public void onColorBreakEvent(BlockEvent.BreakEvent event) {

        Block block = event.getState().getBlock();

        if (!event.getPlayer().isCreative() && block instanceof BlockCandleSoyColored) {

            Level level = (Level) event.getWorld();

            int colorID = event.getState().getValue(BlockCandleSoyColored.COLOR).getId();
            ItemStack stack = new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
            stack.setDamageValue(colorID);
            level.addFreshEntity(new ItemEntity((Level) event.getWorld(), event.getPos().getX() + 0.5D, event.getPos().getY() + 0.5D, event.getPos().getZ() + 0.5D, stack));
        }
    }
}
