package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.ferullogaming.countercraft.client.ClientNotification;
import com.ferullogaming.countercraft.client.ClientTickHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.stats.StatList;

public class PacketBanned extends G_TcpPacketCustomPayload {
    public PacketBanned() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e != null) {
            mc.field_71413_E.func_77450_a(StatList.field_75947_j, 1);
            mc.field_71441_e.func_72882_A();
            mc.func_71403_a((WorldClient)null);
        }

        ClientTickHandler.addClientNotification(new ClientNotification("You have been banned from Counter Craft."));
    }
}
