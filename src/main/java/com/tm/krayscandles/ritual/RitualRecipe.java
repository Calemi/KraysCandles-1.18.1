package com.tm.krayscandles.ritual;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.blockentity.BlockEntityStoneAltarTile;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * The recipe for a ritual
 */
public class RitualRecipe {

    private final boolean baseRecipe;
    private RitualStructureType ritualStructure;

    private final List<Item> altarItems = new ArrayList<>();
    private final List<EntityType<?>> soulFlameTypes = new ArrayList<>();

    private Item handItem = Items.AIR;
    private Item dropResult;
    private Block blockResult;

    /**
     * Creates a recipe for a ritual.
     * @param ritualStructure The structure of the ritual.
     * @param baseRecipe Is it a base recipe.
     */
    public RitualRecipe(RitualStructureType ritualStructure, boolean baseRecipe) {
        this.baseRecipe = baseRecipe;
        this.ritualStructure = ritualStructure;
        RitualRecipes.allRitualRecipes.add(this);
    }

    /**
     * Creates a recipe for a ritual.
     * @param ritualStructure The structure of the ritual.
     */
    public RitualRecipe(RitualStructureType ritualStructure) {
        this(ritualStructure, false);
    }

    /**
     * Creates a recipe for a ritual.
     */
    public RitualRecipe() {
        this(null);
    }

    /**
     * @return The structure of the recipe.
     */
    public RitualStructureType getRitualStructure() {
        return ritualStructure;
    }

    /**
     * @return The required Item to be held in order to perform the ritual.
     */
    public Item getHandItem() {
        return handItem;
    }

    /**
     * @return The resulting Item after performing the ritual.
     */
    public Item getDropResult() {
        return dropResult;
    }

    /**
     * @return The resulting Block after performing the ritual.
     */
    public Block getBlockResult() {
        return blockResult;
    }

    /**
     * @return The required Items to be placed in the Altars.
     */
    public List<Item> getAltarItems() {
        return altarItems;
    }

    /**
     * Adds a base recipe to the recipe.
     * @param baseRecipe The base recipe to add.
     */
    public void addBaseRecipe(RitualRecipe baseRecipe) {
        ritualStructure = baseRecipe.ritualStructure;
        altarItems.addAll(baseRecipe.altarItems);
        soulFlameTypes.addAll(baseRecipe.soulFlameTypes);
        handItem = baseRecipe.handItem;
    }

    /**
     * Adds an Item ingredient to the recipe.
     * @param alterItem The Item to add.
     */
    public void addIngredient(Item alterItem) {
        addIngredient(alterItem, 1);
    }

    /**
     * Adds multiple Item ingredients to the recipe.
     * @param alterItem The Item to add.
     */
    public void addIngredient(Item alterItem, int count) {

        for (int i = 0; i < count; i++) {
            altarItems.add(alterItem);
        }
    }

    /**
     * Adds a soul flame ingredient to the recipe.
     * @param soulFlameType The soul flame type to add.
     */
    public void addIngredient(EntityType<?> soulFlameType) {
        soulFlameTypes.add(soulFlameType);
    }


    /**
     * Sets the required Item to be held in order to perform the ritual.
     * @param handItem The Item to be set.
     */
    public void setHandItem(Item handItem) {
        this.handItem = handItem;
    }

    /**
     * Sets the resulting Item after performing the ritual.
     * @param dropResult The Item to be set.
     */
    public void setDropResult(Item dropResult) {
        this.dropResult = dropResult;
    }

    /**
     * Sets the resulting Block after performing the ritual.
     * @param blockResult The Block to set.
     */
    public void setBlockResult(Block blockResult) {
        this.blockResult = blockResult;
    }

    /**
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @param player The Player performing the ritual.
     * @return true, if the ritual has a complete structure and all required ingredients are present.
     */
    public boolean isRitualComplete(Level level, BlockPos origin, Player player) {

        if (baseRecipe) {
            return false;
        }

        if (getRitualStructure() == null) {
            return false;
        }

        if (!getRitualStructure().isStructureComplete(level, origin)) {
            return false;
        }

        List<Item> alterItemsCopy = new ArrayList<>(this.altarItems);
        List<EntityType<?>> soulFlameTypesCopy = new ArrayList<>(this.soulFlameTypes);

        if (searchRitualStructure(level, origin, alterItemsCopy, soulFlameTypesCopy, getRitualStructure())) {

            for (RitualBlock ritualBlock : getRitualStructure().getRitualBlocks()) {
                ritualBlock.onRitualComplete(level, origin, player);
            }

            return true;
        }

        if (searchRitualStructure(level, origin, alterItemsCopy, soulFlameTypesCopy, getRitualStructure().getRotatedRitual())) {

            for (RitualBlock ritualBlock : getRitualStructure().getRotatedRitual().getRitualBlocks()) {
                ritualBlock.onRitualComplete(level, origin, player);
            }

            return true;
        }

        return false;
    }

    /**
     * Searches through the ritual blocks for all required ingredients.
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @param alterItems The Items to search for.
     * @param soulFlameTypes The soul flames to search for.
     * @param structure The structure of the ritual.
     * @return true, if all required ingredients are found.
     */
    private boolean searchRitualStructure(Level level, BlockPos origin, List<Item> alterItems, List<EntityType<?>> soulFlameTypes, RitualStructureType structure) {

        for (RitualBlock ritualBlock : structure.getRitualBlocks()) {

            Location location = ritualBlock.getRealLocation(level, origin);

            if (ritualBlock instanceof RitualBlockAltar) {

                if (location.getBlockEntity() instanceof BlockEntityStoneAltarTile altarTile) {

                    for (Item alterItem : alterItems) {

                        if (alterItem.equals(altarTile.getRitualStack().getItem())) {
                            alterItems.remove(alterItem);
                            break;
                        }
                    }
                }
            }

            else if (ritualBlock instanceof RitualBlockCandle) {

                for (EntityType<?> soulFlameType : soulFlameTypes) {

                    if (location.getBlockEntity() instanceof BlockEntityCandleBase candle) {

                        if (soulFlameType == candle.getSoul().getEntity()) {
                            soulFlameTypes.remove(soulFlameType);
                            break;
                        }
                    }
                }
            }
        }

        return (alterItems.isEmpty() && soulFlameTypes.isEmpty());
    }
}
