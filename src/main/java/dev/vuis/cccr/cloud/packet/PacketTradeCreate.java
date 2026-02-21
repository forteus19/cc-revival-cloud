package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.data.PunishmentType;
import dev.vuis.cccr.cloud.data.Trade;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.util.PacketUtil;
import dev.vuis.cccr.cloud.util.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class PacketTradeCreate extends G_TcpPacketCustomPayload {
	private String target;
	private List<UUID> offerUuids;
	private List<UUID> returnUuids;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		readString(in);
		target = readString(in);

		int offerSize = in.readInt();
		offerUuids = new ArrayList<>(offerSize);
		for (int i = 0; i < offerSize; i++) {
			offerUuids.add(UUID.fromString(readString(in)));
		}

		int returnSize = in.readInt();
		returnUuids = new ArrayList<>(returnSize);
		for (int i = 0; i < returnSize; i++) {
			returnUuids.add(UUID.fromString(readString(in)));
		}
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		if (connectionHandler.getConnectionUsername().equalsIgnoreCase(target)) {
			return;
		}

		PlayerData requesterData = DataHandler.instance().getPlayerData(connectionHandler.getConnectionUsername());
		if (requesterData == null || requesterData.hasActivePunishment(PunishmentType.BAN_TRADING, PunishmentType.BAN_CLOUD)) {
			return;
		}

		PlayerData targetData = DataHandler.instance().getPlayerData(target);
		if (targetData == null || targetData.hasActivePunishment(PunishmentType.BAN_TRADING, PunishmentType.BAN_CLOUD)) {
			return;
		}

		PlayerInventory requesterInventory = DataHandler.instance().getPlayerInventory(requesterData.uuid);
		PlayerInventory targetInventory = DataHandler.instance().getPlayerInventory(targetData.uuid);

		List<CloudItemStack> offerStacks = new ArrayList<>(offerUuids.size());
		for (UUID offerUuid : offerUuids) {
			CloudItemStack stack = requesterInventory.getItemStack(offerUuid);
			if (stack == null) {
				connectionHandler.sendPacket(new PacketNotificationFromCloud(
					"An offering item could not be found!", CloudConstants.CC_NAME, 0
				));
				return;
			}
			if (!stack.item.isTradable()) {
				return;
			}
			offerStacks.add(stack);
		}

		List<CloudItemStack> returnStacks = new ArrayList<>(returnUuids.size());
		for (UUID returnUuid : returnUuids) {
			CloudItemStack stack = targetInventory.getItemStack(returnUuid);
			if (stack == null) {
				connectionHandler.sendPacket(new PacketNotificationFromCloud(
					"A return item could not be found!", CloudConstants.CC_NAME, 0
				));
				return;
			}
			if (!stack.item.isTradable()) {
				return;
			}
			returnStacks.add(stack);
		}

		DataHandler.instance().populateTrade(UUID.randomUUID(), new Trade(
			Util.utcNow(),
			requesterData.uuid, targetData.uuid,
			offerStacks, returnStacks
		));
		connectionHandler.sendPacket(new PacketNotificationFromCloud(
			String.format("Successfully sent trade request to %s!", target), CloudConstants.CC_NAME, 0
		));
		Cloud.instance().sendPacketToPlayer(target, new PacketNotificationFromCloud(
			String.format("%s sent you a trade request!", connectionHandler.getConnectionUsername()), CloudConstants.CC_NAME, 0
		));
	}
}
