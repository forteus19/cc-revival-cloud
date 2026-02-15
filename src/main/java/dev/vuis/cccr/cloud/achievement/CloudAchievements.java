package dev.vuis.cccr.cloud.achievement;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class CloudAchievements {
	static final Map<Integer, CloudAchievement> ID_MAP = new HashMap<>();

	public static final CloudAchievement ACH_COUNT_5_HEADSHOT_PISTOL = new CloudAchievement(0, "It's High Noon");
	public static final CloudAchievement ACH_COUNT_5_SNIPER = new CloudAchievement(1, "Sniper Elite");
	public static final CloudAchievement ACH_COUNT_10_SHOTGUN = new CloudAchievement(2, "Pump it Up!");
	public static final CloudAchievement ACH_COUNT_15_AK47 = new CloudAchievement(3, "Kalash");
	public static final CloudAchievement ACH_COUNT_15_M4A4 = new CloudAchievement(4, "Emm' Four");
	public static final CloudAchievement ACH_COUNT_15_DEAGLE = new CloudAchievement(5, "Juan Deag");
	public static final CloudAchievement ACH_COUNT_15_FAMAS = new CloudAchievement(6, "Init, Fam");
	public static final CloudAchievement ACH_STATS_HEADSHOT_10 = new CloudAchievement(7, "Boom, Headshot");
	public static final CloudAchievement ACH_STATS_HEADSHOT_30 = new CloudAchievement(8, "Melon-splitter");
	public static final CloudAchievement ACH_STATS_HEADSHOT_60 = new CloudAchievement(9, "Skull-sploder");
	public static final CloudAchievement ACH_STATS_HEADSHOT_100 = new CloudAchievement(10, "Scalped");
	public static final CloudAchievement ACH_STATS_KILLS_20 = new CloudAchievement(11, "Killer");
	public static final CloudAchievement ACH_STATS_KILLS_50 = new CloudAchievement(12, "Murderer");
	public static final CloudAchievement ACH_STATS_KILLS_100 = new CloudAchievement(13, "Executioner");
	public static final CloudAchievement ACH_STATS_KILLS_300 = new CloudAchievement(14, "Slaughterer");
	public static final CloudAchievement ACH_STATS_KILLS_800 = new CloudAchievement(15, "Terminator");
	public static final CloudAchievement ACH_STATS_DEATHS_20 = new CloudAchievement(16, "Rest in Peace");
	public static final CloudAchievement ACH_STATS_DEATHS_50 = new CloudAchievement(17, "Rest in Pieces");
	public static final CloudAchievement ACH_STATS_DEATHS_100 = new CloudAchievement(18, "Rip in Pieces");
	public static final CloudAchievement ACH_STATS_DEATHS_300 = new CloudAchievement(19, "Ripped to Shreds");
	public static final CloudAchievement ACH_STATS_DEATHS_800 = new CloudAchievement(20, "Decomposing");
	public static final CloudAchievement ACH_MATCH_WIN_COMP = new CloudAchievement(50, "Comp Champ");
	public static final CloudAchievement ACH_MATCH_WIN_TDM = new CloudAchievement(51, "TDM Killer");
	public static final CloudAchievement ACH_MATCH_WIN_CAS = new CloudAchievement(52, "Casual Pro");
	public static final CloudAchievement ACH_MATCH_WIN_DOM = new CloudAchievement(53, "Dominator");
	public static final CloudAchievement ACH_MATCH_WIN_INF = new CloudAchievement(54, "Infectious");
	public static final CloudAchievement ACH_MATCH_WIN_FFA = new CloudAchievement(55, "Freelance");
	public static final CloudAchievement ACH_MATCH_WIN_SG = new CloudAchievement(56, "Survivalist");
	public static final CloudAchievement ACH_USER_PRESTIGE_1 = new CloudAchievement(60, "Prestige");
	public static final CloudAchievement ACH_USER_MARKET_SELL_1 = new CloudAchievement(70, "Money, Money");
	public static final CloudAchievement ACH_USER_MARKET_BUY_1 = new CloudAchievement(71, "Bargain Hunter");
	public static final CloudAchievement ACH_USER_INV_OPEN_ULTRA_RARE = new CloudAchievement(80, "Lucky Charm");
	public static final CloudAchievement ACH_USER_INV_OPEN_CASE = new CloudAchievement(81, "Try Your Luck");
	public static final CloudAchievement ACH_USER_INV_ACTIVATE_BOOSTER_EMERALD_X2 = new CloudAchievement(82, "Boost 'em Up!");
	public static final CloudAchievement ACH_USER_INV_ACTIVATE_BOOSTER_EXP_X2 = new CloudAchievement(83, "Experienced");
	public static final CloudAchievement ACH_USER_INV_ACTIVATE_BOOSTER_EXP_X3 = new CloudAchievement(84, "Very Experienced");
	public static final CloudAchievement ACH_USER_RAGEQUIT = new CloudAchievement(100, "Rage-Quit Clown");
	public static final CloudAchievement ACH_USER_DISCORD_LINK = new CloudAchievement(101, "Discord Linked!");

	private CloudAchievements() {
	}

	public static CloudAchievement getById(int id) {
		return ID_MAP.get(id);
	}

	public static CloudAchievement getKillsAchievement(int kills) {
		return switch (kills) {
			case 20 -> ACH_STATS_KILLS_20;
			case 50 -> ACH_STATS_KILLS_50;
			case 100 -> ACH_STATS_KILLS_100;
			case 300 -> ACH_STATS_KILLS_300;
			case 800 -> ACH_STATS_KILLS_800;
			default -> null;
		};
	}

	public static CloudAchievement getDeathsAchievement(int deaths) {
		return switch (deaths) {
			case 20 -> ACH_STATS_DEATHS_20;
			case 50 -> ACH_STATS_DEATHS_50;
			case 100 -> ACH_STATS_DEATHS_100;
			case 300 -> ACH_STATS_DEATHS_300;
			case 800 -> ACH_STATS_DEATHS_800;
			default -> null;
		};
	}
}
