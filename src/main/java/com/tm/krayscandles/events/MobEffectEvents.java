package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.entity.Cloud;
import com.tm.krayscandles.init.InitMobEffects;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobEffectEvents {

    @SubscribeEvent
    public void onEffectAdded(PotionEvent.PotionAddedEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (event.getPotionEffect().getEffect() == InitMobEffects.FLIGHT.get()) {

            if (entity.getVehicle() == null) {

                LogHelper.log(KCReference.MOD_NAME, "RIDE");

                Cloud cloud = new Cloud(entity);
                entity.getLevel().addFreshEntity(cloud);
                entity.startRiding(cloud, true);
            }
        }

        else {
            if (entity.getVehicle() instanceof Cloud cloud) {
                cloud.kill();
            }
        }
    }
}
