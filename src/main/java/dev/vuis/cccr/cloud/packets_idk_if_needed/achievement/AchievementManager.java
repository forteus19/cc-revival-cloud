package dev.vuis.cccr.cloud.packets.achievement;

import com.ferullogaming.countercraft.item.ItemManager;
import com.ferullogaming.countercraft.item.gun.ItemGun;
import java.util.TreeMap;

import net.minecraft.util.EnumChatFormatting;

public class AchievementManager {
    public static final TreeMap<Integer, CloudAchievement> ACHIEVEMENTS = new TreeMap();
    public static final CloudAchievement ACH_COUNT_5_HEADSHOT_PISTOL;
    public static final CloudAchievement ACH_COUNT_5_SNIPER;
    public static final CloudAchievement ACH_COUNT_10_SHOTGUN;
    public static final CloudAchievement ACH_COUNT_15_AK47;
    public static final CloudAchievement ACH_COUNT_15_M4A4;
    public static final CloudAchievement ACH_COUNT_15_DEAGLE;
    public static final CloudAchievement ACH_COUNT_15_FAMAS;
    public static final CloudAchievement ACH_STATS_HEADSHOT_10;
    public static final CloudAchievement ACH_STATS_HEADSHOT_30;
    public static final CloudAchievement ACH_STATS_HEADSHOT_60;
    public static final CloudAchievement ACH_STATS_HEADSHOT_100;
    public static final CloudAchievement ACH_STATS_KILLS_20;
    public static final CloudAchievement ACH_STATS_KILLS_50;
    public static final CloudAchievement ACH_STATS_KILLS_100;
    public static final CloudAchievement ACH_STATS_KILLS_300;
    public static final CloudAchievement ACH_STATS_KILLS_800;
    public static final CloudAchievement ACH_STATS_DEATHS_20;
    public static final CloudAchievement ACH_STATS_DEATHS_50;
    public static final CloudAchievement ACH_STATS_DEATHS_100;
    public static final CloudAchievement ACH_STATS_DEATHS_300;
    public static final CloudAchievement ACH_STATS_DEATHS_800;
    public static final CloudAchievement ACH_MATCH_WIN_COMP;
    public static final CloudAchievement ACH_MATCH_WIN_TDM;
    public static final CloudAchievement ACH_MATCH_WIN_CAS;
    public static final CloudAchievement ACH_MATCH_WIN_DOM;
    public static final CloudAchievement ACH_MATCH_WIN_INF;
    public static final CloudAchievement ACH_MATCH_WIN_FFA;
    public static final CloudAchievement ACH_MATCH_WIN_SG;
    public static final CloudAchievement ACH_USER_PRESTIGE_1;
    public static final CloudAchievement ACH_USER_MARKET_SELL_1;
    public static final CloudAchievement ACH_USER_MARKET_BUY_1;
    public static final CloudAchievement ACH_USER_INV_OPEN_ULTRA_RARE;
    public static final CloudAchievement ACH_USER_INV_OPEN_CASE;
    public static final CloudAchievement ACH_USER_INV_ACTIVATE_BOOSTER_EMERALD_X2;
    public static final CloudAchievement ACH_USER_INV_ACTIVATE_BOOSTER_EXP_X2;
    public static final CloudAchievement ACH_USER_INV_ACTIVATE_BOOSTER_EXP_X3;
    public static final CloudAchievement ACH_USER_RAGEQUIT;
    public static final CloudAchievement ACH_USER_DISCORD_LINK;

    public AchievementManager() {
    }

    public static CloudAchievement getAchievementFromID(int givenID) {
        return ACHIEVEMENTS.containsKey(givenID) ? (CloudAchievement)ACHIEVEMENTS.get(givenID) : null;
    }

    static {
        ACH_COUNT_5_HEADSHOT_PISTOL = new CloudAchievementGunKillStreak(0, "It's High Noon", "Get " + EnumChatFormatting.WHITE + "5" + EnumChatFormatting.RESET + " head-shots in one match with an R8 Revolver", "r8_revolver", true, 5, new ItemGun[]{(ItemGun)ItemManager.r8});
        ACH_COUNT_5_SNIPER = new CloudAchievementGunKillStreak(1, "Sniper Elite", "Kill " + EnumChatFormatting.WHITE + "5" + EnumChatFormatting.RESET + " players in one match with a Sniper Rifle", "sniper", false, 5, new ItemGun[]{(ItemGun)ItemManager.awp, (ItemGun)ItemManager.dragunov, (ItemGun)ItemManager.scar20, (ItemGun)ItemManager.ssg08});
        ACH_COUNT_10_SHOTGUN = new CloudAchievementGunKillStreak(2, "Pump it Up!", "Kill " + EnumChatFormatting.WHITE + "10" + EnumChatFormatting.RESET + " players in one match with a Shotgun", "shotgun", false, 10, new ItemGun[]{(ItemGun)ItemManager.sawedoff, (ItemGun)ItemManager.nova, (ItemGun)ItemManager.mag7});
        ACH_COUNT_15_AK47 = new CloudAchievementGunKillStreak(3, "Kalash", "Kill " + EnumChatFormatting.WHITE + "15" + EnumChatFormatting.RESET + " players in one match with an AK47", "ak47", false, 15, new ItemGun[]{(ItemGun)ItemManager.ak47});
        ACH_COUNT_15_M4A4 = new CloudAchievementGunKillStreak(4, "Emm' Four", "Kill " + EnumChatFormatting.WHITE + "15" + EnumChatFormatting.RESET + " players in one match with an M4", "m4", false, 15, new ItemGun[]{(ItemGun)ItemManager.m4a1});
        ACH_COUNT_15_DEAGLE = new CloudAchievementGunKillStreak(5, "Juan Deag", "Get " + EnumChatFormatting.WHITE + "15" + EnumChatFormatting.RESET + " head-shots in one match with a Deagle", "deagle", true, 15, new ItemGun[]{(ItemGun)ItemManager.deagle});
        ACH_COUNT_15_FAMAS = new CloudAchievementGunKillStreak(6, "Init, Fam", "Get " + EnumChatFormatting.WHITE + "15" + EnumChatFormatting.RESET + " head-shots in one match with a Famas", "famas", true, 15, new ItemGun[]{(ItemGun)ItemManager.famas});
        ACH_STATS_HEADSHOT_10 = new CloudAchievement(7, "Boom, Headshot", "Get 10 headshots");
        ACH_STATS_HEADSHOT_30 = new CloudAchievement(8, "Melon-splitter", "Get 30 headshots");
        ACH_STATS_HEADSHOT_60 = new CloudAchievement(9, "Skull-sploder", "Get 60 headshots");
        ACH_STATS_HEADSHOT_100 = new CloudAchievement(10, "Scalped", "Get 100 headshots");
        ACH_STATS_KILLS_20 = new CloudAchievement(11, "Killer", "Get 20 total kills");
        ACH_STATS_KILLS_50 = new CloudAchievement(12, "Murderer", "Get 50 total kills");
        ACH_STATS_KILLS_100 = new CloudAchievement(13, "Executioner", "Get 100 total kills");
        ACH_STATS_KILLS_300 = new CloudAchievement(14, "Slaughterer", "Get 300 total kills");
        ACH_STATS_KILLS_800 = new CloudAchievement(15, "Terminator", "Get 800 total kills");
        ACH_STATS_DEATHS_20 = new CloudAchievement(16, "Rest in Peace", "Get 20 total deaths");
        ACH_STATS_DEATHS_50 = new CloudAchievement(17, "Rest in Pieces", "Get 50 total deaths");
        ACH_STATS_DEATHS_100 = new CloudAchievement(18, "Rip in Pieces", "Get 100 total deaths");
        ACH_STATS_DEATHS_300 = new CloudAchievement(19, "Ripped to Shreds", "Get 300 total deaths");
        ACH_STATS_DEATHS_800 = new CloudAchievement(20, "Decomposing", "Get 800 total deaths");
        ACH_MATCH_WIN_COMP = new CloudAchievement(50, "Comp Champ", "Win match on a Competitive Game-mode");
        ACH_MATCH_WIN_TDM = new CloudAchievement(51, "TDM Killer", "Win match on a TDM Game-mode");
        ACH_MATCH_WIN_CAS = new CloudAchievement(52, "Casual Pro", "Win match on a Casual Game-mode");
        ACH_MATCH_WIN_DOM = new CloudAchievement(53, "Dominator", "Win match on a Domination Game-mode");
        ACH_MATCH_WIN_INF = new CloudAchievement(54, "Infectious", "Win match on an Infected Game-mode");
        ACH_MATCH_WIN_FFA = new CloudAchievement(55, "Freelance", "Win match on a Free For All Game-mode");
        ACH_MATCH_WIN_SG = new CloudAchievement(56, "Survivalist", "Win match on a Survival Games Game-mode");
        ACH_USER_PRESTIGE_1 = new CloudAchievement(60, "Prestige", "Prestige your rank at least once");
        ACH_USER_MARKET_SELL_1 = new CloudAchievement(70, "Money, Money", "Sell a skin on the market");
        ACH_USER_MARKET_BUY_1 = new CloudAchievement(71, "Bargain Hunter", "Buy a skin on the market");
        ACH_USER_INV_OPEN_ULTRA_RARE = new CloudAchievement(80, "Lucky Charm", "Unlock a super-rare skin from a crate");
        ACH_USER_INV_OPEN_CASE = new CloudAchievement(81, "Try Your Luck", "Open a weapon case from your inventory");
        ACH_USER_INV_ACTIVATE_BOOSTER_EMERALD_X2 = new CloudAchievement(82, "Boost 'em Up!", "Activate a 2x emerald booster");
        ACH_USER_INV_ACTIVATE_BOOSTER_EXP_X2 = new CloudAchievement(83, "Experienced", "Activate a 2x EXP booster");
        ACH_USER_INV_ACTIVATE_BOOSTER_EXP_X3 = new CloudAchievement(84, "Very Experienced", "Activate a 3x EXP booster");
        ACH_USER_RAGEQUIT = (new CloudAchievement(100, "Rage-Quit Clown", "You've rage-quit from a match after dying... What a clown!")).disableReward();
        ACH_USER_DISCORD_LINK = (new CloudAchievement(101, "Discord Linked!", "You've linked your Discord Account to your CC profile!")).disableReward();
    }
}
