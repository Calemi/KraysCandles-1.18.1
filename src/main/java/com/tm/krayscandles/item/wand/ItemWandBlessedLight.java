package com.tm.krayscandles.item.wand;

import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.entity.Cloud;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemWandBlessedLight extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        if (player.getVehicle() == null) {

            Cloud cloud = new Cloud(player);
            level.addFreshEntity(cloud);
            player.startRiding(cloud);

            SoundHelper.playAtPlayer(player, SoundEvents.AMBIENT_UNDERWATER_ENTER, 1, 10);
            SoundHelper.playAtPlayer(player, SoundEvents.BLAZE_SHOOT, 1, -5);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_BLESSED;
    }
}


