package com.tm.krayscandles.item.base;

import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public class ItemArmorBase extends ArmorItem {

    public ItemArmorBase(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Properties().tab(KraysCandles.TAB_TOOL).stacksTo(1));
    }
}
