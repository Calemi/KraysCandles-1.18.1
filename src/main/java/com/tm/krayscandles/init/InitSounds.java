package com.tm.krayscandles.init;

import com.tm.krayscandles.main.KCReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KCReference.MOD_ID);
    public static final RegistryObject<SoundEvent> WRAITH_DEATH = SOUNDS.register("entity.wraith_death", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_death")));
    public static final RegistryObject<SoundEvent> WRAITH_AMBIENT = SOUNDS.register("entity.wraith_ambient", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_ambient")));
    public static final RegistryObject<SoundEvent> WRAITH_HURT = SOUNDS.register("entity.wraith_hurt", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_hurt")));

    public static final RegistryObject<SoundEvent> WRAITH_DAMNED_DEATH = SOUNDS.register("entity.wraith_damned_death", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_damned_death")));
    public static final RegistryObject<SoundEvent> WRAITH_DAMNED_AMBIENT = SOUNDS.register("entity.wraith_damned_ambient", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_damned_ambient")));
    public static final RegistryObject<SoundEvent> WRAITH_DAMNED_HURT = SOUNDS.register("entity.wraith_damned_hurt", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_damned_hurt")));

    public static final RegistryObject<SoundEvent> RUNE_RITUAL = SOUNDS.register("block.rune_ritual", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "block.rune_ritual")));
    public static final RegistryObject<SoundEvent> WAND_RITUAL = SOUNDS.register("block.wand_ritual", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "block.wand_ritual")));
    public static final RegistryObject<SoundEvent> WRAITH_RITUAL = SOUNDS.register("block.wraith_ritual", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "block.wraith_ritual")));

    public static final RegistryObject<SoundEvent> MUSIC_DISC_CHUNK = SOUNDS.register("disc.music_disc_chunk", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "disc.music_disc_chunk")));


    public static final RegistryObject<SoundEvent> VAMPIRE_AMBIENT = SOUNDS.register("entity.vampire_ambient", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_ambient")));
    public static final RegistryObject<SoundEvent> VAMPIRE_HURT = SOUNDS.register("entity.vampire_hurt", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_hurt")));
    public static final RegistryObject<SoundEvent> VAMPIRE_DEATH = SOUNDS.register("entity.vampire_death", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_death")));
    public static final RegistryObject<SoundEvent> VAMPIRE_VANISH = SOUNDS.register("entity.vampire_vanish", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_vanish")));
    public static final RegistryObject<SoundEvent> VAMPIRE_WEAKENED = SOUNDS.register("entity.vampire_weakened", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_weakened")));
    public static final RegistryObject<SoundEvent> VAMPIRE_BARON_HURT = SOUNDS.register("entity.vampire_baron_hurt", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_baron_hurt")));
    public static final RegistryObject<SoundEvent> VAMPIRE_BARON_DEATH = SOUNDS.register("entity.vampire_baron_death", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_baron_death")));
    public static final RegistryObject<SoundEvent> VAMPIRE_BARON_AMBIENT = SOUNDS.register("entity.vampire_baron_ambient", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.vampire_baron_ambient")));
}

