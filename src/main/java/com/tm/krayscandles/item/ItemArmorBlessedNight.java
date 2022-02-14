package com.tm.krayscandles.item;

import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.entity.Cloud;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitSounds;
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

        Cloud cloud = new Cloud(player);

        if (player.getInventory().getArmor(3).getItem() == InitItems.BLESSED_NIGHT_MASK.get()) {
            MobEffectHelper.addMobEffect(MobEffects.NIGHT_VISION, 600, 0, player);
            MobEffectHelper.addMobEffect(MobEffects.GLOWING, 20, 0, player);
        }

        if (player.getInventory().getArmor(2).getItem() == InitItems.BLESSED_NIGHT_MANTLE.get()) {

            if (player.getVehicle() == null) {
                world.addFreshEntity(cloud);
                player.startRiding(cloud);
                SoundHelper.playAtPlayer(player, InitSounds.MANTLE_EQUIP.get(), 1, 1);
            }
        }
    }
}


