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

import static dev.vuis.cccr.cloud.item.CloudItemCollection.ALPHA_1;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.ALPHA_2;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.ALPHA_3;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.EXO;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.HALLOWEEN;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.PREALPHA;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.COAL;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.DEFAULT;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.DIAMOND;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.GOLD;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.IRON;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.LAPIS;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.OBSIDIAN;

@SuppressWarnings("unused")
public final class CloudItems {
	static final List<CloudItem> DEFAULTS = new ArrayList<>();
	static final Map<Integer, CloudItem> ID_MAP = new HashMap<>();
	static final Multimap<CloudItemRarity, CloudItem> RARITY_MAP = HashMultimap.create();

	public static final CloudItemGun M4A4 = new CloudItemGun(100, 4256, DEFAULT, "M4A4", true);
	public static final CloudItemGun M4A4_WAVES = new CloudItemGun(101, 4256, IRON, "M4A4 Waves", false, ALPHA_1);
	public static final CloudItemGun M4A4_EVILDAIMYO = new CloudItemGun(102, 4256, GOLD, "M4A4 EvilDaimyo", false, ALPHA_1);
	public static final CloudItemGun M4A4_CAKEY = new CloudItemGun(103, 4256, IRON, "M4A4 Cakey", false, ALPHA_2);
	public static final CloudItemGun M4A4_HOWL = new CloudItemGun(104, 4256, OBSIDIAN, "M4A4 Howl", false, ALPHA_3);
	public static final CloudItemGun M4A4_NERFED = new CloudItemGun(105, 4256, IRON, "M4A4 Nerfed", false, ALPHA_3);
	public static final CloudItemGun M4A4_PUMPKIN = new CloudItemGun(106, 4256, GOLD, "M4A4 Pumpkin", false, HALLOWEEN);
	public static final CloudItemGun M4A4_PATRIOT = new CloudItemGun(107, 4256, LAPIS, "M4A4 Patriot", false, EXO);
	public static final CloudItemGun M4A4_CYREX = new CloudItemGun(108, 4256, LAPIS, "M4A4 Cyrex", false, PREALPHA);

	public static final CloudItemGun AK47 = new CloudItemGun(200, 4257, DEFAULT, "AK47", true);
	public static final CloudItemGun AK47_PROPAGANDA = new CloudItemGun(201, 4257, GOLD, "AK47 Propaganda", false, ALPHA_1);
	public static final CloudItemGun AK47_ASMO = new CloudItemGun(202, 4257, DIAMOND, "AK47 Asmo", false, ALPHA_1);
	public static final CloudItemGun AK47_NEONREVOLUTION = new CloudItemGun(203, 4257, GOLD, "AK47 Neon Revolution", false, ALPHA_2);
	public static final CloudItemGun AK47_HAUNTED = new CloudItemGun(204, 4257, DIAMOND, "AK47 Haunted", false, HALLOWEEN);
	public static final CloudItemGun AK47_BLOODSPORT = new CloudItemGun(205, 4257, IRON, "AK47 Bloodsport", false, EXO);
	public static final CloudItemGun AK47_FOX = new CloudItemGun(206, 4257, LAPIS, "AK47 Fox", false, EXO);
	public static final CloudItemGun AK47_VULCAN = new CloudItemGun(207, 4257, DIAMOND, "AK47 Vulcan", false, PREALPHA);

	public static final CloudItemGun FAMAS = new CloudItemGun(300, 4258, DEFAULT, "Famas", true);
	public static final CloudItemGun FAMAS_AKARI = new CloudItemGun(301, 4258, LAPIS, "Famas Akari", false, ALPHA_2);
	public static final CloudItemGun FAMAS_WARHAWK = new CloudItemGun(302, 4258, DIAMOND, "Famas Warhawk", false, ALPHA_3);
	public static final CloudItemGun FAMAS_SPOOKY = new CloudItemGun(303, 4258, LAPIS, "Famas Spooky", false, HALLOWEEN);
	public static final CloudItemGun FAMAS_PULSE = new CloudItemGun(304, 4258, LAPIS, "Famas Pulse", false, PREALPHA);

	public static final CloudItemGun FNFAL = new CloudItemGun(400, 4259, DEFAULT, "FN FAL", true);
	public static final CloudItemGun FNFAL_VAPORWAVE = new CloudItemGun(401, 4259, GOLD, "FN FAL Vaporwave", false, ALPHA_3);
	public static final CloudItemGun FNFAL_ASMO = new CloudItemGun(402, 4259, GOLD, "FN FAL Asmo", false, EXO);
	public static final CloudItemGun FNFAL_STAIN = new CloudItemGun(403, 4259, IRON, "FN FAL Stain", false, PREALPHA);

	public static final CloudItemGun GALIL = new CloudItemGun(450, 4260, DEFAULT, "Galil AR", true);

	public static final CloudItemGun M1911 = new CloudItemGun(500, 4266, DEFAULT, "M1911", true);
	public static final CloudItemGun M1911_DRAGON = new CloudItemGun(501, 4266, LAPIS, "M1911 Dragon", false, ALPHA_3);
	public static final CloudItemGun M1911_TUXEDO = new CloudItemGun(502, 4266, IRON, "M1911 Tuxedo", false, HALLOWEEN);
	public static final CloudItemGun M1911_RAIN = new CloudItemGun(503, 4266, COAL, "M1911 Rain", false, PREALPHA);

	public static final CloudItemGun G18 = new CloudItemGun(600, 4267, DEFAULT, "G18", true);
	public static final CloudItemGun G18_RACE = new CloudItemGun(601, 4267, IRON, "G18 Race", false, ALPHA_3);
	public static final CloudItemGun G18_HIGHROLLER = new CloudItemGun(602, 4267, DIAMOND, "G18 Highroller", false, HALLOWEEN);
	public static final CloudItemGun G18_DEEP_BLUE = new CloudItemGun(603, 4267, IRON, "G18 Deep Blue", false, HALLOWEEN);
	public static final CloudItemGun G18_FADE = new CloudItemGun(604, 4267, GOLD, "G18 Fade", false, PREALPHA);

	public static final CloudItemGun TEC9 = new CloudItemGun(650, 4270, DEFAULT, "TEC9", true);
	public static final CloudItemGun TEC9_QUAKORONIC = new CloudItemGun(651, 4270, IRON, "TEC9 Quakoronic", false, ALPHA_2);
	public static final CloudItemGun TEC9_ECO = new CloudItemGun(652, 4270, IRON, "TEC9 Eco", false, EXO);
	public static final CloudItemGun TEC9_AQUARIUM = new CloudItemGun(653, 4270, DIAMOND, "TEC9 Aquarium", false, EXO);

	public static final CloudItemGun DEAGLE = new CloudItemGun(700, 4268, DEFAULT, "Deagle", true);
	public static final CloudItemGun DEAGLE_NIGHTRAZER = new CloudItemGun(701, 4268, LAPIS, "Deagle NightRazer", false, ALPHA_1);
	public static final CloudItemGun DEAGLE_DEADHORIZON = new CloudItemGun(702, 4268, GOLD, "Deagle Dead Horizon", false, ALPHA_3);
	public static final CloudItemGun DEAGLE_GRIN = new CloudItemGun(703, 4268, LAPIS, "Deagle Grin", false, HALLOWEEN);
	public static final CloudItemGun DEAGLE_NAVY = new CloudItemGun(704, 4268, COAL, "Deagle Navy", false, PREALPHA);

	public static final CloudItemGun P250 = new CloudItemGun(750, 4269, DEFAULT, "P250", true);
	public static final CloudItemGun P250_IRRADIATED = new CloudItemGun(751, 4269, IRON, "P250 Irradiated", false, ALPHA_1);
	public static final CloudItemGun P250_SEEYALATER = new CloudItemGun(752, 4269, DIAMOND, "P250 SeeYaLater", false, ALPHA_2);
	public static final CloudItemGun P250_CITY = new CloudItemGun(753, 4269, IRON, "P250 City", false, ALPHA_3);
	public static final CloudItemGun P250_BEAST = new CloudItemGun(754, 4269, GOLD, "P250 Beast", false, EXO);
	public static final CloudItemGun P250_ARCTIC = new CloudItemGun(755, 4269, COAL, "P250 Arctic", false, PREALPHA);

	public static final CloudItemGun P90 = new CloudItemGun(800, 4276, DEFAULT, "P90", true);
	public static final CloudItemGun P90_RUST = new CloudItemGun(801, 4276, IRON, "P90 Rust", false, ALPHA_1);
	public static final CloudItemGun P90_KRAKEN = new CloudItemGun(802, 4276, GOLD, "P90 Kraken", false, ALPHA_2);
	public static final CloudItemGun P90_RUBY = new CloudItemGun(803, 4276, IRON, "P90 Ruby", false, PREALPHA);

	public static final CloudItemGun MAC10 = new CloudItemGun(900, 4277, DEFAULT, "MAC10", true);
	public static final CloudItemGun MAC10_RACE = new CloudItemGun(901, 4277, IRON, "MAC10 Race", false, ALPHA_2);
	public static final CloudItemGun MAC10_PUMPKIN = new CloudItemGun(902, 4277, LAPIS, "MAC10 Pumpkin", false, HALLOWEEN);
	public static final CloudItemGun MAC10_VIOLET = new CloudItemGun(903, 4277, LAPIS, "MAC10 Violet", false, PREALPHA);

	public static final CloudItemGun VECTOR = new CloudItemGun(1000, 4278, DEFAULT, "Vector", true);
	public static final CloudItemGun VECTOR_CIRCUIT = new CloudItemGun(1001, 4278, IRON, "Vector Circuit", false, ALPHA_1);
	public static final CloudItemGun VECTOR_BLOODYEQUATION = new CloudItemGun(1002, 4278, IRON, "Vector Bloody Equation", false, ALPHA_3);
	public static final CloudItemGun VECTOR_ARCTIC = new CloudItemGun(1003, 4278, GOLD, "Vector Arctic", false, PREALPHA);

	public static final CloudItemGun AWP = new CloudItemGun(1100, 4286, DEFAULT, "AWP", true);
	public static final CloudItemGun AWP_DRAGON = new CloudItemGun(1101, 4286, OBSIDIAN, "AWP Dragon", false, ALPHA_1);
	public static final CloudItemGun AWP_MANOWAR = new CloudItemGun(1102, 4286, LAPIS, "AWP Man O War", false, ALPHA_2);
	public static final CloudItemGun AWP_ASMO = new CloudItemGun(1103, 4286, DIAMOND, "AWP Asmo", false, ALPHA_3);
	public static final CloudItemGun AWP_BONES = new CloudItemGun(1104, 4286, GOLD, "AWP Bones", false, HALLOWEEN);
	public static final CloudItemGun AWP_REDLINE = new CloudItemGun(1105, 4286, IRON, "AWP Redline", false, EXO);
	public static final CloudItemGun AWP_BOOM = new CloudItemGun(1106, 4286, GOLD, "AWP Boom", false, EXO);
	public static final CloudItemGun AWP_LIGHTNING = new CloudItemGun(1107, 4286, DIAMOND, "AWP Lightning", false, PREALPHA);

	public static final CloudItemGun DRAGUNOV = new CloudItemGun(1200, 4287, DEFAULT, "Dragunov", true);
	public static final CloudItemGun DRAGUNOV_SKY = new CloudItemGun(1201, 4287, IRON, "Dragunov Sky", false, ALPHA_1);
	public static final CloudItemGun DRAGUNOV_STEAMPUNK = new CloudItemGun(1202, 4287, LAPIS, "Dragunov Steampunk", false, EXO);
	public static final CloudItemGun DRAGUNOV_JUNGLE = new CloudItemGun(1203, 4287, IRON, "Dragunov Jungle", false, PREALPHA);

	public static final CloudItemGun SCAR20 = new CloudItemGun(1300, 4288, DEFAULT, "Scar20", true);
	public static final CloudItemGun SCAR20_CYREX = new CloudItemGun(1301, 4288, LAPIS, "Scar20 Cyrex", false, ALPHA_1);
	public static final CloudItemGun SCAR20_UNICORN = new CloudItemGun(1302, 4288, DIAMOND, "Scar20 Unicorn", false, ALPHA_2);
	public static final CloudItemGun SCAR20_ELITE = new CloudItemGun(1303, 4288, COAL, "Scar20 Elite", false, PREALPHA);

	public static final CloudItemGun SSG08 = new CloudItemGun(1400, 4289, DEFAULT, "SSG-08", true);

	public static final CloudItemKnife KNIFE_TAC = new CloudItemKnife(1500, 4456, DEFAULT, "Tac Knife", true);
	public static final CloudItemKnife KNIFE_TAC_DISPLAY = new CloudItemKnife(1501, 4457, OBSIDIAN, "Special Item", false);
	public static final CloudItemKnife KNIFE_TAC_MARBLE = new CloudItemKnife(1502, 4458, DIAMOND, "Tac Knife Marble", false);
	public static final CloudItemKnife KNIFE_TAC_TIGER = new CloudItemKnife(1503, 4459, DIAMOND, "Tac Knife Tiger", false);
	public static final CloudItemKnife KNIFE_TAC_STEEL = new CloudItemKnife(1504, 4460, DIAMOND, "Tac Knife Steel", false);
	public static final CloudItemKnife KNIFE_TAC_DOPPLAR = new CloudItemKnife(1505, 4461, DIAMOND, "Tac Knife Doppler", false);
	public static final CloudItemKnife KNIFE_TAC_LORE = new CloudItemKnife(1506, 4462, DIAMOND, "Tac Knife Lore", false);
	public static final CloudItemKnife KNIFE_TAC_GAMMA = new CloudItemKnife(1507, 4463, DIAMOND, "Tac Knife Gamma", false);
	public static final CloudItemKnife KNIFE_TAC_PRESTIGE = new CloudItemKnife(1508, 4464, OBSIDIAN, "Tac Knife Prestige", false);

	public static final CloudItemKnife KNIFE_BAY = new CloudItemKnife(1510, 4466, DIAMOND, "Bayonet", false);
	public static final CloudItemKnife KNIFE_BAY_MARBLE = new CloudItemKnife(1511, 4467, DIAMOND, "Bayonet Marble", false);
	public static final CloudItemKnife KNIFE_BAY_TIGER = new CloudItemKnife(1512, 4468, DIAMOND, "Bayonet Tiger", false);
	public static final CloudItemKnife KNIFE_BAY_STEEL = new CloudItemKnife(1513, 4469, DIAMOND, "Bayonet Steel", false);
	public static final CloudItemKnife KNIFE_BAY_DOPPLAR = new CloudItemKnife(1514, 4470, DIAMOND, "Bayonet Doppler", false);
	public static final CloudItemKnife KNIFE_BAY_LORE = new CloudItemKnife(1515, 4471, DIAMOND, "Bayonet Lore", false);
	public static final CloudItemKnife KNIFE_BAY_GAMMA = new CloudItemKnife(1516, 4472, DIAMOND, "Bayonet Gamma", false);
	public static final CloudItemKnife KNIFE_BAY_DRAGONLORE = new CloudItemKnife(1517, 4473, DIAMOND, "Bayonet Dragonlore", false);
	public static final CloudItemKnife KNIFE_BAY_PRESTIGE = new CloudItemKnife(1518, 4474, OBSIDIAN, "Bayonet Prestige", false);

	public static final CloudItemKnife KNIFE_KAR = new CloudItemKnife(1520, 4476, DIAMOND, "Karambit", false);
	public static final CloudItemKnife KNIFE_KAR_MARBLE = new CloudItemKnife(1521, 4477, DIAMOND, "Karambit Marble", false);
	public static final CloudItemKnife KNIFE_KAR_TIGER = new CloudItemKnife(1522, 4478, DIAMOND, "Karambit Tiger", false);
	public static final CloudItemKnife KNIFE_KAR_STEEL = new CloudItemKnife(1523, 4479, DIAMOND, "Karambit Steel", false);
	public static final CloudItemKnife KNIFE_KAR_DOPPLAR = new CloudItemKnife(1524, 4480, DIAMOND, "Karambit Doppler", false);
	public static final CloudItemKnife KNIFE_KAR_LORE = new CloudItemKnife(1525, 4481, DIAMOND, "Karambit Lore", false);
	public static final CloudItemKnife KNIFE_KAR_GAMMA = new CloudItemKnife(1526, 4482, DIAMOND, "Karambit Gamma", false);
	public static final CloudItemKnife KNIFE_KAR_EAGLE = new CloudItemKnife(1527, 4483, DIAMOND, "Karambit Eagle", false);
	public static final CloudItemKnife KNIFE_KAR_PRESTIGE = new CloudItemKnife(1528, 4483, OBSIDIAN, "Karambit Prestige", false);

	public static final CloudItemKnife KNIFE_BFL = new CloudItemKnife(1530, 4486, DIAMOND, "Butterfly", false);
	public static final CloudItemKnife KNIFE_BFL_PRESTIGE = new CloudItemKnife(1531, 4487, OBSIDIAN, "Butterfly Prestige", false);

	public static final CloudItemGun NOVA = new CloudItemGun(1600, 4296, DEFAULT, "Nova", true);
	public static final CloudItemGun NOVA_OXIDEBLAZE = new CloudItemGun(1601, 4296, IRON, "Nova Oxide Blaze", false, ALPHA_2);
	public static final CloudItemGun NOVA_HYPER_BEAST = new CloudItemGun(1602, 4296, DIAMOND, "Nova Hyper Beast", false, EXO);

	public static final CloudItemGun SAWEDOFF = new CloudItemGun(1650, 4297, DEFAULT, "Sawed-Off", true);
	public static final CloudItemGun SAWEDOFF_WASTELANDPRINCESS = new CloudItemGun(1651, 4297, GOLD, "Sawed-Off WastelandPrincess", false, ALPHA_1);
	public static final CloudItemGun SAWEDOFF_KRAKEN = new CloudItemGun(1652, 4297, LAPIS, "Sawed-Off Kraken", false, ALPHA_2);
	public static final CloudItemGun SAWEDOFF_MIDNIGHT = new CloudItemGun(1653, 4297, IRON, "Sawed-Off Midnight", false, EXO);

	public static final CloudItemGun MAG7 = new CloudItemGun(1700, 4298, DEFAULT, "Mag-7", true);
	public static final CloudItemGun MAG7_ROYALFIRE = new CloudItemGun(1701, 4298, LAPIS, "Mag-7 Royal Fire", false, ALPHA_1);
	public static final CloudItemGun MAG7_VAMPIRE = new CloudItemGun(1702, 4298, GOLD, "Mag-7 Vampire", false, HALLOWEEN);

	public static final CloudItemGun M249 = new CloudItemGun(1750, 4299, DEFAULT, "M249", true);
	public static final CloudItemGun M249_NEON = new CloudItemGun(1751, 4299, LAPIS, "M249 Neon", false, ALPHA_1);
	public static final CloudItemGun M249_GHOST = new CloudItemGun(1752, 4299, IRON, "M249 Ghost", false, HALLOWEEN);

	public static final CloudItemGun CZ75 = new CloudItemGun(1800, 4271, DEFAULT, "CZ75", true);
	public static final CloudItemGun CZ75_PHANTOM = new CloudItemGun(1801, 4271, GOLD, "CZ75 Phantom", false, ALPHA_3);

	public static final CloudItemGun FIVESEVEN = new CloudItemGun(1815, 4272, DEFAULT, "Fiveseven", true);

	public static final CloudItemGun R8 = new CloudItemGun(1826, 4273, DEFAULT, "R8", true);
	public static final CloudItemGun R8_FADE = new CloudItemGun(1827, 4273, DIAMOND, "R8 Fade", false, ALPHA_1);
	public static final CloudItemGun R8_GOLD = new CloudItemGun(1828, 4273, LAPIS, "R8 Gold", false, ALPHA_3);

	public static final CloudItemGun UMP45 = new CloudItemGun(1850, 4279, DEFAULT, "UMP-45", true);
	public static final CloudItemGun UMP45_PRIMAL = new CloudItemGun(1851, 4279, LAPIS, "UMP-45 Primal", false, ALPHA_3);
	public static final CloudItemGun UMP45_EDIFICE = new CloudItemGun(1852, 4279, IRON, "UMP-45 Edifice", false, HALLOWEEN);

	public static final CloudItemHat HAT_TOPHAT = new CloudItemHat(1900, 4656, LAPIS, "Top Hat", false);
	public static final CloudItemHat HAT_CATHAT = new CloudItemHat(1901, 4657, DIAMOND, "Cat's Hat", false);
	public static final CloudItemHat HAT_HARDRED = new CloudItemHat(1902, 4658, COAL, "Hardhat Red", false);
	public static final CloudItemHat HAT_HARDORANGE = new CloudItemHat(1903, 4659, COAL, "Hardhat Orange", false);
	public static final CloudItemHat HAT_HARDYELLOW = new CloudItemHat(1904, 4660, COAL, "Hardhat Yellow", false);
	public static final CloudItemHat HAT_VIKING = new CloudItemHat(1905, 4661, GOLD, "Viking Helmet", false);
	public static final CloudItemHat HAT_FIREMAN = new CloudItemHat(1906, 4662, IRON, "Fireman Hat", false);
	public static final CloudItemHat HAT_FIREMANCHIEF = new CloudItemHat(1907, 4663, IRON, "Fire Chief Hat", false);
	public static final CloudItemHat HAT_TV = new CloudItemHat(1908, 4664, LAPIS, "TV Hat", false);

	public static final CloudItemGeneric TRADE_CONTRACT = new CloudItemGeneric(2040, 4706, DEFAULT, "Trade Contract", true, true, true, true, true, true);

	public static final CloudItemGeneric NAME_TAG = new CloudItemGeneric(2041, 4707, COAL, "Name Tag", false, true, true, false, false, false);

	public static final CloudItemCoin COIN_BETA = new CloudItemCoin(2050, 4716, LAPIS, "Coin BETA Age", false);
	public static final CloudItemCoin COIN_ALPHA = new CloudItemCoin(2051, 4717, LAPIS, "Coin ALPHA Age", false);
	public static final CloudItemCoin COIN_DISCORD = new CloudItemCoin(2052, 4718, LAPIS, "Coin Discord", false);
	public static final CloudItemCoin COIN_SKINNER = new CloudItemCoin(2053, 4719, LAPIS, "Coin Skinning", false);
	public static final CloudItemCoin COIN_DEV = new CloudItemCoin(2054, 4720, OBSIDIAN, "Coin Developer", false);
	public static final CloudItemCoin COIN_STAFF = new CloudItemCoin(2055, 4721, LAPIS, "Coin Staffing", false);
	public static final CloudItemCoin COIN_SUPPORTER = new CloudItemCoin(2056, 4722, LAPIS, " Coin Supporter", false);
	public static final CloudItemCoin COIN_MODELER = new CloudItemCoin(2057, 4723, LAPIS, "Coin Modelling", false);
	public static final CloudItemCoin COIN_PRESTIGE = new CloudItemCoin(2058, 4724, LAPIS, "Coin Prestige", false);
	public static final CloudItemCoin COIN_ASSETDEV = new CloudItemCoin(2059, 4725, LAPIS, "Coin Asset Dev", false);
	public static final CloudItemCoin COIN_FUZE = new CloudItemCoin(2060, 4726, LAPIS, "Coin Team Fuze", false);
	public static final CloudItemCoin COIN_ALXTON = new CloudItemCoin(2061, 4727, LAPIS, "Coin Team Alxton", false);
	public static final CloudItemCoin COIN_JEROME = new CloudItemCoin(2062, 4728, LAPIS, "Coin Team Jerome", false);
	public static final CloudItemCoin COIN_PEDRO = new CloudItemCoin(2063, 4729, LAPIS, "Coin Team Pedro", false);
	public static final CloudItemCoin COIN_EDGE = new CloudItemCoin(2064, 4730, LAPIS, "Coin Team CreepersEdge", false);
	public static final CloudItemCoin COIN_JABUTI = new CloudItemCoin(2065, 4731, LAPIS, "Coin Team Jabuti", false);
	public static final CloudItemCoin COIN_INEMAFOO = new CloudItemCoin(2066, 4732, LAPIS, "Coin Team Inemafoo", false);
	public static final CloudItemCoin COIN_MAPPER = new CloudItemCoin(2067, 4733, LAPIS, "Coin Mapper", false);
	public static final CloudItemCoin COIN_NITRO = new CloudItemCoin(2068, 4734, OBSIDIAN, "Coin Nitro", false);
	public static final CloudItemCoin COIN_BATTLEFIELDS = new CloudItemCoin(2069, 4735, LAPIS, "Coin Battlefields", false);
	public static final CloudItemCoin COIN_CCLEAGUE_BRONZE = new CloudItemCoin(2070, 4736, OBSIDIAN, "Coin CCL Bronze", false);
	public static final CloudItemCoin COIN_CCLEAGUE_SILVER = new CloudItemCoin(2071, 4737, OBSIDIAN, "Coin CCL Silver", false);
	public static final CloudItemCoin COIN_CCLEAGUE_GOLD = new CloudItemCoin(2072, 4738, OBSIDIAN, "Coin CCL Gold", false);

	public static final CloudItemBooster BOOSTER_EXP_2 = new CloudItemBooster(2100, 4756, DIAMOND, "x2 EXP 30m Boost", false);
	public static final CloudItemBooster BOOSTER_EXP_3 = new CloudItemBooster(2101, 4757, DIAMOND, "x3 EXP 30m Boost", false);
	public static final CloudItemBooster BOOSTER_EMD_2 = new CloudItemBooster(2102, 4758, DIAMOND, "x2 Emerald 30m Boost", false);

	public static final CloudItemSticker STICKER_CAMP = new CloudItemSticker(2200, 4806, IRON, "Sticker Camper", false);
	public static final CloudItemSticker STICKER_CHICKEN_STRIKE = new CloudItemSticker(2201, 4807, IRON, "Sticker Chicken Strike", false);
	public static final CloudItemSticker STICKER_DINKED = new CloudItemSticker(2202, 4808, IRON, "Sticker Dinked", false);
	public static final CloudItemSticker STICKER_KATO2014_IBUYPOWER = new CloudItemSticker(2203, 4809, LAPIS, "Sticker iBuyPower (Katowice 2014)", false);
	public static final CloudItemSticker STICKER_KAWAIIKILLER = new CloudItemSticker(2204, 4810, LAPIS, "Sticker Kawaii Killer", false);
	public static final CloudItemSticker STICKER_LUCKY13 = new CloudItemSticker(2205, 4811, GOLD, "Sticker Lucky 13", false);
	public static final CloudItemSticker STICKER_HOWL = new CloudItemSticker(2206, 4812, DIAMOND, "Sticker Howl", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_GIS = new CloudItemSticker(2207, 4813, IRON, "Sticker GIS", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_GRAVITY = new CloudItemSticker(2208, 4814, IRON, "Sticker Gravity", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_KINGDEAD = new CloudItemSticker(2209, 4815, IRON, "Sticker KingDead", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_PARADISE9 = new CloudItemSticker(2210, 4816, IRON, "Sticker Paradise 9", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_SEALTEAM6 = new CloudItemSticker(2211, 4817, IRON, "Sticker SEAL Team 6", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_SIKA = new CloudItemSticker(2212, 4818, IRON, "Sticker SIKA", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_TBK = new CloudItemSticker(2213, 4819, IRON, "Sticker TBK", false);
	public static final CloudItemSticker STICKER_CCL_2020_S1_CLAN_CCTN = new CloudItemSticker(2214, 4820, IRON, "Sticker CCTN", false);

	public static final CloudItemStickerCapsule SC_ALPHA = new CloudItemStickerCapsule(3000, 4856, COAL, "Alpha Capsule", false);
	public static final CloudItemStickerCapsule SC_CCL_2020_S1 = new CloudItemStickerCapsule(3001, 4857, COAL, "CCL '20 S1 Capsule", false);

	public static final CloudItemCape CAPE_SUPPORTER = new CloudItemCape(3100, 4956, GOLD, "Cape Supporter", false);
	public static final CloudItemCape CAPE_COUNTERCRAFT = new CloudItemCape(3101, 4957, GOLD, "Cape CounterCraft", false);
	public static final CloudItemCape CAPE_DAWAE = new CloudItemCape(3102, 4958, GOLD, "Cape DaWae", false);
	public static final CloudItemCape CAPE_BOEHMOD = new CloudItemCape(3103, 4959, GOLD, "Cape BoehMod", false);
	public static final CloudItemCape CAPE_POGCHAMP = new CloudItemCape(3104, 4960, GOLD, "Cape PogChamp", false);
	public static final CloudItemCape CAPE_PEPEDANK = new CloudItemCape(3105, 4961, GOLD, "Cape PepeDank", false);
	public static final CloudItemCape CAPE_MCGA = new CloudItemCape(3106, 4962, GOLD, "Cape MCGA", false);
	public static final CloudItemCape CAPE_PUMPKIN = new CloudItemCape(3107, 4963, GOLD, "Cape Pumpkin", false);

	public static final CloudItemCallingCard CARD_DEFAULT = new CloudItemCallingCard(4000, 5256, DEFAULT, "Card Default", true);
	public static final CloudItemCallingCard CARD_NATION_GB = new CloudItemCallingCard(4001, 5256, DEFAULT, "Card GB", true);
	public static final CloudItemCallingCard CARD_NATION_USA = new CloudItemCallingCard(4002, 5256, DEFAULT, "Card USA", true);
	public static final CloudItemCallingCard CARD_NATION_RUS = new CloudItemCallingCard(4003, 5256, DEFAULT, "Card RUS", true);
	public static final CloudItemCallingCard CARD_NATION_CAN = new CloudItemCallingCard(4004, 5256, DEFAULT, "Card CAN", true);
	public static final CloudItemCallingCard CARD_NATION_BR = new CloudItemCallingCard(4005, 5256, DEFAULT, "Card BR", true);
	public static final CloudItemCallingCard CARD_NATION_AU = new CloudItemCallingCard(4006, 5256, DEFAULT, "Card AU", true);
	public static final CloudItemCallingCard CARD_NATION_NZ = new CloudItemCallingCard(4007, 5256, DEFAULT, "Card NZ", true);
	public static final CloudItemCallingCard CARD_NATION_CN = new CloudItemCallingCard(4008, 5256, DEFAULT, "Card CN", true);
	public static final CloudItemCallingCard CARD_NATION_HK = new CloudItemCallingCard(4009, 5256, DEFAULT, "Card HK", true);
	public static final CloudItemCallingCard CARD_NATION_SCOT = new CloudItemCallingCard(4010, 5256, DEFAULT, "Card SCOT", true);
	public static final CloudItemCallingCard CARD_NATION_ENG = new CloudItemCallingCard(4011, 5256, DEFAULT, "Card ENG", true);
	public static final CloudItemCallingCard CARD_NATION_IE = new CloudItemCallingCard(4012, 5256, DEFAULT, "Card IE", true);
	public static final CloudItemCallingCard CARD_NATION_UA = new CloudItemCallingCard(4013, 5256, DEFAULT, "Card UA", true);
	public static final CloudItemCallingCard CARD_NATION_FR = new CloudItemCallingCard(4014, 5256, DEFAULT, "Card FR", true);
	public static final CloudItemCallingCard CARD_NATION_GER = new CloudItemCallingCard(4015, 5256, DEFAULT, "Card GER", true);
	public static final CloudItemCallingCard CARD_NATION_JP = new CloudItemCallingCard(4016, 5256, DEFAULT, "Card JP", true);
	public static final CloudItemCallingCard CARD_NATION_ES = new CloudItemCallingCard(4017, 5256, DEFAULT, "Card ES", true);
	public static final CloudItemCallingCard CARD_CUSTOM_SLIME_GHOST = new CloudItemCallingCard(4018, 5256, LAPIS, "Card Slime Ghost", false);
	public static final CloudItemCallingCard CARD_CUSTOM_FLOWER_WARFARE = new CloudItemCallingCard(4019, 5256, LAPIS, "Card Flower Warfare", false);
	public static final CloudItemCallingCard CARD_CUSTOM_FLOWER_POWER = new CloudItemCallingCard(4020, 5256, LAPIS, "Card Flower Power", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_BOOM = new CloudItemCallingCard(4021, 5256, LAPIS, "Card Cartoon Boom", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_HEY = new CloudItemCallingCard(4022, 5256, LAPIS, "Card Cartoon Hey", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_OH = new CloudItemCallingCard(4023, 5256, LAPIS, "Card Cartoon Oh", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_OOPS = new CloudItemCallingCard(4024, 5256, LAPIS, "Card Cartoon Oops", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_SMILE = new CloudItemCallingCard(4025, 5256, LAPIS, "Card Cartoon Smile", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_WOW = new CloudItemCallingCard(4026, 5256, LAPIS, "Card Cartoon Wow", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CARTOON_WTF = new CloudItemCallingCard(4027, 5256, LAPIS, "Card Cartoon WTF", false);
	public static final CloudItemCallingCard CARD_CUSTOM_CHILI_PEPPER = new CloudItemCallingCard(4028, 5256, LAPIS, "Card Chili Pepper", false);
	public static final CloudItemCallingCard CARD_CUSTOM_WITCH_KITTY = new CloudItemCallingCard(4029, 5256, LAPIS, "Card Witch Kitty", false);
	public static final CloudItemCallingCard CARD_CUSTOM_PURPLE_NINJA = new CloudItemCallingCard(4030, 5256, LAPIS, "Card Purple Ninja", false);
	public static final CloudItemCallingCard CARD_CUSTOM_GAME_OVER = new CloudItemCallingCard(4031, 5256, LAPIS, "Card Game Over", false);
	public static final CloudItemCallingCard CARD_CUSTOM_SKULL_PATRIOT = new CloudItemCallingCard(4032, 5256, LAPIS, "Card Skull Patriot", false);
	public static final CloudItemCallingCard CARD_CUSTOM_GAMER_FLARE = new CloudItemCallingCard(4033, 5256, LAPIS, "Card Gamer Flare", false);
	public static final CloudItemCallingCard CARD_CUSTOM_GREEN_VIP = new CloudItemCallingCard(4034, 5256, LAPIS, "Card Green VIP", false);
	public static final CloudItemCallingCard CARD_CUSTOM_RANGER = new CloudItemCallingCard(4035, 5256, LAPIS, "Card Ranger", false);
	public static final CloudItemCallingCard CARD_CUSTOM_INSERTION = new CloudItemCallingCard(4036, 5256, LAPIS, "Card Insertion", false);
	public static final CloudItemCallingCard CARD_CUSTOM_SOLDIER_SILHOUETTE = new CloudItemCallingCard(4037, 5256, LAPIS, "Card Soldier Silhouette", false);
	public static final CloudItemCallingCard CARD_NATION_BE = new CloudItemCallingCard(4038, 5256, DEFAULT, "Card BE", true);

	public static final CloudItemKey CASE_KEY_ALPHA = new CloudItemKey(4500, 5756, COAL, "Key Alpha", false);
	public static final CloudItemKey CASE_KEY_ALPHA2 = new CloudItemKey(4501, 5757, COAL, "Key Alpha 2", false);
	public static final CloudItemKey CASE_KEY_ALPHA3 = new CloudItemKey(4502, 5758, COAL, "Key Alpha 3", false);
	public static final CloudItemKey CASE_KEY_HAT = new CloudItemKey(4503, 5759, COAL, "Key Hats 1", false);
	public static final CloudItemKey CASE_KEY_HALLOWEEN = new CloudItemKey(4504, 5760, COAL, "Key Halloween 1", false);
	public static final CloudItemKey CASE_KEY_EXO = new CloudItemKey(4505, 5761, COAL, "Key Exo", false);
	public static final CloudItemKey CASE_KEY_PREALPHA = new CloudItemKey(4506, 5762, COAL, "Key Prealpha", false);
	public static final CloudItemKey CASE_KEY_PRESTIGE = new CloudItemKey(4507, 5763, COAL, "Key Prestige", false);

	public static final List<CloudItem> POOL_KNIFE_REGULAR = List.of(
		KNIFE_TAC_MARBLE, KNIFE_TAC_TIGER, KNIFE_TAC_STEEL, KNIFE_TAC_DOPPLAR, KNIFE_TAC_LORE, KNIFE_TAC_GAMMA,
		KNIFE_BAY, KNIFE_BAY_MARBLE, KNIFE_BAY_TIGER, KNIFE_BAY_STEEL, KNIFE_BAY_DOPPLAR, KNIFE_BAY_LORE, KNIFE_BAY_GAMMA,
		KNIFE_KAR, KNIFE_KAR_MARBLE, KNIFE_KAR_TIGER, KNIFE_KAR_STEEL, KNIFE_KAR_DOPPLAR, KNIFE_KAR_LORE, KNIFE_KAR_GAMMA,
		KNIFE_BFL
	);
	public static final List<CloudItem> POOL_KNIFE_TAC = List.of(
		KNIFE_TAC_MARBLE, KNIFE_TAC_TIGER, KNIFE_TAC_STEEL, KNIFE_TAC_DOPPLAR, KNIFE_TAC_LORE, KNIFE_TAC_GAMMA
	);
	public static final List<CloudItem> POOL_KNIFE_BAY = List.of(
		KNIFE_BAY, KNIFE_BAY_MARBLE, KNIFE_BAY_TIGER, KNIFE_BAY_STEEL, KNIFE_BAY_DOPPLAR, KNIFE_BAY_LORE, KNIFE_BAY_GAMMA
	);
	public static final List<CloudItem> POOL_KNIFE_KAR = List.of(
		KNIFE_KAR, KNIFE_KAR_MARBLE, KNIFE_KAR_TIGER, KNIFE_KAR_STEEL, KNIFE_KAR_DOPPLAR, KNIFE_KAR_LORE, KNIFE_KAR_GAMMA
	);
	public static final List<CloudItem> POOL_KNIFE_BFL = List.of(
		KNIFE_BFL
	);
	public static final List<CloudItem> POOL_KNIFE_PRESTIGE = List.of(
		KNIFE_TAC_PRESTIGE, KNIFE_BAY_PRESTIGE, KNIFE_KAR_PRESTIGE, KNIFE_BFL_PRESTIGE
	);

	public static final CloudItemCase CASE_ALPHA1 = new CloudItemCase(2000, 4356, COAL, "Alpha 1 Case", false, CASE_KEY_ALPHA, POOL_KNIFE_TAC, true)
		.modifyContents(collection -> collection
			.add(M4A4_WAVES, 120)
			.add(P250_IRRADIATED, 120)
			.add(P90_RUST, 120)
			.add(VECTOR_CIRCUIT, 120)
			.add(DRAGUNOV_SKY, 120)
			.add(DEAGLE_NIGHTRAZER, 40)
			.add(SCAR20_CYREX, 40)
			.add(MAG7_ROYALFIRE, 40)
			.add(M249_NEON, 40)
			.add(M4A4_EVILDAIMYO, 10)
			.add(AK47_PROPAGANDA, 10)
			.add(SAWEDOFF_WASTELANDPRINCESS, 10)
			.add(AK47_ASMO, 4)
			.add(R8_FADE, 4)
			.add(AWP_DRAGON, 2, false)
			.add(KNIFE_TAC_DISPLAY, 2, false)
		);
	public static final CloudItemCase CASE_ALPHA2 = new CloudItemCase(2001, 4357, COAL, "Alpha 2 Case", false, CASE_KEY_ALPHA2, POOL_KNIFE_BAY, true)
		.modifyContents(collection -> collection
			.add(M4A4_CAKEY, 150)
			.add(TEC9_QUAKORONIC, 150)
			.add(MAC10_RACE, 150)
			.add(NOVA_OXIDEBLAZE, 150)
			.add(FAMAS_AKARI, 54)
			.add(AWP_MANOWAR, 54)
			.add(SAWEDOFF_KRAKEN, 54)
			.add(AK47_NEONREVOLUTION, 15)
			.add(P90_KRAKEN, 15)
			.add(P250_SEEYALATER, 4)
			.add(SCAR20_UNICORN, 4)
			.add(KNIFE_TAC_DISPLAY, 3, false)
		);
	public static final CloudItemCase CASE_ALPHA3 = new CloudItemCase(2002, 4358, COAL, "Alpha 3 Case", false, CASE_KEY_ALPHA3, POOL_KNIFE_KAR, true)
		.modifyContents(collection -> collection
			.add(M4A4_NERFED, 150)
			.add(G18_RACE, 150)
			.add(P250_CITY, 150)
			.add(VECTOR_BLOODYEQUATION, 150)
			.add(M1911_DRAGON, 54)
			.add(R8_GOLD, 54)
			.add(UMP45_PRIMAL, 54)
			.add(FNFAL_VAPORWAVE, 10)
			.add(DEAGLE_DEADHORIZON, 10)
			.add(CZ75_PHANTOM, 10)
			.add(FAMAS_WARHAWK, 4)
			.add(AWP_ASMO, 4)
			.add(M4A4_HOWL, 2, false)
			.add(KNIFE_TAC_DISPLAY, 2, false)
		);
	public static final CloudItemCase CASE_HAT = new CloudItemCase(2003, 4359, COAL, "Hat Case", false, CASE_KEY_HAT, null, true)
		.modifyContents(collection -> collection
			.add(HAT_HARDRED, 200)
			.add(HAT_HARDORANGE, 200)
			.add(HAT_HARDYELLOW, 200)
			.add(HAT_FIREMAN, 130)
			.add(HAT_FIREMANCHIEF, 130)
			.add(HAT_TOPHAT, 80)
			.add(HAT_TV, 80)
			.add(HAT_VIKING, 30)
			.add(HAT_CATHAT, 10)
		);
	public static final CloudItemCase CASE_HALLOWEEN = new CloudItemCase(2004, 4360, COAL, "Halloween Case", false, CASE_KEY_HALLOWEEN, POOL_KNIFE_KAR, true)
		.modifyContents(collection -> collection
			.add(M1911_TUXEDO, 150)
			.add(G18_DEEP_BLUE, 150)
			.add(M249_GHOST, 150)
			.add(UMP45_EDIFICE, 150)
			.add(FAMAS_SPOOKY, 54)
			.add(DEAGLE_GRIN, 54)
			.add(MAC10_PUMPKIN, 54)
			.add(M4A4_PUMPKIN, 10)
			.add(AWP_BONES, 10)
			.add(MAG7_VAMPIRE, 10)
			.add(AK47_HAUNTED, 4)
			.add(G18_HIGHROLLER, 4)
			.add(KNIFE_TAC_DISPLAY, 3, false)
		);
	public static final CloudItemCase CASE_EXO = new CloudItemCase(2005, 4361, COAL, "Exo Case", false, CASE_KEY_EXO, POOL_KNIFE_BFL, true)
		.modifyContents(collection -> collection
			.add(AK47_BLOODSPORT, 150)
			.add(TEC9_ECO, 150)
			.add(AWP_REDLINE, 150)
			.add(SAWEDOFF_MIDNIGHT, 150)
			.add(M4A4_PATRIOT, 54)
			.add(AK47_FOX, 54)
			.add(DRAGUNOV_STEAMPUNK, 54)
			.add(FNFAL_ASMO, 10)
			.add(P250_BEAST, 10)
			.add(AWP_BOOM, 10)
			.add(TEC9_AQUARIUM, 4)
			.add(NOVA_HYPER_BEAST, 4)
			.add(KNIFE_TAC_DISPLAY, 3, false)
		);
	public static final CloudItemCase CASE_PREALPHA = new CloudItemCase(2006, 4362, COAL, "Prealpha Case", false, CASE_KEY_PREALPHA, POOL_KNIFE_TAC, true)
		.modifyContents(collection -> collection
			.add(M1911_RAIN, 150)
			.add(DEAGLE_NAVY, 150)
			.add(P250_ARCTIC, 150)
			.add(SCAR20_ELITE, 150)
			.add(FNFAL_STAIN, 134)
			.add(P90_RUBY, 134)
			.add(DRAGUNOV_JUNGLE, 134)
			.add(M4A4_CYREX, 54)
			.add(FAMAS_PULSE, 54)
			.add(MAC10_VIOLET, 54)
			.add(G18_FADE, 15)
			.add(VECTOR_ARCTIC, 15)
			.add(AK47_VULCAN, 4)
			.add(AWP_LIGHTNING, 4)
			.add(KNIFE_TAC_DISPLAY, 3, false)
		);
	public static final CloudItemCase CASE_PRESTIGE = new CloudItemCase(2007, 4363, OBSIDIAN, "Prestige Case", false, CASE_KEY_PRESTIGE, POOL_KNIFE_PRESTIGE, false)
		.modifyContents(collection -> collection
			.add(M4A4_PATRIOT, 30)
			.add(M4A4_CYREX, 30)
			.add(AK47_FOX, 30)
			.add(FAMAS_AKARI, 30)
			.add(FAMAS_SPOOKY, 30)
			.add(FAMAS_PULSE, 30)
			.add(M1911_DRAGON, 30)
			.add(DEAGLE_NIGHTRAZER, 30)
			.add(DEAGLE_GRIN, 30)
			.add(MAC10_PUMPKIN, 30)
			.add(MAC10_VIOLET, 30)
			.add(AWP_MANOWAR, 30)
			.add(DRAGUNOV_STEAMPUNK, 30)
			.add(SCAR20_CYREX, 30)
			.add(SAWEDOFF_KRAKEN, 30)
			.add(MAG7_ROYALFIRE, 30)
			.add(M249_NEON, 30)
			.add(R8_GOLD, 30)
			.add(UMP45_PRIMAL, 30)
			.add(M4A4_EVILDAIMYO, 25)
			.add(M4A4_PUMPKIN, 25)
			.add(AK47_PROPAGANDA, 25)
			.add(AK47_NEONREVOLUTION, 25)
			.add(FNFAL_VAPORWAVE, 25)
			.add(FNFAL_ASMO, 25)
			.add(G18_FADE, 25)
			.add(DEAGLE_DEADHORIZON, 25)
			.add(P250_BEAST, 25)
			.add(P90_KRAKEN, 25)
			.add(VECTOR_ARCTIC, 25)
			.add(AWP_BONES, 25)
			.add(AWP_BOOM, 25)
			.add(SAWEDOFF_WASTELANDPRINCESS, 25)
			.add(MAG7_VAMPIRE, 25)
			.add(CZ75_PHANTOM, 25)
			.add(AK47_ASMO, 14)
			.add(AK47_HAUNTED, 14)
			.add(AK47_VULCAN, 14)
			.add(FAMAS_WARHAWK, 14)
			.add(G18_HIGHROLLER, 14)
			.add(TEC9_AQUARIUM, 14)
			.add(P250_SEEYALATER, 14)
			.add(AWP_ASMO, 14)
			.add(AWP_LIGHTNING, 14)
			.add(SCAR20_UNICORN, 14)
			.add(NOVA_HYPER_BEAST, 14)
			.add(R8_FADE, 14)
			.add(M4A4_HOWL, 10, false)
			.add(AWP_DRAGON, 10, false)
			.add(KNIFE_TAC_DISPLAY, 10, false)
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
