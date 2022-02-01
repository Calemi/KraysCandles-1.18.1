package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.ChatHelper;
import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.entity.vampire.Vampire;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VampireEvents {

    private static final int SPAWN_RANGE = 500;

    private boolean canSpawnToday = true;

    @SubscribeEvent
    public void onTick(TickEvent.WorldTickEvent event) {

        Level level = event.world;

        if (level.players().size() > 0) {

            Player randomPlayer = level.players().get(level.random.nextInt(level.players().size()));

            if (level.getDayTime() == 18000) {

                if (canSpawnToday) {

                    if (level.random.nextInt(5) == 0) {

                        canSpawnToday = false;
                        int randX = randomPlayer.getBlockX() + (SPAWN_RANGE - level.random.nextInt(SPAWN_RANGE * 2));
                        int randZ = randomPlayer.getBlockZ() + (SPAWN_RANGE - level.random.nextInt(SPAWN_RANGE * 2));

                        Vampire vampire = new Vampire(level);
                        level.addFreshEntity(vampire);
                        vampire.setPos(randX, 251, randZ);
                        ChatHelper.broadcastMessage(level, new TranslatableComponent("chat.vampire").withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC).append(" [" + randX + ", " + randZ + "]!"));
                        SoundHelper.playGlobal(level, SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 1, 1);
                    }
                }
            }
        } else canSpawnToday = true;
    }

    @SubscribeEvent
    public void onVampireGarlicHit(LivingDamageEvent event) {

        if (event.getSource().getDirectEntity() instanceof Player player) {

            ItemStack offItem = player.getOffhandItem();

            if (event.getEntity() instanceof Vampire vampire) {

                if (offItem.getItem() == InitItems.GARLIC.get()) {
                    vampire.playSound(InitSounds.VAMPIRE_WEAKENED.get(), 2, 1);
                    vampire.hurt(DamageSource.WITHER, 10);
                    MobEffectHelper.addMobEffect(MobEffects.WEAKNESS, 100, 0);
                    MobEffectHelper.addMobEffect(MobEffects.MOVEMENT_SLOWDOWN, 100, 0);
                }
            }
        }
    }
}
