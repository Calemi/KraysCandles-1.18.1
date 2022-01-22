package com.tm.krayscandles.events;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class MobEffectEvents {

    @SubscribeEvent
    public void onEffectAdded(LivingEvent event) {

        /*LivingEntity entity = event.getEntityLiving();

        if (entity.getEffect(InitMobEffects.FLIGHT.get()) != null) {

            List<Location> locations = new ArrayList<>();

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    locations.add(new Location(entity.getLevel(), entity.getBlockX() + x, entity.getBlockY() - 1, entity.getBlockZ() + z));
                }
            }

            boolean canPlace = true;

            if (entity instanceof Player player) {

                if (player.isCrouching()) {
                    canPlace = false;

                    for (Location location : locations) {

                        if (location.getBlock() == InitItems.CLOUD.get()) {
                            location.setBlockToAir();
                        }
                    }
                }
            }

            if (canPlace && entity.getLevel().getGameTime() % 5 == 0) {

                for (Location location : locations) {

                    if (location.isBlockValidForPlacing() || location.getBlock() == InitItems.CLOUD.get()) {
                        location.setBlock(InitItems.CLOUD.get().defaultBlockState());
                        location.level.scheduleTick(location.getBlockPos(), InitItems.CLOUD.get(), 20);
                    }
                }
            }
        }*/
    }
}
