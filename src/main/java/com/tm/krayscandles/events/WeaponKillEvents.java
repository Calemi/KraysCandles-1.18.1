package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.calemicore.util.helper.MathHelper;
import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.soul.Soul;
import com.tm.krayscandles.util.helper.SoulHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WeaponKillEvents {

    @SubscribeEvent
    public void onScalpelKill(AttackEntityEvent event) {

        ItemStack heldItem = event.getEntityLiving().getMainHandItem();
        Entity entity = event.getTarget();

        if (entity instanceof Zombie zombie) {

            if (heldItem.getItem() == InitItems.SCALPEL.get()) {

                if (!zombie.isSilent()) {

                    zombie.spawnAtLocation(InitItems.ZOMBIE_EAR.get());
                    zombie.spawnAtLocation(InitItems.ZOMBIE_EAR.get());
                    zombie.setSilent(true);
                }
            }
        } else if (entity instanceof Bat bat) {

            if (heldItem.getItem() == InitItems.SCALPEL.get()) {

                if (!bat.isSilent()) {

                    bat.spawnAtLocation(InitItems.BAT_EYEBALL.get());
                    bat.setSilent(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onBladeOfCursedNightKill(LivingDeathEvent event) {

        if (event.getSource().getDirectEntity() instanceof Player player) {

            ItemStack heldItem = player.getMainHandItem();
            ItemStack offItem = player.getOffhandItem();

            if (event.getEntity() instanceof LivingEntity killedEntity) {

                if (heldItem.getItem() == InitItems.BLADE_NIGHT.get()) {

                    if (!(killedEntity instanceof Player)) {

                        int lootingLevel = EnchantmentHelper.getMobLooting(player);

                        if (MathHelper.rollChance(25 + (lootingLevel * 25))) {
                            killedEntity.spawnAtLocation(InitItems.SOUL_ESSENCE_LESSER.get());
                        }

                        killedEntity.spawnAtLocation(InitItems.SOUL_ESSENCE_LESSER.get());
                    }

                    MobEffectHelper.addMobEffect(MobEffects.BLINDNESS, 300, 0);
                    killedEntity.playSound(SoundEvents.AMBIENT_CAVE, 1, 1);

                    if (offItem.getItem() == Items.SOUL_LANTERN) {

                        ItemStack lanternSoulTrapped = new ItemStack(InitItems.LANTERN_SOUL_TRAPPED.get().asItem());
                        SoulHelper.setSoulStack(lanternSoulTrapped, new Soul(killedEntity.getType()));
                        offItem.shrink(1);
                        ItemHelper.spawnStackAtEntity(player.level, player, lanternSoulTrapped);

                        SoundHelper.playAtPlayer(player, SoundEvents.AMBIENT_CAVE, 1, 1);
                        SoundHelper.playAtPlayer(player, SoundEvents.GHAST_DEATH, 1, -7);
                    }
                }

                else if (event.getEntity().getLevel().getRandom().nextInt(5) == 0) {
                    if (!(killedEntity instanceof Player)) killedEntity.spawnAtLocation(InitItems.SOUL_ESSENCE_LESSER.get());
                }
            }
        }
    }
}
