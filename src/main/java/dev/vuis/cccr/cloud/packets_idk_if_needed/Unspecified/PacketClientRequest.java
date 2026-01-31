package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.ferullogaming.countercraft.client.cloud.ClientCloudManager;
import com.ferullogaming.countercraft.server.ServerCloudManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketClientRequest extends G_TcpPacketCustomPayload {
    private RequestType type;
    private String[] requestData;

    public PacketClientRequest(RequestType par1, String... par2Data) {
        this.type = par1;
        this.requestData = par2Data;
    }

    @SideOnly(Side.CLIENT)
    public static void sendRequest(RequestType par1, String... par2) {
        PacketClientRequest packet = new PacketClientRequest(par1, par2);
        ClientCloudManager.sendPacket(packet);
    }

    @SideOnly(Side.SERVER)
    public static void sendRequestServer(RequestType par1, String... par2) {
        PacketClientRequest packet = new PacketClientRequest(par1, par2);
        ServerCloudManager.sendPacket(packet);
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        writeString(par1, this.type.toString());
        par1.writeBoolean(this.requestData != null);
        par1.writeInt(this.requestData.length);
        String[] arr$ = this.requestData;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String requestDatum = arr$[i$];
            writeString(par1, requestDatum);
        }

    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}
