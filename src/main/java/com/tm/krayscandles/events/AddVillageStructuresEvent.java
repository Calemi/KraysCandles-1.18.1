package com.tm.krayscandles.events;

import com.tm.krayscandles.config.KCConfig;
import com.tm.krayscandles.init.InitVillagers;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AddVillageStructuresEvent {

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

        int ritualWeight = KCConfig.worldgen.villageRitualsWeight.get();
        int houseWeight = KCConfig.worldgen.villageCandleHouseWeight.get();

        if (ritualWeight > 0) {

            //Rituals
            InitVillagers.addVillageStructure(event, "village/plains/houses",         "village/plains/essence_ritual",  ritualWeight);
            InitVillagers.addVillageStructure(event, "village/desert/houses",         "village/desert/rune_ritual",     ritualWeight);
            InitVillagers.addVillageStructure(event, "village/taiga/houses",          "village/taiga/candle_ritual",    ritualWeight);
            InitVillagers.addVillageStructure(event, "village/savanna/houses",        "village/savanna/crystal_ritual", ritualWeight);
            InitVillagers.addVillageStructure(event, "village/snowy/houses",          "village/snowy/wand_ritual",      ritualWeight);
            InitVillagers.addVillageStructure(event, "village/plains/zombie/houses",  "village/zombie/wraith_ritual",   ritualWeight);
            InitVillagers.addVillageStructure(event, "village/desert/zombie/houses",  "village/zombie/wraith_ritual",   ritualWeight);
            InitVillagers.addVillageStructure(event, "village/taiga/zombie/houses",   "village/zombie/wraith_ritual",   ritualWeight);
            InitVillagers.addVillageStructure(event, "village/savanna/zombie/houses", "village/zombie/wraith_ritual",   ritualWeight);
            InitVillagers.addVillageStructure(event, "village/snowy/zombie/houses",   "village/zombie/wraith_ritual",   ritualWeight);
        }

        if (houseWeight > 0) {

            //Candle Houses
            InitVillagers.addVillageStructure(event, "village/plains/houses",         "village/plains/plains_candle_house",   houseWeight);
            InitVillagers.addVillageStructure(event, "village/desert/houses",         "village/desert/desert_candle_house",   houseWeight);
            InitVillagers.addVillageStructure(event, "village/taiga/houses",          "village/taiga/taiga_candle_house",     houseWeight);
            InitVillagers.addVillageStructure(event, "village/savanna/houses",        "village/savanna/savanna_candle_house", houseWeight);
            InitVillagers.addVillageStructure(event, "village/snowy/houses",          "village/snowy/snowy_candle_house",     houseWeight);
        }
    }
}
