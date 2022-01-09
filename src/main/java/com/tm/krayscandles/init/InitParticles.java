package com.tm.krayscandles.init;

import com.tm.krayscandles.main.KCReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = KCReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, KCReference.MOD_ID);

    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_NORMAL = PARTICLES.register("soul_flame_normal", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_FIRE = PARTICLES.register("soul_flame_fire", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_WATER = PARTICLES.register("soul_flame_water", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_AIR = PARTICLES.register("soul_flame_air", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_EXPLOSION = PARTICLES.register("soul_flame_explosion", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_MAGIC = PARTICLES.register("soul_flame_magic", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_MOB = PARTICLES.register("soul_flame_mob", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SOUL_FLAME_DAMNED = PARTICLES.register("soul_flame_damned", () -> new SimpleParticleType(true));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_NORMAL.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_FIRE.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_WATER.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_AIR.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_EXPLOSION.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_MAGIC.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_MOB.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SOUL_FLAME_DAMNED.get(), FlameParticle.Provider::new);
    }
}
