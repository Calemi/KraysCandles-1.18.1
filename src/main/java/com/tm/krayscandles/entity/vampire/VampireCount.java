package com.tm.krayscandles.entity.vampire;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class VampireCount extends VampireBase {

    public VampireCount(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public VampireCount(Location location) {
        super(InitEntityTypes.VAMPIRE_COUNT.get(), location);
    }

    @Override
    public String getRankPrefix() {
        return "count";
    }

    /**
     * Called to register the Entity's attributes (like health, damage, etc.)
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.ATTACK_DAMAGE, 4);
    }

    /**
     * Called every tick.
     * Use to render flames and smoke.
     */
    @Override
    public void tick() {

        if (getLevel().isClientSide()) {
            getLevel().addParticle(ParticleTypes.LARGE_SMOKE, getRandomX(0.5D), getRandomY(), getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
        }

        else {

            if (getLevel().isDay()){

                if (!hasCustomName()) {

                    playSound(InitSounds.VAMPIRE_COUNT_VANISH.get(), 1,1);

                    MobEffectHelper.addMobEffect(MobEffects.INVISIBILITY, 20, 4);
                    Bat bat = new Bat(EntityType.BAT, getLevel());
                    bat.setPos(getX(), getY(), getZ());
                    getLevel().addFreshEntity(bat);
                    kill();
                }
            }
        }

        super.tick();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return InitSounds.VAMPIRE_COUNT_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return InitSounds.VAMPIRE_COUNT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.VAMPIRE_COUNT_DEATH.get();
    }
}
