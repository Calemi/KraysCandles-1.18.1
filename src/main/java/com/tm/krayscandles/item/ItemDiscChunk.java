package com.tm.krayscandles.item;

import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Supplier;

public class ItemDiscChunk extends RecordItem {

    public ItemDiscChunk(Supplier<SoundEvent> soundSupplier) {
        super(15, soundSupplier, new Item.Properties().tab(KraysCandles.TAB_MAIN).stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent("item.krayscandles.music_disc_chunk.desc").withStyle(ChatFormatting.GRAY));
    }
}