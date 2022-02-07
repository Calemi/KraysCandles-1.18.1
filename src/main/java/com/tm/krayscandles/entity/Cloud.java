package com.tm.krayscandles.entity;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.packet.KCPacketHandler;
import com.tm.krayscandles.packet.PacketCloudControl;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
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

    public Cloud(EntityType<?> type, Level level) {
        super(type, level);
    }

    public Cloud(Entity entity) {
        super(InitEntityTypes.CLOUD.get(), entity.getLevel());
        setPos(entity.getX(), entity.getY(), entity.getZ());
    }

    @Override
    public void tick() {
        super.tick();

        if (getFirstPassenger() != null && getFirstPassenger() instanceof Player player) {

            player.resetFallDistance();

            if (getLevel().isClientSide() && getFirstPassenger() instanceof LocalPlayer localPlayer) {

                handleInput(localPlayer);
            }

            handleMovement(player);
        }
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

        KCPacketHandler.INSTANCE.sendToServer(new PacketCloudControl(getEntityData().get(JUMP_KEY), getEntityData().get(CROUCH_KEY), getEntityData().get(FORWARD_KEY), getEntityData().get(BACKWARDS_KEY), getEntityData().get(LEFT_KEY), getEntityData().get(RIGHT_KEY)));
    }

    private void handleMovement(Player player) {

        if (getEntityData().get(JUMP_KEY)) {
            LogHelper.logCommon(KCReference.MOD_NAME, getLevel(), "UP");
            move(MoverType.SELF, new Vec3(0, 0.5, 0));
        }

        if (getEntityData().get(CROUCH_KEY)) {
            LogHelper.logCommon(KCReference.MOD_NAME, getLevel(), "DOWN");
            move(MoverType.SELF, new Vec3(0, -0.5, 0));
        }

        if (getEntityData().get(FORWARD_KEY)) {
            LogHelper.logCommon(KCReference.MOD_NAME, getLevel(), "FORWARD");
            move(MoverType.SELF, player.getForward());
        }

        if (getEntityData().get(BACKWARDS_KEY)) {
            LogHelper.logCommon(KCReference.MOD_NAME, getLevel(), "BACKWARDS");
            move(MoverType.SELF, player.getForward().reverse());
        }

        if (getEntityData().get(LEFT_KEY)) {
            LogHelper.logCommon(KCReference.MOD_NAME, getLevel(), "LEFT");
            move(MoverType.SELF, player.getForward().multiply(1, 0, 1).yRot(Mth.PI / 2));
        }

        if (getEntityData().get(RIGHT_KEY)) {
            LogHelper.logCommon(KCReference.MOD_NAME, getLevel(), "RIGHT");
            move(MoverType.SELF, player.getForward().multiply(1, 0, 1).yRot(-Mth.PI / 2));
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() + 0.1D;
    }

    @Override
    public void ejectPassengers() {}

    @Override
    public void stopRiding() {}

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        getEntityData().set(JUMP_KEY, tag.getBoolean("JumpKey"));
        getEntityData().set(CROUCH_KEY, tag.getBoolean("CrouchKey"));
        getEntityData().set(FORWARD_KEY, tag.getBoolean("ForwardKey"));
        getEntityData().set(BACKWARDS_KEY, tag.getBoolean("BackwardsKey"));
        getEntityData().set(LEFT_KEY, tag.getBoolean("LeftKey"));
        getEntityData().set(RIGHT_KEY, tag.getBoolean("RightKey"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putBoolean("JumpKey", getEntityData().get(JUMP_KEY));
        tag.putBoolean("CrouchKey", getEntityData().get(CROUCH_KEY));
        tag.putBoolean("ForwardKey", getEntityData().get(FORWARD_KEY));
        tag.putBoolean("BackwardsKey", getEntityData().get(BACKWARDS_KEY));
        tag.putBoolean("LeftKey", getEntityData().get(LEFT_KEY));
        tag.putBoolean("RightKey", getEntityData().get(RIGHT_KEY));
    }

    @Override
    protected void defineSynchedData() {
        getEntityData().define(JUMP_KEY, false);
        getEntityData().define(CROUCH_KEY, false);
        getEntityData().define(FORWARD_KEY, false);
        getEntityData().define(BACKWARDS_KEY, false);
        getEntityData().define(LEFT_KEY, false);
        getEntityData().define(RIGHT_KEY, false);

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
