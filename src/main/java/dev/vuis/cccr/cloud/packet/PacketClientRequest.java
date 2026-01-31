package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.RequestType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public final class PacketClientRequest extends G_TcpPacketCustomPayload {
	private RequestType type;
	private String[] requestData;

	@Override
	public void writePacketToStream(DataOutputStream out) {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		String typeStr = readString(in);
		type = Arrays.stream(RequestType.values()).filter(requestType -> requestType.toString().equals(typeStr)).findFirst().orElseThrow();
		if (in.readBoolean()) {
			requestData = new String[in.readInt()];
			for (int i = 0; i < requestData.length; i++) {
				requestData[i] = readString(in);
			}
		}
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
		System.out.println(type);
	}
}
