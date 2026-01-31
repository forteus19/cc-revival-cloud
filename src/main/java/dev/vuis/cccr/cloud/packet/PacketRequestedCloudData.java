package dev.vuis.cccr.cloud.packet;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class PacketRequestedCloudData extends G_TcpPacketCustomPayload {
	public FDSTagCompound tag = new FDSTagCompound("cloudstats");

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
		tag.write(out);
	}

	@Override
	public void readPacketFromStream(DataInputStream in) {
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) {
	}
}
