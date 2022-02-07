package com.tm.krayscandles.entity.vampire;


import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.tm.krayscandles.init.InitEntityTypes.VAMPIRE_BARONESS;

public class VampireBaroness extends Monster {

    private static final EntityDataAccessor<String> VAMPIRE_NAME = SynchedEntityData.defineId(VampireBaroness.class, EntityDataSerializers.STRING);

    /**
     * Constructs a Vampire
     *
     * @param type  The type of entity.
     * @param level The level of the entity.
     */
    public VampireBaroness(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        getEntityData().set(VAMPIRE_NAME, randName());
    }

    /**
     * Constructs a Vampire
     *
     * @param level The level of the entity.
     */
    public VampireBaroness(Level level) {
        super(VAMPIRE_BARONESS.get(), level);
        getEntityData().set(VAMPIRE_NAME, randName());
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
     * @return The Entity's displayed name with a random name from the list.
     */
    @Override
    public Component getDisplayName() {
        return new TextComponent("Baroness" + " " + getEntityData().get(VAMPIRE_NAME));
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
            setDeltaMovement(getDeltaMovement().multiply(1.0D, 1.0D, 1.0D));
        }
        getLevel().addParticle(InitParticles.SOUL_FLAME_MAGIC.get(), getRandomX(0.5D), getRandomY(), getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
        getLevel().addParticle(ParticleTypes.LARGE_SMOKE, getRandomX(0.5D), getRandomY(), getRandomZ(0.1D), 0.0D, 0.0D, 0.0D);

        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(VAMPIRE_NAME, "");
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
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public String randName() {
        List<String> list = new ArrayList<>();
        list.add("Silvana");
        list.add("Jillian");
        list.add("Elizabeth");
        list.add("Gertrude");
        list.add("Judy");
        list.add("Elizabeth");
        list.add("Lexie");
        list.add("Betsy");
        list.add("Eryn");
        list.add("Emily");
        list.add("Stephanie");
        list.add("Stephania");
        list.add("Rochelle");
        list.add("Richelle");
        list.add("Margaret");
        list.add("Jane");
        list.add("Rowena");
        list.add("Hestia");
        list.add("Charity");
        list.add("Feronia");
        list.add("Valenthia");
        list.add("Valentine");
        list.add("Petra");
        list.add("Ravette");
        list.add("Minerva");
        list.add("Gretchen");
        list.add("Silvia");
        list.add("Nora");
        list.add("Lorraine");
        list.add("Ursula");

        int index = new Random().nextInt(list.size());
        final String name = list.get(index);
        return name;
    }


}


