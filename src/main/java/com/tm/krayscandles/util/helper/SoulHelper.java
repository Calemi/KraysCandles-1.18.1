package com.tm.krayscandles.util.helper;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.krayscandles.soul.BlockEntitySoulHolder;
import com.tm.krayscandles.soul.Soul;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

/**
 * Use this class to help with adding and removing souls.
 */
public class SoulHelper {

    /**
     * @param location The Location of the lantern.
     * @return The soul.
     */
    public static Soul getSoulBlock(Location location) {

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntitySoulHolder blockEntity) {
            return blockEntity.getSoul();
        }

        return new Soul(null);
    }

    /**
     * @param stack The ItemStack of the lantern.
     * @return The soul.
     */
    public static Soul getSoulStack(ItemStack stack) {

        CompoundTag tag = ItemHelper.getNBT(stack);
        return Soul.load(tag);
    }

    /**
     * Sets the current trapped soul.
     * @param location The Location of the lantern.
     * @param soul The soul to set
     */
    public static void setSoulBlock(Location location, Soul soul) {

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntitySoulHolder blockEntity) {
            blockEntity.setSoul(soul);
        }
    }

    /**
     * Sets the current trapped soul.
     * @param stack The ItemStack of the lantern.
     * @param soul The soul to set
     */
    public static void setSoulStack(ItemStack stack, Soul soul) {

        CompoundTag tag = ItemHelper.getNBT(stack);
        soul.save(tag);
        stack.setTag(tag);
    }

    /**
     * @param location The Location of the lantern.
     * Removes the current trapped soul.
     */
    public static void removeSoulBlock(Location location) {

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntitySoulHolder lantern) {
            lantern.removeSoul();
        }
    }

    /**
     * @param stack The ItemStack of the lantern.
     * Removes the current trapped soul.
     */
    public static void removeSoulStack(ItemStack stack) {

        Soul soul = new Soul(null);
        setSoulStack(stack, soul);
    }
}
