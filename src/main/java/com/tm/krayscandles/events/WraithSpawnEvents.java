package com.tm.krayscandles.events;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.config.KCConfig;
import com.tm.krayscandles.entity.wraith.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WraithSpawnEvents {

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if ((KCConfig.wraiths.spawnWraithOnPlayerDeath.get() && entity instanceof Player) || (KCConfig.wraiths.spawnWraithOnVillagerDeath.get() && entity instanceof Villager)) {

            if (entity instanceof Villager && event.getSource().getDirectEntity() instanceof Zombie) {
                return;
            }

            Level level = entity.getLevel();
            Location location = new Location(entity);

            DamageSource source = event.getSource();

            String killedName = entity.getDisplayName().getString();
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();

            if (source.isFire()) {
                level.addFreshEntity(new WraithFire(location, killedName));
            }

            else if (source == DamageSource.DROWN) {
                level.addFreshEntity(new WraithWater(location, killedName));
            }

            else if (source == DamageSource.FALL || source == DamageSource.FALLING_BLOCK || source == DamageSource.ANVIL || source == DamageSource.FLY_INTO_WALL) {
                level.addFreshEntity(new WraithAir(location, killedName));
            }

            else if (source.isExplosion()) {
                level.addFreshEntity(new WraithExplosion(location, killedName));
            }

            else if (source.isMagic()) {
                level.addFreshEntity(new WraithMagic(location, killedName));
            }

            else if (source == DamageSource.DRAGON_BREATH || source == DamageSource.WITHER) {
                level.addFreshEntity(new WraithMob(location, killedName));
            }

            else if (event.getSource().getDirectEntity() instanceof Monster && !(event.getSource().getDirectEntity() instanceof Wraith)) {
                level.addFreshEntity(new WraithMob(location, killedName));
            }
        }
    }
}
