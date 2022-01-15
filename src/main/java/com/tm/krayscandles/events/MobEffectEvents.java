package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.init.InitMobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobEffectEvents {

    @SubscribeEvent
    public void onEffectAdded(PotionEvent.PotionAddedEvent event) {

        if (event.getPotionEffect().getEffect() == InitMobEffects.FLIGHT.get()) {

            event.getEntityLiving().removeEffect(MobEffects.SLOW_FALLING);

            if (event.getEntityLiving() instanceof Player player) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
        }
    }

    @SubscribeEvent
    public void onEffectDropped(PotionEvent.PotionExpiryEvent event) {
        if (event.getPotionEffect() != null && event.getPotionEffect().getEffect() == InitMobEffects.FLIGHT.get()) removeFlight(event.getPotionEffect().getEffect(), event.getEntityLiving());
    }

    private void removeFlight(MobEffect effect, LivingEntity entity) {

        if (entity instanceof Player player) {

            SoundHelper.playAtPlayer(player, SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 1, 10);

            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
        }
    }
}
