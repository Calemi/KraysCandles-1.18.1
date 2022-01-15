package com.tm.krayscandles.blockentity.base;

import com.tm.krayscandles.item.ItemCrystal;

/**
 * Used by Block Entities that contain Crystals.
 */
public interface BlockEntityCrystalHolder {

    /**
     * @return The maximum amount of Crystals (disregarding the type) the Block Entity can hold.
     */
    int getMaxCrystalCount();

    /**
     * @param crystalType The Crystal type to check for.
     * @return The maximum amount of Crystal types can be held by the Block Entity.
     */
    int getMaxCrystalCountOfType(ItemCrystal.CrystalType crystalType);
}
