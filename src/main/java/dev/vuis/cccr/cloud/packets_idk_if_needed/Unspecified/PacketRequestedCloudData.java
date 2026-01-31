package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import com.ferullogaming.countercraft.client.ModSettings;
import com.ferullogaming.countercraft.game.references.GameType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketRequestedCloudData extends G_TcpPacketCustomPayload {
    private FDSTagCompound tag = new FDSTagCompound("cloudstats");

    public PacketRequestedCloudData() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
        this.tag.load(par1);
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
        ModSettings.instance().USERS_ONLINE = this.tag.getInteger("usersonline");
        ModSettings.instance().USERS_UNIQUE = this.tag.getInteger("unique");
        ModSettings.instance().USERS_TDM = this.tag.getInteger("usersonlinetdm");
        ModSettings.instance().USERS_CAS = this.tag.getInteger("usersonlinecas");
        ModSettings.instance().USERS_COMP = this.tag.getInteger("usersonlinecomp");
        ModSettings.instance().playerCounts.put("cas", this.tag.getInteger("cascount"));
        ModSettings.instance().playerCounts.put("dom", this.tag.getInteger("domcount"));
        ModSettings.instance().playerCounts.put("tdm", this.tag.getInteger("tdmcount"));
        ModSettings.instance().playerCounts.put("cbd", this.tag.getInteger("cbdcount"));
        ModSettings.instance().playerCounts.put("inf", this.tag.getInteger("infcount"));
        ModSettings.instance().COMP_REQUIRES_RANK = this.tag.getBoolean("compRequiresRank");
        GameType.ACTIVE_COMPETITIVE = this.tag.getBoolean("compactive");
        ModSettings.instance().clanScores.clear();
        ModSettings.instance().playerScores.clear();
        if (this.tag.hasTag("scoreboardResetTime")) {
            try {
                ModSettings.instance().scoreboardResetTime = Long.parseLong(this.tag.getString("scoreboardResetTime"));
            } catch (NumberFormatException var7) {
            }
        }

        int clanScoreCount = this.tag.getInteger("clanScoreCount");

        int playerScoreCount;
        for(playerScoreCount = 0; playerScoreCount < clanScoreCount; ++playerScoreCount) {
            String clanName = this.tag.getString("clan_" + playerScoreCount);
            int clanScore = this.tag.getInteger("clan_score_" + playerScoreCount);
            if (clanName != null && !clanName.isEmpty() && clanName.length() > 0) {
                ModSettings.instance().clanScores.put(clanName, clanScore);
            }
        }

        playerScoreCount = this.tag.getInteger("playerScoreCount");

        for(int i = 0; i < playerScoreCount; ++i) {
            String playerName = this.tag.getString("player_" + i);
            int playerScore = this.tag.getInteger("player_score_" + i);
            if (playerName != null && !playerName.isEmpty() && playerName.length() > 0) {
                ModSettings.instance().playerScores.put(playerName, playerScore);
            }
        }

    }
}
