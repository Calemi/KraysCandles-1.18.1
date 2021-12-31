package com.tm.krayscandles.item.base;

import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

/**
 * The base class for food Items.
 */
public class ItemFoodBase extends ItemBase {

    public ItemFoodBase(int hunger, float saturation) {
        super(new Item.Properties().tab(KraysCandles.TAB_MAIN).food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).alwaysEat().build()));
    }
}
