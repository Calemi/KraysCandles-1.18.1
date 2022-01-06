package com.tm.krayscandles.ritual;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;

/**
 * An ingredient to a Ritual that requires a Candle to be lit, and placed in a specific Location.
 */
public class RitualBlockCandle  extends RitualBlock {

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * @param block The required Block for the ritual.
     * @param offset The required offset from the ritual center.
     */
    public RitualBlockCandle(Block block, BlockPos offset) {
        super(block, offset);
    }

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * @param block The required Block for the ritual.
     * @param x The required x offset from the ritual center.
     * @param y The required y offset from the ritual center.
     * @param z The required z offset from the ritual center.
     */
    public RitualBlockCandle(Block block, int x, int y, int z) {
        super(block, x, y, z);
    }

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * Offset will be at the origin (0, 0, 0).
     * @param block The required Block for the ritual.
     */
    public RitualBlockCandle(Block block) {
        super(block, 0, 0, 0);
    }

    /**
     * @param rotation The rotation of the origin.
     * @return A new ritual block with its location computed by a rotation of the center.
     */
    @Override
    public RitualBlockCandle rotate(Rotation rotation) {
        return new RitualBlockCandle(getState().getBlock(), getOffset().rotate(rotation));
    }

    /**
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @return true, if the ritual block is perfectly valid for the ritual.
     */
    @Override
    public boolean isValid(Level level, BlockPos origin) {

        Location location = new Location(level, origin.offset(getOffset()));

        if (location.getBlock() instanceof BlockCandleBase) {
            return location.getBlockState().getValue(BlockCandleBase.LIT);
        }

        return false;
    }

    /**
     * Called when the ritual is completed. Used for effects and consuming items.
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @param player The Player performing the ritual.
     */
    @Override
    public void onRitualComplete(Level level, BlockPos origin, Player player) {

        Location location = getRealLocation(level, origin);

        if (location.getBlock() instanceof BlockCandleBase) {
            BlockCandleBase.setLit(location, false);
        }

        level.addParticle(ParticleTypes.LARGE_SMOKE, location.x + 0.5F, location.y + 0.4F, location.z + 0.5F, 0, 0, 0);
        SoundHelper.playAtLocation(location, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.05F, 1.0F);
    }
}
