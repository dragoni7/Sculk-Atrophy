package com.github.dragoni7;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {

	private static SimpleChannel INSTANCE;
	private static int ID = 0;
	
	private static int nextID() {
		return ID++;
	}
	
	public static void registerMessages() {
		INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(SculkAtrophy.MODID, "sculkatrophy_network"), () -> "1.0", s -> true, s -> true);
		
		INSTANCE.messageBuilder(PacketApplySculkAtrophy.class, nextID())
		.encoder(PacketApplySculkAtrophy::toBytes)
		.decoder(PacketApplySculkAtrophy::new)
		.consumer(PacketApplySculkAtrophy::handle)
		.add();
	}
	
	public static void sendToClient(Object packet, ServerPlayer player) {
		INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
	}
	
	public static void sendToServer(Object packet) {
		INSTANCE.sendToServer(packet);
	}
}
