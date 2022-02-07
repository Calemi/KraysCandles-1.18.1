package com.tm.krayscandles.events;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ChatHelper;
import com.tm.calemicore.util.helper.MathHelper;
import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.entity.vampire.Vampire;
import com.tm.krayscandles.entity.vampire.VampireBaron;
import com.tm.krayscandles.entity.vampire.VampireBaroness;
import com.tm.krayscandles.entity.wraith.WraithFire;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
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
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

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
    @SubscribeEvent
    public void onVampireBaronDeath(LivingDeathEvent event) {

        if (event.getSource().getDirectEntity() instanceof Player player) {
            Level level = player.getLevel();
            if (event.getEntity() instanceof VampireBaron vampireB) {
                Random rand = new Random();
                int upperbound = 30;
                int int_random = rand.nextInt(upperbound);
                int int_random2 = rand.nextInt(upperbound);
                int int_random3 = rand.nextInt(upperbound);
                int randX = vampireB.getBlockX() + int_random;
                int randZ = vampireB.getBlockZ() + int_random2;
                int randX2 = vampireB.getBlockX() + int_random2;
                int randZ2 = vampireB.getBlockZ() + int_random3;
                int randX3 = vampireB.getBlockX() + int_random2;
                int randZ3 = vampireB.getBlockZ() + int_random;
                int attractionBound = 7;
                int attractionMultiplier = rand.nextInt(attractionBound);

                if(attractionMultiplier == 3){
                    VampireBaron vampire = new VampireBaron(level);
                    VampireBaron vampire2 = new VampireBaron(level);
                    VampireBaron vampire3 = new VampireBaron(level);
                    level.addFreshEntity(vampire);
                    vampire.setPos(randX, 251, randZ);
                    level.addFreshEntity(vampire2);
                    vampire2.setPos(randX2, 251, randZ2);
                    level.addFreshEntity(vampire3);
                    vampire3.setPos(randX3, 251, randZ3);
                }
                if(attractionMultiplier == 2){
                    VampireBaron vampire = new VampireBaron(level);
                    VampireBaron vampire2 = new VampireBaron(level);
                    level.addFreshEntity(vampire);
                    vampire.setPos(randX, 251, randZ);
                    level.addFreshEntity(vampire2);
                    vampire2.setPos(randX2, 251, randZ2);

                }
                if(attractionMultiplier == 1){
                    VampireBaron vampire = new VampireBaron(level);
                    level.addFreshEntity(vampire);
                    vampire.setPos(randX, 251, randZ);

                }
                }
            }
        }

    @SubscribeEvent
    public void onVampireBaronessDeath(LivingDeathEvent event) {

        if (event.getSource().getDirectEntity() instanceof Player player) {
            Level level = player.getLevel();
            if (event.getEntity() instanceof VampireBaroness vampireB) {
                Random rand = new Random();
                int upperbound = 30;
                int int_random = rand.nextInt(upperbound);
                int int_random2 = rand.nextInt(upperbound);
                int int_random3 = rand.nextInt(upperbound);
                int randX = vampireB.getBlockX() + int_random;
                int randZ = vampireB.getBlockZ() + int_random2;
                int randX2 = vampireB.getBlockX() + int_random2;
                int randZ2 = vampireB.getBlockZ() + int_random3;
                int randX3 = vampireB.getBlockX() + int_random2;
                int randZ3 = vampireB.getBlockZ() + int_random;
                int attractionBound = 7;
                int attractionMultiplier = rand.nextInt(attractionBound);

                if(attractionMultiplier == 3){
                    VampireBaroness vampire = new VampireBaroness(level);
                    VampireBaroness vampire2 = new VampireBaroness(level);
                    VampireBaroness vampire3 = new VampireBaroness(level);
                    level.addFreshEntity(vampire);
                    vampire.setPos(randX, 251, randZ);
                    level.addFreshEntity(vampire2);
                    vampire2.setPos(randX2, 251, randZ2);
                    level.addFreshEntity(vampire3);
                    vampire3.setPos(randX3, 251, randZ3);
                }
                if(attractionMultiplier == 2){
                    VampireBaroness vampire = new VampireBaroness(level);
                    VampireBaroness vampire2 = new VampireBaroness(level);
                    level.addFreshEntity(vampire);
                    vampire.setPos(randX, 251, randZ);
                    level.addFreshEntity(vampire2);
                    vampire2.setPos(randX2, 251, randZ2);

                }
                if(attractionMultiplier == 1){
                    VampireBaroness vampire = new VampireBaroness(level);
                    level.addFreshEntity(vampire);
                    vampire.setPos(randX, 251, randZ);

                }
            }
        }
    }

}

