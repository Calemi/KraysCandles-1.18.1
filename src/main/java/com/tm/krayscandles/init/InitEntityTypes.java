package com.tm.krayscandles.init;

import com.tm.krayscandles.entity.Cloud;
import com.tm.krayscandles.entity.vampire.Vampire;
import com.tm.krayscandles.entity.vampire.VampireBaron;
import com.tm.krayscandles.entity.wraith.*;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, KCReference.MOD_ID);

    public static final RegistryObject<EntityType<WraithFire>> WRAITH_FIRE = regWraith("fire", WraithFire::new);
    public static final RegistryObject<EntityType<WraithWater>> WRAITH_WATER = regWraith("water", WraithWater::new);
    public static final RegistryObject<EntityType<WraithAir>> WRAITH_AIR = regWraith("air", WraithAir::new);
    public static final RegistryObject<EntityType<WraithExplosion>> WRAITH_EXPLOSION = regWraith("explosion", WraithExplosion::new);
    public static final RegistryObject<EntityType<WraithMagic>> WRAITH_MAGIC = regWraith("magic", WraithMagic::new);
    public static final RegistryObject<EntityType<WraithMob>> WRAITH_MOB = regWraith("mob", WraithMob::new);
    public static final RegistryObject<EntityType<WraithDamnedBoss>> WRAITH_DAMNED = ENTITY_TYPES.register("wraith_damned", () -> EntityType.Builder.<WraithDamnedBoss>of
            (WraithDamnedBoss::new, MobCategory.MONSTER).sized(2.5F, 5F).build(new ResourceLocation(KCReference.MOD_ID, "wraith_damned").toString()));

    public static final RegistryObject<EntityType<Vampire>> VAMPIRE = ENTITY_TYPES.register("vampire", () -> EntityType.Builder.<Vampire>of
            (Vampire::new, MobCategory.MONSTER).sized(0.8F, 1.8F).clientTrackingRange(10).build(new ResourceLocation(KCReference.MOD_ID, "vampire").toString()));

    public static final RegistryObject<EntityType<VampireBaron>> VAMPIRE_BARON = ENTITY_TYPES.register("vampire_baron", () -> EntityType.Builder.<VampireBaron>of
            (VampireBaron::new, MobCategory.MONSTER).sized(0.8F, 1.8F).clientTrackingRange(10).build(new ResourceLocation(KCReference.MOD_ID, "vampire_baron").toString()));

    public static final RegistryObject<EntityType<Cloud>> CLOUD = ENTITY_TYPES.register("cloud", () -> EntityType.Builder.<Cloud>of
            (Cloud::new, MobCategory.MISC).sized(3F, 0.5F).clientTrackingRange(10).build(new ResourceLocation(KCReference.MOD_ID, "cloud").toString()));

    public static <T extends Wraith> RegistryObject<EntityType<T>> regWraith(String type, EntityType.EntityFactory<T> sup) {
        return ENTITY_TYPES.register("wraith_" + type, () -> EntityType.Builder.of(sup, MobCategory.MONSTER).sized(0.8F, 1.8F).clientTrackingRange(10).build(new ResourceLocation(KCReference.MOD_ID, "wraith_" + type).toString()));
    }
}
