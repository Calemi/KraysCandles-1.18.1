package com.tm.krayscandles.entity.wraith;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class WraithAir extends Wraith {

    public WraithAir(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public WraithAir(Location location, String playerName) {
        super(InitEntityTypes.WRAITH_AIR.get(), location, playerName);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.AIR;
    }
}
