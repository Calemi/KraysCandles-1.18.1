package com.tm.krayscandles.item;

import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.krayscandles.item.base.ItemArmorBase;
import com.tm.krayscandles.item.tier.KCArmorTiers;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemArmorBlessedNight extends ItemArmorBase {

    public ItemArmorBlessedNight(EquipmentSlot slot) {
        super(KCArmorTiers.BLESSED_NIGHT, slot);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        if (stack.getEquipmentSlot() == EquipmentSlot.HEAD) {
            MobEffectHelper.addMobEffect(MobEffects.NIGHT_VISION, 600, 0, player);
            MobEffectHelper.addMobEffect(MobEffects.GLOWING, 20, 0, player);
        }
    }
}
