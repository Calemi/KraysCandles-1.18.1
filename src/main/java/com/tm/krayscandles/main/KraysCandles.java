package com.tm.krayscandles.main;

import com.tm.krayscandles.config.KCConfig;
import com.tm.krayscandles.init.*;
import com.tm.krayscandles.tab.KCCandleTab;
import com.tm.krayscandles.tab.KCMainTab;
import com.tm.krayscandles.tab.KCToolTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

    public static final CreativeModeTab TAB_MAIN = new KCMainTab();
    public static final CreativeModeTab TAB_CANDLE = new KCCandleTab();
    public static final CreativeModeTab TAB_TOOL = new KCToolTab();

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

        InitItems.init();
        InitSounds.SOUNDS.register(MOD_EVENT_BUS);
        InitMobEffects.MOB_EFFECTS.register(MOD_EVENT_BUS);
        InitBlockEntityTypes.BLOCK_ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitRecipes.RECIPES.register(MOD_EVENT_BUS);
        InitEntityTypes.ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitVillagers.POI_TYPES.register(MOD_EVENT_BUS);
        InitVillagers.VILLAGER_PROFESSIONS.register(MOD_EVENT_BUS);
        InitParticles.PARTICLES.register(MOD_EVENT_BUS);
        KCConfig.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting (ServerStartingEvent event) {

        if (KCConfig.villages.villageRitualsWeight.get() > 0) {

            //Rituals
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/plains/houses", new ResourceLocation(KCReference.MOD_ID, "village/plains/essence_ritual"), KCConfig.villages.villageRitualsWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/desert/houses", new ResourceLocation(KCReference.MOD_ID, "village/desert/rune_ritual"), KCConfig.villages.villageRitualsWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/taiga/houses", new ResourceLocation(KCReference.MOD_ID, "village/taiga/candle_ritual"), KCConfig.villages.villageRitualsWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/savanna/houses", new ResourceLocation(KCReference.MOD_ID, "village/savanna/crystal_ritual"), KCConfig.villages.villageRitualsWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/snowy/houses", new ResourceLocation(KCReference.MOD_ID, "village/snowy/wand_ritual"), KCConfig.villages.villageRitualsWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/plains/zombie/houses", new ResourceLocation(KCReference.MOD_ID, "village/zombie/wraith_ritual"), KCConfig.villages.villageRitualsWeight.get());

            //Candle Houses
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/plains/houses", new ResourceLocation(KCReference.MOD_ID, "village/plains/plains_candle_house"), KCConfig.villages.villageCandleHouseWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/desert/houses", new ResourceLocation(KCReference.MOD_ID, "village/desert/desert_candle_house"), KCConfig.villages.villageCandleHouseWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/taiga/houses", new ResourceLocation(KCReference.MOD_ID, "village/taiga/taiga_candle_house"), KCConfig.villages.villageCandleHouseWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/savanna/houses", new ResourceLocation(KCReference.MOD_ID, "village/savanna/savanna_candle_house"), KCConfig.villages.villageCandleHouseWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/snowy/houses", new ResourceLocation(KCReference.MOD_ID, "village/desert/plains_candle_house"), KCConfig.villages.villageCandleHouseWeight.get());
            InitVillagers.addVillageStructure(event.getServer().registryAccess(), "village/plains/zombie/houses", new ResourceLocation(KCReference.MOD_ID, "village/desert/plains_candle_house"), KCConfig.villages.villageCandleHouseWeight.get());
        }
    }
}
