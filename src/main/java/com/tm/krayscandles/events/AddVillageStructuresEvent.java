package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.config.KCConfig;
import com.tm.krayscandles.init.InitVillagers;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.data.worldgen.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;

public class AddVillageStructuresEvent {

    public static void onParallelDispatch(ParallelDispatchEvent event) {

        LogHelper.log(KCReference.MOD_NAME, "ParallelDispatchEvent called");

        event.enqueueWork(AddVillageStructuresEvent::addVillageStructures);
    }

    private static void addVillageStructures() {

        LogHelper.log(KCReference.MOD_NAME, "Adding Village Structures");

        int ritualWeight = KCConfig.worldgen.villageRitualsWeight.get();
        int houseWeight = KCConfig.worldgen.villageCandleHouseWeight.get();

        PlainVillagePools.bootstrap();
        SnowyVillagePools.bootstrap();
        SavannaVillagePools.bootstrap();
        DesertVillagePools.bootstrap();
        TaigaVillagePools.bootstrap();

        if (ritualWeight > 0) {

            //Rituals
            InitVillagers.addVillageStructure("plains", "essence_ritual", ritualWeight);
            InitVillagers.addVillageStructure("desert", "rune_ritual", ritualWeight);
            InitVillagers.addVillageStructure("taiga", "candle_ritual", ritualWeight);
            InitVillagers.addVillageStructure("savanna", "crystal_ritual", ritualWeight);
            InitVillagers.addVillageStructure("snowy", "wand_ritual", ritualWeight);
            InitVillagers.addVillageStructure("plains/zombie", "wraith_ritual", ritualWeight);
        }

        if (houseWeight > 0) {

            //Candle Houses
            InitVillagers.addVillageStructure("plains", "plains_candle_house", ritualWeight);
            InitVillagers.addVillageStructure("desert", "desert_candle_house", ritualWeight);
            InitVillagers.addVillageStructure("taiga", "taiga_candle_house", ritualWeight);
            InitVillagers.addVillageStructure("savanna", "savanna_candle_house", ritualWeight);
            InitVillagers.addVillageStructure("snowy", "snowy_candle_house", ritualWeight);
        }
    }
}
