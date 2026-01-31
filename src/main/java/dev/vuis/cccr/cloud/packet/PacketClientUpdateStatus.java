package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.IPlayerData;
import dev.vuis.cccr.cloud.main.Cloud;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketClientUpdateStatus extends G_TcpPacketCustomPayload {
	FDSTagCompound tag = new FDSTagCompound("data");

	@Override
	public void writePacketToStream(DataOutputStream out) {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		tag.load(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
		String status = tag.getString("status");
		if (status == null) {
			return;
		}

		IPlayerData playerData = Cloud.getInstance().getDataHolder().getPlayerData(connectionHandler.getConnectionUsername(), false);
		if (playerData == null) {
			return;
		}
		playerData.setStatus(status);
	}
}
