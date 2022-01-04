package com.tm.krayscandles.init;

import com.google.common.collect.Sets;
import com.tm.krayscandles.blockentity.BlockEntityStoneAltarTile;
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

    //public static final RegistryObject<TileEntityType<TileEntityCandleSoy>> IRON_CHEST = TILE_ENTITY_TYPES.register(
            //"iron_chest", () -> new BlockEntityType<>(TileEntityCandleSoy::new, Sets.newHashSet(InitItems.CANDLE_SOY.get()), null));

    //public static final RegistryObject<TileEntityType<TileEntityCandleSoy>> CANDLE_SOY = TILE_ENTITY_TYPES.register(
            //"candle_soy", () -> new BlockEntityType<>(TileEntityCandleSoy::new, Sets.newHashSet(InitItems.CANDLE_SOY.get(), InitItems.CANDLE_SOY_COLORED.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleBlessed>> CANDLE_BLESSED = TILE_ENTITY_TYPES.register(
            //"candle_blessed", () -> new BlockEntityType<>(TileEntityCandleBlessed::new, Sets.newHashSet(InitItems.CANDLE_BLESSED.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleCursed>> CANDLE_CURSED = TILE_ENTITY_TYPES.register(
            //"candle_cursed", () -> new BlockEntityType<>(TileEntityCandleCursed::new, Sets.newHashSet(InitItems.CANDLE_CURSED.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandlePurged>> CANDLE_PURGED = TILE_ENTITY_TYPES.register(
            //"candle_purged", () -> new BlockEntityType<>(TileEntityCandlePurged::new, Sets.newHashSet(InitItems.CANDLE_PURGED.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleFire>> CANDLE_FIRE = TILE_ENTITY_TYPES.register(
            //"candle_fire", () -> new BlockEntityType<>(TileEntityCandleFire::new, Sets.newHashSet(InitItems.CANDLE_FIRE.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleLevitate>> CANDLE_LEVITATE = TILE_ENTITY_TYPES.register(
            //"candle_levitate", () -> new BlockEntityType<>(TileEntityCandleLevitate::new, Sets.newHashSet(InitItems.CANDLE_LEVITATE.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleZen>> CANDLE_ZEN = TILE_ENTITY_TYPES.register(
            //"candle_zen", () -> new BlockEntityType<>(TileEntityCandleZen::new, Sets.newHashSet(InitItems.CANDLE_ZEN.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleInvis>> CANDLE_INVIS = TILE_ENTITY_TYPES.register(
            //"candle_invis", () -> new BlockEntityType<>(TileEntityCandleInvis::new, Sets.newHashSet(InitItems.CANDLE_INVIS.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleLuck>> CANDLE_LUCK = TILE_ENTITY_TYPES.register(
            //"candle_luck", () -> new BlockEntityType<>(TileEntityCandleLuck::new, Sets.newHashSet(InitItems.CANDLE_LUCK.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleEnergy>> CANDLE_ENERGY = TILE_ENTITY_TYPES.register(
            //"candle_energy", () -> new BlockEntityType<>(TileEntityCandleEnergy::new, Sets.newHashSet(InitItems.CANDLE_ENERGY.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleCavern>> CANDLE_CAVERN = TILE_ENTITY_TYPES.register(
            //"candle_cavern", () -> new BlockEntityType<>(TileEntityCandleCavern::new, Sets.newHashSet(InitItems.CANDLE_CAVERN.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleNull>> CANDLE_NULL = TILE_ENTITY_TYPES.register(
            //"candle_null", () -> new BlockEntityType<>(TileEntityCandleNull::new, Sets.newHashSet(InitItems.CANDLE_NULL.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleWaxBee>> CANDLE_WAX_BEE = TILE_ENTITY_TYPES.register(
            //"candle_wax_bee", () -> new BlockEntityType<>(TileEntityCandleWaxBee::new, Sets.newHashSet(InitItems.CANDLE_WAX_BEE.get()), null));
    //public static final RegistryObject<TileEntityType<TileEntityCandleWaxEar>> CANDLE_WAX_EAR = TILE_ENTITY_TYPES.register(
            //"candle_wax_ear", () -> new BlockEntityType<>(TileEntityCandleWaxEar::new, Sets.newHashSet(InitItems.CANDLE_WAX_EAR.get()), null));

    //public static final RegistryObject<TileEntityType<TileEntityCandleMount>> CANDLE_SOY_MOUNT = TILE_ENTITY_TYPES.register(
            //"candle_soy_mount", () -> new BlockEntityType<>(TileEntityCandleMount::new, Sets.newHashSet(InitItems.CANDLE_SOY_MOUNT.get()), null));

    //public static final RegistryObject<TileEntityType<TileEntityLanternSoulTrapped>> LANTERN_SOUL_TRAPPED = TILE_ENTITY_TYPES.register(
            //"lantern_soul_trapped", () -> new BlockEntityType<>(TileEntityLanternSoulTrapped::new, Sets.newHashSet(InitItems.LANTERN_SOUL_TRAPPED.get()), null));

    public static final RegistryObject<BlockEntityType<BlockEntityStoneAltarTile>> STONE_ALTAR_TILE = BLOCK_ENTITY_TYPES.register(
            "stone_alter_tile", () -> new BlockEntityType<>(BlockEntityStoneAltarTile::new, Sets.newHashSet(InitItems.STONE_ALTAR_TILE.get()), null));
}
