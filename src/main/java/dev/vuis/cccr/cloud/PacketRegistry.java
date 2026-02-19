package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.G_TcpPacket;
import dev.vuis.cccr.cloud.packet.PacketCheckPrestige;
import dev.vuis.cccr.cloud.packet.PacketClientRequest;
import dev.vuis.cccr.cloud.packet.PacketClientUpdateStatus;
import dev.vuis.cccr.cloud.packet.PacketConsoleCommand;
import dev.vuis.cccr.cloud.packet.PacketConsoleMessage;
import dev.vuis.cccr.cloud.packet.PacketEditMood;
import dev.vuis.cccr.cloud.packet.PacketFriendPoke;
import dev.vuis.cccr.cloud.packet.PacketFriendRemove;
import dev.vuis.cccr.cloud.packet.PacketFriendRequest;
import dev.vuis.cccr.cloud.packet.PacketFriendRequestAccept;
import dev.vuis.cccr.cloud.packet.PacketInventoryCaseResults;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemAdded;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemDelete;
import dev.vuis.cccr.cloud.packet.PacketInventoryItemRemoved;
import dev.vuis.cccr.cloud.packet.PacketInventoryOpenCase;
import dev.vuis.cccr.cloud.packet.PacketInventorySetItemDefault;
import dev.vuis.cccr.cloud.packet.PacketNotificationFromCloud;
import dev.vuis.cccr.cloud.packet.PacketRequestedCloudData;
import dev.vuis.cccr.cloud.packet.PacketRequestedFriends;
import dev.vuis.cccr.cloud.packet.PacketRequestedInventory;
import dev.vuis.cccr.cloud.packet.PacketRequestedItemDefault;
import dev.vuis.cccr.cloud.packet.PacketRequestedPlayerData;
import dev.vuis.cccr.cloud.packet.market.PacketMarketStoreOrder;
import dev.vuis.cccr.cloud.packet.market.PacketRequestedMarketStoreItems;
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
import dev.vuis.cccr.cloud.packet.server.PacketServerNotification;

public final class PacketRegistry {
	private PacketRegistry() {
	}

	public static void registerPackets() {
		// see PacketIdListMain for packet ids
		G_TcpPacket.addPacketMapping(10, PacketClientRequest.class);
		G_TcpPacket.addPacketMapping(11, PacketRequestedPlayerData.class);
		G_TcpPacket.addPacketMapping(12, PacketRequestedInventory.class);
		G_TcpPacket.addPacketMapping(13, PacketPartyCreate.class);
		G_TcpPacket.addPacketMapping(14, PacketNotificationFromCloud.class);
		G_TcpPacket.addPacketMapping(15, PacketRequestedFriends.class);
		G_TcpPacket.addPacketMapping(16, PacketFriendRequest.class);
		G_TcpPacket.addPacketMapping(17, PacketFriendRequestAccept.class);
		G_TcpPacket.addPacketMapping(18, PacketFriendRemove.class);
		G_TcpPacket.addPacketMapping(19, PacketInventorySetItemDefault.class);
		G_TcpPacket.addPacketMapping(20, PacketRequestedItemDefault.class);
		G_TcpPacket.addPacketMapping(21, PacketInventoryItemAdded.class);
		G_TcpPacket.addPacketMapping(22, PacketInventoryOpenCase.class);
		G_TcpPacket.addPacketMapping(23, PacketInventoryCaseResults.class);
		G_TcpPacket.addPacketMapping(24, PacketFriendPoke.class);
		G_TcpPacket.addPacketMapping(39, PacketServerMatchMaking.class);
		G_TcpPacket.addPacketMapping(40, PacketServerNotification.class);
		G_TcpPacket.addPacketMapping(41, PacketServerGameStatus.class);
		G_TcpPacket.addPacketMapping(42, PacketMMSearch.class);
		G_TcpPacket.addPacketMapping(43, PacketMMSearchCloudCanceled.class);
		G_TcpPacket.addPacketMapping(44, PacketMMSearchHostCanceled.class);
		G_TcpPacket.addPacketMapping(45, PacketRequestedCloudData.class);
		G_TcpPacket.addPacketMapping(46, PacketInventoryItemDelete.class);
		G_TcpPacket.addPacketMapping(49, PacketInventoryItemRemoved.class);
		G_TcpPacket.addPacketMapping(50, PacketRequestedMarketStoreItems.class);
		G_TcpPacket.addPacketMapping(51, PacketMarketStoreOrder.class);
		G_TcpPacket.addPacketMapping(53, PacketEditMood.class);
		G_TcpPacket.addPacketMapping(63, PacketServerMatchMakingAccepted.class);
		G_TcpPacket.addPacketMapping(64, PacketMMJoinServer.class);
		G_TcpPacket.addPacketMapping(66, PacketPlayerDataIncreaseValue.class);
		G_TcpPacket.addPacketMapping(67, PacketPlayerDataRandomDrop.class);
		G_TcpPacket.addPacketMapping(69, PacketClientUpdateStatus.class);
		G_TcpPacket.addPacketMapping(74, PacketConsoleCommand.class);
		G_TcpPacket.addPacketMapping(75, PacketConsoleMessage.class);
		G_TcpPacket.addPacketMapping(96, PacketCheckPrestige.class);
	}
}
