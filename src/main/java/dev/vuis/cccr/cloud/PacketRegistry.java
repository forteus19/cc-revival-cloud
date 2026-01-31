package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.G_TcpPacket;
import dev.vuis.cccr.cloud.packet.PacketClientRequest;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;

public final class PacketRegistry {
	private PacketRegistry() {
	}

	public static void registerPackets() {
		G_TcpPacket.addPacketMapping(10, PacketClientRequest.class);
		G_TcpPacket.addPacketMapping(11, PacketRequestedPlayerData.class);
	}
}
