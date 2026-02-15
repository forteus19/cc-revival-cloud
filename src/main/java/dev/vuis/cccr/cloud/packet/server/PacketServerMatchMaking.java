package dev.vuis.cccr.cloud.packet.server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.cloud.server.G_CloudTcpConnectionHandler;
import dev.vuis.cccr.cloud.Cloud;
import dev.vuis.cccr.cloud.mm.server.ServerStatus;
import dev.vuis.cccr.cloud.util.PacketUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketServerMatchMaking extends G_TcpPacketCustomPayload {
	private boolean isMatchMaking;
	private String serverRegion;
	private String matchMakingPassword;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		isMatchMaking = in.readBoolean();
		serverRegion = readString(in);
		matchMakingPassword = readString(in);
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		if (PacketUtil.kickIfServer(connectionHandler)) {
			return;
		}
		if (!isMatchMaking) {
			return;
		}
		if (!Cloud.instance().checkMatchMakingPassword(matchMakingPassword)) {
			((G_CloudTcpConnectionHandler) connectionHandler).kickClientConnection("Invalid matchmaking password!");
			return;
		}

		String previous = ServerStatus.REGION.put(connectionHandler.getConnectionUsername(), serverRegion);
		if (previous == null) {
			connectionHandler.sendPacket(new PacketServerMatchMakingAccepted());
			Cloud.instanceLog("Registered %s for matchmaking", connectionHandler.getConnectionUsername());
		}
	}
}
