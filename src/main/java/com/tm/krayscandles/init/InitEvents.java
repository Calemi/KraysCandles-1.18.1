package com.tm.krayscandles.init;

import com.tm.krayscandles.events.EntityDropEvents;
import net.minecraftforge.common.MinecraftForge;

/**
 * Handles setting up the events for the mod.
 */
public class InitEvents {

    /**
     * Called to initialize common events.
     */
    public static void initCommon() {
        MinecraftForge.EVENT_BUS.register(new EntityDropEvents());
    }

    /**
     * Called to initialize client events.
     */
    public static void initClient() {

    }
}
