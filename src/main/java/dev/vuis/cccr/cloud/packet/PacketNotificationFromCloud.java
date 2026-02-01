package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public final class PacketNotificationFromCloud extends G_TcpPacketCustomPayload {
	private String message;
	private String title;
	private int type;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		writeString(out, title);
		writeString(out, message);
		writeString(out, Integer.toString(type));
	}

	@Override
	public void readPacketFromStream(DataInputStream in) {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
	}
}
