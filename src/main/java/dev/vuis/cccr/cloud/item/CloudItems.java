package dev.vuis.cccr.cloud.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.vuis.cccr.cloud.item.impl.CloudItemBooster;
import dev.vuis.cccr.cloud.item.impl.CloudItemCallingCard;
import dev.vuis.cccr.cloud.item.impl.CloudItemCape;
import dev.vuis.cccr.cloud.item.impl.CloudItemCase;
import dev.vuis.cccr.cloud.item.impl.CloudItemCoin;
import dev.vuis.cccr.cloud.item.impl.CloudItemGeneric;
import dev.vuis.cccr.cloud.item.impl.CloudItemGun;
import dev.vuis.cccr.cloud.item.impl.CloudItemHat;
import dev.vuis.cccr.cloud.item.impl.CloudItemKey;
import dev.vuis.cccr.cloud.item.impl.CloudItemKnife;
import dev.vuis.cccr.cloud.item.impl.CloudItemSticker;
import dev.vuis.cccr.cloud.item.impl.CloudItemStickerCapsule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public final class CloudItems {
	static final List<CloudItem> DEFAULTS = new ArrayList<>();
	static final Map<Integer, CloudItem> ID_MAP = new HashMap<>();
	static final Multimap<CloudItemRarity, CloudItem> RARITY_MAP = HashMultimap.create();

	public static final CloudItemGun M4A4 = new CloudItemGun(100, 4256, CloudItemRarity.DEFAULT, "M4A4", true);
	public static final CloudItemGun M4A4_WAVES = new CloudItemGun(101, 4256, CloudItemRarity.IRON, "M4A4 Waves", false);
	public static final CloudItemGun M4A4_EVILDAIMYO = new CloudItemGun(102, 4256, CloudItemRarity.GOLD, "M4A4 EvilDaimyo", false);
	public static final CloudItemGun M4A4_CAKEY = new CloudItemGun(103, 4256, CloudItemRarity.IRON, "M4A4 Cakey", false);
	public static final CloudItemGun M4A4_HOWL = new CloudItemGun(104, 4256, CloudItemRarity.OBSIDIAN, "M4A4 Howl", false);
	public static final CloudItemGun M4A4_NERFED = new CloudItemGun(105, 4256, CloudItemRarity.IRON, "M4A4 Nerfed", false);
	public static final CloudItemGun M4A4_PUMPKIN = new CloudItemGun(106, 4256, CloudItemRarity.GOLD, "M4A4 Pumpkin", false);
	public static final CloudItemGun M4A4_PATRIOT = new CloudItemGun(107, 4256, CloudItemRarity.LAPIS, "M4A4 Patriot", false);

	public static final CloudItemGun AK47 = new CloudItemGun(200, 4257, CloudItemRarity.DEFAULT, "AK47", true);
	public static final CloudItemGun AK47_PROPAGANDA = new CloudItemGun(201, 4257, CloudItemRarity.GOLD, "AK47 Propaganda", false);
	public static final CloudItemGun AK47_ASMO = new CloudItemGun(202, 4257, CloudItemRarity.DIAMOND, "AK47 Asmo", false);
	public static final CloudItemGun AK47_NEONREVOLUTION = new CloudItemGun(203, 4257, CloudItemRarity.GOLD, "AK47 Neon Revolution", false);
	public static final CloudItemGun AK47_HAUNTED = new CloudItemGun(204, 4257, CloudItemRarity.DIAMOND, "AK47 Haunted", false);
	public static final CloudItemGun AK47_BLOODSPORT = new CloudItemGun(205, 4257, CloudItemRarity.IRON, "AK47 Bloodsport", false);
	public static final CloudItemGun AK47_FOX = new CloudItemGun(206, 4257, CloudItemRarity.LAPIS, "AK47 Fox", false);

	public static final CloudItemGun FAMAS = new CloudItemGun(300, 4258, CloudItemRarity.DEFAULT, "Famas", true);
	public static final CloudItemGun FAMAS_AKARI = new CloudItemGun(301, 4258, CloudItemRarity.LAPIS, "Famas Akari", false);
	public static final CloudItemGun FAMAS_WARHAWK = new CloudItemGun(302, 4258, CloudItemRarity.DIAMOND, "Famas Warhawk", false);
	public static final CloudItemGun FAMAS_SPOOKY = new CloudItemGun(303, 4258, CloudItemRarity.LAPIS, "Famas Spooky", false);

	public static final CloudItemGun FNFAL = new CloudItemGun(400, 4259, CloudItemRarity.DEFAULT, "FN FAL", true);
	public static final CloudItemGun FNFAL_VAPORWAVE = new CloudItemGun(401, 4259, CloudItemRarity.GOLD, "FN FAL Vaporwave", false);
	public static final CloudItemGun FNFAL_ASMO = new CloudItemGun(402, 4259, CloudItemRarity.GOLD, "FN FAL Asmo", false);

	public static final CloudItemGun GALIL = new CloudItemGun(450, 4260, CloudItemRarity.DEFAULT, "Galil AR", true);

	public static final CloudItemGun M1911 = new CloudItemGun(500, 4266, CloudItemRarity.DEFAULT, "M1911", true);
	public static final CloudItemGun M1911_DRAGON = new CloudItemGun(501, 4266, CloudItemRarity.LAPIS, "M1911 Dragon", false);
	public static final CloudItemGun M1911_TUXEDO = new CloudItemGun(502, 4266, CloudItemRarity.IRON, "M1911 Tuxedo", false);

	public static final CloudItemGun G18 = new CloudItemGun(600, 4267, CloudItemRarity.DEFAULT, "G18", true);
	public static final CloudItemGun G18_RACE = new CloudItemGun(601, 4267, CloudItemRarity.IRON, "G18 Race", false);
	public static final CloudItemGun G18_HIGHROLLER = new CloudItemGun(602, 4267, CloudItemRarity.DIAMOND, "G18 Highroller", false);
	public static final CloudItemGun G18_DEEP_BLUE = new CloudItemGun(603, 4267, CloudItemRarity.IRON, "G18 Deep Blue", false);

	public static final CloudItemGun TEC9 = new CloudItemGun(650, 4270, CloudItemRarity.DEFAULT, "TEC9", true);
	public static final CloudItemGun TEC9_QUAKORONIC = new CloudItemGun(651, 4270, CloudItemRarity.IRON, "TEC9 Quakoronic", false);
	public static final CloudItemGun TEC9_ECO = new CloudItemGun(652, 4270, CloudItemRarity.IRON, "TEC9 Eco", false);
	public static final CloudItemGun TEC9_AQUARIUM = new CloudItemGun(653, 4270, CloudItemRarity.DIAMOND, "TEC9 Aquarium", false);

	public static final CloudItemGun DEAGLE = new CloudItemGun(700, 4268, CloudItemRarity.DEFAULT, "Deagle", true);
	public static final CloudItemGun DEAGLE_NIGHTRAZER = new CloudItemGun(701, 4268, CloudItemRarity.LAPIS, "Deagle NightRazer", false);
	public static final CloudItemGun DEAGLE_DEADHORIZON = new CloudItemGun(702, 4268, CloudItemRarity.GOLD, "Deagle Dead Horizon", false);
	public static final CloudItemGun DEAGLE_GRIN = new CloudItemGun(703, 4268, CloudItemRarity.LAPIS, "Deagle Grin", false);

	public static final CloudItemGun P250 = new CloudItemGun(750, 4269, CloudItemRarity.DEFAULT, "P250", true);
	public static final CloudItemGun P250_IRRADIATED = new CloudItemGun(751, 4269, CloudItemRarity.IRON, "P250 Irradiated", false);
	public static final CloudItemGun P250_SEEYALATER = new CloudItemGun(752, 4269, CloudItemRarity.DIAMOND, "P250 SeeYaLater", false);
	public static final CloudItemGun P250_CITY = new CloudItemGun(753, 4269, CloudItemRarity.IRON, "P250 City", false);
	public static final CloudItemGun P250_BEAST = new CloudItemGun(754, 4269, CloudItemRarity.GOLD, "P250 Beast", false);

	public static final CloudItemGun P90 = new CloudItemGun(800, 4276, CloudItemRarity.DEFAULT, "P90", true);
	public static final CloudItemGun P90_RUST = new CloudItemGun(801, 4276, CloudItemRarity.IRON, "P90 Rust", false);
	public static final CloudItemGun P90_KRAKEN = new CloudItemGun(802, 4276, CloudItemRarity.GOLD, "P90 Kraken", false);

	public static final CloudItemGun MAC10 = new CloudItemGun(900, 4277, CloudItemRarity.DEFAULT, "MAC10", true);
	public static final CloudItemGun MAC10_RACE = new CloudItemGun(901, 4277, CloudItemRarity.IRON, "MAC10 Race", false);
	public static final CloudItemGun MAC10_PUMPKIN = new CloudItemGun(902, 4277, CloudItemRarity.LAPIS, "MAC10 Pumpkin", false);

	public static final CloudItemGun VECTOR = new CloudItemGun(1000, 4278, CloudItemRarity.DEFAULT, "Vector", true);
	public static final CloudItemGun VECTOR_CIRCUIT = new CloudItemGun(1001, 4278, CloudItemRarity.IRON, "Vector Circuit", false);
	public static final CloudItemGun VECTOR_BLOODYEQUATION = new CloudItemGun(1002, 4278, CloudItemRarity.IRON, "Vector Bloody Equation", false);

	public static final CloudItemGun AWP = new CloudItemGun(1100, 4286, CloudItemRarity.DEFAULT, "AWP", true);
	public static final CloudItemGun AWP_DRAGON = new CloudItemGun(1101, 4286, CloudItemRarity.OBSIDIAN, "AWP Dragon", false);
	public static final CloudItemGun AWP_MANOWAR = new CloudItemGun(1102, 4286, CloudItemRarity.LAPIS, "AWP Man O War", false);
	public static final CloudItemGun AWP_ASMO = new CloudItemGun(1103, 4286, CloudItemRarity.DIAMOND, "AWP Asmo", false);
	public static final CloudItemGun AWP_BONES = new CloudItemGun(1104, 4286, CloudItemRarity.GOLD, "AWP Bones", false);
	public static final CloudItemGun AWP_REDLINE = new CloudItemGun(1105, 4286, CloudItemRarity.IRON, "AWP Redline", false);
	public static final CloudItemGun AWP_BOOM = new CloudItemGun(1106, 4286, CloudItemRarity.GOLD, "AWP Boom", false);

	public static final CloudItemGun DRAGUNOV = new CloudItemGun(1200, 4287, CloudItemRarity.DEFAULT, "Dragunov", true);
	public static final CloudItemGun DRAGUNOV_SKY = new CloudItemGun(1201, 4287, CloudItemRarity.IRON, "Dragunov Sky", false);
	public static final CloudItemGun DRAGUNOV_STEAMPUNK = new CloudItemGun(1202, 4287, CloudItemRarity.LAPIS, "Dragunov Steampunk", false);

	public static final CloudItemGun SCAR20 = new CloudItemGun(1300, 4288, CloudItemRarity.DEFAULT, "Scar20", true);
	public static final CloudItemGun SCAR20_CYREX = new CloudItemGun(1301, 4288, CloudItemRarity.LAPIS, "Scar20 Cyrex", false);
	public static final CloudItemGun SCAR20_UNICORN = new CloudItemGun(1302, 4288, CloudItemRarity.DIAMOND, "Scar20 Unicorn", false);

	public static final CloudItemGun SSG08 = new CloudItemGun(1400, 4289, CloudItemRarity.DEFAULT, "SSG-08", true);

	public static final CloudItemKnife KNIFE_TAC = new CloudItemKnife(1500, 4456, CloudItemRarity.DEFAULT, "Tac Knife", true);
	public static final CloudItemKnife KNIFE_TAC_DISPLAY = new CloudItemKnife(1501, 4457, CloudItemRarity.OBSIDIAN, "Special Item", false);
	public static final CloudItemKnife KNIFE_TAC_MARBLE = new CloudItemKnife(1502, 4458, CloudItemRarity.DIAMOND, "Tac Knife Marble", false);
	public static final CloudItemKnife KNIFE_TAC_TIGER = new CloudItemKnife(1503, 4459, CloudItemRarity.DIAMOND, "Tac Knife Tiger", false);
	public static final CloudItemKnife KNIFE_TAC_STEEL = new CloudItemKnife(1504, 4460, CloudItemRarity.DIAMOND, "Tac Knife Steel", false);
	public static final CloudItemKnife KNIFE_TAC_DOPPLAR = new CloudItemKnife(1505, 4461, CloudItemRarity.DIAMOND, "Tac Knife Doppler", false);
	public static final CloudItemKnife KNIFE_TAC_LORE = new CloudItemKnife(1506, 4462, CloudItemRarity.DIAMOND, "Tac Knife Lore", false);
	public static final CloudItemKnife KNIFE_TAC_GAMMA = new CloudItemKnife(1507, 4463, CloudItemRarity.DIAMOND, "Tac Knife Gamma", false);

	public static final CloudItemKnife KNIFE_BAY = new CloudItemKnife(1510, 4466, CloudItemRarity.DIAMOND, "Bayonet", false);
	public static final CloudItemKnife KNIFE_BAY_MARBLE = new CloudItemKnife(1511, 4467, CloudItemRarity.DIAMOND, "Bayonet Marble", false);
	public static final CloudItemKnife KNIFE_BAY_TIGER = new CloudItemKnife(1512, 4468, CloudItemRarity.DIAMOND, "Bayonet Tiger", false);
	public static final CloudItemKnife KNIFE_BAY_STEEL = new CloudItemKnife(1513, 4469, CloudItemRarity.DIAMOND, "Bayonet Steel", false);
	public static final CloudItemKnife KNIFE_BAY_DOPPLAR = new CloudItemKnife(1514, 4470, CloudItemRarity.DIAMOND, "Bayonet Doppler", false);
	public static final CloudItemKnife KNIFE_BAY_LORE = new CloudItemKnife(1515, 4471, CloudItemRarity.DIAMOND, "Bayonet Lore", false);
	public static final CloudItemKnife KNIFE_BAY_GAMMA = new CloudItemKnife(1516, 4472, CloudItemRarity.DIAMOND, "Bayonet Gamma", false);
	public static final CloudItemKnife KNIFE_BAY_DRAGONLORE = new CloudItemKnife(1517, 4473, CloudItemRarity.DIAMOND, "Bayonet Dragonlore", false);

	public static final CloudItemKnife KNIFE_KAR = new CloudItemKnife(1520, 4476, CloudItemRarity.DIAMOND, "Karambit", false);
	public static final CloudItemKnife KNIFE_KAR_MARBLE = new CloudItemKnife(1521, 4477, CloudItemRarity.DIAMOND, "Karambit Marble", false);
	public static final CloudItemKnife KNIFE_KAR_TIGER = new CloudItemKnife(1522, 4478, CloudItemRarity.DIAMOND, "Karambit Tiger", false);
	public static final CloudItemKnife KNIFE_KAR_STEEL = new CloudItemKnife(1523, 4479, CloudItemRarity.DIAMOND, "Karambit Steel", false);
	public static final CloudItemKnife KNIFE_KAR_DOPPLAR = new CloudItemKnife(1524, 4480, CloudItemRarity.DIAMOND, "Karambit Doppler", false);
	public static final CloudItemKnife KNIFE_KAR_LORE = new CloudItemKnife(1525, 4481, CloudItemRarity.DIAMOND, "Karambit Lore", false);
	public static final CloudItemKnife KNIFE_KAR_GAMMA = new CloudItemKnife(1526, 4482, CloudItemRarity.DIAMOND, "Karambit Gamma", false);
	public static final CloudItemKnife KNIFE_KAR_EAGLE = new CloudItemKnife(1527, 4483, CloudItemRarity.DIAMOND, "Karambit Eagle", false);

	public static final CloudItemKnife KNIFE_BUTTERFLY = new CloudItemKnife(1530, 4486, CloudItemRarity.DIAMOND, "Butterfly", false);

	public static final CloudItemGun NOVA = new CloudItemGun(1600, 4296, CloudItemRarity.DEFAULT, "Nova", true);
	public static final CloudItemGun NOVA_OXIDEBLAZE = new CloudItemGun(1601, 4296, CloudItemRarity.IRON, "Nova Oxide Blaze", false);
	public static final CloudItemGun NOVA_HYPER_BEAST = new CloudItemGun(1602, 4296, CloudItemRarity.DIAMOND, "Nova Hyper Beast", false);

	public static final CloudItemGun SAWEDOFF = new CloudItemGun(1650, 4297, CloudItemRarity.DEFAULT, "Sawed-Off", true);
	public static final CloudItemGun SAWEDOFF_WASTELANDPRINCESS = new CloudItemGun(1651, 4297, CloudItemRarity.GOLD, "Sawed-Off WastelandPrincess", false);
	public static final CloudItemGun SAWEDOFF_KRAKEN = new CloudItemGun(1652, 4297, CloudItemRarity.LAPIS, "Sawed-Off Kraken", false);
	public static final CloudItemGun SAWEDOFF_MIDNIGHT = new CloudItemGun(1653, 4297, CloudItemRarity.IRON, "Sawed-Off Midnight", false);

	public static final CloudItemGun MAG7 = new CloudItemGun(1700, 4298, CloudItemRarity.DEFAULT, "Mag-7", true);
	public static final CloudItemGun MAG7_ROYALFIRE = new CloudItemGun(1701, 4298, CloudItemRarity.LAPIS, "Mag-7 Royal Fire", false);
	public static final CloudItemGun MAG7_VAMPIRE = new CloudItemGun(1702, 4298, CloudItemRarity.GOLD, "Mag-7 Vampire", false);

	public static final CloudItemGun M249 = new CloudItemGun(1750, 4299, CloudItemRarity.DEFAULT, "M249", true);
	public static final CloudItemGun M249_NEON = new CloudItemGun(1751, 4299, CloudItemRarity.LAPIS, "M249 Neon", false);
	public static final CloudItemGun M249_GHOST = new CloudItemGun(1752, 4299, CloudItemRarity.IRON, "M249 Ghost", false);

	public static final CloudItemGun CZ75 = new CloudItemGun(1800, 4271, CloudItemRarity.DEFAULT, "CZ75", true);
	public static final CloudItemGun CZ75_PHANTOM = new CloudItemGun(1801, 4271, CloudItemRarity.GOLD, "CZ75 Phantom", false);
	public static final CloudItemGun FIVESEVEN = new CloudItemGun(1815, 4272, CloudItemRarity.DEFAULT, "Fiveseven", true);

	public static final CloudItemGun R8 = new CloudItemGun(1826, 4273, CloudItemRarity.DEFAULT, "R8", true);
	public static final CloudItemGun R8_FADE = new CloudItemGun(1827, 4273, CloudItemRarity.DIAMOND, "R8 Fade", false);
	public static final CloudItemGun R8_GOLD = new CloudItemGun(1828, 4273, CloudItemRarity.LAPIS, "R8 Gold", false);

	public static final CloudItemGun UMP45 = new CloudItemGun(1850, 4279, CloudItemRarity.DEFAULT, "UMP-45", true);
	public static final CloudItemGun UMP45_PRIMAL = new CloudItemGun(1851, 4279, CloudItemRarity.LAPIS, "UMP-45 Primal", false);
	public static final CloudItemGun UMP45_EDIFICE = new CloudItemGun(1852, 4279, CloudItemRarity.IRON, "UMP-45 Edifice", false);

	public static final CloudItemHat HAT_TOPHAT = new CloudItemHat(1900, 4656, CloudItemRarity.LAPIS, "Top Hat", false);
	public static final CloudItemHat HAT_CATHAT = new CloudItemHat(1901, 4657, CloudItemRarity.DIAMOND, "Cat's Hat", false);
	public static final CloudItemHat HAT_HARDRED = new CloudItemHat(1902, 4658, CloudItemRarity.COAL, "Hardhat Red", false);
	public static final CloudItemHat HAT_HARDORANGE = new CloudItemHat(1903, 4659, CloudItemRarity.COAL, "Hardhat Orange", false);
	public static final CloudItemHat HAT_HARDYELLOW = new CloudItemHat(1904, 4660, CloudItemRarity.COAL, "Hardhat Yellow", false);
	public static final CloudItemHat HAT_VIKING = new CloudItemHat(1905, 4661, CloudItemRarity.GOLD, "Viking Helmet", false);
	public static final CloudItemHat HAT_FIREMAN = new CloudItemHat(1906, 4662, CloudItemRarity.IRON, "Fireman Hat", false);
	public static final CloudItemHat HAT_FIREMANCHIEF = new CloudItemHat(1907, 4663, CloudItemRarity.IRON, "Fire Chief Hat", false);
	public static final CloudItemHat HAT_TV = new CloudItemHat(1908, 4664, CloudItemRarity.LAPIS, "TV Hat", false);

	public static final CloudItemGeneric TRADE_CONTRACT = new CloudItemGeneric(2040, 4706, CloudItemRarity.DEFAULT, "Trade Contract", true, true, true, true, true, true);

	public static final CloudItemGeneric NAME_TAG = new CloudItemGeneric(2041, 4707, CloudItemRarity.COAL, "Name Tag", false, true, true, false, false, false);

	public static final CloudItemCoin COIN_BETA = new CloudItemCoin(2050, 4716, CloudItemRarity.LAPIS, "Coin BETA Age", false);
	public static final CloudItemCoin COIN_ALPHA = new CloudItemCoin(2051, 4717, CloudItemRarity.LAPIS, "Coin ALPHA Age", false);
	public static final CloudItemCoin COIN_DISCORD = new CloudItemCoin(2052, 4718, CloudItemRarity.LAPIS, "Coin Discord", false);
	public static final CloudItemCoin COIN_SKINNER = new CloudItemCoin(2053, 4719, CloudItemRarity.LAPIS, "Coin Skinning", false);
	public static final CloudItemCoin COIN_DEV = new CloudItemCoin(2054, 4720, CloudItemRarity.OBSIDIAN, "Coin Developer", false);
	public static final CloudItemCoin COIN_STAFF = new CloudItemCoin(2055, 4721, CloudItemRarity.LAPIS, "Coin Staffing", false);
	public static final CloudItemCoin COIN_SUPPORTER = new CloudItemCoin(2056, 4722, CloudItemRarity.LAPIS, " Coin Supporter", false);
	public static final CloudItemCoin COIN_MODELER = new CloudItemCoin(2057, 4723, CloudItemRarity.LAPIS, "Coin Modelling", false);
	public static final CloudItemCoin COIN_PRESTIGE = new CloudItemCoin(2058, 4724, CloudItemRarity.LAPIS, "Coin Prestige", false);
	public static final CloudItemCoin COIN_ASSETDEV = new CloudItemCoin(2059, 4725, CloudItemRarity.LAPIS, "Coin Asset Dev", false);
	public static final CloudItemCoin COIN_FUZE = new CloudItemCoin(2060, 4726, CloudItemRarity.LAPIS, "Coin Team Fuze", false);
	public static final CloudItemCoin COIN_ALXTON = new CloudItemCoin(2061, 4727, CloudItemRarity.LAPIS, "Coin Team Alxton", false);
	public static final CloudItemCoin COIN_JEROME = new CloudItemCoin(2062, 4728, CloudItemRarity.LAPIS, "Coin Team Jerome", false);
	public static final CloudItemCoin COIN_PEDRO = new CloudItemCoin(2063, 4729, CloudItemRarity.LAPIS, "Coin Team Pedro", false);
	public static final CloudItemCoin COIN_EDGE = new CloudItemCoin(2064, 4730, CloudItemRarity.LAPIS, "Coin Team CreepersEdge", false);
	public static final CloudItemCoin COIN_JABUTI = new CloudItemCoin(2065, 4731, CloudItemRarity.LAPIS, "Coin Team Jabuti", false);
	public static final CloudItemCoin COIN_INEMAFOO = new CloudItemCoin(2066, 4732, CloudItemRarity.LAPIS, "Coin Team Inemafoo", false);
	public static final CloudItemCoin COIN_MAPPER = new CloudItemCoin(2067, 4733, CloudItemRarity.LAPIS, "Coin Mapper", false);
	public static final CloudItemCoin COIN_NITRO = new CloudItemCoin(2068, 4734, CloudItemRarity.OBSIDIAN, "Coin Nitro", false);
	public static final CloudItemCoin COIN_BATTLEFIELDS = new CloudItemCoin(2069, 4735, CloudItemRarity.LAPIS, "Coin Battlefields", false);
	public static final CloudItemCoin COIN_CCLEAGUE_BRONZE = new CloudItemCoin(2070, 4736, CloudItemRarity.OBSIDIAN, "Coin CCL Bronze", false);
	public static final CloudItemCoin COIN_CCLEAGUE_SILVER = new CloudItemCoin(2071, 4737, CloudItemRarity.OBSIDIAN, "Coin CCL Silver", false);
	public static final CloudItemCoin COIN_CCLEAGUE_GOLD = new CloudItemCoin(2072, 4738, CloudItemRarity.OBSIDIAN, "Coin CCL Gold", false);

	public static final CloudItemBooster BOOSTER_EXP_2 = new CloudItemBooster(2100, 4756, CloudItemRarity.DIAMOND, "x2 EXP 30m Boost", false);
	public static final CloudItemBooster BOOSTER_EXP_3 = new CloudItemBooster(2101, 4757, CloudItemRarity.DIAMOND, "x3 EXP 30m Boost", false);
	public static final CloudItemBooster BOOSTER_EMD_2 = new CloudItemBooster(2102, 4758, CloudItemRarity.DIAMOND, "x2 Emerald 30m Boost", false);

	public static final CloudItemSticker STICKER_CAMP = new CloudItemSticker(2200, 4806, CloudItemRarity.IRON, "Sticker Camper", false);
	public static final CloudItemSticker STICKER_CHICKEN_STRIKE = new CloudItemSticker(2201, 4807, CloudItemRarity.IRON, "Sticker Chicken Strike", false);
	public static final CloudItemSticker STICKER_DINKED = new CloudItemSticker(2202, 4808, CloudItemRarity.IRON, "Sticker Dinked", false);
	public static final CloudItemSticker STICKER_KATO2014_IBUYPOWER = new CloudItemSticker(2203, 4809, CloudItemRarity.LAPIS, "Sticker iBuyPower (Katowice 2014)", false);
	public static final CloudItemSticker STICKER_KAWAIIKILLER = new CloudItemSticker(2204, 4810, CloudItemRarity.LAPIS, "Sticker Kawaii Killer", false);
	public static final CloudItemSticker STICKER_LUCKY13 = new CloudItemSticker(2205, 4811, CloudItemRarity.GOLD, "Sticker Lucky 13", false);
	public static final CloudItemSticker STICKER_HOWL = new CloudItemSticker(2206, 4812, CloudItemRarity.DIAMOND, "Sticker Howl", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_GIS = new CloudItemSticker(2207, 4813, CloudItemRarity.IRON, "Sticker GIS", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_GRAVITY = new CloudItemSticker(2208, 4814, CloudItemRarity.IRON, "Sticker Gravity", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_KINGDEAD = new CloudItemSticker(2209, 4815, CloudItemRarity.IRON, "Sticker KingDead", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_PARADISE9 = new CloudItemSticker(2210, 4816, CloudItemRarity.IRON, "Sticker Paradise 9", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_SEALTEAM6 = new CloudItemSticker(2211, 4817, CloudItemRarity.IRON, "Sticker SEAL Team 6", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_SIKA = new CloudItemSticker(2212, 4818, CloudItemRarity.IRON, "Sticker SIKA", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_TBK = new CloudItemSticker(2213, 4819, CloudItemRarity.IRON, "Sticker TBK", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_CCTN = new CloudItemSticker(2214, 4820, CloudItemRarity.IRON, "Sticker CCTN", false);

	public static final CloudItemStickerCapsule SC_ALPHA = new CloudItemStickerCapsule(3000, 4856, CloudItemRarity.COAL, "Alpha Capsule", false);
	public static final CloudItemStickerCapsule SC_CCL_2020_S1 = new CloudItemStickerCapsule(3001, 4857, CloudItemRarity.COAL, "CCL '20 S1 Capsule", false);

	public static final CloudItemCape CAPE_SUPPORTER = new CloudItemCape(3100, 4956, CloudItemRarity.GOLD, "Cape Supporter", false);
	public static final CloudItemCape CAPE_COUNTERCRAFT = new CloudItemCape(3101, 4957, CloudItemRarity.GOLD, "Cape CounterCraft", false);
	public static final CloudItemCape CAPE_DAWAE = new CloudItemCape(3102, 4958, CloudItemRarity.GOLD, "Cape DaWae", false);
	public static final CloudItemCape CAPE_BOEHMOD = new CloudItemCape(3103, 4959, CloudItemRarity.GOLD, "Cape BoehMod", false);
	public static final CloudItemCape CAPE_POGCHAMP = new CloudItemCape(3104, 4960, CloudItemRarity.GOLD, "Cape PogChamp", false);
	public static final CloudItemCape CAPE_PEPEDANK = new CloudItemCape(3105, 4961, CloudItemRarity.GOLD, "Cape PepeDank", false);
	public static final CloudItemCape CAPE_MCGA = new CloudItemCape(3106, 4962, CloudItemRarity.GOLD, "Cape MCGA", false);
	public static final CloudItemCape CAPE_PUMPKIN = new CloudItemCape(3107, 4963, CloudItemRarity.GOLD, "Cape Pumpkin", false);

	public static final CloudItemCallingCard CARD_DEFAULT = new CloudItemCallingCard(4000, 5256, CloudItemRarity.DEFAULT, "Card Default", true);
	public static final CloudItemCallingCard CARD_NATION_GB = new CloudItemCallingCard(4001, 5256, CloudItemRarity.DEFAULT, "Card GB", true);
	public static final CloudItemCallingCard CARD_NATION_USA = new CloudItemCallingCard(4002, 5256, CloudItemRarity.DEFAULT, "Card USA", true);
	public static final CloudItemCallingCard CARD_NATION_RUS = new CloudItemCallingCard(4003, 5256, CloudItemRarity.DEFAULT, "Card RUS", true);
	public static final CloudItemCallingCard CARD_NATION_CAN = new CloudItemCallingCard(4004, 5256, CloudItemRarity.DEFAULT, "Card CAN", true);
	public static final CloudItemCallingCard CARD_NATION_BR = new CloudItemCallingCard(4005, 5256, CloudItemRarity.DEFAULT, "Card BR", true);
	public static final CloudItemCallingCard CARD_NATION_AU = new CloudItemCallingCard(4006, 5256, CloudItemRarity.DEFAULT, "Card AU", true);
	public static final CloudItemCallingCard CARD_NATION_NZ = new CloudItemCallingCard(4007, 5256, CloudItemRarity.DEFAULT, "Card NZ", true);
	public static final CloudItemCallingCard CARD_NATION_CN = new CloudItemCallingCard(4008, 5256, CloudItemRarity.DEFAULT, "Card CN", true);
	public static final CloudItemCallingCard CARD_NATION_HK = new CloudItemCallingCard(4009, 5256, CloudItemRarity.DEFAULT, "Card HK", true);
	public static final CloudItemCallingCard CARD_NATION_SCOT = new CloudItemCallingCard(4010, 5256, CloudItemRarity.DEFAULT, "Card SCOT", true);
	public static final CloudItemCallingCard CARD_NATION_ENG = new CloudItemCallingCard(4011, 5256, CloudItemRarity.DEFAULT, "Card ENG", true);
	public static final CloudItemCallingCard CARD_NATION_IE = new CloudItemCallingCard(4012, 5256, CloudItemRarity.DEFAULT, "Card IE", true);
	public static final CloudItemCallingCard CARD_NATION_UA = new CloudItemCallingCard(4013, 5256, CloudItemRarity.DEFAULT, "Card UA", true);
	public static final CloudItemCallingCard CARD_NATION_FR = new CloudItemCallingCard(4014, 5256, CloudItemRarity.DEFAULT, "Card FR", true);
	public static final CloudItemCallingCard CARD_NATION_GER = new CloudItemCallingCard(4015, 5256, CloudItemRarity.DEFAULT, "Card GER", true);
	public static final CloudItemCallingCard CARD_NATION_JP = new CloudItemCallingCard(4016, 5256, CloudItemRarity.DEFAULT, "Card JP", true);
	public static final CloudItemCallingCard CARD_NATION_ES = new CloudItemCallingCard(4017, 5256, CloudItemRarity.DEFAULT, "Card ES", true);
	public static final CloudItemCallingCard CARD_CUSTOM_SLIME_GHOST = new CloudItemCallingCard(4018, 5256, CloudItemRarity.LAPIS, "Card Slime Ghost", false);
	public static final CloudItemCallingCard CARD_CUSTOM_FLOWER_WARFARE = new CloudItemCallingCard(4019, 5256, CloudItemRarity.LAPIS, "Card Flower Warfare", false);
	public static final CloudItemCallingCard CARD_CUSTOM_FLOWER_POWER = new CloudItemCallingCard(4020, 5256, CloudItemRarity.LAPIS, "Card Flower Power", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_BOOM = new CloudItemCallingCard(4021, 5256, CloudItemRarity.LAPIS, "Card Cartoon Boom", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_HEY = new CloudItemCallingCard(4022, 5256, CloudItemRarity.LAPIS, "Card Cartoon Hey", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_OH = new CloudItemCallingCard(4023, 5256, CloudItemRarity.LAPIS, "Card Cartoon Oh", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_OOPS = new CloudItemCallingCard(4024, 5256, CloudItemRarity.LAPIS, "Card Cartoon Oops", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_SMILE = new CloudItemCallingCard(4025, 5256, CloudItemRarity.LAPIS, "Card Cartoon Smile", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_WOW = new CloudItemCallingCard(4026, 5256, CloudItemRarity.LAPIS, "Card Cartoon Wow", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_WTF = new CloudItemCallingCard(4027, 5256, CloudItemRarity.LAPIS, "Card Cartoon WTF", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CHILI_PEPPER = new CloudItemCallingCard(4028, 5256, CloudItemRarity.LAPIS, "Card Chili Pepper", false);
	public static final CloudItemCallingCard CARD_CUSTOM_WITCH_KITTY = new CloudItemCallingCard(4029, 5256, CloudItemRarity.LAPIS, "Card Witch Kitty", false);
	public static final CloudItemCallingCard CARD_CUSTOM_PURPLE_NINJA = new CloudItemCallingCard(4030, 5256, CloudItemRarity.LAPIS, "Card Purple Ninja", false);
	public static final CloudItemCallingCard CARD_CUSTOM_GAME_OVER = new CloudItemCallingCard(4031, 5256, CloudItemRarity.LAPIS, "Card Game Over", false);
	public static final CloudItemCallingCard CARD_CUSTOM_SKULL_PATRIOT = new CloudItemCallingCard(4032, 5256, CloudItemRarity.LAPIS, "Card Skull Patriot", false);
	public static final CloudItemCallingCard CARD_CUSTOM_GAMER_FLARE = new CloudItemCallingCard(4033, 5256, CloudItemRarity.LAPIS, "Card Gamer Flare", false);
	public static final CloudItemCallingCard CARD_CUSTOM_GREEN_VIP = new CloudItemCallingCard(4034, 5256, CloudItemRarity.LAPIS, "Card Green VIP", false);
	public static final CloudItemCallingCard CARD_CUSTOM_RANGER = new CloudItemCallingCard(4035, 5256, CloudItemRarity.LAPIS, "Card Ranger", false);
	public static final CloudItemCallingCard CARD_CUSTOM_INSERTION = new CloudItemCallingCard(4036, 5256, CloudItemRarity.LAPIS, "Card Insertion", false);
	public static final CloudItemCallingCard CARD_CUSTOM_SOLDIER_SILHOUETTE = new CloudItemCallingCard(4037, 5256, CloudItemRarity.LAPIS, "Card Soldier Silhouette", false);
	public static final CloudItemCallingCard CARD_NATION_BE = new CloudItemCallingCard(4038, 5256, CloudItemRarity.DEFAULT, "Card BE", true);

	public static final CloudItemKey CASE_KEY_ALPHA = new CloudItemKey(4500, 5756, CloudItemRarity.COAL, "Key Alpha", false);
	public static final CloudItemKey CASE_KEY_ALPHA2 = new CloudItemKey(4501, 5757, CloudItemRarity.COAL, "Key Alpha 2", false);
	public static final CloudItemKey CASE_KEY_ALPHA3 = new CloudItemKey(4502, 5758, CloudItemRarity.COAL, "Key Alpha 3", false);
	public static final CloudItemKey CASE_KEY_HAT = new CloudItemKey(4503, 5759, CloudItemRarity.COAL, "Key Hats 1", false);
	public static final CloudItemKey CASE_KEY_HALLOWEEN1 = new CloudItemKey(4504, 5760, CloudItemRarity.COAL, "Key Halloween 1", false);
	public static final CloudItemKey CASE_KEY_EXO = new CloudItemKey(4505, 5761, CloudItemRarity.COAL, "Key Exo", false);

	private static final List<CloudItem> POOL_KNIFE_TAC = List.of(
		KNIFE_TAC_MARBLE, KNIFE_TAC_TIGER, KNIFE_TAC_STEEL, KNIFE_TAC_DOPPLAR, KNIFE_TAC_LORE, KNIFE_TAC_GAMMA
	);
	private static final List<CloudItem> POOL_KNIFE_BAY = List.of(
		KNIFE_BAY, KNIFE_BAY_MARBLE, KNIFE_BAY_TIGER, KNIFE_BAY_STEEL, KNIFE_BAY_DOPPLAR, KNIFE_BAY_LORE, KNIFE_BAY_GAMMA, KNIFE_BAY_DRAGONLORE
	);
	private static final List<CloudItem> POOL_KNIFE_KAR = List.of(
		KNIFE_KAR, KNIFE_KAR_MARBLE, KNIFE_KAR_TIGER, KNIFE_KAR_STEEL, KNIFE_KAR_DOPPLAR, KNIFE_KAR_LORE, KNIFE_KAR_GAMMA, KNIFE_KAR_EAGLE
	);
	private static final List<CloudItem> POOL_KNIFE_BUTTERFLY = List.of(
		KNIFE_BUTTERFLY
	);

	public static final CloudItemCase WC_ALPHA = new CloudItemCase(2000, 4100, CloudItemRarity.COAL, "Alpha 1 Case", false, CASE_KEY_ALPHA, POOL_KNIFE_TAC)
		.modifyContents(collection -> collection
			.add(M4A4_WAVES, 110)
			.add(P250_IRRADIATED, 110)
			.add(DRAGUNOV_SKY, 110)
			.add(VECTOR_CIRCUIT, 100)
			.add(P90_RUST, 80)
			.add(SCAR20_CYREX, 60)
			.add(MAG7_ROYALFIRE, 40)
			.add(DEAGLE_NIGHTRAZER, 20)
			.add(AK47_PROPAGANDA, 10)
			.add(M249_NEON, 10)
			.add(M4A4_EVILDAIMYO, 4)
			.add(SAWEDOFF_WASTELANDPRINCESS, 4)
			.add(AK47_ASMO, 2)
			.add(R8_FADE, 2)
			.add(AWP_DRAGON, 1, false)
			.add(KNIFE_TAC_DISPLAY, 1, false)
		);
	public static final CloudItemCase WC_ALPHA2 = new CloudItemCase(2001, 4101, CloudItemRarity.COAL, "Alpha 2 Case", false, CASE_KEY_ALPHA2, POOL_KNIFE_BAY)
		.modifyContents(collection -> collection
			.add(NOVA_OXIDEBLAZE, 100)
			.add(TEC9_QUAKORONIC, 80)
			.add(MAC10_RACE, 60)
			.add(M4A4_CAKEY, 40)
			.add(AWP_MANOWAR, 20)
			.add(FAMAS_AKARI, 10)
			.add(SAWEDOFF_KRAKEN, 10)
			.add(AK47_NEONREVOLUTION, 4)
			.add(P90_KRAKEN, 4)
			.add(P250_SEEYALATER, 2)
			.add(SCAR20_UNICORN, 2)
			.add(KNIFE_TAC_DISPLAY, 1, false)
		);
	public static final CloudItemCase WC_ALPHA3 = new CloudItemCase(2002, 4102, CloudItemRarity.COAL, "Alpha 3 Case", false, CASE_KEY_ALPHA3, POOL_KNIFE_KAR)
		.modifyContents(collection -> collection
			.add(M4A4_NERFED, 100)
			.add(G18_RACE, 90)
			.add(VECTOR_BLOODYEQUATION, 85)
			.add(P250_CITY, 80)
			.add(M1911_DRAGON, 60)
			.add(UMP45_PRIMAL, 40)
			.add(R8_GOLD, 20)
			.add(DEAGLE_DEADHORIZON, 10)
			.add(CZ75_PHANTOM, 10)
			.add(FAMAS_WARHAWK, 4)
			.add(FNFAL_VAPORWAVE, 4)
			.add(AWP_ASMO, 2)
			.add(M4A4_HOWL, 1, false)
			.add(KNIFE_TAC_DISPLAY, 1, false)
		);
	public static final CloudItemCase HC_ALPHA1 = new CloudItemCase(2003, 4103, CloudItemRarity.COAL, "Hat Case", false, CASE_KEY_HAT, null)
		.modifyContents(collection -> collection
			.add(HAT_HARDRED, 110)
			.add(HAT_HARDORANGE, 110)
			.add(HAT_HARDYELLOW, 110)
			.add(HAT_FIREMAN, 80)
			.add(HAT_FIREMANCHIEF, 80)
			.add(HAT_TOPHAT, 30)
			.add(HAT_TV, 30)
			.add(HAT_VIKING, 15)
			.add(HAT_CATHAT, 6)
		);
	public static final CloudItemCase WC_HALLOWEEN1 = new CloudItemCase(2004, 4104, CloudItemRarity.COAL, "Halloween Case", false, CASE_KEY_HALLOWEEN1, POOL_KNIFE_KAR)
		.modifyContents(collection -> collection
			.add(UMP45_EDIFICE, 100)
			.add(M1911_TUXEDO, 85)
			.add(M249_GHOST, 80)
			.add(G18_DEEP_BLUE, 60)
			.add(FAMAS_SPOOKY, 40)
			.add(DEAGLE_GRIN, 20)
			.add(M4A4_PUMPKIN, 10)
			.add(MAC10_PUMPKIN, 10)
			.add(AWP_BONES, 4)
			.add(MAG7_VAMPIRE, 4)
			.add(AK47_HAUNTED, 2)
			.add(G18_HIGHROLLER, 2)
			.add(KNIFE_TAC_DISPLAY, 1, false)
		);
	public static final CloudItemCase WC_EXO = new CloudItemCase(2005, 4105, CloudItemRarity.COAL, "Exo Case", false, CASE_KEY_EXO, POOL_KNIFE_BUTTERFLY)
		.modifyContents(collection -> collection
			.add(TEC9_ECO, 100)
			.add(SAWEDOFF_MIDNIGHT, 100)
			.add(AK47_BLOODSPORT, 90)
			.add(AWP_REDLINE, 80)
			.add(AK47_FOX, 70)
			.add(M4A4_PATRIOT, 60)
			.add(DRAGUNOV_STEAMPUNK, 50)
			.add(AWP_BOOM, 20)
			.add(FNFAL_ASMO, 20)
			.add(P250_BEAST, 20)
			.add(TEC9_AQUARIUM, 5)
			.add(NOVA_HYPER_BEAST, 5)
			.add(KNIFE_TAC_DISPLAY, 1, false)
		);

	private CloudItems() {
	}

	public static Collection<CloudItem> all() {
		return Collections.unmodifiableCollection(ID_MAP.values());
	}

	public static List<CloudItem> defaults() {
		return Collections.unmodifiableList(DEFAULTS);
	}

	public static CloudItem getById(int id) {
		return ID_MAP.get(id);
	}

	public static CloudItem getRandomDrop() {
		// [0, 3]
		int value = ThreadLocalRandom.current().nextInt(4);
		if (value < 3) {
			return CloudItemCase.getRandomForDrop();
		} else {
			return CloudItemGun.getRandomForDrop();
		}
	}
}
