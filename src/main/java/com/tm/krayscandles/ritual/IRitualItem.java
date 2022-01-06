package com.tm.krayscandles.ritual;

/**
 * Used by Items that contain a Ritual to obtain.
 */
public interface IRitualItem {

    /**
     * @return The Ritual Recipe needed to obtain the Item.
     */
    RitualRecipe getRitualRecipe();
}
