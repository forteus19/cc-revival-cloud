package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
}
