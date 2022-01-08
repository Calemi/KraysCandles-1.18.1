package com.tm.krayscandles.item.wand;

import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ItemWandFire extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        Vec3 aim = player.getLookAngle();
        SmallFireball fireball = new SmallFireball(level, player, 0, 0, 0);
        fireball.setPos(player.getX() + aim.x * 2D, player.getY() + aim.y * 2D + 1.5D, player.getZ() + aim.z * 2D);
        fireball.xPower = aim.x * .1;
        fireball.yPower = aim.y * .1;
        fireball.zPower = aim.z * .1;
        level.addFreshEntity(fireball);
        MobEffectHelper.addMobEffect(MobEffects.FIRE_RESISTANCE, 20, 1, player);

        return true;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_FIRE;
    }
}


