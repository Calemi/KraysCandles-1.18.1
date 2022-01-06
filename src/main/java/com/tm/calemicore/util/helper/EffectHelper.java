package com.tm.calemicore.util.helper;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/**
 * Use this class to help with Potion Effects.
 */
public class EffectHelper {

    /**
     * Applies a effect to Entities.
     * @param effect The effect to add.
     * @param durationTicks The duration in ticks of the effect.
     * @param amplifier The power of the effect. Starts at 0.
     * @param entities The entities that will receive the effect.
     */
    public static void addPotionEffect(MobEffect effect, int durationTicks, int amplifier, LivingEntity... entities) {

        for (LivingEntity player : entities) {

            if (!(player instanceof Player) || !((Player)player).isCreative()) {
                player.removeEffect(effect);
                player.addEffect(new MobEffectInstance(effect, durationTicks, amplifier, true, false));
            }
        }
    }

    /**
     * Applies an infinite effect to Entities. Infinite effects last until they are removed.
     * @param effect The effect to add.
     * @param amplifier The power of the effect. Starts at 0.
     * @param entities The entities that will receive the effect.
     */
    public static void addInfinitePotionEffect(MobEffect effect, int amplifier, LivingEntity... entities) {
        addPotionEffect(effect, 1000000, amplifier, entities);
    }
}
