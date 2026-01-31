package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import com.ferullogaming.countercraft.client.ClientManager;
import com.ferullogaming.countercraft.game.GameManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketClientUpdateStatus extends G_TcpPacketCustomPayload {
    public PacketClientUpdateStatus() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        FDSTagCompound tag = new FDSTagCompound("data");
        tag.setString("status", ClientManager.instance().getCloudManager().clientStatus);
        if (GameManager.instance().serverOnUUID != null) {
            tag.setString("serverOn", GameManager.instance().serverOnUUID);
        }

        tag.write(par1);
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}
