package com.tm.krayscandles.entity.wraith;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class WraithWater extends WraithBase {

    public WraithWater(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public WraithWater(Location location, String playerName) {
        super(InitEntityTypes.WRAITH_WATER.get(), location, playerName);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.WATER;
    }
}
