package com.tm.krayscandles.item.tier;

import com.tm.krayscandles.init.InitItems;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum KCSwordTiers implements Tier {

    NIGHT_BLADE (200, 8F, 14, 1.2F, () -> {return Ingredient.of(Items.IRON_INGOT);}),
    SCALPEL     (50, 5F, 10, 0.9F, () -> {return Ingredient.of(Items.IRON_INGOT);}),
    WAX         (50, 2.5F, 12, 1.5F, () -> {return Ingredient.of(InitItems.WAX_CHUNK_SOY_SMALL.get());});

    public final int durability;
    public final float attackDamage;
    public final int enchantability;
    public final float efficiency;
    public final float attackSpeed;
    public final LazyLoadedValue<Ingredient> repairMaterial;

    KCSwordTiers(int durability, float attackDamage, int enchantability, float attackSpeed, Supplier<Ingredient> repairMaterial) {
        this.durability = durability;
        this.efficiency = 5F;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.attackSpeed = attackSpeed;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getUses() {
        return durability;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
