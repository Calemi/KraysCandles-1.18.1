package com.tm.krayscandles.item;

import com.tm.calemicore.util.helper.LoreHelper;
import com.tm.krayscandles.item.base.ItemMagicSwordBase;
import com.tm.krayscandles.item.tier.KCSwordTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemBladeNight extends ItemMagicSwordBase {

    public ItemBladeNight() {
        super(KCSwordTiers.NIGHT_BLADE);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        LoreHelper.addInformationLore(tooltip, new TranslatableComponent("lore.blade_night"), true);
    }
}
