package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.LoreHelper;
import com.tm.krayscandles.ritual.RitualResultItem;
import com.tm.krayscandles.ritual.RitualRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;

public class LoreEvents {

    @SubscribeEvent
    public void onRitual(ItemTooltipEvent event) {

        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if (item instanceof RitualResultItem || (item instanceof BlockItem && Block.byItem(item) instanceof RitualResultItem)) {

            RitualResultItem ritual;

            if (item instanceof BlockItem) {
                ritual = (RitualResultItem) Block.byItem(item);
            }

            else ritual = (RitualResultItem) item;

            RitualRecipe recipe = ritual.getRitualRecipe();

            if (recipe != null) {

                if (Screen.hasShiftDown()) {

                    event.getToolTip().add(new TranslatableComponent("ritual.made_in").withStyle(ChatFormatting.GRAY).append(" ").append(recipe.getRitualStructure().getNameKey()).withStyle(ChatFormatting.LIGHT_PURPLE));
                    event.getToolTip().add(new TranslatableComponent("ritual.use_item").withStyle(ChatFormatting.GRAY));
                    event.getToolTip().add(recipe.getHandItem().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE));
                    if (!recipe.getAltarItems().isEmpty()) event.getToolTip().add(new TranslatableComponent("ritual.place_in_altars").withStyle(ChatFormatting.GRAY));

                    List<Item> passedItems = new ArrayList<>();

                    for (Item alterItem : recipe.getAltarItems()) {

                        if (passedItems.contains(alterItem)) {
                            continue;
                        }

                        passedItems.add(alterItem);

                        int count = 0;

                        for (Item other : recipe.getAltarItems()) {

                            if (alterItem.equals(other)) {
                                count++;
                            }
                        }

                        event.getToolTip().add(alterItem.getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE).append(new TextComponent(" x" + count).withStyle(ChatFormatting.DARK_PURPLE)));
                    }
                }

                else {
                    event.getToolTip().add(LoreHelper.getPlateText("lore.key.shift", ChatFormatting.AQUA).append(" ").append(new TranslatableComponent("lore.recipes").withStyle(ChatFormatting.GRAY)));
                }
            }
        }
    }
}















