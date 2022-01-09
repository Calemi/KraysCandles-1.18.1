package com.tm.krayscandles.init;

import com.google.common.collect.Sets;
import com.tm.krayscandles.blockentity.BlockEntityCandleSoyMount;
import com.tm.krayscandles.blockentity.BlockEntityLanternSoulTrapped;
import com.tm.krayscandles.blockentity.BlockEntityStoneAltarTile;
import com.tm.krayscandles.blockentity.candle.*;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Handles setting up the Block Entities for the mod.
 */
public class InitBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, KCReference.MOD_ID);

    public static final RegistryObject<BlockEntityType<BlockEntityCandleSoy>> CANDLE_SOY = BLOCK_ENTITY_TYPES.register(
            "candle_soy", () -> new BlockEntityType<>(BlockEntityCandleSoy::new, Sets.newHashSet(InitItems.CANDLE_SOY.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleSoyColored>> CANDLE_SOY_COLORED = BLOCK_ENTITY_TYPES.register(
            "candle_soy_colored", () -> new BlockEntityType<>(BlockEntityCandleSoyColored::new, Sets.newHashSet(InitItems.CANDLE_SOY_COLORED.get(), InitItems.CANDLE_SOY_COLORED.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleBlessed>> CANDLE_BLESSED = BLOCK_ENTITY_TYPES.register(
            "candle_blessed", () -> new BlockEntityType<>(BlockEntityCandleBlessed::new, Sets.newHashSet(InitItems.CANDLE_BLESSED.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleCursed>> CANDLE_CURSED = BLOCK_ENTITY_TYPES.register(
            "candle_cursed", () -> new BlockEntityType<>(BlockEntityCandleCursed::new, Sets.newHashSet(InitItems.CANDLE_CURSED.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandlePurged>> CANDLE_PURGED = BLOCK_ENTITY_TYPES.register(
            "candle_purged", () -> new BlockEntityType<>(BlockEntityCandlePurged::new, Sets.newHashSet(InitItems.CANDLE_PURGED.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleFire>> CANDLE_FIRE = BLOCK_ENTITY_TYPES.register(
            "candle_fire", () -> new BlockEntityType<>(BlockEntityCandleFire::new, Sets.newHashSet(InitItems.CANDLE_FIRE.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleLevitate>> CANDLE_LEVITATE = BLOCK_ENTITY_TYPES.register(
            "candle_levitate", () -> new BlockEntityType<>(BlockEntityCandleLevitate::new, Sets.newHashSet(InitItems.CANDLE_LEVITATE.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleZen>> CANDLE_ZEN = BLOCK_ENTITY_TYPES.register(
            "candle_zen", () -> new BlockEntityType<>(BlockEntityCandleZen::new, Sets.newHashSet(InitItems.CANDLE_ZEN.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleInvis>> CANDLE_INVIS = BLOCK_ENTITY_TYPES.register(
            "candle_invis", () -> new BlockEntityType<>(BlockEntityCandleInvis::new, Sets.newHashSet(InitItems.CANDLE_INVIS.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleLuck>> CANDLE_LUCK = BLOCK_ENTITY_TYPES.register(
            "candle_luck", () -> new BlockEntityType<>(BlockEntityCandleLuck::new, Sets.newHashSet(InitItems.CANDLE_LUCK.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleEnergy>> CANDLE_ENERGY = BLOCK_ENTITY_TYPES.register(
            "candle_energy", () -> new BlockEntityType<>(BlockEntityCandleEnergy::new, Sets.newHashSet(InitItems.CANDLE_ENERGY.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleCavern>> CANDLE_CAVERN = BLOCK_ENTITY_TYPES.register(
            "candle_cavern", () -> new BlockEntityType<>(BlockEntityCandleCavern::new, Sets.newHashSet(InitItems.CANDLE_CAVERN.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleNull>> CANDLE_NULL = BLOCK_ENTITY_TYPES.register(
            "candle_null", () -> new BlockEntityType<>(BlockEntityCandleNull::new, Sets.newHashSet(InitItems.CANDLE_NULL.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleWaxBee>> CANDLE_WAX_BEE = BLOCK_ENTITY_TYPES.register(
            "candle_wax_bee", () -> new BlockEntityType<>(BlockEntityCandleWaxBee::new, Sets.newHashSet(InitItems.CANDLE_WAX_BEE.get()), null));
    public static final RegistryObject<BlockEntityType<BlockEntityCandleWaxEar>> CANDLE_WAX_EAR = BLOCK_ENTITY_TYPES.register(
            "candle_wax_ear", () -> new BlockEntityType<>(BlockEntityCandleWaxEar::new, Sets.newHashSet(InitItems.CANDLE_WAX_EAR.get()), null));

    public static final RegistryObject<BlockEntityType<BlockEntityCandleSoyMount>> CANDLE_SOY_MOUNT = BLOCK_ENTITY_TYPES.register(
            "candle_soy_mount", () -> new BlockEntityType<>(BlockEntityCandleSoyMount::new, Sets.newHashSet(InitItems.CANDLE_SOY_MOUNT.get()), null));

    public static final RegistryObject<BlockEntityType<BlockEntityLanternSoulTrapped>> LANTERN_SOUL_TRAPPED = BLOCK_ENTITY_TYPES.register(
            "lantern_soul_trapped", () -> new BlockEntityType<>(BlockEntityLanternSoulTrapped::new, Sets.newHashSet(InitItems.LANTERN_SOUL_TRAPPED.get()), null));

    public static final RegistryObject<BlockEntityType<BlockEntityStoneAltarTile>> STONE_ALTAR_TILE = BLOCK_ENTITY_TYPES.register(
            "stone_alter_tile", () -> new BlockEntityType<>(BlockEntityStoneAltarTile::new, Sets.newHashSet(InitItems.STONE_ALTAR_TILE.get()), null));
}
