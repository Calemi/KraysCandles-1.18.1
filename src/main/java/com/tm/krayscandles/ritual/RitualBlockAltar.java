package com.tm.krayscandles.ritual;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.blockentity.BlockEntityStoneAltarTile;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;

import java.util.Random;

/**
 * An ingredient to a Ritual that requires an Altar, an Item to be placed in the Altar, and in a specific Location.
 */
public class RitualBlockAltar extends RitualBlock {

    /**
     * Creates a altar ritual block that will be used as an ingredient for a ritual.
     * @param offset The required offset from the ritual center.
     */
    public RitualBlockAltar(BlockPos offset) {
        super(InitItems.STONE_ALTAR_TILE.get(), offset);
    }

    /**
     * Creates a altar ritual block that will be used as an ingredient for a ritual.
     * @param x The required x offset from the ritual center.
     * @param y The required y offset from the ritual center.
     * @param z The required z offset from the ritual center.
     */
    public RitualBlockAltar(int x, int y, int z) {
        super(InitItems.STONE_ALTAR_TILE.get(), x, y, z);
    }

    /**
     * Creates a altar ritual block that will be used as an ingredient for a ritual.
     * Offset will be at the origin (0, 0, 0).
     */
    public RitualBlockAltar() {
        super(InitItems.STONE_ALTAR_TILE.get(), 0, 0, 0);
    }

    /**
     * @param rotation The rotation of the origin.
     * @return A new ritual block with its location computed by a rotation of the center.
     */
    @Override
    public RitualBlockAltar rotate(Rotation rotation) {
        return new RitualBlockAltar(getOffset().rotate(rotation));
    }

    /**
     * Called when the ritual is completed. Used for effects and consuming items.
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @param player The Player performing the ritual.
     */
    @Override
    public void onRitualComplete(Level level, BlockPos origin, Player player) {

        Random rand = new Random();
        Location location = getRealLocation(level, origin);

        if (location.getBlockEntity() instanceof BlockEntityStoneAltarTile altarTile) {
            altarTile.takeRitualStack();
        }

        for (int i = 0; i < 5; i++) {
            level.addParticle(ParticleTypes.SOUL, location.x + rand.nextDouble(), location.y + 0.2F, location.z + rand.nextDouble(), 0, 0, 0);
            level.addParticle(ParticleTypes.SMOKE, location.x + rand.nextDouble(), location.y + 0.2F, location.z + rand.nextDouble(), 0, 0, 0);
        }

        SoundHelper.playAtLocation(location, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.05F, 1.0F);
    }
}