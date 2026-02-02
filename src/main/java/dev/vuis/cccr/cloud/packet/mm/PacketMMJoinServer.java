package dev.vuis.cccr.cloud.packet.mm;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketMMJoinServer extends G_TcpPacketCustomPayload {
	public FDSTagCompound tag = new FDSTagCompound("values");

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		tag.write(out);
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
	}
}
