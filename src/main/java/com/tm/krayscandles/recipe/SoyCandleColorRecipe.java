package com.tm.krayscandles.recipe;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SoyCandleColorRecipe extends ColoredRecipe {

    public SoyCandleColorRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public Item getItemToColor() {
        return InitItems.CANDLE_SOY.get().asItem();
    }

    @Override
    public ItemStack getResultItem() {
        return new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return InitRecipes.CANDLE_SOY_COLORED.get();
    }
}
