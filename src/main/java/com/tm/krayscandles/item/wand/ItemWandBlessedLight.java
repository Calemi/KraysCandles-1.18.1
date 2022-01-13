package com.tm.krayscandles.item.wand;

import com.tm.krayscandles.entity.Cloud;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemWandBlessedLight extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        Cloud cloud = new Cloud(player);
        level.addFreshEntity(cloud);

        return true;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_BLESSED;
    }
}


