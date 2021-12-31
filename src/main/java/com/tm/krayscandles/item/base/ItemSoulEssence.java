package com.tm.krayscandles.item.base;

import com.tm.calemicore.util.helper.LoreHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class ItemSoulEssence extends ItemBase {

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        LoreHelper.addInformationLore(tooltip, new TranslatableComponent("lore.soul_essence"), true);
    }
}