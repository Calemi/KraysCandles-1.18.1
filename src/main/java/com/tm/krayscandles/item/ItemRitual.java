package com.tm.krayscandles.item;

import com.tm.krayscandles.item.base.ItemBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualResultItem;

/**
 * Items with Ritual Recipes to craft them.
 */
public class ItemRitual extends ItemBase implements RitualResultItem {

    /**
     * The ritual recipe to craft the Item.
     */
    private final RitualRecipe ritualRecipe;

    public ItemRitual(RitualRecipe ritualRecipe) {
        this.ritualRecipe = ritualRecipe;
    }

    /**
     * @return The ritual recipe to craft the Item.
     */
    @Override
    public RitualRecipe getRitualRecipe() {
        return ritualRecipe;
    }
}
