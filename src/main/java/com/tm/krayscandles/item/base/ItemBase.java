package com.tm.krayscandles.item.base;

import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

/**
 * The base class for Items.
 */
public class ItemBase extends Item {

    /**
     * Used to identify a type of Item.
     */
    private String tag = "";

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase(CreativeModeTab tab) {
        super(new Item.Properties().tab(tab));
    }

    public ItemBase() {
        super(new Item.Properties().tab(KraysCandles.TAB_MAIN));
    }

    /**
     * Sets the Item's tag.
     * @param tag The tag to set.
     * @return The Item.
     */
    public ItemBase setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * @param item The item to check.
     * @param tag The tag to check.
     * @return true, if the Item has the tag.
     */
    public static boolean hasTag(Item item, String tag) {

        if (item instanceof ItemBase) {
            return ((ItemBase)item).tag.equalsIgnoreCase(tag);
        }

        return false;
    }
}
