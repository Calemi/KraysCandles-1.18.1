package com.tm.krayscandles.item.wand;

import com.tm.calemicore.util.helper.MathHelper;
import com.tm.calemicore.util.helper.RayTraceHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.level.Level;

public class ItemWandLevitation extends ItemWandBase {

    private static final int MAX_DISTANCE = 30;

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        Entity entityHit = RayTraceHelper.rayTraceEntity(level, player, MAX_DISTANCE);

        if (entityHit != null) {

            ShulkerBullet bill = new ShulkerBullet(level, player, entityHit, Direction.Axis.getRandom(MathHelper.random));
            level.addFreshEntity(bill);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_LEVITATE;
    }
}


