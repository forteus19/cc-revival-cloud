package dev.vuis.cccr.cloud.packets_idk_if_needed.Unspecified;

import com.f3rullo14.cloud.G_ITcpConnectionHandler;
import com.f3rullo14.cloud.G_TcpPacketCustomPayload;
import com.f3rullo14.fds.tag.FDSTagCompound;
import com.ferullogaming.countercraft.client.cloud.Booster;
import com.ferullogaming.countercraft.client.cloud.EnumCompRank;
import com.ferullogaming.countercraft.client.cloud.Group;
import com.ferullogaming.countercraft.client.cloud.MatchParty;
import com.ferullogaming.countercraft.client.cloud.PlayerDataCloud;
import com.ferullogaming.countercraft.client.cloud.Punishment;
import com.ferullogaming.countercraft.client.cloud.PunishmentType;
import com.ferullogaming.countercraft.player.PlayerDataHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PacketRequestedPlayerData extends G_TcpPacketCustomPayload {
    private String username;
    private FDSTagCompound tag = new FDSTagCompound("playerdata");

    public PacketRequestedPlayerData() {
    }

    public void writePacketToStream(DataOutputStream par1) throws IOException {
    }

    public void readPacketFromStream(DataInputStream par1) throws IOException {
        this.username = readString(par1);
        this.tag.load(par1);
    }

    public void processPacketOnMainThread(G_ITcpConnectionHandler par1) throws IOException {
        PlayerDataCloud cloudData = PlayerDataHandler.getPlayerCloudData(this.username);
        cloudData.setUsername(this.username);
        cloudData.trophies = this.tag.getInteger("trophies");
        cloudData.totalKills = this.tag.getInteger("kills");
        cloudData.totalDeaths = this.tag.getInteger("deaths");
        cloudData.totalGames = this.tag.getInteger("games");
        cloudData.statHeadshots = this.tag.getInteger("staths");
        cloudData.statFirstBloods = this.tag.getInteger("statfb");
        cloudData.statFireKills = this.tag.getInteger("statfk");
        cloudData.matchKarma = this.tag.getInteger("matchKarma");
        cloudData.maxInvSlots = this.tag.getInteger("maxInv");
        cloudData.maxFriendSlots = this.tag.getInteger("maxFriend");
        cloudData.hasCompetitiveRejoinButton = this.tag.hasTag("lastcomp");
        cloudData.timePlayed = this.tag.getInteger("time");
        cloudData.exp = this.tag.getInteger("exp");
        cloudData.isOnline = this.tag.getBoolean("online");
        cloudData.emeralds = this.tag.getInteger("emeralds");
        cloudData.discordUserId = this.tag.getString("discordUserId");
        cloudData.enableNotification_FriendRequest = this.tag.getBoolean("enableNotification_FriendRequest");
        cloudData.enableNotification_FriendPoke = this.tag.getBoolean("enableNotification_FriendPoke");
        cloudData.enableNotification_TradeRequest = this.tag.getBoolean("enableNotification_TradeRequest");
        cloudData.enableProfileViewing = this.tag.getBoolean("enableProfileViewing");
        cloudData.enableTradeRequests = this.tag.getBoolean("enableTradeRequests");
        cloudData.enableFriendRequests = this.tag.getBoolean("enableFriendRequests");
        cloudData.mood = this.tag.getString("mood").length() > 0 ? this.tag.getString("mood") : "I am a newb, tehe :D";
        cloudData.status = this.tag.getString("status");
        cloudData.clanTag = this.tag != null && this.tag.getString("clantag") != null && this.tag.getString("clantag").length() >= 0 ? this.tag.getString("clantag") : "NONE";
        cloudData.serverOn = this.tag.getString("serverOn");
        cloudData.readShowcaseFromFDS(this.tag);
        cloudData.coinDisplayed = this.tag.getInteger("coinDisplayed");
        cloudData.compRank = this.getRankFromInteger(this.tag.getInteger("compRank"));
        cloudData.compRankXP = this.tag.getInteger("compRankXP");
        cloudData.compRankRequiredXP = this.tag.getInteger("compRankRequiredXP");
        cloudData.halloweenCasesUnboxed = this.tag.getInteger("halloweenCasesUnboxed");
        cloudData.playerUUID = this.tag.getString("playerUUID");
        cloudData.twoFactorEnable = this.tag.getBoolean("twoFactorEnable");
        if (this.tag.hasTag("isSupporter")) {
            cloudData.isSupporter = this.tag.getBoolean("isSupporter");
        }

        if (this.tag.hasTag("enableCape")) {
            cloudData.enableCape = this.tag.getBoolean("enableCape");
        }

        if (this.tag.hasTag("prestigeLevel")) {
            cloudData.prestigeLevel = this.tag.getInteger("prestigeLevel");
        }

        int achievementCount = this.tag.getInteger("achievementCount");
        ArrayList<Integer> achievements = new ArrayList();

        int i;
        for(i = 0; i < achievementCount; ++i) {
            achievements.add(this.tag.getInteger("achievement_" + i));
        }

        cloudData.unlockedAchievements = achievements;
        cloudData.boostersActive.clear();
        FDSTagCompound partytag;
        if (this.tag.hasTag("booster0")) {
            for(i = 0; i < 5; ++i) {
                if (this.tag.hasTag("booster" + i)) {
                    partytag = this.tag.getTagCompound("booster" + i);
                    Booster boost = Booster.readFromFDS(partytag);
                    cloudData.boostersActive.add(boost);
                }
            }
        }

        if (this.tag.hasTag("groupName")) {
            if (cloudData.group == null) {
                cloudData.group = new Group();
            }

            cloudData.group.readFromFDS(this.tag);
        } else if (cloudData.group != null) {
            cloudData.group = null;
        }

        boolean hasParty = this.tag.getBoolean("hasparty");
        if (hasParty) {
            partytag = this.tag.getTagCompound("party");
            MatchParty party = new MatchParty();
            party.readFromFDS(partytag);
            cloudData.setParty(party);
        } else {
            cloudData.setParty((MatchParty)null);
        }

        int var1;
        int i;
        if (this.tag.hasTag("punishSize")) {
            var1 = this.tag.getInteger("punishSize");

            for(i = 0; i < var1; ++i) {
                if (this.tag.hasTag("pun" + i)) {
                    FDSTagCompound tag1 = this.tag.getTagCompound("pun" + i);
                    Punishment punish = Punishment.readFromFDS(tag1);
                    cloudData.addPunishment(punish);
                }
            }
        }

        int i;
        if (this.tag.hasTag("playerFeedSize")) {
            var1 = this.tag.getInteger("playerFeedSize");
            ArrayList<String> newFeedList = new ArrayList();

            for(i = 0; i < var1; ++i) {
                if (this.tag.hasTag("playerFeed" + i)) {
                    newFeedList.add(this.tag.getString("playerFeed" + i));
                }
            }

            cloudData.playerFeed = newFeedList;
        }

        PunishmentType[] arr$ = PunishmentType.values();
        i = arr$.length;

        for(i = 0; i < i; ++i) {
            PunishmentType type = arr$[i];
            cloudData.playerPunishmentCounts.put(type.toString(), this.tag.getInteger("punCount" + type.toString()));
        }

    }

    public EnumCompRank getRankFromInteger(int givenRankID) {
        switch (givenRankID) {
            case 1:
                return EnumCompRank.IRON_2;
            case 2:
                return EnumCompRank.IRON_3;
            case 3:
                return EnumCompRank.IRON_MASTER;
            case 4:
                return EnumCompRank.GOLD_1;
            case 5:
                return EnumCompRank.GOLD_2;
            case 6:
                return EnumCompRank.GOLD_3;
            case 7:
                return EnumCompRank.GOLD_MASTER;
            case 8:
                return EnumCompRank.DIAMOND_1;
            case 9:
                return EnumCompRank.DIAMOND_2;
            case 10:
                return EnumCompRank.DIAMOND_3;
            case 11:
                return EnumCompRank.DIAMOND_MASTER;
            case 12:
                return EnumCompRank.OBSIDIAN_1;
            case 13:
                return EnumCompRank.OBSIDIAN_2;
            case 14:
                return EnumCompRank.OBSIDIAN_3;
            case 15:
                return EnumCompRank.OBSIDIAN_MASTER;
            case 16:
                return EnumCompRank.ENDER_ELITE;
            default:
                return EnumCompRank.IRON_1;
        }
    }
}
