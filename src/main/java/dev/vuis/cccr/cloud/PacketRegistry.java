package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.G_TcpPacket;
import dev.vuis.cccr.cloud.packet.PacketClientRequest;
import dev.vuis.cccr.cloud.packet.PacketClientUpdateStatus;
import dev.vuis.cccr.cloud.packet.PacketEditMood;
import dev.vuis.cccr.cloud.packet.PacketFriendPoke;
import dev.vuis.cccr.cloud.packet.PacketFriendRemove;
import dev.vuis.cccr.cloud.packet.PacketFriendRequest;
import dev.vuis.cccr.cloud.packet.PacketFriendRequestAccept;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemAdded;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.PacketRequestedCloudData;
import dev.vuis.cccr.cloud.packet.PacketRequestedFriends;
import dev.vuis.cccr.cloud.packet.PacketRequestedInventory;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;
import dev.vuis.cccr.cloud.packet.mm.PacketMMJoinServer;
import dev.vuis.cccr.cloud.packet.mm.PacketMMSearch;
import dev.vuis.cccr.cloud.packet.mm.PacketMMSearchCloudCanceled;
import dev.vuis.cccr.cloud.packet.mm.PacketMMSearchHostCanceled;
import dev.vuis.cccr.cloud.packet.mm.PacketPartyCreate;
import dev.vuis.cccr.cloud.packet.server.PacketPlayerDataIncreaseValue;
import dev.vuis.cccr.cloud.packet.server.PacketPlayerDataRandomDrop;
import dev.vuis.cccr.cloud.packet.server.PacketServerGameStatus;
import dev.vuis.cccr.cloud.packet.server.PacketServerMatchMaking;
import dev.vuis.cccr.cloud.packet.server.PacketServerMatchMakingAccepted;

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
		G_TcpPacket.addPacketMapping(15, PacketRequestedFriends.class);
		G_TcpPacket.addPacketMapping(16, PacketFriendRequest.class);
		G_TcpPacket.addPacketMapping(17, PacketFriendRequestAccept.class);
		G_TcpPacket.addPacketMapping(18, PacketFriendRemove.class);
		G_TcpPacket.addPacketMapping(21, PacketInventoryItemAdded.class);
		G_TcpPacket.addPacketMapping(24, PacketFriendPoke.class);
		G_TcpPacket.addPacketMapping(39, PacketServerMatchMaking.class);
		G_TcpPacket.addPacketMapping(41, PacketServerGameStatus.class);
		G_TcpPacket.addPacketMapping(42, PacketMMSearch.class);
		G_TcpPacket.addPacketMapping(43, PacketMMSearchCloudCanceled.class);
		G_TcpPacket.addPacketMapping(44, PacketMMSearchHostCanceled.class);
		G_TcpPacket.addPacketMapping(45, PacketRequestedCloudData.class);
		G_TcpPacket.addPacketMapping(53, PacketEditMood.class);
		G_TcpPacket.addPacketMapping(63, PacketServerMatchMakingAccepted.class);
		G_TcpPacket.addPacketMapping(64, PacketMMJoinServer.class);
		G_TcpPacket.addPacketMapping(66, PacketPlayerDataIncreaseValue.class);
		G_TcpPacket.addPacketMapping(67, PacketPlayerDataRandomDrop.class);
		G_TcpPacket.addPacketMapping(69, PacketClientUpdateStatus.class);
	}
}
