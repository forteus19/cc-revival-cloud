package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerData;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketClientUpdateStatus extends G_TcpPacketCustomPayload {
	private final FDSTagCompound tag = new FDSTagCompound("data");

	@Override
	public void writePacketToStream(DataOutputStream out) {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		tag.load(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
		if (PacketUtil.checkAuthentication(connectionHandler)) {
			return;
		}

		String status = tag.getString("status");
		if (status == null) {
			return;
		}

		PlayerData playerData = DataMaps.getPlayerData(connectionHandler.getConnectionUsername());
		if (playerData == null) {
			return;
		}
		playerData.status.set(status);
	}
}
