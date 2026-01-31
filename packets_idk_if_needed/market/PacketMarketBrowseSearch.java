package dev.vuis.cccr.cloud.packets_idk_if_needed.market;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;

public class PacketMarketBrowseSearch extends G_TcpPacketCustomPayload {
    private String[] filters;

    public PacketMarketBrowseSearch(String... par1) {
        this.filters = par1;
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        writeString(par1, Minecraft.func_71410_x().func_110432_I().func_111285_a());
        par1.writeInt(this.filters.length);
        String[] arr$ = this.filters;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String filter = arr$[i$];
            writeString(par1, filter);
        }

    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}
