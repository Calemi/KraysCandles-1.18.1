package com.tm.krayscandles.item.wand;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.calemicore.util.helper.RayTraceHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemWandMining extends ItemWandBase {

    private static final int MAX_DISTANCE = 30;

    @Override
    public int getCooldown() {
        return 4;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        RayTraceHelper.BlockTrace rayTrace = RayTraceHelper.rayTraceBlock(level, player, MAX_DISTANCE);

        if (rayTrace != null) {

            Location location = rayTrace.getHit();

            for (int i = 0; i < 3; i++) {
                level.addFreshEntity(new PrimedTnt(level, location.x + 0.5D, location.y + 0.5D, location.z + 0.5D, player));
            }

            MobEffectHelper.addMobEffect(MobEffects.DIG_SLOWDOWN, 20*4, 1, player);
            MobEffectHelper.addMobEffect(MobEffects.MOVEMENT_SLOWDOWN, 20*5, 1, player);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_MINING;
    }
}


