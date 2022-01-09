package com.tm.krayscandles.recipe;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class ColoredRecipe extends CustomRecipe {

    public ColoredRecipe(ResourceLocation id) {
        super(id);
    }

    public abstract Item getItemToColor();
    public abstract ItemStack getResultItem();

    @Override
    public boolean matches(CraftingContainer inv, Level world) {

        int itemCount = 0;
        int dyeCount = 0;

        for (int i = 0; i < inv.getContainerSize(); i++) {

            ItemStack stackInSlot = inv.getItem(i);

            if (stackInSlot.getItem() == getItemToColor()) {
                itemCount++;
            }

            else if (stackInSlot.getItem() instanceof DyeItem) {
                dyeCount++;
            }

            else if (!stackInSlot.isEmpty()) {
                return false;
            }
        }

        return itemCount == 1 && dyeCount == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv) {

        List<DyeItem> list = Lists.newArrayList();
        int colorID = 0;

        for (int i = 0; i < inv.getContainerSize(); i++) {

            ItemStack stackInSlot = inv.getItem(i);

            if (stackInSlot.getItem() instanceof DyeItem dye) {
                colorID = dye.getDyeColor().getId();
            }
        }

        getResultItem().setDamageValue(colorID);

        return getResultItem();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }
}