package dev.vuis.cccr.cloud;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacket;
import com.f3rullo14.cloud.server.G_CloudManager;
import com.f3rullo14.cloud.server.ICloudPacketHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CloudPacketHandler implements ICloudPacketHandler {
	private final G_CloudManager cloudManager;

	@Override
	public void handlePacket(G_ITcpConnectionHandler connectionHandler, G_TcpPacket packet) {
		cloudManager.log(packet.getClass().getName());
	}
}
