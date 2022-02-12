package com.tm.krayscandles.entity.vampire;


import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import java.util.Random;

public class VampireBaroness extends VampireBaron {

    public VampireBaroness(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public VampireBaroness(Location location) {
        super(InitEntityTypes.VAMPIRE_BARONESS.get(), location);
    }

    @Override
    public String getRankPrefix() {
        return "baroness";
    }

    /**
     * Called every tick.
     * Use to render flames and smoke.
     */
    @Override
    public void tick() {

        if (getLevel().isClientSide()) {
            getLevel().addParticle(InitParticles.SOUL_FLAME_MAGIC.get(), getRandomX(0.5D), getRandomY(), getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            getLevel().addParticle(ParticleTypes.LARGE_SMOKE, getRandomX(0.5D), getRandomY(), getRandomZ(0.1D), 0.0D, 0.0D, 0.0D);
        }

        super.tick();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return InitSounds.VAMPIRE_BARONESS_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return InitSounds.VAMPIRE_BARONESS_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.VAMPIRE_BARONESS_DEATH.get();
    }

    @Override
    public String getRandomName() {

        String[] names = new String[]{
"Silvana","Jillian","Elizabeth","Gertrude","Judy","Elizabeth","Lexie","Betsy","Eryn","Emily","Stephanie","Stephania","Richelle","Margaret","Jane","Rowena","Hestia","Charity","Feronia","Petra","Ravette","Minerva","Gretchen","Silvia","Nora", "Lorraine","Ursula","Tabatha","Sophia","Persephone","Hannah","Madison","Ashley","Sarah","Alexis","Samantha"};
        return names[new Random().nextInt(names.length)];
    }
}


