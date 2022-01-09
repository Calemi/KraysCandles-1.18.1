package com.tm.krayscandles.init;

import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.recipe.SoyCandleColorRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KCReference.MOD_ID);
    public static final RegistryObject<RecipeSerializer<SoyCandleColorRecipe>> CANDLE_SOY_COLORED = RECIPES.register("candle_soy_colored", () -> new SimpleRecipeSerializer<>(SoyCandleColorRecipe::new));

}
