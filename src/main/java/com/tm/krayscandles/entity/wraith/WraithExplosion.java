package com.tm.krayscandles.entity.wraith;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class WraithExplosion extends WraithBase {

    public WraithExplosion(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public WraithExplosion(Location location, String playerName) {
        super(InitEntityTypes.WRAITH_EXPLOSION.get(), location, playerName);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.EXPLOSION;
    }
}
