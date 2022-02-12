package com.tm.krayscandles.entity.vampire;


import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class VampireBaron extends VampireBase {

    public static final EntityDataAccessor<Boolean> REINFORCEMENT = SynchedEntityData.defineId(VampireBaron.class, EntityDataSerializers.BOOLEAN);

    public VampireBaron(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public VampireBaron(EntityType<? extends Monster> type, Location location) {
        super(type, location);
    }

    public VampireBaron(Location location) {
        super(InitEntityTypes.VAMPIRE_BARON.get(), location);
    }

    @Override
    public String getRankPrefix() {
        return "baron";
    }

    public boolean isReinforcement() {
        return getEntityData().get(REINFORCEMENT);
    }

    public void setReinforcement() {
        getEntityData().set(REINFORCEMENT, true);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(REINFORCEMENT, false);
    }

    /**
     * Called to register the Entity's attributes (like health, damage, etc.)
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 4);
    }

    /**
     * Called every tick.
     * Use to render flames and smoke.
     */
    @Override
    public void tick() {

        if (getLevel().isClientSide()) {
            if (getDeathSound() == InitSounds.VAMPIRE_BARON_DEATH.get() ){
            getLevel().addParticle(InitParticles.SOUL_FLAME_MOB.get(), getRandomX(0.5D), getRandomY(), getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
            getLevel().addParticle(ParticleTypes.LARGE_SMOKE, getRandomX(0.5D), getRandomY(), getRandomZ(0.1D), 0.0D, 0.0D, 0.0D);
        }

        super.tick();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return InitSounds.VAMPIRE_BARON_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return InitSounds.VAMPIRE_BARON_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.VAMPIRE_BARON_DEATH.get();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        getEntityData().set(REINFORCEMENT, tag.getBoolean("IsReinforcement"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("IsReinforcement", getEntityData().get(REINFORCEMENT));
    }
}

