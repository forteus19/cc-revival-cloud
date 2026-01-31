package dev.vuis.cccr.cloud.packets_idk_if_needed.Server;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.ferullogaming.countercraft.server.ServerManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketServerMatchMakingAccepted extends G_TcpPacketCustomPayload {
    public PacketServerMatchMakingAccepted() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
        ServerManager.instance().isMatchMakingAccepted = true;
    }
}
