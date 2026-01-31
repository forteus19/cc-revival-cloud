package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.G_TcpPacket;
import dev.vuis.cccr.cloud.packet.PacketClientRequest;
import dev.vuis.cccr.cloud.packet.PacketClientUpdateStatus;
import dev.vuis.cccr.cloud.packet.PacketEditMood;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.PacketRequestedCloudData;
import dev.vuis.cccr.cloud.packet.PacketRequestedInventory;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;
import dev.vuis.cccr.cloud.packet.mm.PacketPartyCreate;

public final class PacketRegistry {
	private PacketRegistry() {
	}

	public static void registerPackets() {
		// see PacketIdList for packet ids
		G_TcpPacket.addPacketMapping(10, PacketClientRequest.class);
		G_TcpPacket.addPacketMapping(11, PacketRequestedPlayerData.class);
		G_TcpPacket.addPacketMapping(12, PacketRequestedInventory.class);
		G_TcpPacket.addPacketMapping(13, PacketPartyCreate.class);
		G_TcpPacket.addPacketMapping(14, PacketNotificationFromCloud.class);
		G_TcpPacket.addPacketMapping(45, PacketRequestedCloudData.class);
		G_TcpPacket.addPacketMapping(53, PacketEditMood.class);
		G_TcpPacket.addPacketMapping(69, PacketClientUpdateStatus.class);
	}
}
