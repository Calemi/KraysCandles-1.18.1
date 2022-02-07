package com.tm.krayscandles.packet;


import com.tm.krayscandles.entity.Cloud;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketCloudControl {

    private boolean jumpKey;
    private boolean crouchKey;
    private boolean forwardKey;
    private boolean backwardsKey;
    private boolean leftKey;
    private boolean rightKey;

    public PacketCloudControl() {}

    public PacketCloudControl(boolean jumpKey, boolean crouchKey, boolean forwardKey, boolean backwardsKey, boolean leftKey, boolean rightKey) {
        this.jumpKey = jumpKey;
        this.crouchKey = crouchKey;
        this.forwardKey = forwardKey;
        this.backwardsKey = backwardsKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public PacketCloudControl(FriendlyByteBuf buf) {
        jumpKey = buf.readBoolean();
        crouchKey = buf.readBoolean();
        forwardKey = buf.readBoolean();
        backwardsKey = buf.readBoolean();
        leftKey = buf.readBoolean();
        rightKey = buf.readBoolean();
    }

    public void toBytes (FriendlyByteBuf buf) {
        buf.writeBoolean(jumpKey);
        buf.writeBoolean(crouchKey);
        buf.writeBoolean(forwardKey);
        buf.writeBoolean(backwardsKey);
        buf.writeBoolean(leftKey);
        buf.writeBoolean(rightKey);
    }

    public void handle (Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null && player.getVehicle() instanceof Cloud cloud) {

                cloud.getEntityData().set(Cloud.JUMP_KEY, jumpKey);
                cloud.getEntityData().set(Cloud.CROUCH_KEY, crouchKey);
                cloud.getEntityData().set(Cloud.FORWARD_KEY, forwardKey);
                cloud.getEntityData().set(Cloud.BACKWARDS_KEY, backwardsKey);
                cloud.getEntityData().set(Cloud.LEFT_KEY, leftKey);
                cloud.getEntityData().set(Cloud.RIGHT_KEY, rightKey);
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
