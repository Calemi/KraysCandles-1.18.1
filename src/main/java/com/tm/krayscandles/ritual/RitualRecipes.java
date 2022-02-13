package com.tm.krayscandles.ritual;

import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class RitualRecipes {

    public static List<RitualRecipe> allRitualRecipes = new ArrayList<>();

    public static final RitualRecipe ESSENCE_GREATER = new RitualRecipe(RitualStructureTypes.ESSENCE);
    public static final RitualRecipe ESSENCE_BLESSED = new RitualRecipe(RitualStructureTypes.ESSENCE);

    public static final RitualRecipe RUNE_BASE_GREATER = new RitualRecipe(RitualStructureTypes.RUNE, true);
    public static final RitualRecipe RUNE_BASE_BLESSED = new RitualRecipe(RitualStructureTypes.RUNE, true);
    public static final RitualRecipe RUNE_BLESSED = new RitualRecipe();
    public static final RitualRecipe RUNE_CURSED = new RitualRecipe();
    public static final RitualRecipe RUNE_PURGED = new RitualRecipe();
    public static final RitualRecipe RUNE_FIRE = new RitualRecipe();
    public static final RitualRecipe RUNE_LEVITATE = new RitualRecipe();
    public static final RitualRecipe RUNE_ZEN = new RitualRecipe();
    public static final RitualRecipe RUNE_INVIS = new RitualRecipe();
    public static final RitualRecipe RUNE_LUCK = new RitualRecipe();
    public static final RitualRecipe RUNE_ENERGY = new RitualRecipe();
    public static final RitualRecipe RUNE_MINING = new RitualRecipe();
    public static final RitualRecipe RUNE_NULL = new RitualRecipe();

    public static final RitualRecipe CANDLE_BASE = new RitualRecipe(RitualStructureTypes.CANDLE, true);
    public static final RitualRecipe CANDLE_BLESSED = new RitualRecipe();
    public static final RitualRecipe CANDLE_CURSED = new RitualRecipe();
    public static final RitualRecipe CANDLE_PURGED = new RitualRecipe();
    public static final RitualRecipe CANDLE_FIRE = new RitualRecipe();
    public static final RitualRecipe CANDLE_LEVITATE = new RitualRecipe();
    public static final RitualRecipe CANDLE_ZEN = new RitualRecipe();
    public static final RitualRecipe CANDLE_INVIS = new RitualRecipe();
    public static final RitualRecipe CANDLE_LUCK = new RitualRecipe();
    public static final RitualRecipe CANDLE_ENERGY = new RitualRecipe();
    public static final RitualRecipe CANDLE_MINING = new RitualRecipe();
    public static final RitualRecipe CANDLE_NULL = new RitualRecipe();

    public static final RitualRecipe CRYSTAL_BASE = new RitualRecipe(RitualStructureTypes.CRYSTAL, true);
    public static final RitualRecipe CRYSTAL_AMPLIFYING = new RitualRecipe();
    public static final RitualRecipe CRYSTAL_POTENCY = new RitualRecipe();
    public static final RitualRecipe CRYSTAL_INVERTING = new RitualRecipe();

    public static final RitualRecipe WAND_BASE = new RitualRecipe(RitualStructureTypes.WAND, true);
    public static final RitualRecipe WAND_FIRE = new RitualRecipe();
    public static final RitualRecipe WAND_ENERGY = new RitualRecipe();
    public static final RitualRecipe WAND_MINING = new RitualRecipe();
    public static final RitualRecipe WAND_INVIS = new RitualRecipe();
    public static final RitualRecipe WAND_LEVITATE = new RitualRecipe();
    public static final RitualRecipe WAND_BLESSED = new RitualRecipe();

    public static final RitualRecipe WRAITH = new RitualRecipe(RitualStructureTypes.WRAITH);

    public static final RitualRecipe ORB = new RitualRecipe(RitualStructureTypes.ORB);

    public static void init() {

        ESSENCE_GREATER.setHandItem(InitItems.SOUL_ESSENCE_GREATER_DEPLETED.get());
        ESSENCE_GREATER.setDropResult(InitItems.SOUL_ESSENCE_GREATER.get());
        ESSENCE_BLESSED.setHandItem(InitItems.SOUL_ESSENCE_BLESSED_DEPLETED.get());
        ESSENCE_BLESSED.setDropResult(InitItems.SOUL_ESSENCE_BLESSED.get());

        ORB.setHandItem(InitItems.ORB_DEPLETED.get());
        ORB.setDropResult(InitItems.ORB_CURSED.get());

        RUNE_BASE_GREATER.setHandItem(InitItems.RUNE_CATALYST.get());
        RUNE_BASE_GREATER.addIngredient(Items.DIAMOND);
        RUNE_BASE_GREATER.addIngredient(InitItems.SOUL_ESSENCE_GREATER.get(), 2);

        RUNE_BASE_BLESSED.setHandItem(InitItems.RUNE_CATALYST.get());
        RUNE_BASE_BLESSED.addIngredient(Items.DIAMOND);
        RUNE_BASE_BLESSED.addIngredient(InitItems.SOUL_ESSENCE_BLESSED.get(), 2);

        RUNE_BLESSED.addBaseRecipe(RUNE_BASE_BLESSED);
        RUNE_BLESSED.addIngredient(InitItems.WINGS_DIVINE.get());
        RUNE_BLESSED.setDropResult(InitItems.RUNE_BLESSED_FLIGHT.get());

        RUNE_CURSED.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_CURSED.addIngredient(InitItems.BAT_EYEBALL.get());
        RUNE_CURSED.setDropResult(InitItems.RUNE_CURSED_NIGHT.get());

        RUNE_PURGED.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_PURGED.addIngredient(Items.PRISMARINE_CRYSTALS);
        RUNE_PURGED.setDropResult(InitItems.RUNE_PURGED_LIGHT.get());

        RUNE_FIRE.addBaseRecipe(RUNE_BASE_BLESSED);
        RUNE_FIRE.addIngredient(Items.BLAZE_POWDER);
        RUNE_FIRE.setDropResult(InitItems.RUNE_GREAT_FIRE.get());

        RUNE_LEVITATE.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_LEVITATE.addIngredient(Items.SHULKER_SHELL);
        RUNE_LEVITATE.setDropResult(InitItems.RUNE_GREAT_LEVITATION.get());

        RUNE_ZEN.addBaseRecipe(RUNE_BASE_BLESSED);
        RUNE_ZEN.addIngredient(Items.GOLDEN_APPLE);
        RUNE_ZEN.setDropResult(InitItems.RUNE_ZEN_HEALING.get());

        RUNE_INVIS.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_INVIS.addIngredient(Items.ENDER_EYE);
        RUNE_INVIS.setDropResult(InitItems.RUNE_GREAT_MAGIC.get());

        RUNE_LUCK.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_LUCK.addIngredient(Items.RABBIT_FOOT);
        RUNE_LUCK.setDropResult(InitItems.RUNE_GREAT_LUCK.get());

        RUNE_ENERGY.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_ENERGY.addIngredient(Blocks.REDSTONE_BLOCK.asItem());
        RUNE_ENERGY.setDropResult(InitItems.RUNE_GREAT_ENERGY.get());

        RUNE_MINING.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_MINING.addIngredient(Blocks.TNT.asItem());
        RUNE_MINING.setDropResult(InitItems.RUNE_GREAT_MINING.get());

        RUNE_NULL.addBaseRecipe(RUNE_BASE_GREATER);
        RUNE_NULL.addIngredient(InitItems.CLOTH_BLOODY.get());
        RUNE_NULL.setDropResult(InitItems.RUNE_NULL.get());


        CANDLE_BASE.addIngredient(InitItems.WAX_CHUNK_CURSED_SMALL.get(), 4);
        CANDLE_BASE.addIngredient(InitItems.WAX_CHUNK_PURGED_SMALL.get(), 4);

        CANDLE_BLESSED.addBaseRecipe(CANDLE_BASE);
        CANDLE_BLESSED.setHandItem(InitItems.RUNE_BLESSED_FLIGHT.get());
        CANDLE_BLESSED.setBlockResult(InitItems.CANDLE_BLESSED.get());

        CANDLE_CURSED.addBaseRecipe(CANDLE_BASE);
        CANDLE_CURSED.setHandItem(InitItems.RUNE_CURSED_NIGHT.get());
        CANDLE_CURSED.setBlockResult(InitItems.CANDLE_CURSED.get());

        CANDLE_PURGED.addBaseRecipe(CANDLE_BASE);
        CANDLE_PURGED.setHandItem(InitItems.RUNE_PURGED_LIGHT.get());
        CANDLE_PURGED.setBlockResult(InitItems.CANDLE_PURGED.get());

        CANDLE_FIRE.addBaseRecipe(CANDLE_BASE);
        CANDLE_FIRE.setHandItem(InitItems.RUNE_GREAT_FIRE.get());
        CANDLE_FIRE.setBlockResult(InitItems.CANDLE_FIRE.get());

        CANDLE_LEVITATE.addBaseRecipe(CANDLE_BASE);
        CANDLE_LEVITATE.setHandItem(InitItems.RUNE_GREAT_LEVITATION.get());
        CANDLE_LEVITATE.setBlockResult(InitItems.CANDLE_LEVITATE.get());

        CANDLE_ZEN.addBaseRecipe(CANDLE_BASE);
        CANDLE_ZEN.setHandItem(InitItems.RUNE_ZEN_HEALING.get());
        CANDLE_ZEN.setBlockResult(InitItems.CANDLE_ZEN.get());

        CANDLE_INVIS.addBaseRecipe(CANDLE_BASE);
        CANDLE_INVIS.setHandItem(InitItems.RUNE_GREAT_MAGIC.get());
        CANDLE_INVIS.setBlockResult(InitItems.CANDLE_INVIS.get());

        CANDLE_LUCK.addBaseRecipe(CANDLE_BASE);
        CANDLE_LUCK.setHandItem(InitItems.RUNE_GREAT_LUCK.get());
        CANDLE_LUCK.setBlockResult(InitItems.CANDLE_LUCK.get());

        CANDLE_ENERGY.addBaseRecipe(CANDLE_BASE);
        CANDLE_ENERGY.setHandItem(InitItems.RUNE_GREAT_ENERGY.get());
        CANDLE_ENERGY.setBlockResult(InitItems.CANDLE_ENERGY.get());

        CANDLE_MINING.addBaseRecipe(CANDLE_BASE);
        CANDLE_MINING.setHandItem(InitItems.RUNE_GREAT_MINING.get());
        CANDLE_MINING.setBlockResult(InitItems.CANDLE_CAVERN.get());

        CANDLE_NULL.addBaseRecipe(CANDLE_BASE);
        CANDLE_NULL.setHandItem(InitItems.RUNE_NULL.get());
        CANDLE_NULL.setBlockResult(InitItems.CANDLE_NULL.get());



        CRYSTAL_BASE.addIngredient(InitItems.WAX_CHUNK_BLESSED_SMALL.get());
        CRYSTAL_BASE.addIngredient(InitItems.WAX_CHUNK_CURSED_SMALL.get());
        CRYSTAL_BASE.addIngredient(InitItems.WAX_CHUNK_PURGED_SMALL.get());
        CRYSTAL_BASE.setHandItem(Items.EMERALD);

        CRYSTAL_AMPLIFYING.addBaseRecipe(CRYSTAL_BASE);
        CRYSTAL_AMPLIFYING.addIngredient(InitItems.RUNE_GREAT_ENERGY.get());
        CRYSTAL_AMPLIFYING.setDropResult(InitItems.CRYSTAL_AMPLIFYING.get());

        CRYSTAL_POTENCY.addBaseRecipe(CRYSTAL_BASE);
        CRYSTAL_POTENCY.addIngredient(InitItems.RUNE_GREAT_FIRE.get());
        CRYSTAL_POTENCY.setDropResult(InitItems.CRYSTAL_POTENCY.get());

        CRYSTAL_INVERTING.addBaseRecipe(CRYSTAL_BASE);
        CRYSTAL_INVERTING.addIngredient(InitItems.RUNE_NULL.get());
        CRYSTAL_INVERTING.setDropResult(InitItems.CRYSTAL_INVERTING.get());



        WAND_BASE.addIngredient(InitItems.WAX_CHUNK_BLESSED_SMALL.get(), 4);
        WAND_BASE.setHandItem(InitItems.WAND_HILT.get());

        WAND_FIRE.addBaseRecipe(WAND_BASE);
        WAND_FIRE.addIngredient(InitItems.CANDLE_FIRE.get().asItem());
        WAND_FIRE.setDropResult(InitItems.WAND_FIRE.get());

        WAND_ENERGY.addBaseRecipe(WAND_BASE);
        WAND_ENERGY.addIngredient(InitItems.CANDLE_ENERGY.get().asItem());
        WAND_ENERGY.setDropResult(InitItems.WAND_ENERGY.get());

        WAND_MINING.addBaseRecipe(WAND_BASE);
        WAND_MINING.addIngredient(InitItems.CANDLE_CAVERN.get().asItem());
        WAND_MINING.setDropResult(InitItems.WAND_MINING.get());

        WAND_INVIS.addBaseRecipe(WAND_BASE);
        WAND_INVIS.addIngredient(InitItems.CANDLE_INVIS.get().asItem());
        WAND_INVIS.setDropResult(InitItems.WAND_MAGICIAN.get());

        WAND_LEVITATE.addBaseRecipe(WAND_BASE);
        WAND_LEVITATE.addIngredient(InitItems.CANDLE_LEVITATE.get().asItem());
        WAND_LEVITATE.setDropResult(InitItems.WAND_LEVITATION.get());

        WAND_BLESSED.addBaseRecipe(WAND_BASE);
        WAND_BLESSED.addIngredient(InitItems.CANDLE_BLESSED.get().asItem());
        WAND_BLESSED.setDropResult(InitItems.WAND_BLESSED_LIGHT.get());



        WRAITH.setHandItem(Items.NETHER_STAR);
        WRAITH.addIngredient(InitEntityTypes.WRAITH_FIRE.get());
        WRAITH.addIngredient(InitEntityTypes.WRAITH_WATER.get());
        WRAITH.addIngredient(InitEntityTypes.WRAITH_AIR.get());
        WRAITH.addIngredient(InitEntityTypes.WRAITH_EXPLOSION.get());
        WRAITH.addIngredient(InitEntityTypes.WRAITH_MAGIC.get());
        WRAITH.addIngredient(InitEntityTypes.WRAITH_MOB.get());
    }
}
