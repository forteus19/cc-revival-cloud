package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.ferullogaming.countercraft.server.ServerManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketKickedPlayer extends G_TcpPacketCustomPayload {
    private String givenUsername;
    private String cloudAuthCode;
    private String givenTime;
    private String givenReason;

    public PacketKickedPlayer(String par1, String par2, String par3) {
        this.givenUsername = par1;
        this.givenTime = par2;
        this.givenReason = par3;
        this.cloudAuthCode = ServerManager.instance().matchMakingPassword;
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        writeString(par1, this.givenUsername);
        writeString(par1, this.cloudAuthCode);
        writeString(par1, this.givenTime);
        writeString(par1, this.givenReason);
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}
