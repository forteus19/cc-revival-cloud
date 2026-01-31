package dev.vuis.cccr.cloud.packets_idk_if_needed.MM;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PacketPartyEditSettings extends G_TcpPacketCustomPayload {
    private String gamemode;
    private ArrayList<String> maps = new ArrayList();
    private String region;

    public PacketPartyEditSettings(String givenGamemode, ArrayList<String> givenMaps, String givenRegion) {
        this.gamemode = givenGamemode;
        this.maps = givenMaps;
        this.region = givenRegion;
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
        FDSTagCompound tag = new FDSTagCompound("settings");
        tag.setString("gamemode", this.gamemode);
        tag.setStringArrayList("maps", this.maps);
        tag.setString("region", this.region);
        tag.write(par1);
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
    }
}

