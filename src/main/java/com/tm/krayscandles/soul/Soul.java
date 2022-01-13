package com.tm.krayscandles.soul;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import java.util.Optional;

/**
 * A simple object for a Soul.
 */
public class Soul {

    /**
     * The trapped Entity.
     */
    private final EntityType<?> entity;

    private final Type type = Type.BASIC;

    /**
     * Creates a Soul.
     * @param entity The entity to grab its Soul from.
     */
    public Soul(EntityType<?> entity) {
        this.entity = entity;
    }

    /**
     * @return The trapped Entity.
     */
    public EntityType<?> getEntity() {
        return entity;
    }

    /**
     * @return true, if there is no attached soul.
     */
    public boolean isNull() {
        return entity == null;
    }

    /**
     * Use this method to load a Soul from an NBT tag.
     * @param tag The tag to load from.
     * @return The constructed Soul.
     */
    public static Soul load(CompoundTag tag) {
        CompoundTag soulTag = tag.getCompound("Soul");

        if (soulTag.getString("entity").isEmpty()) {
            return new Soul(null);
        }

        Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOptional(new ResourceLocation(soulTag.getString("entity")));
        return new Soul(entityType.orElse(null));
    }

    /**
     * Use this method to save the soul to an NBT tag.
     * @param tag Tge tag to save to.
     */
    public void save(CompoundTag tag) {
        CompoundTag soulTag = new CompoundTag();
        soulTag.putInt("type", type.getId());
        soulTag.putString("entity", entity != null ? getEntity().getRegistryName().toString() : "");
        tag.put("Soul", soulTag);
    }

    /**
     * Used to determine the type of Soul.
     * This is here because a Player Soul might act differently than a basic one.
     */
    public enum Type {

        BASIC(0, Soul.class);

        private final int id;
        private final Class<? extends Soul> soul;

        Type(int id, Class<? extends Soul> soul) {
            this.id = id;
            this.soul = soul;
        }

        public int getId() {
            return id;
        }

        public Class<? extends Soul> getSoul() {
            return soul;
        }

        public static Type fromId (int id) {
            return Type.values()[id];
        }
    }
}
