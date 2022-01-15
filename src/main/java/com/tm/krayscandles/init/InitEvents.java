package com.tm.krayscandles.init;

import com.tm.krayscandles.events.*;
import net.minecraftforge.common.MinecraftForge;

/**
 * Handles setting up the events for the mod.
 */
public class InitEvents {

    /**
     * Called to initialize common events.
     */
    public static void initCommon() {
        MinecraftForge.EVENT_BUS.register(new RecipeEvents());
        MinecraftForge.EVENT_BUS.register(new RitualEvent());
        MinecraftForge.EVENT_BUS.register(new WeaponKillEvents());
        MinecraftForge.EVENT_BUS.register(new WraithSpawnEvents());
        MinecraftForge.EVENT_BUS.register(new EntityDropEvents());
        MinecraftForge.EVENT_BUS.register(new VampireEvents());
        MinecraftForge.EVENT_BUS.register(new BlockDropEvents());
        MinecraftForge.EVENT_BUS.register(new MobEffectEvents());
    }

    /**
     * Called to initialize client events.
     */
    public static void initClient() {
        MinecraftForge.EVENT_BUS.register(new LoreEvents());
    }
}
