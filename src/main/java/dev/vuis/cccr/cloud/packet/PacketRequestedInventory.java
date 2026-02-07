package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public final class PacketRequestedInventory extends G_TcpPacketCustomPayload {
	public String username;
	public int section;
	public boolean hasInv;
	public FDSTagCompound tagSplit = new FDSTagCompound("invSplit");

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		writeString(out, username);
		out.writeInt(section);
		out.writeBoolean(hasInv);
		if (hasInv) {
			tagSplit.write(out);
		}
	}

	@Override
	public void readPacketFromStream(DataInputStream in) {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
	}

	public static void sendForPlayer(G_ITcpConnectionHandler connectionHandler, String username) {
		PlayerInventory inventory = DataMaps.getPlayerInventory(username, false);
		if (inventory == null) {
			return;
		}

		List<FDSTagCompound> tags = inventory.writeStacksTagList();
		for (int i = 0; i < tags.size(); i++) {
			PacketRequestedInventory packet = new PacketRequestedInventory();
			packet.username = connectionHandler.getConnectionUsername();
			packet.section = i;
			packet.hasInv = true;
			packet.tagSplit = tags.get(i);

			connectionHandler.sendPacket(packet);
		}
	}
}
