package dev.vuis.cccr.cloud.packets_idk_if_needed.TwoFactor;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.ferullogaming.countercraft.client.gui.GuiCCTPTwoFactor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;

public class PacketTwoFactorEnableResponse extends G_TcpPacketCustomPayload {
    private String imageURL;

    public PacketTwoFactorEnableResponse() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
        this.imageURL = readString(par1);
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
        Minecraft mc = Minecraft.func_71410_x();
        mc.func_71373_a(new GuiCCTPTwoFactor(mc.field_71462_r, this.imageURL));
    }
}
