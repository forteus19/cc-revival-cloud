package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.ferullogaming.countercraft.client.ClientNotification;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;

public class PacketLinkDiscordResponse extends G_TcpPacketCustomPayload {
    private String discordAuthCode;

    public PacketLinkDiscordResponse() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
        this.discordAuthCode = par1.readUTF();
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
        String command = "cc!link " + Minecraft.func_71410_x().func_110432_I().func_111285_a() + " " + this.discordAuthCode;
        StringSelection selection = new StringSelection(command);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        ClientNotification.createNotification("Received auth-code copy! Paste it in BoehMod discord!");
    }
}
