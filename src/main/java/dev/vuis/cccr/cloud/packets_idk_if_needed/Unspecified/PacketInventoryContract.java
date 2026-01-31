package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class PacketInventoryContract extends G_TcpPacketCustomPayload {
    private final int tier;
    private final ArrayList<String> uuids;

    public PacketInventoryContract(int par1, ArrayList<String> par2) {
        this.tier = par1;
        this.uuids = par2;
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        par1.writeInt(this.tier);
        Iterator i$ = this.uuids.iterator();

        while(i$.hasNext()) {
            String uuid = (String)i$.next();
            writeString(par1, uuid);
        }

    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}
