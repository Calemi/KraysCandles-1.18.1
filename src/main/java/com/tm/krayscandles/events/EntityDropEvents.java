package com.tm.krayscandles.events;

import com.tm.calemicore.util.helper.MathHelper;
import com.tm.krayscandles.entity.wraith.Wraith;
import com.tm.krayscandles.entity.wraith.WraithDamnedBoss;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityDropEvents {

    @SubscribeEvent
    public void onEntityDrops(LivingDropsEvent event) {

        Level level = event.getEntity().getLevel();
        LivingEntity entity = event.getEntityLiving();

        double lootingMultiplier = event.getLootingLevel() * 10;

        if (entity instanceof Witch) {

            if (MathHelper.rollChance(50 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(InitItems.SOYBEAN_CROP.get().asItem())));
            }

            if (MathHelper.rollChance(20 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(InitItems.BAT_EYEBALL.get().asItem())));
            }

            if (MathHelper.rollChance(10 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(InitItems.ZOMBIE_EAR.get().asItem())));
            }

            if (MathHelper.rollChance(10 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(InitItems.GARLIC.get().asItem())));
            }
        }

        if (entity instanceof Wraith) {

            if (MathHelper.rollChance(20 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(InitItems.CLOTH_CURSED.get().asItem())));
            }
        }

        if (entity instanceof WraithDamnedBoss) {
            event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(InitItems.WINGS_WRAITH.get().asItem())));
        }
    }
}
