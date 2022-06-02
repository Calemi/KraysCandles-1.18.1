package com.tm.krayscandles.events;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.*;
import com.tm.krayscandles.config.KCConfig;
import com.tm.krayscandles.entity.vampire.VampireBase;
import com.tm.krayscandles.entity.vampire.VampireBaron;
import com.tm.krayscandles.entity.vampire.VampireBaroness;
import com.tm.krayscandles.entity.vampire.VampireCount;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitSounds;
import com.tm.krayscandles.main.KCReference;
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
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
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

                        VampireCount vampire = new VampireCount(new Location(level, randX, 251, randZ));
                        level.addFreshEntity(vampire);
                        ChatHelper.broadcastMessage(level, new TranslatableComponent("chat.vampire").withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC).append(" [" + randX + ", " + randZ + "]!"));
                        SoundHelper.playGlobal(level, SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 1, 1);
                    }
                }
            }
        }

        else canSpawnToday = true;
    }

    @SubscribeEvent
    public void onVampireGarlicHit(LivingDamageEvent event) {

        if (event.getSource().getDirectEntity() instanceof Player player) {

            ItemStack offItem = player.getOffhandItem();

            if (event.getEntity() instanceof VampireBase vampire) {

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
    public void onVampireSpawn(LivingSpawnEvent.SpecialSpawn event) {

        if (event.getEntity() instanceof VampireBase vampire) {
            setRandomVampireName(vampire);
        }
    }

    private void setRandomVampireName(VampireBase vampireBase) {

        if (vampireBase instanceof VampireCount count) {
            count.getEntityData().set(VampireBase.VAMPIRE_NAME, count.getRandomName());
        }

        else if (vampireBase instanceof VampireBaroness baroness) {
            baroness.getEntityData().set(VampireBaroness.VAMPIRE_NAME, baroness.getRandomName());
        }

        else if (vampireBase instanceof VampireBaron baron) {
            baron.getEntityData().set(VampireBase.VAMPIRE_NAME, baron.getRandomName());
        }
    }

    @SubscribeEvent
    public void onVampireDeath(LivingDeathEvent event) {

        if (event.getSource().getDirectEntity() instanceof Player player) {

            Level level = player.getLevel();

            if (event.getEntity() instanceof VampireBaroness baroness) {

                if (!baroness.isReinforcement()) {

                    checkCallForReinforcements(level, baroness, (location) -> {
                        VampireBaroness newBaroness = new VampireBaroness(location);
                        location.level.addFreshEntity(newBaroness);
                        setRandomVampireName(newBaroness);
                        return newBaroness;
                    });
                }
            }

            else if (event.getEntity() instanceof VampireBaron baron) {

                if (!baron.isReinforcement()) {
                    checkCallForReinforcements(level, baron, (location) -> {
                        VampireBaron newBaron = new VampireBaron(location);
                        location.level.addFreshEntity(newBaron);
                        setRandomVampireName(newBaron);
                        return newBaron;
                    });
                }
            }
        }
    }

    private void checkCallForReinforcements(Level level, VampireBase hurtVampire, VampireSpawnListener spawnedVampire) {

        LogHelper.log(KCReference.MOD_NAME, "CHECKING FOR REINFORCEMENTS");

        Random rand = level.getRandom();

        if (MathHelper.rollChance(KCConfig.vampires.vampireReinforcementChance.get())) {

            LogHelper.log(KCReference.MOD_NAME, "ROLLED");

            for (int i = 0; i < 3; i++) {

                int randX = hurtVampire.getBlockX() + rand.nextInt(10) - 5;
                int randY = hurtVampire.getBlockY();
                int randZ = hurtVampire.getBlockZ() + rand.nextInt(10) - 5;

                Location location = new Location(level, randX, randY, randZ);

                if (location.isAirBlock()) {

                    VampireBaron vampire = spawnedVampire.spawnVampire(location);
                    vampire.setPos(randX, randY, randZ);
                    vampire.setReinforcement();

                    LogHelper.log(KCReference.MOD_NAME, "SPAWNED");
                }
            }
        }
    }

    interface VampireSpawnListener {
        VampireBaron spawnVampire(Location location);
    }
}

