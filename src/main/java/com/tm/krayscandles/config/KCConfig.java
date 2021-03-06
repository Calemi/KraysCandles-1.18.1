package com.tm.krayscandles.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class KCConfig {

    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static final CategoryWraiths wraiths = new CategoryWraiths(SERVER_BUILDER);
    public static final CategoryVampires vampires = new CategoryVampires(SERVER_BUILDER);
    public static final CategoryCandles candles = new CategoryCandles(SERVER_BUILDER);
    public static final CategoryWorldGen worldgen = new CategoryWorldGen(COMMON_BUILDER);

    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }

    public static class CategoryCandles {

        public final ForgeConfigSpec.ConfigValue<Integer> candleEffectRangeStart;
        public final ForgeConfigSpec.ConfigValue<Integer> candleEffectRangeAdd;

        public CategoryCandles (ForgeConfigSpec.Builder builder) {

            builder.push("Candles");

            candleEffectRangeStart = builder
                    .comment("Candle Effect Range Starting Value", "The starting radius of the Candle's square effect range (Without any Crystals attached).")
                    .defineInRange("candleEffectRangeStart", 8, 0, 64);

            candleEffectRangeAdd = builder
                    .comment("Candle Effect Range Add Value", "The amount to add to the Candle's square effect radius when an Amplifying Crystal is added.")
                    .defineInRange("candleEffectRangeAdd", 2, 0, 16);

            builder.pop();
        }
    }

    public static class CategoryWraiths {

        public final ForgeConfigSpec.ConfigValue<Boolean> spawnWraithOnPlayerDeath;
        public final ForgeConfigSpec.ConfigValue<Boolean> spawnWraithOnVillagerDeath;

        public CategoryWraiths (ForgeConfigSpec.Builder builder) {

            builder.push("Wraiths");

            spawnWraithOnPlayerDeath = builder
                    .comment("Spawn Wraith on Player Death", "Disabled to stop Wraiths from spawning when a Player dies.")
                    .define("spawnWraithOnPlayerDeath", true);

            spawnWraithOnVillagerDeath = builder
                    .comment("Spawn Wraith on Villager Death", "Disabled to stop Wraiths from spawning when a Villager dies.")
                    .define("spawnWraithOnVillagerDeath", true);

            builder.pop();
        }
    }

    public static class CategoryVampires {

        public final ForgeConfigSpec.ConfigValue<Integer> vampireReinforcementChance;

        public CategoryVampires (ForgeConfigSpec.Builder builder) {

            builder.push("Vampires");

            vampireReinforcementChance = builder
                    .comment("Vampire Reinforcement Percentage Chance", "The chance of reinforcement Vampires spawning when a Vampire Baron or Baroness dies.", "Note: this spawning cant loop.")
                    .defineInRange("vampireReinforcementChance", 30, 0, 100);

            builder.pop();
        }
    }

    public static class CategoryWorldGen {

        public final ForgeConfigSpec.ConfigValue<Integer> villageRitualsWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> villageCandleHouseWeight;

        public final ForgeConfigSpec.ConfigValue<Boolean> vampireManorGen;
        public final ForgeConfigSpec.ConfigValue<Integer> vampireManorAverageSpread;
        public final ForgeConfigSpec.ConfigValue<Integer> vampireManorMinSpread;

        public final ForgeConfigSpec.ConfigValue<Integer> candleHutAverageSpread;
        public final ForgeConfigSpec.ConfigValue<Integer> candleHutMinSpread;

        public CategoryWorldGen(ForgeConfigSpec.Builder builder) {

            builder.push("World Generation");

            villageRitualsWeight = builder
                    .comment("Village Rituals Weight", "The higher the weight, the more frequent they will spawn", "Set to 0 to disable.")
                    .defineInRange("villageRitualsWeight", 2, 0, 32);

            villageCandleHouseWeight = builder
                    .comment("Village Candle House Weight", "The higher the weight, the more frequent they will spawn", "Set to 0 to disable.")
                    .defineInRange("villageCandleHouseWeight", 6, 0, 32);

            vampireManorGen = builder
                    .comment("Vampire Manor Gen", "Disable this to prevent vampire manors from generating")
                    .define("vampireManorGen", true);

            vampireManorAverageSpread = builder
                    .comment("Vampire Manor Average Spread", "The amount of chunks on average to space the manor")
                    .defineInRange("vampireManorAverageSpread", 20, 1, 100);

            vampireManorMinSpread = builder
                    .comment("Vampire Manor Minimum Spread", "The minimum amount of chunks on to space the manor", "Note: this value must be smaller than the average!")
                    .defineInRange("vampireManorMinSpread", 15, 1, 100);

            candleHutAverageSpread = builder
                    .comment("Candle Maker Hut Average Spread", "The amount of chunks on average to space the candle maker huts")
                    .defineInRange("candleHutAverageSpread", 20, 1, 100);

            candleHutMinSpread = builder
                    .comment("Candle Maker Hut Minimum Spread", "The minimum amount of chunks to space the candle maker huts", "Note: this value must be smaller than the average!")
                    .defineInRange("candleHutMinSpread", 10, 1, 100);

            builder.pop();
        }
    }
}