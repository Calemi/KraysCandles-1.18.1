package com.tm.krayscandles.item.wand;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.LevelEffectHelper;
import com.tm.calemicore.util.helper.RayTraceHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemWandEnergy extends ItemWandBase {

    private static final int MAX_DISTANCE = 40;

    @Override
    public int getCooldown() {
        return 4;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        RayTraceHelper.BlockTrace rayTrace = RayTraceHelper.rayTraceBlock(level, player, MAX_DISTANCE);

        if (rayTrace != null) {

            Location location = rayTrace.getHit();
            LevelEffectHelper.spawnLightning(location, false);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_ENERGY;
    }
}


