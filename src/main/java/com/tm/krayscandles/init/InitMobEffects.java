package com.tm.krayscandles.init;

import com.tm.krayscandles.effect.FlightMobEffect;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KCReference.MOD_ID);

    public static final RegistryObject<MobEffect> FLIGHT = MOB_EFFECTS.register("flight", FlightMobEffect::new);
}
