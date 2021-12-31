package com.tm.krayscandles.init;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.main.KCReference;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Handles setting up the mod on the client and common side.
 */
public class InitSetup {

    /**
     * Used to register everything the mod needs on the client and server side.
     */
    public static void initCommon(final FMLCommonSetupEvent event) {
        LogHelper.log(KCReference.MOD_NAME, "Initializing Common-Side for " + KCReference.MOD_NAME);

        InitEvents.initCommon();
    }

    /**
     * Used to register everything the mod needs on the client side only.
     */
    public static void initClient(final FMLClientSetupEvent event) {
        LogHelper.log(KCReference.MOD_NAME, "Initializing Client-Side for " + KCReference.MOD_NAME);

        InitEvents.initClient();
        InitRenderLayers.init();
    }
}


