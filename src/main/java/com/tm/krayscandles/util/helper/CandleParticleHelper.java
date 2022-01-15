package com.tm.krayscandles.util.helper;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.soul.BlockEntitySoulHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public class CandleParticleHelper {

    public static void renderCandleAura(Location location) {

        if (location.getBlockEntity() instanceof BlockEntityCandleBase candle) {

            ParticleOptions flameType = getFlameType(location);

            double x = location.x + 0.5D;
            double y = location.y + 0.5D;
            double z = location.z + 0.5D;

            int effectRange = candle.getEffectRange();
            double speed = 0.3D;

            for (int i = -effectRange; i <= effectRange; i++) {
                location.level.addParticle(getFlameType(location), x + effectRange, y, z + i, 0, 0, 0);
                location.level.addParticle(getFlameType(location), x - effectRange, y, z + i, 0, 0, 0);
            }

            for (int i = -effectRange + 1; i <= effectRange - 1; i++) {
                location.level.addParticle(getFlameType(location), x + i, y, z + effectRange, 0, 0, 0);
                location.level.addParticle(getFlameType(location), x + i, y, z - effectRange, 0, 0, 0);
            }

            location.level.addParticle(getFlameType(location), x + effectRange, y, z, -speed, 0, 0);
            location.level.addParticle(getFlameType(location), x - effectRange, y, z, speed, 0, 0);
            location.level.addParticle(getFlameType(location), x, y, z + effectRange, 0, 0, -speed);
            location.level.addParticle(getFlameType(location), x, y, z - effectRange, 0, 0, speed);
        }
    }

    public static void renderFlame(Level level, BlockPos pos, double x, double y, double z) {
        level.addParticle(getFlameType(new Location(level, pos)), x, y, z, 0.0D, 0.0D, 0.0D);
        renderSmoke(level, x, y, z);
    }

    public static void renderSmoke(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    public static ParticleOptions getFlameType(Location location) {

        ParticleOptions flameType = ParticleTypes.FLAME;

        if (location.getBlockEntity() instanceof BlockEntitySoulHolder trappedSoul) {

            if (!trappedSoul.getSoul().isNull()) {
                flameType = InitParticles.SOUL_FLAME_NORMAL.get();
            }

            if (trappedSoul.getSoul().getEntity() == InitEntityTypes.WRAITH_FIRE.get()) {
                flameType = InitParticles.SOUL_FLAME_FIRE.get();
            } else if (trappedSoul.getSoul().getEntity() == InitEntityTypes.WRAITH_WATER.get()) {
                flameType = InitParticles.SOUL_FLAME_WATER.get();
            } else if (trappedSoul.getSoul().getEntity() == InitEntityTypes.WRAITH_AIR.get()) {
                flameType = InitParticles.SOUL_FLAME_AIR.get();
            } else if (trappedSoul.getSoul().getEntity() == InitEntityTypes.WRAITH_EXPLOSION.get()) {
                flameType = InitParticles.SOUL_FLAME_EXPLOSION.get();
            } else if (trappedSoul.getSoul().getEntity() == InitEntityTypes.WRAITH_MAGIC.get()) {
                flameType = InitParticles.SOUL_FLAME_MAGIC.get();
            } else if (trappedSoul.getSoul().getEntity() == InitEntityTypes.WRAITH_MOB.get()) {
                flameType = InitParticles.SOUL_FLAME_MOB.get();
            } else if (trappedSoul.getSoul().getEntity() == InitEntityTypes.WRAITH_DAMNED.get()) {
                flameType = InitParticles.SOUL_FLAME_DAMNED.get();
            }
        }

        return flameType;
    }
}
