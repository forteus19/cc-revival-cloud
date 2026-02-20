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
public final class PacketTradeRequestedUser extends G_TcpPacketCustomPayload {
	private String username;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		writeString(out, username);
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
	}
}
