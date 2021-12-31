package com.tm.krayscandles.init;

import com.tm.krayscandles.block.BlockSoybeanCrop;
import com.tm.krayscandles.block.base.BlockCropBase;
import com.tm.krayscandles.block.base.BlockItemBase;
import com.tm.krayscandles.block.candle.BlockCandleSoy;
import com.tm.krayscandles.item.base.ItemBase;
import com.tm.krayscandles.item.base.ItemFoodBase;
import com.tm.krayscandles.item.base.ItemSoulEssence;
import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * Handles setting up the Items and Blocks for the mod.
 */
public class InitItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KCReference.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KCReference.MOD_ID);

    /**
     * Called to initialize the Items and Blocks.
     */
    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //------ITEMS------\\


    //INGREDIENTS

    public static final RegistryObject<Item> WAX_CHUNK_SOY_RAW =             regItem("wax_chunk_soy_raw", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_SOY =                 regItem("wax_chunk_soy", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_SOY_SMALL =           regItem("wax_chunk_soy_small", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_BLESSED =             regItem("wax_chunk_blessed", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_BLESSED_SMALL =       regItem("wax_chunk_blessed_small", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_CURSED =              regItem("wax_chunk_cursed", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_CURSED_SMALL =        regItem("wax_chunk_cursed_small", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_PURGED =              regItem("wax_chunk_purged", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_PURGED_SMALL =        regItem("wax_chunk_purged_small", ItemBase::new);
    public static final RegistryObject<Item> WAX_CHUNK_BEE =                 regItem("wax_chunk_bee", ItemBase::new);
    public static final RegistryObject<Item> WAX_EAR =                       regItem("wax_ear", ItemBase::new);

    public static final RegistryObject<Item> CANDLE_WICK =                   regItem("candle_wick", ItemBase::new);

    public static final RegistryObject<Item> SOUL_ESSENCE_LESSER =           regItem("soul_essence_lesser", ItemSoulEssence::new);
    public static final RegistryObject<Item> SOUL_ESSENCE_GREATER_DEPLETED = regItem("soul_essence_greater_depleted", ItemBase::new);
    //public static final RegistryObject<Item> SOUL_ESSENCE_GREATER =          regItem("soul_essence_greater", () -> new ItemRitual(RitualRecipes.ESSENCE_GREATER));
    public static final RegistryObject<Item> SOUL_ESSENCE_BLESSED_DEPLETED = regItem("soul_essence_blessed_depleted", ItemBase::new);
    //public static final RegistryObject<Item> SOUL_ESSENCE_BLESSED =          regItem("soul_essence_blessed", () -> new ItemRitual(RitualRecipes.ESSENCE_BLESSED));

    public static final RegistryObject<Item> IRON_PRESS =                    regItem("iron_press", () -> new ItemBase().setTag("iron_press"));
    public static final RegistryObject<Item> IRON_PRESS_FULL =               regItem("iron_press_full", () -> new ItemBase().setTag("iron_press"));
    public static final RegistryObject<Item> IRON_PRESS_BLESSED =            regItem("iron_press_blessed", () -> new ItemBase().setTag("iron_press"));
    public static final RegistryObject<Item> IRON_PRESS_CURSED =             regItem("iron_press_cursed", () -> new ItemBase().setTag("iron_press"));
    public static final RegistryObject<Item> IRON_PRESS_PURGED =             regItem("iron_press_purged", () -> new ItemBase().setTag("iron_press"));

    public static final RegistryObject<Item> WAND_HILT =                     regItem("wand_hilt", ItemBase::new);
    public static final RegistryObject<Item> SCALPEL_HILT =                  regItem("scalpel_hilt", ItemBase::new);
    public static final RegistryObject<Item> SCALPEL_BLADE_DULL =            regItem("scalpel_blade_dull", ItemBase::new);
    public static final RegistryObject<Item> SCALPEL_BLADE_SHARP =           regItem("scalpel_blade_sharp", ItemBase::new);
    public static final RegistryObject<Item> NIGHT_SWORD_HILT =              regItem("night_sword_hilt", ItemBase::new);
    public static final RegistryObject<Item> NIGHT_SWORD_BLADE_DULL =        regItem("night_sword_blade_dull", ItemBase::new);
    public static final RegistryObject<Item> NIGHT_SWORD_BLADE_SHARP =       regItem("night_sword_blade_sharp", ItemBase::new);

    public static final RegistryObject<Item> STONE_MAN_HEAD =                regItem("stone_man_head", ItemBase::new);
    public static final RegistryObject<Item> STONE_MAN_BODY =                regItem("stone_man_body", ItemBase::new);
    public static final RegistryObject<Item> STONE_MAN_BASE =                regItem("stone_man_base", ItemBase::new);
    public static final RegistryObject<Item> STONE_STATUE_UNCARVED =         regItem("stone_statue_uncarved", ItemBase::new);
    public static final RegistryObject<Item> STONE_STATUE_CARVED =           regItem("stone_statue_carved", ItemBase::new);

    //public static final RegistryObject<Item> ZOMBIE_EAR =                    regItem("zombie_ear", ItemZombieEar::new);
    //public static final RegistryObject<Item> FLYING_BAT_EYEBALL =            regItem("flying_bat_eyeball", ItemBatEyeball::new);
    public static final RegistryObject<Item> Q_TIP =                         regItem("q_tip", ItemBase::new);
    public static final RegistryObject<Item> CLOTH_CURSED =                  regItem("cloth_cursed", ItemBase::new);
    public static final RegistryObject<Item> WINGS_WRAITH =                  regItem("wings_wraith", ItemBase::new);
    public static final RegistryObject<Item> WINGS_DIVINE =                  regItem("wings_divine", ItemBase::new);
    public static final RegistryObject<Item> GARLIC =                        regItem("garlic", ItemBase::new);

    //CROPS
    public static final RegistryObject<Item> SOYBEAN =                       regItem("soybean", () -> new ItemFoodBase(3, 1.2F));

    //RUNES
    public static final RegistryObject<Item> RUNE_CATALYST =                 regItem("rune_catalyst", ItemBase::new);
    //public static final RegistryObject<Item> RUNE_BLESSED_FLIGHT =           regItem("rune_blessed_flight", () -> new ItemRitual(RitualRecipes.RUNE_BLESSED));
    //public static final RegistryObject<Item> RUNE_CURSED_NIGHT =             regItem("rune_cursed_night", () -> new ItemRitual(RitualRecipes.RUNE_CURSED));
    //public static final RegistryObject<Item> RUNE_PURGED_LIGHT =             regItem("rune_purged_light", () -> new ItemRitual(RitualRecipes.RUNE_PURGED));
    //public static final RegistryObject<Item> RUNE_GREAT_FIRE =               regItem("rune_great_fire", () -> new ItemRitual(RitualRecipes.RUNE_FIRE));
    //public static final RegistryObject<Item> RUNE_GREAT_LEVITATION =         regItem("rune_great_levitation", () -> new ItemRitual(RitualRecipes.RUNE_LEVITATE));
    //public static final RegistryObject<Item> RUNE_ZEN_HEALING =              regItem("rune_zen_healing", () -> new ItemRitual(RitualRecipes.RUNE_ZEN));
    //public static final RegistryObject<Item> RUNE_GREAT_MAGIC =              regItem("rune_great_magic", () -> new ItemRitual(RitualRecipes.RUNE_INVIS));
    //public static final RegistryObject<Item> RUNE_GREAT_LUCK =               regItem("rune_great_luck", () -> new ItemRitual(RitualRecipes.RUNE_LUCK));
    //public static final RegistryObject<Item> RUNE_GREAT_ENERGY =             regItem("rune_great_energy", () -> new ItemRitual(RitualRecipes.RUNE_ENERGY));
    //public static final RegistryObject<Item> RUNE_GREAT_MINING =             regItem("rune_great_mining", () -> new ItemRitual(RitualRecipes.RUNE_MINING));
    //public static final RegistryObject<Item> RUNE_NULL =                     regItem("rune_null", () -> new ItemRitual(RitualRecipes.RUNE_NULL));


    //WANDS
    //public static final RegistryObject<Item> WAND_BASIC =                    regItem("wand_basic", ItemWandBasic::new);
    //public static final RegistryObject<Item> WAND_ENERGY =                   regItem("wand_energy", ItemWandEnergy::new);
    //public static final RegistryObject<Item> WAND_MINING =                   regItem("wand_mining", ItemWandMine::new);
    //public static final RegistryObject<Item> WAND_MAGICIAN =                 regItem("wand_magician", ItemWandMagician::new);
    //public static final RegistryObject<Item> WAND_LEVITATION =               regItem("wand_levitation", ItemWandLevitation::new);
    //public static final RegistryObject<Item> WAND_BLESSED_LIGHT =            regItem("wand_blessed_light", ItemWandBlessedLight::new);

    //WEAPONS
    //public static final RegistryObject<Item> SCALPEL =                  regItem("scalpel", () -> new ItemMagicSword(KCSwordTiers.SCALPEL));
    //public static final RegistryObject<Item> WAX_SWORD =                regItem("wax_sword", () -> new ItemMagicSword(KCSwordTiers.WAX));
    //public static final RegistryObject<Item> BLADE_NIGHT =              regItem("blade_night", ItemNightSword::new);

    //ARMOR
    //public static final RegistryObject<Item> HELMET_BLESSED_NIGHT =     regItem("helmet_blessed_night", ItemBlessedNightHelmet::new);

    //RITUAL NOTES
    public static final RegistryObject<Item> RITUAL_NOTE_ESSENCE =      regItem("ritual_note_essence", ItemBase::new);
    public static final RegistryObject<Item> RITUAL_NOTE_RUNE =         regItem("ritual_note_rune", ItemBase::new);
    public static final RegistryObject<Item> RITUAL_NOTE_CANDLE =       regItem("ritual_note_candle", ItemBase::new);
    public static final RegistryObject<Item> RITUAL_NOTE_WAND =         regItem("ritual_note_wand", ItemBase::new);
    public static final RegistryObject<Item> RITUAL_NOTE_WRAITH =       regItem("ritual_note_wraith", ItemBase::new);

    //MUSIC DISCS
    //public static final RegistryObject<Item> MUSIC_DISC_CHUNK =         regItem("music_disc_chunk", () -> new ItemDiscChunk(InitSounds.MUSIC_DISC_CHUNK));



    //------BLOCKS------\\

    //CANDLES
    public static final RegistryObject<Block> CANDLE_SOY =                    regBlock("candle_soy", KraysCandles.TAB_CANDLE, BlockCandleSoy::new);
    //public static final RegistryObject<Block> CANDLE_SOY_COLORED =     BLOCKS.register("candle_soy_colored", BlockCandleSoyColored::new);
    //public static final RegistryObject<Item>  CANDLE_SOY_COLORED_ITEM =        regItem("candle_soy_colored", () -> new BlockCandleSoyColoredItem(CANDLE_SOY_COLORED.get()));

    //public static final RegistryObject<Block> CANDLE_BLESSED =                regBlock("candle_blessed", KraysCandles.TAB_CANDLE, BlockCandleBlessed::new);
    //public static final RegistryObject<Block> CANDLE_CURSED =                 regBlock("candle_cursed", KraysCandles.TAB_CANDLE, BlockCandleCursed::new);
    //public static final RegistryObject<Block> CANDLE_PURGED =                 regBlock("candle_purged", KraysCandles.TAB_CANDLE, BlockCandlePurged::new);
    //public static final RegistryObject<Block> CANDLE_FIRE =                   regBlock("candle_fire", KraysCandles.TAB_CANDLE, BlockCandleFire::new);
    //public static final RegistryObject<Block> CANDLE_LEVITATE =               regBlock("candle_levitate", KraysCandles.TAB_CANDLE, BlockCandleLevitate::new);
    //public static final RegistryObject<Block> CANDLE_ZEN =                    regBlock("candle_zen", KraysCandles.TAB_CANDLE, BlockCandleZen::new);
    //public static final RegistryObject<Block> CANDLE_INVIS =                  regBlock("candle_invis", KraysCandles.TAB_CANDLE, BlockCandleInvis::new);
    //public static final RegistryObject<Block> CANDLE_LUCK =                   regBlock("candle_luck", KraysCandles.TAB_CANDLE, BlockCandleLuck::new);
    //public static final RegistryObject<Block> CANDLE_ENERGY =                 regBlock("candle_energy", KraysCandles.TAB_CANDLE, BlockCandleEnergy::new);
    //public static final RegistryObject<Block> CANDLE_CAVERN =                 regBlock("candle_cavern", KraysCandles.TAB_CANDLE, BlockCandleCavern::new);
    //public static final RegistryObject<Block> CANDLE_NULL =                   regBlock("candle_null", KraysCandles.TAB_CANDLE, BlockCandleNull::new);
    //public static final RegistryObject<Block> CANDLE_WAX_BEE =                regBlock("candle_wax_bee", KraysCandles.TAB_CANDLE, BlockCandleWaxBee::new);
    //public static final RegistryObject<Block> CANDLE_WAX_EAR =                regBlock("candle_wax_ear", KraysCandles.TAB_CANDLE, BlockCandleWaxEar::new);

    //CANDLE HOLDERS
    //public static final RegistryObject<Block> CANDLE_SOY_MOUNT =              regBlock("candle_soy_mount", KraysCandles.TAB_CANDLE, BlockSoyCandleMount::new);

    //TRAPPED SOUL LANTERN
    //public static final RegistryObject<Block> LANTERN_SOUL_TRAPPED =          regBlock("lantern_soul_trapped", KraysCandles.TAB_MAIN, BlockLanternSoulTrapped::new);

    //RITUAL
    //public static final RegistryObject<Block> STONE_ALTAR_TILE =              regBlock("stone_altar_tile", KraysCandles.TAB_MAIN, BlockStoneAltarTile::new);
    //public static final RegistryObject<Block> STATUE =                        regBlock("statue", KraysCandles.TAB_MAIN, BlockStatue::new);

    //CROPS
    public static final RegistryObject<Block> SOYBEAN_CROP =                  regBlock("soybean_crop", KraysCandles.TAB_MAIN, BlockSoybeanCrop::new);

    //WAXED BLOCKS
    //public static final RegistryObject<Block> WAXED_RED_SAND =                regBlock("waxed_red_sand", KraysCandles.TAB_MAIN, BlockWaxed::new);
    //public static final RegistryObject<Block> WAXED_SAND =                    regBlock("waxed_sand", KraysCandles.TAB_MAIN, BlockWaxed::new);
    //public static final RegistryObject<Block> WAXED_GRAVEL =                  regBlock("waxed_gravel", KraysCandles.TAB_MAIN, BlockWaxed::new);*/


    /**
     * Used to register an Item.
     * @param name The name of the Item.
     * @param sup The Item class.
     */
    public static RegistryObject<Item> regItem(String name, final Supplier<? extends Item> sup) {
        return ITEMS.register(name, sup);
    }

    /**
     * Used to register a Block.
     * @param name The name of the Block.
     * @param tab The Creative Tab for the Block.
     * @param sup The Item class.
     */
    public static RegistryObject<Block> regBlock(String name, CreativeModeTab tab, final Supplier<? extends Block> sup) {
        RegistryObject<Block> registryBlock = BLOCKS.register(name, sup);
        RegistryObject<Item> registryItem = ITEMS.register(name, () -> new BlockItemBase(registryBlock.get(), tab));
        return registryBlock;
    }
}
