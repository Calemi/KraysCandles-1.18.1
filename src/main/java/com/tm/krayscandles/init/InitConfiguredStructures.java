package com.tm.krayscandles.init;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class InitConfiguredStructures {

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_VAMPIRE_HOUSE = InitStructures.VAMPIRE_MANOR.get().configured(new JigsawConfiguration(() -> PlainVillagePools.START, 0));

    public static void init() {

        LogHelper.log(KCReference.MOD_NAME, "Initializing Configured Structures");

        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(KCReference.MOD_ID, "configured_vampire_manor"), CONFIGURED_VAMPIRE_HOUSE);
    }
}
