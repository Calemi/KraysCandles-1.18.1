package com.tm.krayscandles.entity.wraith;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

/**
 * The base class for Wraiths.
 */
public abstract class WraithBase extends Monster {

    /**
     * The data holding a Player's name.
     */
    private static final EntityDataAccessor<String> PLAYER_NAME = SynchedEntityData.defineId(WraithBase.class, EntityDataSerializers.STRING);

    /**
     * Constructs a Wraith
     * @param type The type of entity.
     * @param level The level of the entity.
     */
    public WraithBase(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    /**
     * Constructs a Wraith
     * @param type The type of entity.
     * @param location The location of the entity.
     * @param playerName A Player's name, if one exists.
     */
    public WraithBase(EntityType<? extends Monster> type, Location location, String playerName) {
        super(type, location.level);
        setPos(location.x, location.y, location.z);
        getEntityData().set(PLAYER_NAME, playerName);
    }

    /**
     * @return The Wraith's type.
     */
    public abstract WraithType getWraithType();

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(PLAYER_NAME, "");
    }

    /**
     * Called to register the Entity's attributes (like health, damage, etc.)
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 4);
    }

    /**
     * Registers the Entity's goals.
     */
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /**
     * Called every tick.
     * Use to render flames and smoke.
     */
    @Override
    public void tick() {

        if (!onGround && getDeltaMovement().y < 0.0D) {
            setDeltaMovement(getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }

        if (getLevel().isClientSide()) {
            getLevel().addParticle(getWraithType().getParticle(), getRandomX(0.5D), getRandomY(), getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            getLevel().addParticle(ParticleTypes.LARGE_SMOKE, getRandomX(0.5D), getRandomY(), getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
        }

        super.tick();
    }

    /**
     * @return The Entity's displayed name.
     */
    @Override
    public Component getDisplayName() {

        if (getEntityData().get(PLAYER_NAME).isEmpty()) {
            return new TranslatableComponent("entity.krayscandles.wraith_" + getWraithType().getNameKey());
        }

        return new TextComponent(getEntityData().get(PLAYER_NAME) + "'s ").append(new TranslatableComponent("entity.krayscandles.wraith_" + getWraithType().getNameKey()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        getEntityData().set(PLAYER_NAME, tag.getString("player_name"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("player_name", getEntityData().get(PLAYER_NAME));
    }

    @Override
    public boolean shouldShowName() {
        return true;
    }

    @Override
    protected int calculateFallDamage(float distance, float damageMultiplier) {
        return 0;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    protected int getExperienceReward(Player player) {
        return 40;
    }

    @Override
    public boolean removeWhenFarAway(double distance) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return InitSounds.WRAITH_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return InitSounds.WRAITH_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.WRAITH_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {}

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /**
     * The different types of Wraiths.
     */
    public enum WraithType {

        FIRE ("fire", InitParticles.SOUL_FLAME_FIRE.get()),
        WATER ( "water", InitParticles.SOUL_FLAME_WATER.get()),
        AIR ("air", InitParticles.SOUL_FLAME_AIR.get()),
        EXPLOSION ("explosion", InitParticles.SOUL_FLAME_EXPLOSION.get()),
        MAGIC ("magic", InitParticles.SOUL_FLAME_MAGIC.get()),
        MOB ("mob", InitParticles.SOUL_FLAME_MOB.get());

        private final String nameKey;
        private final SimpleParticleType particle;

        WraithType(String nameKey, SimpleParticleType particle) {
            this.nameKey = nameKey;
            this.particle = particle;
        }

        public String getNameKey() {
            return nameKey;
        }

        public SimpleParticleType getParticle() {
            return particle;
        }
    }
}
