package com.tm.krayscandles.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FlightMobEffect extends MobEffect {

    public FlightMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public String getDescriptionId() {
        return "mob_effect.krayscandles.flight";
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
