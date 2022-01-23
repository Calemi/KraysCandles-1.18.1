package com.tm.krayscandles.item.wand;

import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemWandMagician extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 60;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        Rabbit bugs = new Rabbit(EntityType.RABBIT, level);
        bugs.setPos(player.getX(), player.getY(), player.getZ());
        bugs.setRabbitType(0);
        level.addFreshEntity(bugs);
        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1, 12);
        MobEffectHelper.addMobEffect(MobEffects.INVISIBILITY, 120*20, 1, player);
        MobEffectHelper.addMobEffect(MobEffects.LUCK, 120*20, 1, player);

        return true;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_INVIS;
    }
}


