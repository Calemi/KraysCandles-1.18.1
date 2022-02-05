package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.entity.vampire.Vampire;
import com.tm.krayscandles.entity.vampire.VampireBaron;
import com.tm.krayscandles.entity.vampire.VampireBaroness;
import com.tm.krayscandles.entity.wraith.*;
import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.main.KCReference;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KCReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeEvent {

    @SubscribeEvent
    public static void initEntityAttributes(EntityAttributeCreationEvent event) {

        LogHelper.log(KCReference.MOD_NAME, "Registering Entity attributes.");

        event.put(InitEntityTypes.WRAITH_FIRE.get(), WraithFire.createAttributes().build());
        event.put(InitEntityTypes.WRAITH_WATER.get(), WraithWater.createAttributes().build());
        event.put(InitEntityTypes.WRAITH_AIR.get(), WraithAir.createAttributes().build());
        event.put(InitEntityTypes.WRAITH_EXPLOSION.get(), WraithExplosion.createAttributes().build());
        event.put(InitEntityTypes.WRAITH_MAGIC.get(), WraithMagic.createAttributes().build());
        event.put(InitEntityTypes.WRAITH_MOB.get(), WraithMob.createAttributes().build());
        event.put(InitEntityTypes.WRAITH_DAMNED.get(), WraithDamnedBoss.createAttributes().build());
        event.put(InitEntityTypes.VAMPIRE.get(), Vampire.createAttributes().build());
        event.put(InitEntityTypes.VAMPIRE_BARON.get(), VampireBaron.createAttributes().build());
        event.put(InitEntityTypes.VAMPIRE_BARONESS.get(), VampireBaroness.createAttributes().build());
    }
}
