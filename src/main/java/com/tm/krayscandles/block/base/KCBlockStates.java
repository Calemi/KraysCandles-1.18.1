package com.tm.krayscandles.block.base;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.EnumProperty;

/**
 * General Properties for Blocks and Items
 */
public class KCBlockStates {

    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);
}
