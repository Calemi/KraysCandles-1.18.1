package com.tm.krayscandles.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class KCConfig {

    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static final CategoryWraiths wraiths = new CategoryWraiths(SERVER_BUILDER);
    public static final CategoryCandles candles = new CategoryCandles(SERVER_BUILDER);
    public static final CategoryVillages villages = new CategoryVillages(COMMON_BUILDER);

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

    public static class CategoryVillages {

        public final ForgeConfigSpec.ConfigValue<Integer> villageRitualsWeight;

        public CategoryVillages (ForgeConfigSpec.Builder builder) {

            builder.push("Villages");

            villageRitualsWeight = builder
                    .comment("Village Rituals Weight", "The higher the weight, the more they will spawn", "Set to 0 to disable.")
                    .defineInRange("villageRitualsWeight", 2, 0, 32);

            builder.pop();
        }
    }
}