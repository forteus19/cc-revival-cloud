package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import dev.vuis.cccr.cloud.data.DataMaps;
import dev.vuis.cccr.cloud.data.PlayerInventory;
import dev.vuis.cccr.cloud.item.CloudItemCategory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketRequestedItemDefault extends G_TcpPacketCustomPayload {
	public String username;
	public CloudItemCategory category;
	public final FDSTagCompound tag = new FDSTagCompound("itemDefault");

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		writeString(out, username);
		out.writeBoolean(true);
		writeString(out, category.getId());
		tag.write(out);
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
	}

	public static void sendForPlayer(G_ITcpConnectionHandler connectionHandler, String username, CloudItemCategory category, boolean sendIfEmpty) {
		if (category.getId() == null) {
			return;
		}

		PlayerInventory inventory = DataMaps.getPlayerInventory(username, false);
		if (inventory == null || (!sendIfEmpty && !inventory.hasDefaults(category))) {
			return;
		}

		PacketRequestedItemDefault packet = new PacketRequestedItemDefault();
		packet.username = username;
		packet.category = category;
		inventory.writeDefaultsTag(packet.tag, category);

		connectionHandler.sendPacket(packet);
	}
}
