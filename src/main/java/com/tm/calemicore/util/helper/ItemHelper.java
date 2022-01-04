package com.tm.calemicore.util.helper;

import com.tm.calemicore.util.Location;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Use this class to help with Items.
 */
public class ItemHelper {

    /**
     * Spawns an Item Stack.
     * @param level The Level to spawn in.
     * @param location The Location to spawn at.
     * @param stack The Item Stack to spawn.
     * @return The Item Entity that spawned.
     */
    public static ItemEntity spawnStackAtLocation(Level level, Location location, ItemStack stack) {
        return spawnStack(level, location.x + 0.5F, location.y + 0.5F, location.z + 0.5F, stack);
    }

    /**
     * Spawns an Item Stack.
     * @param level The Level to spawn in.
     * @param entity The Entity to spawn at.
     * @param stack The Item Stack to spawn.
     * @return The Item Entity that spawned.
     */
    public static ItemEntity spawnStackAtEntity(Level level, Entity entity, ItemStack stack) {
        return spawnStack(level, (float) entity.getX() + 0.5F, (float) entity.getY() + 0.5F, (float) entity.getZ() + 0.5F, stack);
    }

    /**
     * Spawns an Item Stack.
     * @param level The Level to spawn in.
     * @param x The x position to spawn at.
     * @param y The y position to spawn at.
     * @param z The z position to spawn at.
     * @param stack The Item Stack to spawn.
     * @return The Item Entity that spawned.
     */
    public static ItemEntity spawnStack(Level level, float x, float y, float z, ItemStack stack) {
        ItemEntity item = new ItemEntity(level, x, y, z, stack);
        item.setNoPickUpDelay();
        item.setDeltaMovement(-0.05F + MathHelper.random.nextFloat() * 0.1F, -0.05F + MathHelper.random.nextFloat() * 0.1F, -0.05F + MathHelper.random.nextFloat() * 0.1F);
        level.addFreshEntity(item);
        return item;
    }
}
