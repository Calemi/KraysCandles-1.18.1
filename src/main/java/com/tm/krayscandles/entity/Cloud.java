package com.tm.krayscandles.entity;

import com.tm.krayscandles.init.InitEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.UUID;

public class Cloud extends Entity {

    /**
     * The data holding a Player's name.
     */
    private static final EntityDataAccessor<String> PLAYER_UUID = SynchedEntityData.defineId(Cloud.class, EntityDataSerializers.STRING);

    public Cloud(EntityType<?> type, Level level) {
        super(type, level);
    }

    public Cloud(Player player) {
        super(InitEntityTypes.CLOUD.get(), player.getLevel());
        setPos(player.getX(), player.getY() - 1, player.getZ());
        getEntityData().set(PLAYER_UUID, player.getStringUUID());
    }

    private Player getPlayer() {
        return getLevel().getPlayerByUUID(UUID.fromString(getEntityData().get(PLAYER_UUID)));
    }

    @Override
    public void tick() {
        super.tick();

        Player player = getPlayer();

        if (player != null) {

            if (player.isCrouching()) {
                move(MoverType.SELF, new Vec3(0, -0.25F, 0));
            }

            else {

                if (getX() - player.getX() > 0.5F) {
                    move(MoverType.SELF, new Vec3(-1, 0, 0));
                }

                else if (player.getX() - getX() > 0.5F) {
                    move(MoverType.SELF, new Vec3(1, 0, 0));
                }

                if (getZ() - player.getZ() > 0.5F) {
                    move(MoverType.SELF, new Vec3(0, 0, -1));
                }

                else if (player.getZ() - getZ() > 0.5F) {
                    move(MoverType.SELF, new Vec3(0, 0, 1));
                }

                if (Math.abs(player.getY() - getY()) > 0.6F) {
                    setPosRaw(getX(), player.getY() - 0.6F, getZ());
                }
            }

            /*if (getLevel().isClientSide()) {

                if (player instanceof LocalPlayer localPlayer) {

                    if (localPlayer.input.jumping) {

                    }
                }
            }*/


        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        getEntityData().define(PLAYER_UUID, "");
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        getEntityData().set(PLAYER_UUID, tag.getString("player_uuid"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("player_uuid", getEntityData().get(PLAYER_UUID));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
