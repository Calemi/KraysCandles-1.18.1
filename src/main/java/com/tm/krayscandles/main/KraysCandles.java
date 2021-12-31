package com.tm.krayscandles.main;

import com.tm.krayscandles.init.InitSetup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * The main class for Kray's Magic Candles.
 */
@Mod(KCReference.MOD_ID)
public class KraysCandles {

    /**
     * A reference to the instance of the mod.
     */
    public static KraysCandles instance;

    /**
     * Used to register the client and common setup methods.
     */
    public static IEventBus MOD_EVENT_BUS;

    /**
     * Everything starts here.
     */
    public KraysCandles() {

        //Initializes the instance.
        instance = this;

        //Registers the client and common setup methods.
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.addListener(InitSetup::initCommon);
        MOD_EVENT_BUS.addListener(InitSetup::initClient);
    }
}
