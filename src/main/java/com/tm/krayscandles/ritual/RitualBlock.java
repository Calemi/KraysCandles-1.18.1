package com.tm.krayscandles.ritual;

import com.tm.calemicore.util.Location;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

/**
 * An ingredient to a Ritual that requires a specific Block to be placed in a specific Location.
 */
public class RitualBlock {

    private final BlockState state;
    private final BlockPos offset;

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * Offset will be at the origin (0, 0, 0).
     * @param block The required Block for the ritual.
     */
    public RitualBlock(Block block) {
        this(block, new BlockPos(0, 0, 0));
    }

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * @param block The required Block for the ritual.
     * @param x The required x offset from the ritual center.
     * @param y The required y offset from the ritual center.
     * @param z The required z offset from the ritual center.
     */
    public RitualBlock(Block block, int x, int y, int z) {
        this(block, new BlockPos(x, y, z));
    }

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * @param block The required Block for the ritual.
     * @param offset The required offset from the ritual center.
     */
    public RitualBlock(Block block, BlockPos offset) {
        this.state = block.defaultBlockState();
        this.offset = offset;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getOffset() {
        return offset;
    }

    /**
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @return The real Location of the ritual block.
     */
    public Location getRealLocation(Level level, BlockPos origin) {
        return new Location(level, origin.offset(getOffset()));
    }

    /**
     * @param rotation The rotation of the origin.
     * @return A new ritual block with its location computed by a rotation of the center.
     */
    public RitualBlock rotate(Rotation rotation) {
        return new RitualBlock(state.getBlock(), offset.rotate(rotation));
    }

    /**
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @return true, if the ritual block is perfectly valid for the ritual.
     */
    public boolean isValid(Level level, BlockPos origin) {
        return level.getBlockState(origin.offset(offset)).getBlock().defaultBlockState() == state;
    }

    /**
     * Called when the ritual is completed. Used for effects and consuming items.
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @param player The Player performing the ritual.
     */
    public void onRitualComplete(Level level, BlockPos origin, Player player) {}
}