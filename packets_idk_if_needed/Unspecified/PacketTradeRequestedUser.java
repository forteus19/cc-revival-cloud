package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.ferullogaming.countercraft.client.gui.GuiCCMenuTradeCreate;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;

public class PacketTradeRequestedUser extends G_TcpPacketCustomPayload {
    private String username;

    public PacketTradeRequestedUser() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
        this.username = readString(par1);
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
        Minecraft.func_71410_x().func_71373_a(new GuiCCMenuTradeCreate(Minecraft.func_71410_x().field_71462_r, this.username));
    }
}
