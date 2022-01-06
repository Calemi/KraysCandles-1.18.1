package com.tm.krayscandles.ritual;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.block.candle.BlockCandleSoy;
import com.tm.krayscandles.block.candle.BlockCandleSoyColored;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;

/**
 * An ingredient to a Ritual that requires a Soy Candle to be lit, and placed in a specific Location.
 */
public class RitualBlockCandleSoy extends RitualBlockCandle {

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * @param offset The required offset from the ritual center.
     */
    public RitualBlockCandleSoy(BlockPos offset) {
        super(InitItems.CANDLE_SOY.get(), offset);
    }

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * @param x The required x offset from the ritual center.
     * @param y The required y offset from the ritual center.
     * @param z The required z offset from the ritual center.
     */
    public RitualBlockCandleSoy(int x, int y, int z) {
        super(InitItems.CANDLE_SOY.get(), x, y, z);
    }

    /**
     * Creates a ritual block that will be used as an ingredient for a ritual.
     * Offset will be at the origin (0, 0, 0).
     */
    public RitualBlockCandleSoy() {
        super(InitItems.CANDLE_SOY.get(), 0, 0, 0);
    }

    /**
     * @param rotation The rotation of the origin.
     * @return A new ritual block with its location computed by a rotation of the center.
     */
    @Override
    public RitualBlockCandleSoy rotate(Rotation rotation) {
        return new RitualBlockCandleSoy(getOffset().rotate(rotation));
    }

    /**
     * @param level The Level the ritual is in.
     * @param origin The origin of the ritual.
     * @return true, if the ritual block is perfectly valid for the ritual.
     */
    @Override
    public boolean isValid(Level level, BlockPos origin) {

        Location location = getRealLocation(level, origin);

        if (location.getBlock() instanceof BlockCandleSoy || location.getBlock() instanceof BlockCandleSoyColored) {
            return location.getBlockState().getValue(BlockCandleBase.LIT);
        }

        return false;
    }
}