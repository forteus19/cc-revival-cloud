package dev.vuis.cccr.cloud.packet.mm;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import dev.vuis.cccr.cloud.mm.SearchHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public final class PacketMMSearch extends G_TcpPacketCustomPayload {
	private String game;
	private String region;
	private ArrayList<String> maps;

	@Override
	public void writePacketToStream(DataOutputStream out) throws IOException {
	}

	@Override
	public void readPacketFromStream(DataInputStream in) throws IOException {
		game = readString(in);
		region = readString(in);

		int numMaps = in.readInt();
		maps = new ArrayList<>(numMaps);
		for (int i = 0; i < numMaps; i++) {
			maps.add(readString(in));
		}
	}

	@Override
	public void processPacketOnMainThread(G_ITcpConnectionHandler connectionHandler) throws IOException {
		SearchHandler.addSearch(connectionHandler.getConnectionUsername(), game, region, maps);
	}
}
