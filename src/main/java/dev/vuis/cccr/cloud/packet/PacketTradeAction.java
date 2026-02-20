package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.CloudConstants;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.data.Trade;
import dev.vuis.cccr.cloud.item.CloudItemStack;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public final class PacketTradeAction extends G_TcpPacketCustomPayload {
	private UUID tradeUuid;
	private int action;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		readString(in);
		tradeUuid = UUID.fromString(readString(in));
		action = in.readInt();
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		Trade trade = DataHandler.instance().getTrade(tradeUuid);
		if (trade == null) {
			return;
		}

		PlayerInventory offererInventory = DataHandler.instance().getPlayerInventory(trade.offererUuid);
		if (offererInventory == null) {
			trade.finalize(Trade.Result.ERROR);
			return;
		}
		PlayerInventory returnerInventory = DataHandler.instance().getPlayerInventory(trade.returnerUuid);
		if (returnerInventory == null) {
			trade.finalize(Trade.Result.ERROR);
			return;
		}

		if (!connectionHandler.getConnectionUsername().equalsIgnoreCase(returnerInventory.username.get())) {
			return;
		}

		switch (action) {
			case 1 -> {
				for (CloudItemStack offerStack : trade.offerStacks) {
					offererInventory.removeItemStack(offerStack.uuid, true);
					returnerInventory.addItemStack(offerStack, true);
				}
				for (CloudItemStack returnStack : trade.returnStacks) {
					returnerInventory.removeItemStack(returnStack.uuid, true);
					offererInventory.addItemStack(returnStack, true);
				}

				trade.finalize(Trade.Result.ACCEPTED);
				connectionHandler.sendPacket(new PacketNotificationFromCloud(
					"Trade request accepted!", CloudConstants.CC_NAME, 0
				));
				Cloud.instance().sendPacketToPlayer(offererInventory.username.get(), new PacketNotificationFromCloud(
					String.format("%s accepted your trade request!", connectionHandler.getConnectionUsername()), CloudConstants.CC_NAME, 0
				));
			}
			case 2 -> {
				trade.finalize(Trade.Result.DENIED);
				connectionHandler.sendPacket(new PacketNotificationFromCloud(
					"Trade request denied!", CloudConstants.CC_NAME, 0
				));
				Cloud.instance().sendPacketToPlayer(offererInventory.username.get(), new PacketNotificationFromCloud(
					String.format("%s denied your trade request!", connectionHandler.getConnectionUsername()), CloudConstants.CC_NAME, 0
				));
			}
		}
	}
}
