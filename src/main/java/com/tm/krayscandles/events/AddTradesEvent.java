package com.tm.krayscandles.events;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitVillagers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AddTradesEvent {

    /**
     * Handles adding new villager trades.
     */
    @SubscribeEvent
    public void onVillagerTrade (VillagerTradesEvent event) {

        if (event.getType() == InitVillagers.TYPE_CANDLE.get()) {

            event.getTrades().get(1).add((entity, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1), new ItemStack(InitItems.SOYBEAN_CROP.get(), 4), 128, 2, 0.05F));

            event.getTrades().get(2).add((entity, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4), new ItemStack(InitItems.GARLIC.get(), 1), 128, 2, 0.05F));

            event.getTrades().get(3).add((entity, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2), new ItemStack(InitItems.BAT_EYEBALL.get(), 1), 128, 2, 0.05F));

            event.getTrades().get(3).add((entity, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2), new ItemStack(InitItems.ZOMBIE_EAR.get(), 1), 128, 2, 0.05F));

            event.getTrades().get(5).add((entity, random) -> new MerchantOffer(
                    new ItemStack(Items.MUSIC_DISC_13, 1), new ItemStack(InitItems.WAX_CHUNK_SOY.get(), 1), new ItemStack(InitItems.MUSIC_DISC_CHUNK.get(), 1), 1, 4, 0.05F));
        }
    }
}
