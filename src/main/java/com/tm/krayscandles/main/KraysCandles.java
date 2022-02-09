package com.tm.krayscandles.main;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.mojang.serialization.Codec;
import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.config.KCConfig;
import com.tm.krayscandles.init.*;
import com.tm.krayscandles.structures.StructureVampireManor;
import com.tm.krayscandles.tab.KCCandleTab;
import com.tm.krayscandles.tab.KCMainTab;
import com.tm.krayscandles.tab.KCToolTab;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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

        InitStructures.STRUCTURES.register(MOD_EVENT_BUS);
        MOD_EVENT_BUS.addListener(InitSetup::initCommon);
        MOD_EVENT_BUS.addListener(InitSetup::initClient);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        forgeBus.addListener(EventPriority.NORMAL, StructureVampireManor::setupStructureSpawns);

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

    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerLevel serverLevel){
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();

            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
                return;
            }

            StructureSettings worldStructureConfig = chunkGenerator.getSettings();

            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> KCStructureToMultiMap = new HashMap<>();

            for (Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {

                Biome.BiomeCategory biomeCategory = biomeEntry.getValue().getBiomeCategory();

                if (biomeCategory != Biome.BiomeCategory.OCEAN && biomeCategory != Biome.BiomeCategory.THEEND && biomeCategory != Biome.BiomeCategory.NETHER && biomeCategory != Biome.BiomeCategory.NONE && biomeCategory != Biome.BiomeCategory.BEACH && biomeCategory != Biome.BiomeCategory.DESERT
                        && biomeCategory != Biome.BiomeCategory.EXTREME_HILLS && biomeCategory != Biome.BiomeCategory.ICY && biomeCategory != Biome.BiomeCategory.JUNGLE && biomeCategory != Biome.BiomeCategory.MESA && biomeCategory != Biome.BiomeCategory.MOUNTAIN
                        && biomeCategory != Biome.BiomeCategory.MUSHROOM && biomeCategory != Biome.BiomeCategory.PLAINS && biomeCategory != Biome.BiomeCategory.RIVER && biomeCategory != Biome.BiomeCategory.SAVANNA && biomeCategory != Biome.BiomeCategory.SWAMP && biomeCategory != Biome.BiomeCategory.TAIGA) {
                    associateBiomeToConfiguredStructure(KCStructureToMultiMap, InitConfiguredStructures.CONFIGURED_VAMPIRE_HOUSE, biomeEntry.getKey());
                }
            }

            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
            worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !KCStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

            KCStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();

            try {
                if (GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch (Exception e){
                LogHelper.log(KCReference.MOD_NAME, "Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureConfig.structureConfig());
            tempMap.putIfAbsent(InitStructures.VAMPIRE_MANOR.get(), StructureSettings.DEFAULTS.get(InitStructures.VAMPIRE_MANOR.get()));
            worldStructureConfig.structureConfig = tempMap;
        }
    }

    /**
     * Helper method that handles setting up the map to multimap relationship to help prevent issues.
     */
    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {

        STStructureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = STStructureToMultiMap.get(configuredStructureFeature.feature);

        if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
            LogHelper.log(KCReference.MOD_NAME,
                    "Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. " +
                            "One will be prevented from spawning. " +
                            "This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure. The two conflicting ConfiguredStructures are: "
                            + BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature) + ", "
                            + BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey)
                            .findFirst().get().getKey()) + "The biome that is attempting to be shared: " + biomeRegistryKey
            );
        }
        else{
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
    }
}
