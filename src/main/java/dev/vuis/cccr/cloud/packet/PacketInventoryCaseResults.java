package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.item.CloudItem;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketInventoryCaseResults extends G_TcpPacketCustomPayload {
	private final List<CloudItem> items;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		out.writeInt(items.size());
		for (CloudItem item : items) {
			out.writeInt(item.getId());
		}
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
	}
}
