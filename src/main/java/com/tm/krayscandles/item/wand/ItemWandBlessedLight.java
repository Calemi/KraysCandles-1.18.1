package com.tm.krayscandles.item.wand;

import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.init.InitMobEffects;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ItemWandBlessedLight extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(Level level, Player player) {

        if (!player.isCreative()) {

            if (player.getEffect(InitMobEffects.FLIGHT.get()) == null) {
                SoundHelper.playAtPlayer(player, SoundEvents.CONDUIT_AMBIENT, SoundSource.PLAYERS, 1, 10);
                MobEffectHelper.addMobEffect(InitMobEffects.FLIGHT.get(), 20*60*8, 0, player);
                player.removeEffect(MobEffects.SLOW_FALLING);
            }

            else {
                player.removeEffect(InitMobEffects.FLIGHT.get());
                MobEffectHelper.addMobEffect(MobEffects.SLOW_FALLING, 20*30, 0, player);
            }

            SoundHelper.playAtPlayer(player, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.PLAYERS, 1, 10);
            SoundHelper.playAtPlayer(player, SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1, -5);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_BLESSED;
    }
}


