package com.tm.krayscandles.entity.vampire;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.init.InitEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

import java.util.Random;

public abstract class VampireBase extends Monster {

    public static final EntityDataAccessor<String> VAMPIRE_NAME = SynchedEntityData.defineId(VampireBase.class, EntityDataSerializers.STRING);

    /**
     * Constructs a Vampire
     * @param type The type of entity.
     * @param level The level of the entity.
     */
    public VampireBase(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    /**
     * Constructs a Vampire
     * @param type The type of entity.
     * @param location The Location of the entity.
     */
    public VampireBase(EntityType<? extends Monster> type, Location location) {
        super(type, location.level);
        setPos(location.x, location.y, location.z);
    }

    public abstract String getRankPrefix();

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(VAMPIRE_NAME, "");
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
     * @return The Entity's displayed name with a random name from the list.
     */
    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("entity.krayscandles.vampire_rank_" + getRankPrefix()).append(" " + getEntityData().get(VAMPIRE_NAME));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        getEntityData().set(VAMPIRE_NAME, tag.getString("VampireName"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("VampireName", getEntityData().get(VAMPIRE_NAME));
    }

    @Override
    public boolean isMaxGroupSizeReached(int size) {
        return size >= 4;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 4;
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
    protected void playStepSound(BlockPos pos, BlockState state) {}

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public String getRandomName() {

        String[] names = new String[]{
                "Anderson",
                "Edward",
                "Von",
                "Richard",
                "Geralt",
                "Bannon",
                "Von Griddle",
                "Bruce",
                "Geddon",
                "Elijah",
                "Valentine",
                "Lance",
                "Brandyn",
                "Alec",
                "Jorin",
                "Jorah",
                "Daire",
                "Nicodemus",
                "Malik",
                "Harold",
                "Duncan",
                "Godfrey",
                "Lothaire",
                "Auberon",
                "Lucian",
                "Mathias",
                "Orion",
                "Norrix",
                "Arthur",
                "Lawrence",
                "Dracula",
        };

        return names[new Random().nextInt(names.length)];
    }
}
