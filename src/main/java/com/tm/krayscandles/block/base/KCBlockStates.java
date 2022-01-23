package com.tm.krayscandles.block.base;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * General Properties for Blocks and Items
 */
public class KCBlockStates {

    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);

    public static final IntegerProperty CRYSTALS = IntegerProperty.create("crystals", 0, 4);
    public static final IntegerProperty WAX_MELTER_LEVEL = IntegerProperty.create("wax_melter_level", 0, 8);
}
