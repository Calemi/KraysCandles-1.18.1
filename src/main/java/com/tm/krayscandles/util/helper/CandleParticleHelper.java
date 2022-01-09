package com.tm.krayscandles.util.helper;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.soul.BlockEntitySoulHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public class CandleParticleHelper {

    public static void renderFlame(Level level, BlockPos pos, double x, double y, double z) {

        Location blockLocation = new Location(level, pos);

        ParticleOptions flameType = ParticleTypes.FLAME;

        if (blockLocation.getBlockEntity() instanceof BlockEntitySoulHolder trappedSoul) {

            if (!trappedSoul.getSoul().isNull()) {
                flameType = InitParticles.SOUL_FLAME_NORMAL.get();
            }

            /*if (soulFlame.getEntityTypeFromSoul() == InitEntityTypes.WRAITH_FIRE.get()) {
                flameType = InitParticles.SOUL_FLAME_FIRE.get();
            }

            else if (soulFlame.getEntityTypeFromSoul() == InitEntityTypes.WRAITH_WATER.get()) {
                flameType = InitParticles.SOUL_FLAME_WATER.get();
            }

            else if (soulFlame.getEntityTypeFromSoul() == InitEntityTypes.WRAITH_AIR.get()) {
                flameType = InitParticles.SOUL_FLAME_AIR.get();
            }

            else if (soulFlame.getEntityTypeFromSoul() == InitEntityTypes.WRAITH_EXPLOSION.get()) {
                flameType = InitParticles.SOUL_FLAME_EXPLOSION.get();
            }

            else if (soulFlame.getEntityTypeFromSoul() == InitEntityTypes.WRAITH_MAGIC.get()) {
                flameType = InitParticles.SOUL_FLAME_MAGIC.get();
            }

            else if (soulFlame.getEntityTypeFromSoul() == InitEntityTypes.WRAITH_MOB.get()) {
                flameType = InitParticles.SOUL_FLAME_MOB.get();
            }

            else if (soulFlame.getEntityTypeFromSoul() == InitEntityTypes.WRAITH_DAMNED.get()) {
                flameType = InitParticles.SOUL_FLAME_DAMNED.get();
            }*/
        }

        level.addParticle(flameType, x, y, z, 0.0D, 0.0D, 0.0D);
        renderSmoke(level, x, y, z);
    }

    public static void renderSmoke(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
    }
}
