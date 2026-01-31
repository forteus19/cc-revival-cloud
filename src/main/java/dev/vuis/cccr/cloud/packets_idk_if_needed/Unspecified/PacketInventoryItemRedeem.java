package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketInventoryItemRedeem extends G_TcpPacketCustomPayload {
    private String givenCode;

    public PacketInventoryItemRedeem(String par1) {
        this.givenCode = par1;
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        writeString(par1, this.givenCode);
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}
