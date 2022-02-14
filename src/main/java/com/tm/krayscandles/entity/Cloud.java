package com.tm.krayscandles.entity;

import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.packet.KCPacketHandler;
import com.tm.krayscandles.packet.PacketCloudControl;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class Cloud extends Entity {

    public static final EntityDataAccessor<Boolean> JUMP_KEY = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> CROUCH_KEY = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> FORWARD_KEY = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> BACKWARDS_KEY = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> LEFT_KEY = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> RIGHT_KEY = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SPRINT = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.BOOLEAN);

    private static int LIFE_TIME = 60;
    private int current_life = LIFE_TIME;

    public Cloud(EntityType<?> type, Level level) {
        super(type, level);
    }

    public Cloud(Entity entity) {
        super(InitEntityTypes.CLOUD.get(), entity.getLevel());
        setPos(entity.getX(), entity.getY(), entity.getZ());
        this.xo = entity.getX();
        this.yo = entity.getY();
        this.zo = entity.getZ();
    }

    @Override
    public void tick() {
        super.tick();

        resetFallDistance();

        if (getFirstPassenger() != null && getFirstPassenger() instanceof Player player) {

            if (getLevel().isClientSide() && getFirstPassenger() instanceof LocalPlayer localPlayer) {

                handleInput(localPlayer);
            }

            handleMovement(player);

            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 19, false, false));
        }

        for (int i = 0; i < current_life / 10; i++) {

            if (getLevel().isClientSide()) {

                double x = getLevel().getRandom().nextDouble() - 0.5F;
                double z = getLevel().getRandom().nextDouble() - 0.5F;
                double y = getLevel().getRandom().nextDouble() * 0.5 - (0.5 / 2);

                getLevel().addParticle(ParticleTypes.CLOUD, getX() + x, getY() + y + 0.6D, getZ() + z, 0, 0, 0);

                if (getFirstPassenger() instanceof Player player && player.getInventory().getArmor(2).getItem() == InitItems.BLESSED_NIGHT_MANTLE.get()) {
                    getLevel().addParticle(InitParticles.SOUL_FLAME_MAGIC.get(), getX() + x, getY() + y + 0.6D, getZ() + z, 0, -0.1D, 0);
                }
            }
        }

        if (getFirstPassenger() == null) current_life--;
        else current_life = LIFE_TIME;

        if(current_life <= 0) kill();
    }

    private void handleInput(LocalPlayer player) {

        if (player.input.jumping && !getEntityData().get(JUMP_KEY)) {
            getEntityData().set(JUMP_KEY, true);
        }

        else if (!player.input.jumping && getEntityData().get(JUMP_KEY)) {
            getEntityData().set(JUMP_KEY, false);
        }

        if (player.input.shiftKeyDown && !getEntityData().get(CROUCH_KEY)) {
            getEntityData().set(CROUCH_KEY, true);
        }

        else if (!player.input.shiftKeyDown && getEntityData().get(CROUCH_KEY)) {
            getEntityData().set(CROUCH_KEY, false);
        }

        if (player.input.up && !getEntityData().get(FORWARD_KEY)) {
            getEntityData().set(FORWARD_KEY, true);
        }

        else if (!player.input.up && getEntityData().get(FORWARD_KEY)) {
            getEntityData().set(FORWARD_KEY, false);
        }

        if (player.input.down && !getEntityData().get(BACKWARDS_KEY)) {
            getEntityData().set(BACKWARDS_KEY, true);
        }

        else if (!player.input.down && getEntityData().get(BACKWARDS_KEY)) {
            getEntityData().set(BACKWARDS_KEY, false);
        }

        if (player.input.left && !getEntityData().get(LEFT_KEY)) {
            getEntityData().set(LEFT_KEY, true);
        }

        else if (!player.input.left && getEntityData().get(LEFT_KEY)) {
            getEntityData().set(LEFT_KEY, false);
        }

        if (player.input.right && !getEntityData().get(RIGHT_KEY)) {
            getEntityData().set(RIGHT_KEY, true);
        }

        else if (!player.input.right && getEntityData().get(RIGHT_KEY)) {
            getEntityData().set(RIGHT_KEY, false);
        }

        if (player.isSprinting() && !getEntityData().get(SPRINT)) {
            getEntityData().set(SPRINT, true);
        }

        else if (!player.isSprinting() && getEntityData().get(SPRINT)) {
            getEntityData().set(SPRINT, false);
        }

        KCPacketHandler.INSTANCE.sendToServer(new PacketCloudControl(getEntityData().get(JUMP_KEY), getEntityData().get(CROUCH_KEY), getEntityData().get(FORWARD_KEY), getEntityData().get(BACKWARDS_KEY), getEntityData().get(LEFT_KEY), getEntityData().get(RIGHT_KEY), getEntityData().get(SPRINT)));
    }

    private void handleMovement(Player player) {

        double speed = 0.5D;

        if (getEntityData().get(SPRINT)) {
            speed += 0.5D;
        }

        if (getEntityData().get(JUMP_KEY)) {
            move(MoverType.SELF, new Vec3(0, speed, 0));
        }

        if (getEntityData().get(CROUCH_KEY)) {
            move(MoverType.SELF, new Vec3(0, -speed, 0));
        }

        if (getEntityData().get(FORWARD_KEY)) {
            move(MoverType.SELF, player.getForward().multiply(speed, speed, speed));
        }

        if (getEntityData().get(BACKWARDS_KEY)) {
            move(MoverType.SELF, player.getForward().multiply(speed, speed, speed).reverse());
        }

        if (getEntityData().get(LEFT_KEY)) {
            move(MoverType.SELF, player.getForward().multiply(speed, 0, speed).yRot(Mth.PI / 2));
        }

        if (getEntityData().get(RIGHT_KEY)) {
            move(MoverType.SELF, player.getForward().multiply(speed, 0, speed).yRot(-Mth.PI / 2));
        }
    }

    @Override
    public void lerpTo(double pX, double pY, double pZ, float pYaw, float pPitch, int pPosRotationIncrements, boolean pTeleport) {
        this.setDeltaMovement(pX, pY, pZ);
    }

    @Override
    public void lerpMotion(double pX, double pY, double pZ) {
        this.setDeltaMovement(pX, pY, pZ);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);

    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() - 1;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        kill();
        return super.hurt(source, amount);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        getEntityData().set(JUMP_KEY, tag.getBoolean("JumpKey"));
        getEntityData().set(CROUCH_KEY, tag.getBoolean("CrouchKey"));
        getEntityData().set(FORWARD_KEY, tag.getBoolean("ForwardKey"));
        getEntityData().set(BACKWARDS_KEY, tag.getBoolean("BackwardsKey"));
        getEntityData().set(LEFT_KEY, tag.getBoolean("LeftKey"));
        getEntityData().set(RIGHT_KEY, tag.getBoolean("RightKey"));
        getEntityData().set(SPRINT, tag.getBoolean("Sprint"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putBoolean("JumpKey", getEntityData().get(JUMP_KEY));
        tag.putBoolean("CrouchKey", getEntityData().get(CROUCH_KEY));
        tag.putBoolean("ForwardKey", getEntityData().get(FORWARD_KEY));
        tag.putBoolean("BackwardsKey", getEntityData().get(BACKWARDS_KEY));
        tag.putBoolean("LeftKey", getEntityData().get(LEFT_KEY));
        tag.putBoolean("RightKey", getEntityData().get(RIGHT_KEY));
        tag.putBoolean("Sprint", getEntityData().get(SPRINT));
    }

    @Override
    protected void defineSynchedData() {
        getEntityData().define(JUMP_KEY, false);
        getEntityData().define(CROUCH_KEY, false);
        getEntityData().define(FORWARD_KEY, false);
        getEntityData().define(BACKWARDS_KEY, false);
        getEntityData().define(LEFT_KEY, false);
        getEntityData().define(RIGHT_KEY, false);
        getEntityData().define(SPRINT, false);

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
