package dev.vuis.cccr.cloud.packets_idk_if_needed.Friend;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;

public class PacketFriendRequestAccept extends G_TcpPacketCustomPayload {
    private String user;
    private int action;

    public PacketFriendRequestAccept(String par1, int par2) {
        this.user = par1;
        this.action = par2;
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        writeString(par1, Minecraft.func_71410_x().func_110432_I().func_111285_a());
        writeString(par1, this.user);
        par1.writeInt(this.action);
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}
