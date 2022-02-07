package com.tm.krayscandles.packet;

import com.tm.krayscandles.main.KCReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class KCPacketHandler {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(KCReference.MOD_ID, KCReference.MOD_ID),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    public static void init() {
        int id = 0;
        KCPacketHandler.INSTANCE.registerMessage(++id, PacketCloudControl.class, PacketCloudControl::toBytes, PacketCloudControl::new, PacketCloudControl::handle);
    }
}
