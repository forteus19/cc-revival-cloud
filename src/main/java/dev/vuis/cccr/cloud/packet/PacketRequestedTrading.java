package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.DataHandler;
import dev.vuis.cccr.cloud.data.Trade;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public final class PacketRequestedTrading extends G_TcpPacketCustomPayload {
	private final FDSTagCompound tag = new FDSTagCompound("tradeHandler");

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		tag.write(out);
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
	}

	public static void sendForPlayer(G_ITcpConnectionHandler connectionHandler, String username) {
		Map<UUID, Trade> trades = DataHandler.instance().getPlayerTrades(username);
		if (trades == null) {
			return;
		}

		PacketRequestedTrading packet = new PacketRequestedTrading();
		int numOpenTrades = 0;
		int numHistoryTrades = 0;

		for (Map.Entry<UUID, Trade> entry : trades.entrySet()) {
			UUID uuid = entry.getKey();
			Trade trade = entry.getValue();

			String tagName;
			if (trade.result == Trade.Result.PENDING) {
				tagName = "openTrade" + numOpenTrades++;
			} else {
				tagName = "history" + numHistoryTrades++;
			}

			FDSTagCompound tradeTag = new FDSTagCompound(tagName);
			tradeTag.setString("uuid", uuid.toString());
			trade.writeTag(tradeTag);
			packet.tag.setTagCompound(tradeTag.getTag(), tradeTag);
		}

		packet.tag.setInteger("openTradeSize", numOpenTrades);
		packet.tag.setInteger("historySize", numHistoryTrades);

		connectionHandler.sendPacket(packet);
	}
}
