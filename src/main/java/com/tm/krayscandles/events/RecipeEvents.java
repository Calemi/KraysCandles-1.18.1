package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.item.base.ItemBase;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RecipeEvents {

    @SubscribeEvent
    public void onCrafted(PlayerEvent.ItemCraftedEvent event) {

        for (int i = 0; i < event.getInventory().getContainerSize(); i++) {

            ItemStack stackInSlot = event.getInventory().getItem(i);

            if (!ItemBase.hasTag(event.getCrafting().getItem(), "iron_press")) {

                if (ItemBase.hasTag(stackInSlot.getItem(), "iron_press")) {
                    //ItemHelper.spawnStackAtEntity(event.getEntity().getLevel(), event.getEntity(), new ItemStack(InitItems.IRON_PRESS.get()));
                    return;
                }
            }
        }
    }
}
