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
import dev.vuis.cccr.cloud.market.MarketCategory;
import dev.vuis.cccr.cloud.market.SuffixMap;
import dev.vuis.cccr.cloud.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.vuis.cccr.cloud.item.CloudItemCollection.ALPHA;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.BETA;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.BIOMECHANIC;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.CHRONICAL;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.CLASH;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.DELTA;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.ELEMENTAL;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.EPSILON;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.GAMMA;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.MYTHIC;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.OASIS;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.PHANTASMAGORIA;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.REVOLUTION;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.SEPULCHER;
import static dev.vuis.cccr.cloud.item.CloudItemCollection.VECTORIZED;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.COAL;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.DEFAULT;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.DIAMOND;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.GOLD;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.IRON;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.LAPIS;
import static dev.vuis.cccr.cloud.item.CloudItemRarity.OBSIDIAN;
import static dev.vuis.cccr.cloud.market.MarketCategory.HEAVY;
import static dev.vuis.cccr.cloud.market.MarketCategory.PISTOL;
import static dev.vuis.cccr.cloud.market.MarketCategory.RIFLE;
import static dev.vuis.cccr.cloud.market.MarketCategory.SMG;
import static dev.vuis.cccr.cloud.market.MarketCategory.SNIPER;

@SuppressWarnings("unused")
public final class CloudItems {
	static final List<CloudItem> DEFAULTS = new ArrayList<>();
	static final Map<Integer, CloudItem> ID_MAP = new HashMap<>();
	static final Multimap<CloudItemRarity, CloudItem> RARITY_MAP = HashMultimap.create();
	static final Map<MarketCategory, Map<String, SuffixMap>> MARKET_MAP = new EnumMap<>(MarketCategory.class);

	public static final CloudItemGun M4A4 = new CloudItemGun(100, 4256, DEFAULT, "M4A4", true, RIFLE);
	public static final CloudItemGun M4A4_WAVES = new CloudItemGun(101, 4256, IRON, "M4A4 Waves", false, M4A4, ELEMENTAL);
	public static final CloudItemGun M4A4_EVILDAIMYO = new CloudItemGun(102, 4256, DIAMOND, "M4A4 EvilDaimyo", false, M4A4, GAMMA);
	public static final CloudItemGun M4A4_CAKEY = new CloudItemGun(103, 4256, LAPIS, "M4A4 Cakey", false, M4A4, BETA);
	public static final CloudItemGun M4A4_HOWL = new CloudItemGun(104, 4256, OBSIDIAN, "M4A4 Howl", false, M4A4, ELEMENTAL);
	public static final CloudItemGun M4A4_NERFED = new CloudItemGun(105, 4256, LAPIS, "M4A4 Nerfed", false, M4A4, CLASH);
	public static final CloudItemGun M4A4_PUMPKIN = new CloudItemGun(106, 4256, GOLD, "M4A4 Pumpkin", false, M4A4, PHANTASMAGORIA);
	public static final CloudItemGun M4A4_PATRIOT = new CloudItemGun(107, 4256, LAPIS, "M4A4 Patriot", false, M4A4, null);
	public static final CloudItemGun M4A4_CYREX = new CloudItemGun(108, 4256, IRON, "M4A4 Cyrex", false, M4A4, VECTORIZED);
	public static final CloudItemGun M4A4_LURKER = new CloudItemGun(109, 4256, IRON, "M4A4 Lurker", false, M4A4, REVOLUTION);
	public static final CloudItemGun M4A4_ASMO = new CloudItemGun(110, 4256, DIAMOND, "M4A4 Asmo", false, M4A4, ALPHA);
	public static final CloudItemGun M4A4_DESERT = new CloudItemGun(111, 4256, COAL, "M4A4 Desert", false, M4A4, OASIS);
	public static final CloudItemGun M4A4_DESOLATE_SPACE = new CloudItemGun(112, 4256, OBSIDIAN, "M4A4 Desolate Space", false, M4A4, MYTHIC);
	public static final CloudItemGun M4A4_SPORT = new CloudItemGun(113, 4256, GOLD, "M4A4 Sport", false, M4A4, DELTA);
	public static final CloudItemGun M4A4_VERTEX = new CloudItemGun(114, 4256, LAPIS, "M4A4 Vertex", false, M4A4, DELTA);
	public static final CloudItemGun M4A4_GOLDEN_COIL = new CloudItemGun(115, 4256, GOLD, "M4A4 Golden Coil", false, M4A4, BIOMECHANIC);
	public static final CloudItemGun M4A4_DAYBREAK = new CloudItemGun(116, 4256, IRON, "M4A4 Daybreak", false, M4A4, BIOMECHANIC);

	public static final CloudItemGun AK47 = new CloudItemGun(200, 4257, DEFAULT, "AK47", true, RIFLE);
	public static final CloudItemGun AK47_PROPAGANDA = new CloudItemGun(201, 4257, DIAMOND, "AK47 Propaganda", false, AK47, CLASH);
	public static final CloudItemGun AK47_ASMO = new CloudItemGun(202, 4257, GOLD, "AK47 Asmo", false, AK47, BETA);
	public static final CloudItemGun AK47_NEONREVOLUTION = new CloudItemGun(203, 4257, GOLD, "AK47 Neon Revolution", false, AK47, null);
	public static final CloudItemGun AK47_HAUNTED = new CloudItemGun(204, 4257, DIAMOND, "AK47 Haunted", false, AK47, SEPULCHER);
	public static final CloudItemGun AK47_BLOODSPORT = new CloudItemGun(205, 4257, GOLD, "AK47 Bloodsport", false, AK47, VECTORIZED);
	public static final CloudItemGun AK47_FOX = new CloudItemGun(206, 4257, LAPIS, "AK47 Fox", false, AK47, EPSILON);
	public static final CloudItemGun AK47_VULCAN = new CloudItemGun(207, 4257, DIAMOND, "AK47 Vulcan", false, AK47, ALPHA);
	public static final CloudItemGun AK47_FUEL = new CloudItemGun(208, 4257, IRON, "AK47 Fuel", false, AK47, BETA);
	public static final CloudItemGun AK47_SHIKARI = new CloudItemGun(209, 4257, IRON, "AK47 Shikari", false, AK47, BIOMECHANIC);
	public static final CloudItemGun AK47_HORUS = new CloudItemGun(210, 4257, OBSIDIAN, "AK47 Horus", false, AK47, OASIS);
	public static final CloudItemGun AK47_FRONTSIDE = new CloudItemGun(211, 4257, DIAMOND, "AK47 Frontside", false, AK47, CHRONICAL);
	public static final CloudItemGun AK47_IRON_BEAST = new CloudItemGun(212, 4257, GOLD, "AK47 Iron Beast", false, AK47, GAMMA);
	public static final CloudItemGun AK47_GRAFFITI = new CloudItemGun(213, 4257, LAPIS, "AK47 Graffiti", false, AK47, DELTA);
	public static final CloudItemGun AK47_STRIPE = new CloudItemGun(214, 4257, IRON, "AK47 Stripe", false, AK47, EPSILON);
	public static final CloudItemGun AK47_MACAW = new CloudItemGun(215, 4257, LAPIS, "AK47 Macaw", false, AK47, CLASH);
	public static final CloudItemGun AK47_XRAY = new CloudItemGun(216, 4257, LAPIS, "AK47 X-Ray", false, AK47, REVOLUTION);
	public static final CloudItemGun AK47_CREEP = new CloudItemGun(217, 4257, OBSIDIAN, "AK47 Creep", false, AK47, PHANTASMAGORIA);
	public static final CloudItemGun AK47_BONES = new CloudItemGun(218, 4257, LAPIS, "AK47 Bones", false, AK47, SEPULCHER);

	public static final CloudItemGun FAMAS = new CloudItemGun(300, 4258, DEFAULT, "Famas", true, RIFLE);
	public static final CloudItemGun FAMAS_AKARI = new CloudItemGun(301, 4258, IRON, "Famas Akari", false, FAMAS, BIOMECHANIC);
	public static final CloudItemGun FAMAS_WARHAWK = new CloudItemGun(302, 4258, DIAMOND, "Famas Warhawk", false, FAMAS, REVOLUTION);
	public static final CloudItemGun FAMAS_SPOOKY = new CloudItemGun(303, 4258, IRON, "Famas Spooky", false, FAMAS, PHANTASMAGORIA);
	public static final CloudItemGun FAMAS_PULSE = new CloudItemGun(304, 4258, COAL, "Famas Pulse", false, FAMAS, BETA);
	public static final CloudItemGun FAMAS_NUCLEAR = new CloudItemGun(305, 4258, LAPIS, "Famas Nuclear", false, FAMAS, ELEMENTAL);
	public static final CloudItemGun FAMAS_SANDSTORM = new CloudItemGun(306, 4258, COAL, "Famas Sandstorm", false, FAMAS, OASIS);
	public static final CloudItemGun FAMAS_VULCAN = new CloudItemGun(307, 4258, GOLD, "Famas Vulcan", false, FAMAS, CHRONICAL);
	public static final CloudItemGun FAMAS_RYBUS = new CloudItemGun(308, 4258, IRON, "Famas Rybus", false, FAMAS, MYTHIC);
	public static final CloudItemGun FAMAS_COBALT = new CloudItemGun(309, 4258, COAL, "Famas Cobalt", false, FAMAS, CLASH);

	public static final CloudItemGun FNFAL = new CloudItemGun(400, 4259, DEFAULT, "FN FAL", true, RIFLE);
	public static final CloudItemGun FNFAL_VAPORWAVE = new CloudItemGun(401, 4259, GOLD, "FN FAL Vaporwave", false, FNFAL, OASIS);
	public static final CloudItemGun FNFAL_ASMO = new CloudItemGun(402, 4259, GOLD, "FN FAL Asmo", false, FNFAL, MYTHIC);
	public static final CloudItemGun FNFAL_STAIN = new CloudItemGun(403, 4259, COAL, "FN FAL Stain", false, FNFAL, CLASH);
	public static final CloudItemGun FNFAL_REBEL = new CloudItemGun(404, 4259, LAPIS, "FN FAL Rebel", false, FNFAL, BETA);
	public static final CloudItemGun FNFAL_VENOM = new CloudItemGun(405, 4259, LAPIS, "FN FAL Venom", false, FNFAL, ALPHA);
	public static final CloudItemGun FNFAL_RACE = new CloudItemGun(406, 4259, IRON, "FN FAL Race", false, FNFAL, GAMMA);
	public static final CloudItemGun FNFAL_SHOCKER = new CloudItemGun(407, 4259, IRON, "FN FAL Shocker", false, FNFAL, CHRONICAL);

	public static final CloudItemGun GALIL = new CloudItemGun(450, 4260, DEFAULT, "Galil AR", true, RIFLE);

	public static final CloudItemGun M1911 = new CloudItemGun(500, 4266, DEFAULT, "M1911", true, PISTOL);
	public static final CloudItemGun M1911_DRAGON = new CloudItemGun(501, 4266, LAPIS, "M1911 Dragon", false, M1911, ALPHA);
	public static final CloudItemGun M1911_TUXEDO = new CloudItemGun(502, 4266, COAL, "M1911 Tuxedo", false, M1911, PHANTASMAGORIA);
	public static final CloudItemGun M1911_RAIN = new CloudItemGun(503, 4266, COAL, "M1911 Rain", false, M1911, EPSILON);
	public static final CloudItemGun M1911_OLDWEST = new CloudItemGun(504, 4266, COAL, "M1911 OldWest", false, M1911, REVOLUTION);
	public static final CloudItemGun M1911_SHOCK = new CloudItemGun(505, 4266, IRON, "M1911 Shock", false, M1911, CHRONICAL);
	public static final CloudItemGun M1911_VENOM = new CloudItemGun(506, 4266, LAPIS, "M1911 Venom", false, M1911, BETA);
	public static final CloudItemGun M1911_TRIARK = new CloudItemGun(507, 4266, GOLD, "M1911 Triark", false, M1911, OASIS);
	public static final CloudItemGun M1911_BLAZE = new CloudItemGun(508, 4266, LAPIS, "M1911 Blaze", false, M1911, MYTHIC);
	public static final CloudItemGun M1911_GOLD = new CloudItemGun(509, 4266, LAPIS, "M1911 Gold", false, M1911, ELEMENTAL);
	public static final CloudItemGun M1911_INDIRECT = new CloudItemGun(510, 4266, IRON, "M1911 Indirect", false, M1911, REVOLUTION);
	public static final CloudItemGun M1911_MUMMY = new CloudItemGun(511, 4266, COAL, "M1911 Mummy", false, M1911, SEPULCHER);

	public static final CloudItemGun G18 = new CloudItemGun(600, 4267, DEFAULT, "G18", true, PISTOL);
	public static final CloudItemGun G18_RACE = new CloudItemGun(601, 4267, IRON, "G18 Race", false, G18, EPSILON);
	public static final CloudItemGun G18_HIGHROLLER = new CloudItemGun(602, 4267, OBSIDIAN, "G18 Highroller", false, G18, BETA);
	public static final CloudItemGun G18_DEEP_BLUE = new CloudItemGun(603, 4267, COAL, "G18 Deep Blue", false, G18, ELEMENTAL);
	public static final CloudItemGun G18_FADE = new CloudItemGun(604, 4267, GOLD, "G18 Fade", false, G18, BETA);
	public static final CloudItemGun G18_URBAN = new CloudItemGun(605, 4267, COAL, "G18 Urban", false, G18, EPSILON);
	public static final CloudItemGun G18_CYREX = new CloudItemGun(606, 4267, IRON, "G18 Cyrex", false, G18, ALPHA);
	public static final CloudItemGun G18_HORNET = new CloudItemGun(607, 4267, IRON, "G18 Hornet", false, G18, OASIS);
	public static final CloudItemGun G18_VULCAN = new CloudItemGun(608, 4267, LAPIS, "G18 Vulcan", false, G18, VECTORIZED);
	public static final CloudItemGun G18_CANDY_APPLE = new CloudItemGun(609, 4267, COAL, "G18 Candy Apple", false, G18, VECTORIZED);
	public static final CloudItemGun G18_MOONRISE = new CloudItemGun(610, 4267, LAPIS, "G18 Moonrise", false, G18, BIOMECHANIC);
	public static final CloudItemGun G18_CIRCUIT = new CloudItemGun(611, 4267, COAL, "G18 Circuit", false, G18, BIOMECHANIC);
	public static final CloudItemGun G18_GHOUL = new CloudItemGun(612, 4267, COAL, "G18 Ghoul", false, G18, PHANTASMAGORIA);
	public static final CloudItemGun G18_GHOST = new CloudItemGun(613, 4267, COAL, "G18 Ghost", false, G18, SEPULCHER);

	public static final CloudItemGun TEC9 = new CloudItemGun(650, 4270, DEFAULT, "TEC9", true, PISTOL);
	public static final CloudItemGun TEC9_QUAKORONIC = new CloudItemGun(651, 4270, COAL, "TEC9 Quakoronic", false, TEC9, CHRONICAL);
	public static final CloudItemGun TEC9_ECO = new CloudItemGun(652, 4270, LAPIS, "TEC9 Eco", false, TEC9, BIOMECHANIC);
	public static final CloudItemGun TEC9_AQUARIUM = new CloudItemGun(653, 4270, LAPIS, "TEC9 Aquarium", false, TEC9, REVOLUTION);
	public static final CloudItemGun TEC9_TORQUE = new CloudItemGun(654, 4270, LAPIS, "TEC9 Torque", false, TEC9, MYTHIC);
	public static final CloudItemGun TEC9_HORNET = new CloudItemGun(655, 4270, IRON, "TEC9 Hornet", false, TEC9, DELTA);
	public static final CloudItemGun TEC9_FUEL = new CloudItemGun(656, 4270, IRON, "TEC9 Fuel", false, TEC9, CLASH);
	public static final CloudItemGun TEC9_CHERRY = new CloudItemGun(657, 4270, GOLD, "TEC9 Cherry", false, TEC9, ELEMENTAL);

	public static final CloudItemGun DEAGLE = new CloudItemGun(700, 4268, DEFAULT, "Deagle", true, PISTOL);
	public static final CloudItemGun DEAGLE_NIGHTRAZER = new CloudItemGun(701, 4268, IRON, "Deagle NightRazer", false, DEAGLE, CHRONICAL);
	public static final CloudItemGun DEAGLE_DEADHORIZON = new CloudItemGun(702, 4268, LAPIS, "Deagle Dead Horizon", false, DEAGLE, CHRONICAL);
	public static final CloudItemGun DEAGLE_GRIN = new CloudItemGun(703, 4268, LAPIS, "Deagle Grin", false, DEAGLE, SEPULCHER);
	public static final CloudItemGun DEAGLE_NAVY = new CloudItemGun(704, 4268, COAL, "Deagle Navy", false, DEAGLE, VECTORIZED);
	public static final CloudItemGun DEAGLE_WATERMELON = new CloudItemGun(705, 4268, GOLD, "Deagle Watermelon", false, DEAGLE, ALPHA);
	public static final CloudItemGun DEAGLE_MYSTIC = new CloudItemGun(706, 4268, COAL, "Deagle Mystic", false, DEAGLE, MYTHIC);
	public static final CloudItemGun DEAGLE_BLOODSPORT = new CloudItemGun(707, 4268, GOLD, "Deagle Bloodsport", false, DEAGLE, GAMMA);
	public static final CloudItemGun DEAGLE_FLATLINE = new CloudItemGun(708, 4268, LAPIS, "Deagle Flatline", false, DEAGLE, GAMMA);
	public static final CloudItemGun DEAGLE_CIRCUIT = new CloudItemGun(709, 4268, COAL, "Deagle Circuit", false, DEAGLE, DELTA);
	public static final CloudItemGun DEAGLE_FADE = new CloudItemGun(710, 4268, GOLD, "Deagle Fade", false, DEAGLE, EPSILON);
	public static final CloudItemGun DEAGLE_KUMICHO_DRAGON = new CloudItemGun(711, 4268, LAPIS, "Deagle Kumicho Dragon", false, DEAGLE, EPSILON);
	public static final CloudItemGun DEAGLE_COBALT = new CloudItemGun(712, 4268, IRON, "Deagle Cobalt", false, DEAGLE, CLASH);
	public static final CloudItemGun DEAGLE_GOLD = new CloudItemGun(713, 4268, LAPIS, "Deagle Gold", false, DEAGLE, ELEMENTAL);
	public static final CloudItemGun DEAGLE_DEVI = new CloudItemGun(714, 4268, COAL, "Deagle Devi", false, DEAGLE, REVOLUTION);
	public static final CloudItemGun DEAGLE_MECHAINDUSTRIES = new CloudItemGun(715, 4268, LAPIS, "Deagle Mechaindustries", false, DEAGLE, BIOMECHANIC);

	public static final CloudItemGun P250 = new CloudItemGun(750, 4269, DEFAULT, "P250", true, PISTOL);
	public static final CloudItemGun P250_IRRADIATED = new CloudItemGun(751, 4269, IRON, "P250 Irradiated", false, P250, DELTA);
	public static final CloudItemGun P250_SEEYALATER = new CloudItemGun(752, 4269, DIAMOND, "P250 SeeYaLater", false, P250, BIOMECHANIC);
	public static final CloudItemGun P250_CITY = new CloudItemGun(753, 4269, LAPIS, "P250 City", false, P250, REVOLUTION);
	public static final CloudItemGun P250_BEAST = new CloudItemGun(754, 4269, GOLD, "P250 Beast", false, P250, CHRONICAL);
	public static final CloudItemGun P250_ARCTIC = new CloudItemGun(755, 4269, COAL, "P250 Arctic", false, P250, BIOMECHANIC);
	public static final CloudItemGun P250_DUNE = new CloudItemGun(756, 4269, COAL, "P250 Dune", false, P250, OASIS);
	public static final CloudItemGun P250_NIPPON = new CloudItemGun(757, 4269, LAPIS, "P250 Nippon", false, P250, EPSILON);
	public static final CloudItemGun P250_HAUNTED = new CloudItemGun(758, 4269, LAPIS, "P250 Haunted", false, P250, SEPULCHER);

	public static final CloudItemGun P90 = new CloudItemGun(800, 4276, DEFAULT, "P90", true, SMG);
	public static final CloudItemGun P90_RUST = new CloudItemGun(801, 4276, COAL, "P90 Rust", false, P90, ELEMENTAL);
	public static final CloudItemGun P90_KRAKEN = new CloudItemGun(802, 4276, LAPIS, "P90 Kraken", false, P90, MYTHIC);
	public static final CloudItemGun P90_RUBY = new CloudItemGun(803, 4276, COAL, "P90 Ruby", false, P90, CHRONICAL);
	public static final CloudItemGun P90_ARROW = new CloudItemGun(804, 4276, IRON, "P90 Arrow", false, P90, ALPHA);
	public static final CloudItemGun P90_MEDICINE = new CloudItemGun(805, 4276, GOLD, "P90 Medicine", false, P90, DELTA);
	public static final CloudItemGun P90_MODULE = new CloudItemGun(806, 4276, IRON, "P90 Module", false, P90, REVOLUTION);
	public static final CloudItemGun P90_GRIM = new CloudItemGun(807, 4276, DIAMOND, "P90 Grim", false, P90, PHANTASMAGORIA);

	public static final CloudItemGun MAC10 = new CloudItemGun(900, 4277, DEFAULT, "MAC10", true, SMG);
	public static final CloudItemGun MAC10_RACE = new CloudItemGun(901, 4277, IRON, "MAC10 Race", false, MAC10, CLASH);
	public static final CloudItemGun MAC10_PUMPKIN = new CloudItemGun(902, 4277, IRON, "MAC10 Pumpkin", false, MAC10, PHANTASMAGORIA);
	public static final CloudItemGun MAC10_VIOLET = new CloudItemGun(903, 4277, COAL, "MAC10 Violet", false, MAC10, REVOLUTION);
	public static final CloudItemGun MAC10_CHICKEN = new CloudItemGun(904, 4277, DIAMOND, "MAC10 Chicken", false, MAC10, OASIS);
	public static final CloudItemGun MAC10_CANDY_APPLE = new CloudItemGun(905, 4277, COAL, "MAC10 Candy Apple", false, MAC10, GAMMA);
	public static final CloudItemGun MAC10_CIRCUIT = new CloudItemGun(906, 4277, COAL, "MAC10 Circuit", false, MAC10, DELTA);
	public static final CloudItemGun MAC10_NEON = new CloudItemGun(907, 4277, DIAMOND, "MAC10 Neon", false, MAC10, CLASH);
	public static final CloudItemGun MAC10_FADE = new CloudItemGun(908, 4277, GOLD, "MAC10 Fade", false, MAC10, ELEMENTAL);
	public static final CloudItemGun MAC10_ASMO = new CloudItemGun(909, 4277, GOLD, "MAC10 Asmo", false, MAC10, VECTORIZED);
	public static final CloudItemGun MAC10_SPIDER = new CloudItemGun(910, 4277, GOLD, "MAC10 Spider", false, MAC10, PHANTASMAGORIA);
	public static final CloudItemGun MAC10_SWARM = new CloudItemGun(911, 4277, IRON, "MAC10 Swarm", false, MAC10, SEPULCHER);

	public static final CloudItemGun VECTOR = new CloudItemGun(1000, 4278, DEFAULT, "Vector", true, SMG);
	public static final CloudItemGun VECTOR_CIRCUIT = new CloudItemGun(1001, 4278, COAL, "Vector Circuit", false, VECTOR, BIOMECHANIC);
	public static final CloudItemGun VECTOR_BLOODYEQUATION = new CloudItemGun(1002, 4278, IRON, "Vector Bloody Equation", false, VECTOR, GAMMA);
	public static final CloudItemGun VECTOR_ARCTIC = new CloudItemGun(1003, 4278, IRON, "Vector Arctic", false, VECTOR, VECTORIZED);
	public static final CloudItemGun VECTOR_SUNNY = new CloudItemGun(1004, 4278, COAL, "Vector Sunny", false, VECTOR, ELEMENTAL);
	public static final CloudItemGun VECTOR_SLIME = new CloudItemGun(1005, 4278, IRON, "Vector Slime", false, VECTOR, MYTHIC);
	public static final CloudItemGun VECTOR_EXTINCT = new CloudItemGun(1006, 4278, DIAMOND, "Vector Extinct", false, VECTOR, VECTORIZED);
	public static final CloudItemGun VECTOR_ORTHUS = new CloudItemGun(1007, 4278, LAPIS, "Vector Orthus", false, VECTOR, PHANTASMAGORIA);

	public static final CloudItemGun AWP = new CloudItemGun(1100, 4286, DEFAULT, "AWP", true, SNIPER);
	public static final CloudItemGun AWP_DRAGON = new CloudItemGun(1101, 4286, DIAMOND, "AWP Dragon", false, AWP, BETA);
	public static final CloudItemGun AWP_MANOWAR = new CloudItemGun(1102, 4286, LAPIS, "AWP Man O War", false, AWP, OASIS);
	public static final CloudItemGun AWP_ASMO = new CloudItemGun(1103, 4286, GOLD, "AWP Asmo", false, AWP, CLASH);
	public static final CloudItemGun AWP_BONES = new CloudItemGun(1104, 4286, LAPIS, "AWP Bones", false, AWP, PHANTASMAGORIA);
	public static final CloudItemGun AWP_REDLINE = new CloudItemGun(1105, 4286, IRON, "AWP Redline", false, AWP, BETA);
	public static final CloudItemGun AWP_BOOM = new CloudItemGun(1106, 4286, LAPIS, "AWP Boom", false, AWP, CHRONICAL);
	public static final CloudItemGun AWP_LIGHTNING = new CloudItemGun(1107, 4286, DIAMOND, "AWP Lightning", false, AWP, ELEMENTAL);
	public static final CloudItemGun AWP_BEAST = new CloudItemGun(1108, 4286, GOLD, "AWP Beast", false, AWP, MYTHIC);
	public static final CloudItemGun AWP_MECHA_DRAGON = new CloudItemGun(1109, 4286, OBSIDIAN, "AWP Mecha Dragon", false, AWP, ALPHA);
	public static final CloudItemGun AWP_ELECTRIC_ARCHIVE = new CloudItemGun(1110, 4286, LAPIS, "AWP Electric Archive", false, AWP, GAMMA);
	public static final CloudItemGun AWP_OCEANDEPTH = new CloudItemGun(1111, 4286, DIAMOND, "AWP Ocean-Depth", false, AWP, DELTA);
	public static final CloudItemGun AWP_AKARI = new CloudItemGun(1112, 4286, LAPIS, "AWP Akari", false, AWP, DELTA);
	public static final CloudItemGun AWP_CROWD = new CloudItemGun(1113, 4286, GOLD, "AWP Crowd", false, AWP, EPSILON);
	public static final CloudItemGun AWP_FOREST = new CloudItemGun(1114, 4286, COAL, "AWP Forest", false, AWP, VECTORIZED);
	public static final CloudItemGun AWP_STEAMPUNK = new CloudItemGun(1115, 4286, GOLD, "AWP Steampunk", false, AWP, BIOMECHANIC);
	public static final CloudItemGun AWP_PUMPKIN = new CloudItemGun(1116, 4286, GOLD, "AWP Pumpkin", false, AWP, SEPULCHER);

	public static final CloudItemGun DRAGUNOV = new CloudItemGun(1200, 4287, DEFAULT, "Dragunov", true, SNIPER);
	public static final CloudItemGun DRAGUNOV_SKY = new CloudItemGun(1201, 4287, IRON, "Dragunov Sky", false, DRAGUNOV, OASIS);
	public static final CloudItemGun DRAGUNOV_STEAMPUNK = new CloudItemGun(1202, 4287, IRON, "Dragunov Steampunk", false, DRAGUNOV, MYTHIC);
	public static final CloudItemGun DRAGUNOV_JUNGLE = new CloudItemGun(1203, 4287, COAL, "Dragunov Jungle", false, DRAGUNOV, ALPHA);
	public static final CloudItemGun DRAGUNOV_WORMGOD = new CloudItemGun(1204, 4287, DIAMOND, "Dragunov Wormgod", false, DRAGUNOV, MYTHIC);
	public static final CloudItemGun DRAGUNOV_ELITE = new CloudItemGun(1205, 4287, COAL, "Dragunov Elite", false, DRAGUNOV, CLASH);
	public static final CloudItemGun DRAGUNOV_GHOST = new CloudItemGun(1206, 4287, COAL, "Dragunov Ghost", false, DRAGUNOV, SEPULCHER);

	public static final CloudItemGun SCAR20 = new CloudItemGun(1300, 4288, DEFAULT, "Scar20", true, SNIPER);
	public static final CloudItemGun SCAR20_CYREX = new CloudItemGun(1301, 4288, IRON, "Scar20 Cyrex", false, SCAR20, ALPHA);
	public static final CloudItemGun SCAR20_UNICORN = new CloudItemGun(1302, 4288, OBSIDIAN, "Scar20 Unicorn", false, SCAR20, CHRONICAL);
	public static final CloudItemGun SCAR20_ELITE = new CloudItemGun(1303, 4288, COAL, "Scar20 Elite", false, SCAR20, MYTHIC);
	public static final CloudItemGun SCAR20_SHOCK = new CloudItemGun(1304, 4288, IRON, "Scar20 Shock", false, SCAR20, VECTORIZED);
	public static final CloudItemGun SCAR20_SPORT = new CloudItemGun(1305, 4288, DIAMOND, "Scar20 Sport", false, SCAR20, BETA);
	public static final CloudItemGun SCAR20_POWERCORE = new CloudItemGun(1306, 4288, COAL, "Scar20 Powercore", false, SCAR20, GAMMA);
	public static final CloudItemGun SCAR20_ARCTIC = new CloudItemGun(1307, 4288, COAL, "Scar20 Arctic", false, SCAR20, CHRONICAL);

	public static final CloudItemGun SSG08 = new CloudItemGun(1400, 4289, DEFAULT, "SSG-08", true, SNIPER);

	public static final CloudItemKnife KNIFE_TAC = new CloudItemKnife(1500, 4456, DEFAULT, "Tac Knife", true);
	public static final CloudItemKnife KNIFE_TAC_DISPLAY = new CloudItemKnife(1501, 4457, OBSIDIAN, "Special Item", false);
	public static final CloudItemKnife KNIFE_TAC_MARBLE = new CloudItemKnife(1502, 4458, OBSIDIAN, "Tac Knife Marble", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_TIGER = new CloudItemKnife(1503, 4459, OBSIDIAN, "Tac Knife Tiger", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_STEEL = new CloudItemKnife(1504, 4460, OBSIDIAN, "Tac Knife Steel", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_DOPPLER = new CloudItemKnife(1505, 4461, OBSIDIAN, "Tac Knife Doppler", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_LORE = new CloudItemKnife(1506, 4462, OBSIDIAN, "Tac Knife Lore", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_GAMMA = new CloudItemKnife(1507, 4463, OBSIDIAN, "Tac Knife Gamma", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_PRESTIGE = new CloudItemKnife(1508, 4464, OBSIDIAN, "Tac Knife Prestige", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_AUTOTRONIC = new CloudItemKnife(1509, 4465, OBSIDIAN, "Tac Knife Autotronic", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_BLACKOUT = new CloudItemKnife(1540, 4496, OBSIDIAN, "Tac Knife Blackout", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_RUBY = new CloudItemKnife(1541, 4497, OBSIDIAN, "Tac Knife Ruby", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_FADE = new CloudItemKnife(1542, 4498, OBSIDIAN, "Tac Knife Fade", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_RUST = new CloudItemKnife(1543, 4499, OBSIDIAN, "Tac Knife Rust", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_ETHERFIRE = new CloudItemKnife(1544, 4500, OBSIDIAN, "Tac Knife Etherfire", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_WATERMELON = new CloudItemKnife(1545, 4501, OBSIDIAN, "Tac Knife Watermelon", false, KNIFE_TAC);
	public static final CloudItemKnife KNIFE_TAC_INVERTED = new CloudItemKnife(1546, 4502, OBSIDIAN, "Tac Knife Inverted", false, KNIFE_TAC);

	public static final CloudItemKnife KNIFE_BAY = new CloudItemKnife(1510, 4466, OBSIDIAN, "Bayonet", false);
	public static final CloudItemKnife KNIFE_BAY_MARBLE = new CloudItemKnife(1511, 4467, OBSIDIAN, "Bayonet Marble", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_TIGER = new CloudItemKnife(1512, 4468, OBSIDIAN, "Bayonet Tiger", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_STEEL = new CloudItemKnife(1513, 4469, OBSIDIAN, "Bayonet Steel", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_DOPPLER = new CloudItemKnife(1514, 4470, OBSIDIAN, "Bayonet Doppler", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_LORE = new CloudItemKnife(1515, 4471, OBSIDIAN, "Bayonet Lore", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_GAMMA = new CloudItemKnife(1516, 4472, OBSIDIAN, "Bayonet Gamma", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_DRAGONLORE = new CloudItemKnife(1517, 4473, OBSIDIAN, "Bayonet Dragonlore", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_PRESTIGE = new CloudItemKnife(1518, 4474, OBSIDIAN, "Bayonet Prestige", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_AUTOTRONIC = new CloudItemKnife(1519, 4475, OBSIDIAN, "Bayonet Autotronic", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_BLACKOUT = new CloudItemKnife(1550, 4506, OBSIDIAN, "Bayonet Blackout", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_RUBY = new CloudItemKnife(1551, 4507, OBSIDIAN, "Bayonet Ruby", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_FADE = new CloudItemKnife(1552, 4508, OBSIDIAN, "Bayonet Fade", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_RUST = new CloudItemKnife(1553, 4509, OBSIDIAN, "Bayonet Rust", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_ETHERFIRE = new CloudItemKnife(1554, 4510, OBSIDIAN, "Bayonet Etherfire", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_WATERMELON = new CloudItemKnife(1555, 4511, OBSIDIAN, "Bayonet Watermelon", false, KNIFE_BAY);
	public static final CloudItemKnife KNIFE_BAY_INVERTED = new CloudItemKnife(1556, 4512, OBSIDIAN, "Bayonet Inverted", false, KNIFE_BAY);

	public static final CloudItemKnife KNIFE_KAR = new CloudItemKnife(1520, 4476, OBSIDIAN, "Karambit", false);
	public static final CloudItemKnife KNIFE_KAR_MARBLE = new CloudItemKnife(1521, 4477, OBSIDIAN, "Karambit Marble", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_TIGER = new CloudItemKnife(1522, 4478, OBSIDIAN, "Karambit Tiger", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_STEEL = new CloudItemKnife(1523, 4479, OBSIDIAN, "Karambit Steel", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_DOPPLER = new CloudItemKnife(1524, 4480, OBSIDIAN, "Karambit Doppler", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_LORE = new CloudItemKnife(1525, 4481, OBSIDIAN, "Karambit Lore", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_GAMMA = new CloudItemKnife(1526, 4482, OBSIDIAN, "Karambit Gamma", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_EAGLE = new CloudItemKnife(1527, 4483, OBSIDIAN, "Karambit Eagle", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_PRESTIGE = new CloudItemKnife(1528, 4484, OBSIDIAN, "Karambit Prestige", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_AUTOTRONIC = new CloudItemKnife(1529, 4485, OBSIDIAN, "Karambit Autotronic", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_BLACKOUT = new CloudItemKnife(1560, 4516, OBSIDIAN, "Karambit Blackout", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_RUBY = new CloudItemKnife(1561, 4517, OBSIDIAN, "Karambit Ruby", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_FADE = new CloudItemKnife(1562, 4518, OBSIDIAN, "Karambit Fade", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_RUST = new CloudItemKnife(1563, 4519, OBSIDIAN, "Karambit Rust", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_ETHERFIRE = new CloudItemKnife(1564, 4520, OBSIDIAN, "Karambit Etherfire", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_WATERMELON = new CloudItemKnife(1565, 4521, OBSIDIAN, "Karambit Watermelon", false, KNIFE_KAR);
	public static final CloudItemKnife KNIFE_KAR_INVERTED = new CloudItemKnife(1566, 4522, OBSIDIAN, "Karambit Inverted", false, KNIFE_KAR);

	public static final CloudItemKnife KNIFE_BFL = new CloudItemKnife(1530, 4486, OBSIDIAN, "Butterfly", false);
	public static final CloudItemKnife KNIFE_BFL_MARBLE = new CloudItemKnife(1531, 4487, OBSIDIAN, "Butterfly Marble", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_TIGER = new CloudItemKnife(1532, 4488, OBSIDIAN, "Butterfly Tiger", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_STEEL = new CloudItemKnife(1533, 4489, OBSIDIAN, "Butterfly Steel", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_DOPPLER = new CloudItemKnife(1534, 4490, OBSIDIAN, "Butterfly Doppler", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_LORE = new CloudItemKnife(1535, 4491, OBSIDIAN, "Butterfly Lore", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_GAMMA = new CloudItemKnife(1536, 4492, OBSIDIAN, "Butterfly Gamma", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_PRESTIGE = new CloudItemKnife(1538, 4494, OBSIDIAN, "Butterfly Prestige", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_AUTOTRONIC = new CloudItemKnife(1539, 4495, OBSIDIAN, "Butterfly Autotronic", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_BLACKOUT = new CloudItemKnife(1570, 4526, OBSIDIAN, "Butterfly Blackout", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_RUBY = new CloudItemKnife(1571, 4527, OBSIDIAN, "Butterfly Ruby", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_FADE = new CloudItemKnife(1572, 4528, OBSIDIAN, "Butterfly Fade", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_RUST = new CloudItemKnife(1573, 4529, OBSIDIAN, "Butterfly Rust", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_ETHERFIRE = new CloudItemKnife(1574, 4530, OBSIDIAN, "Butterfly Etherfire", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_WATERMELON = new CloudItemKnife(1575, 4531, OBSIDIAN, "Butterfly Watermelon", false, KNIFE_BFL);
	public static final CloudItemKnife KNIFE_BFL_INVERTED = new CloudItemKnife(1576, 4532, OBSIDIAN, "Butterfly Inverted", false, KNIFE_BFL);

	public static final CloudItemGun NOVA = new CloudItemGun(1600, 4296, DEFAULT, "Nova", true, HEAVY);
	public static final CloudItemGun NOVA_OXIDEBLAZE = new CloudItemGun(1601, 4296, IRON, "Nova Oxide Blaze", false, NOVA, ELEMENTAL);
	public static final CloudItemGun NOVA_HYPER_BEAST = new CloudItemGun(1602, 4296, DIAMOND, "Nova Hyper Beast", false, NOVA, EPSILON);
	public static final CloudItemGun NOVA_JUNK = new CloudItemGun(1603, 4296, COAL, "Nova Junk", false, NOVA, BETA);
	public static final CloudItemGun NOVA_INFUSED = new CloudItemGun(1604, 4296, LAPIS, "Nova Infused", false, NOVA, CLASH);
	public static final CloudItemGun NOVA_GHOST = new CloudItemGun(1605, 4296, LAPIS, "Nova Ghost", false, NOVA, PHANTASMAGORIA);

	public static final CloudItemGun SAWEDOFF = new CloudItemGun(1650, 4297, DEFAULT, "Sawed-Off", true, HEAVY);
	public static final CloudItemGun SAWEDOFF_WASTELANDPRINCESS = new CloudItemGun(1651, 4297, GOLD, "Sawed-Off WastelandPrincess", false, SAWEDOFF, REVOLUTION);
	public static final CloudItemGun SAWEDOFF_KRAKEN = new CloudItemGun(1652, 4297, LAPIS, "Sawed-Off Kraken", false, SAWEDOFF, ALPHA);
	public static final CloudItemGun SAWEDOFF_MIDNIGHT = new CloudItemGun(1653, 4297, COAL, "Sawed-Off Midnight", false, SAWEDOFF, DELTA);
	public static final CloudItemGun SAWEDOFF_JUNK = new CloudItemGun(1654, 4297, COAL, "Sawed-Off Junk", false, SAWEDOFF, MYTHIC);
	public static final CloudItemGun SAWEDOFF_SOUNDWAVE = new CloudItemGun(1655, 4297, IRON, "Sawed-Off Soundwave", false, SAWEDOFF, GAMMA);
	public static final CloudItemGun SAWEDOFF_POTATO = new CloudItemGun(1656, 4297, COAL, "Sawed-Off Potato", false, SAWEDOFF, EPSILON);
	public static final CloudItemGun SAWEDOFF_ORANGE = new CloudItemGun(1657, 4297, IRON, "Sawed-Off Orange", false, SAWEDOFF, ELEMENTAL);

	public static final CloudItemGun MAG7 = new CloudItemGun(1700, 4298, DEFAULT, "Mag-7", true, HEAVY);
	public static final CloudItemGun MAG7_ROYALFIRE = new CloudItemGun(1701, 4298, IRON, "Mag-7 Royal Fire", false, MAG7, OASIS);
	public static final CloudItemGun MAG7_VAMPIRE = new CloudItemGun(1702, 4298, GOLD, "Mag-7 Vampire", false, MAG7, SEPULCHER);
	public static final CloudItemGun MAG7_BULLDOZER = new CloudItemGun(1703, 4298, COAL, "Mag-7 Bulldozer", false, MAG7, ALPHA);
	public static final CloudItemGun MAG7_SEABIRD = new CloudItemGun(1704, 4298, COAL, "Mag-7 Seabird", false, MAG7, GAMMA);

	public static final CloudItemGun M249 = new CloudItemGun(1750, 4299, DEFAULT, "M249", true, HEAVY);
	public static final CloudItemGun M249_NEON = new CloudItemGun(1751, 4299, IRON, "M249 Neon", false, M249, DELTA);
	public static final CloudItemGun M249_GHOST = new CloudItemGun(1752, 4299, COAL, "M249 Ghost", false, M249, PHANTASMAGORIA);
	public static final CloudItemGun M249_HEATSYNC = new CloudItemGun(1753, 4299, COAL, "M249 Heatsync", false, M249, ALPHA);
	public static final CloudItemGun M249_LOUDMOUTH = new CloudItemGun(1754, 4299, IRON, "M249 Loudmouth", false, M249, EPSILON);
	public static final CloudItemGun M249_TACTICAL_FOREST = new CloudItemGun(1755, 4299, LAPIS, "M249 Tactical Forest", false, M249, GAMMA);
	public static final CloudItemGun M249_COG_SYSTEM_SCHEME = new CloudItemGun(1756, 4299, LAPIS, "M249 Cog System Scheme", false, M249, VECTORIZED);

	public static final CloudItemGun CZ75 = new CloudItemGun(1800, 4271, DEFAULT, "CZ75", true, PISTOL);
	public static final CloudItemGun CZ75_PHANTOM = new CloudItemGun(1801, 4271, GOLD, "CZ75 Phantom", false, CZ75, null);
	public static final CloudItemGun CZ75_GREEN = new CloudItemGun(1802, 4271, COAL, "CZ75 Green", false, CZ75, BETA);
	public static final CloudItemGun CZ75_TIGER = new CloudItemGun(1803, 4271, LAPIS, "CZ75 Tiger", false, CZ75, OASIS);
	public static final CloudItemGun CZ75_ASMO = new CloudItemGun(1804, 4271, GOLD, "CZ75 Asmo", false, CZ75, REVOLUTION);
	public static final CloudItemGun CZ75_NEONPAW = new CloudItemGun(1805, 4271, LAPIS, "CZ75 Neonpaw", false, CZ75, VECTORIZED);
	public static final CloudItemGun CZ75_GHOST = new CloudItemGun(1806, 4271, IRON, "CZ75 Ghost", false, CZ75, PHANTASMAGORIA);
	public static final CloudItemGun CZ75_AFTERLIFE = new CloudItemGun(1807, 4271, IRON, "CZ75 Afterlife", false, CZ75, SEPULCHER);

	public static final CloudItemGun FIVESEVEN = new CloudItemGun(1815, 4272, DEFAULT, "Fiveseven", true, PISTOL);

	public static final CloudItemGun R8 = new CloudItemGun(1826, 4273, DEFAULT, "R8", true, PISTOL);
	public static final CloudItemGun R8_FADE = new CloudItemGun(1827, 4273, GOLD, "R8 Fade", false, R8, CLASH);
	public static final CloudItemGun R8_GOLD = new CloudItemGun(1828, 4273, LAPIS, "R8 Gold", false, R8, OASIS);

	public static final CloudItemGun UMP45 = new CloudItemGun(1850, 4279, DEFAULT, "UMP-45", true, SMG);
	public static final CloudItemGun UMP45_PRIMAL = new CloudItemGun(1851, 4279, GOLD, "UMP-45 Primal", false, UMP45, ALPHA);
	public static final CloudItemGun UMP45_EDIFICE = new CloudItemGun(1852, 4279, IRON, "UMP-45 Edifice", false, UMP45, SEPULCHER);
	public static final CloudItemGun UMP45_XERYC = new CloudItemGun(1853, 4279, IRON, "UMP-45 Xeryc", false, UMP45, BETA);

	public static final CloudItemHat HAT_TOPHAT = new CloudItemHat(1900, 4656, LAPIS, "Top Hat", false);
	public static final CloudItemHat HAT_CATHAT = new CloudItemHat(1901, 4657, DIAMOND, "Cat's Hat", false);
	public static final CloudItemHat HAT_HARDRED = new CloudItemHat(1902, 4658, COAL, "Hardhat Red", false);
	public static final CloudItemHat HAT_HARDORANGE = new CloudItemHat(1903, 4659, COAL, "Hardhat Orange", false);
	public static final CloudItemHat HAT_HARDYELLOW = new CloudItemHat(1904, 4660, COAL, "Hardhat Yellow", false);
	public static final CloudItemHat HAT_VIKING = new CloudItemHat(1905, 4661, GOLD, "Viking Helmet", false);
	public static final CloudItemHat HAT_FIREMAN = new CloudItemHat(1906, 4662, IRON, "Fireman Hat", false);
	public static final CloudItemHat HAT_FIREMANCHIEF = new CloudItemHat(1907, 4663, IRON, "Fire Chief Hat", false);
	public static final CloudItemHat HAT_TV = new CloudItemHat(1908, 4664, LAPIS, "TV Hat", false);

	public static final CloudItemGeneric TRADE_CONTRACT = new CloudItemGeneric(2040, 4706, DEFAULT, "Trade Contract", true, false, false, false, false, false);

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

	public static final CloudItemKey CASE_KEY_PRESTIGE = new CloudItemKey(4500, 5756, COAL, "Key Prestige", false);
	public static final CloudItemKey CASE_KEY_ALPHA = new CloudItemKey(4501, 5757, COAL, "Key Alpha", false);
	public static final CloudItemKey CASE_KEY_BETA = new CloudItemKey(4502, 5758, COAL, "Key Beta", false);
	public static final CloudItemKey CASE_KEY_GAMMA = new CloudItemKey(4503, 5759, COAL, "Key Gamma", false);
	public static final CloudItemKey CASE_KEY_DELTA = new CloudItemKey(4504, 5760, COAL, "Key Delta", false);
	public static final CloudItemKey CASE_KEY_EPSILON = new CloudItemKey(4505, 5761, COAL, "Key Epsilon", false);
	public static final CloudItemKey CASE_KEY_CHRONICAL = new CloudItemKey(4506, 5762, COAL, "Key Chronical", false);
	public static final CloudItemKey CASE_KEY_MYTHIC = new CloudItemKey(4507, 5763, COAL, "Key Mythic", false);
	public static final CloudItemKey CASE_KEY_OASIS = new CloudItemKey(4508, 5764, COAL, "Key Oasis", false);
	public static final CloudItemKey CASE_KEY_CLASH = new CloudItemKey(4509, 5765, COAL, "Key Clash", false);
	public static final CloudItemKey CASE_KEY_ELEMENTAL = new CloudItemKey(4510, 5766, COAL, "Key Elemental", false);
	public static final CloudItemKey CASE_KEY_REVOLUTION = new CloudItemKey(4511, 5767, COAL, "Key Revolution", false);
	public static final CloudItemKey CASE_KEY_VECTORIZED = new CloudItemKey(4512, 5768, COAL, "Key Vectorized", false);
	public static final CloudItemKey CASE_KEY_BIOMECHANIC = new CloudItemKey(4513, 5769, COAL, "Key Biomech", false);
	public static final CloudItemKey CASE_KEY_PHANTASMAGORIA = new CloudItemKey(4514, 5770, COAL, "Key Phantom", false);
	public static final CloudItemKey CASE_KEY_SEPULCHER = new CloudItemKey(4515, 5771, COAL, "Key Sepulcher", false);

	public static final List<CloudItem> POOL_KNIFE_VANILLA = List.of(
		KNIFE_BAY, KNIFE_KAR, KNIFE_BFL
	);
	public static final List<CloudItem> POOL_KNIFE_PRESTIGE = List.of(
		KNIFE_TAC_PRESTIGE, KNIFE_BAY_PRESTIGE, KNIFE_KAR_PRESTIGE, KNIFE_BFL_PRESTIGE
	);
	public static final List<CloudItem> POOL_KNIFE_ETHERFIRE = List.of(
		KNIFE_TAC_ETHERFIRE, KNIFE_BAY_ETHERFIRE, KNIFE_KAR_ETHERFIRE, KNIFE_BFL_ETHERFIRE
	);
	public static final List<CloudItem> POOL_KNIFE_DOPPLER = List.of(
		KNIFE_TAC_DOPPLER, KNIFE_BAY_DOPPLER, KNIFE_KAR_DOPPLER, KNIFE_BFL_DOPPLER
	);
	public static final List<CloudItem> POOL_KNIFE_GAMMA = List.of(
		KNIFE_TAC_GAMMA, KNIFE_BAY_GAMMA, KNIFE_KAR_GAMMA, KNIFE_BFL_GAMMA
	);
	public static final List<CloudItem> POOL_KNIFE_BLACKOUT = List.of(
		KNIFE_TAC_BLACKOUT, KNIFE_BAY_BLACKOUT, KNIFE_KAR_BLACKOUT, KNIFE_BFL_BLACKOUT
	);
	public static final List<CloudItem> POOL_KNIFE_RUBY = List.of(
		KNIFE_TAC_RUBY, KNIFE_BAY_RUBY, KNIFE_KAR_RUBY, KNIFE_BFL_RUBY
	);
	public static final List<CloudItem> POOL_KNIFE_FADE = List.of(
		KNIFE_TAC_FADE, KNIFE_BAY_FADE, KNIFE_KAR_FADE, KNIFE_BFL_FADE
	);
	public static final List<CloudItem> POOL_KNIFE_LORE = List.of(
		KNIFE_TAC_LORE, KNIFE_BAY_LORE, KNIFE_KAR_LORE, KNIFE_BFL_LORE
	);
	public static final List<CloudItem> POOL_KNIFE_TIGER = List.of(
		KNIFE_TAC_TIGER, KNIFE_BAY_TIGER, KNIFE_KAR_TIGER, KNIFE_BFL_TIGER
	);
	public static final List<CloudItem> POOL_KNIFE_MARBLE = List.of(
		KNIFE_TAC_MARBLE, KNIFE_BAY_MARBLE, KNIFE_KAR_MARBLE, KNIFE_BFL_MARBLE
	);
	public static final List<CloudItem> POOL_KNIFE_RUST = List.of(
		KNIFE_TAC_RUST, KNIFE_BAY_RUST, KNIFE_KAR_RUST, KNIFE_BFL_RUST
	);
	public static final List<CloudItem> POOL_KNIFE_STEEL = List.of(
		KNIFE_TAC_STEEL, KNIFE_BAY_STEEL, KNIFE_KAR_STEEL, KNIFE_BFL_STEEL
	);
	public static final List<CloudItem> POOL_KNIFE_AUTOTRONIC = List.of(
		KNIFE_TAC_AUTOTRONIC, KNIFE_BAY_AUTOTRONIC, KNIFE_KAR_AUTOTRONIC, KNIFE_BFL_AUTOTRONIC
	);
	public static final List<CloudItem> POOL_KNIFE_WATERMELON = List.of(
		KNIFE_TAC_WATERMELON, KNIFE_BAY_WATERMELON, KNIFE_KAR_WATERMELON, KNIFE_BFL_WATERMELON
	);
	public static final List<CloudItem> POOL_KNIFE_INVERTED = List.of(
		KNIFE_TAC_INVERTED, KNIFE_BAY_INVERTED, KNIFE_KAR_INVERTED, KNIFE_BFL_INVERTED
	);

	public static final CloudItemCase CASE_PRESTIGE = new CloudItemCase(2000, 4356, OBSIDIAN, "Prestige Case", false, CASE_KEY_PRESTIGE, POOL_KNIFE_PRESTIGE, false);
	public static final CloudItemCase CASE_ALPHA = new CloudItemCase(2001, 4357, COAL, "Alpha Case", false, CASE_KEY_ALPHA, POOL_KNIFE_ETHERFIRE, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 2, false)
			.add(AWP_MECHA_DRAGON, 2, false)
			.add(AK47_VULCAN, 4)
			.add(M4A4_ASMO, 4)
			.add(DEAGLE_WATERMELON, 15)
			.add(UMP45_PRIMAL, 15)
			.add(SAWEDOFF_KRAKEN, 54)
			.add(M1911_DRAGON, 54)
			.add(FNFAL_VENOM, 54)
			.add(G18_CYREX, 133)
			.add(SCAR20_CYREX, 133)
			.add(P90_ARROW, 133)
			.add(MAG7_BULLDOZER, 200)
			.add(M249_HEATSYNC, 200)
			.add(DRAGUNOV_JUNGLE, 200)
		);
	public static final CloudItemCase CASE_BETA = new CloudItemCase(2002, 4358, COAL, "Beta Case", false, CASE_KEY_BETA, POOL_KNIFE_DOPPLER, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 2, false)
			.add(G18_HIGHROLLER, 2, false)
			.add(SCAR20_SPORT, 4)
			.add(AWP_DRAGON, 4)
			.add(AK47_ASMO, 15)
			.add(G18_FADE, 15)
			.add(FNFAL_REBEL, 54)
			.add(M4A4_CAKEY, 54)
			.add(M1911_VENOM, 54)
			.add(AK47_FUEL, 133)
			.add(UMP45_XERYC, 133)
			.add(AWP_REDLINE, 133)
			.add(NOVA_JUNK, 200)
			.add(CZ75_GREEN, 200)
			.add(FAMAS_PULSE, 200)
		);
	public static final CloudItemCase CASE_GAMMA = new CloudItemCase(2003, 4359, COAL, "Gamma Case", false, CASE_KEY_GAMMA, POOL_KNIFE_GAMMA, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(M4A4_EVILDAIMYO, 8)
			.add(DEAGLE_BLOODSPORT, 15)
			.add(AK47_IRON_BEAST, 15)
			.add(DEAGLE_FLATLINE, 54)
			.add(M249_TACTICAL_FOREST, 54)
			.add(AWP_ELECTRIC_ARCHIVE, 54)
			.add(SAWEDOFF_SOUNDWAVE, 133)
			.add(FNFAL_RACE, 133)
			.add(VECTOR_BLOODYEQUATION, 133)
			.add(MAC10_CANDY_APPLE, 200)
			.add(MAG7_SEABIRD, 200)
			.add(SCAR20_POWERCORE, 200)
		);
	public static final CloudItemCase CASE_DELTA = new CloudItemCase(2004, 4360, COAL, "Delta Case", false, CASE_KEY_DELTA, POOL_KNIFE_BLACKOUT, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(AWP_OCEANDEPTH, 8)
			.add(P90_MEDICINE, 15)
			.add(M4A4_SPORT, 15)
			.add(M4A4_VERTEX, 54)
			.add(AK47_GRAFFITI, 54)
			.add(AWP_AKARI, 54)
			.add(M249_NEON, 133)
			.add(TEC9_HORNET, 133)
			.add(P250_IRRADIATED, 133)
			.add(SAWEDOFF_MIDNIGHT, 200)
			.add(DEAGLE_CIRCUIT, 200)
			.add(MAC10_CIRCUIT, 200)
		);
	public static final CloudItemCase CASE_EPSILON = new CloudItemCase(2005, 4361, COAL, "Epsilon Case", false, CASE_KEY_EPSILON, POOL_KNIFE_RUBY, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(NOVA_HYPER_BEAST, 8)
			.add(DEAGLE_FADE, 15)
			.add(AWP_CROWD, 15)
			.add(P250_NIPPON, 54)
			.add(AK47_FOX, 54)
			.add(DEAGLE_KUMICHO_DRAGON, 54)
			.add(AK47_STRIPE, 133)
			.add(G18_RACE, 133)
			.add(M249_LOUDMOUTH, 133)
			.add(G18_URBAN, 200)
			.add(SAWEDOFF_POTATO, 200)
			.add(M1911_RAIN, 200)
		);
	public static final CloudItemCase CASE_CHRONICAL = new CloudItemCase(2006, 4362, COAL, "Chronical Case", false, CASE_KEY_CHRONICAL, POOL_KNIFE_FADE, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 2, false)
			.add(SCAR20_UNICORN, 2, false)
			.add(AK47_FRONTSIDE, 8)
			.add(P250_BEAST, 15)
			.add(FAMAS_VULCAN, 15)
			.add(AWP_BOOM, 80)
			.add(DEAGLE_DEADHORIZON, 80)
			.add(FNFAL_SHOCKER, 133)
			.add(DEAGLE_NIGHTRAZER, 133)
			.add(M1911_SHOCK, 133)
			.add(SCAR20_ARCTIC, 200)
			.add(P90_RUBY, 200)
			.add(TEC9_QUAKORONIC, 200)
		);
	public static final CloudItemCase CASE_MYTHIC = new CloudItemCase(2007, 4363, COAL, "Mythic Case", false, CASE_KEY_MYTHIC, POOL_KNIFE_LORE, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 2, false)
			.add(M4A4_DESOLATE_SPACE, 2, false)
			.add(DRAGUNOV_WORMGOD, 8)
			.add(AWP_BEAST, 15)
			.add(FNFAL_ASMO, 15)
			.add(P90_KRAKEN, 54)
			.add(M1911_BLAZE, 54)
			.add(TEC9_TORQUE, 54)
			.add(VECTOR_SLIME, 133)
			.add(FAMAS_RYBUS, 133)
			.add(DRAGUNOV_STEAMPUNK, 133)
			.add(DEAGLE_MYSTIC, 200)
			.add(SAWEDOFF_JUNK, 200)
			.add(SCAR20_ELITE, 200)
		);
	public static final CloudItemCase CASE_OASIS = new CloudItemCase(2008, 4364, COAL, "Oasis Case", false, CASE_KEY_OASIS, POOL_KNIFE_TIGER, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 2, false)
			.add(AK47_HORUS, 2, false)
			.add(MAC10_CHICKEN, 8)
			.add(M1911_TRIARK, 15)
			.add(FNFAL_VAPORWAVE, 15)
			.add(CZ75_TIGER, 54)
			.add(AWP_MANOWAR, 54)
			.add(R8_GOLD, 54)
			.add(DRAGUNOV_SKY, 133)
			.add(MAG7_ROYALFIRE, 133)
			.add(G18_HORNET, 133)
			.add(M4A4_DESERT, 200)
			.add(P250_DUNE, 200)
			.add(FAMAS_SANDSTORM, 200)
		);
	public static final CloudItemCase CASE_CLASH = new CloudItemCase(2009, 4365, COAL, "Clash Case", false, CASE_KEY_CLASH, POOL_KNIFE_MARBLE, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(AK47_PROPAGANDA, 4)
			.add(MAC10_NEON, 4)
			.add(AWP_ASMO, 15)
			.add(R8_FADE, 15)
			.add(AK47_MACAW, 54)
			.add(NOVA_INFUSED, 54)
			.add(M4A4_NERFED, 54)
			.add(MAC10_RACE, 133)
			.add(TEC9_FUEL, 133)
			.add(DEAGLE_COBALT, 133)
			.add(FNFAL_STAIN, 200)
			.add(DRAGUNOV_ELITE, 200)
			.add(FAMAS_COBALT, 200)
		);
	public static final CloudItemCase CASE_ELEMENTAL = new CloudItemCase(2010, 4366, COAL, "Elemental Case", false, CASE_KEY_ELEMENTAL, POOL_KNIFE_RUST, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 2, false)
			.add(M4A4_HOWL, 2, false)
			.add(AWP_LIGHTNING, 8)
			.add(TEC9_CHERRY, 15)
			.add(MAC10_FADE, 15)
			.add(FAMAS_NUCLEAR, 54)
			.add(M1911_GOLD, 54)
			.add(DEAGLE_GOLD, 54)
			.add(M4A4_WAVES, 133)
			.add(NOVA_OXIDEBLAZE, 133)
			.add(SAWEDOFF_ORANGE, 133)
			.add(P90_RUST, 200)
			.add(VECTOR_SUNNY, 200)
			.add(G18_DEEP_BLUE, 200)
		);
	public static final CloudItemCase CASE_REVOLUTION = new CloudItemCase(2011, 4367, COAL, "Revolution Case", false, CASE_KEY_REVOLUTION, POOL_KNIFE_STEEL, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(FAMAS_WARHAWK, 8)
			.add(SAWEDOFF_WASTELANDPRINCESS, 15)
			.add(CZ75_ASMO, 15)
			.add(AK47_XRAY, 54)
			.add(P250_CITY, 54)
			.add(TEC9_AQUARIUM, 54)
			.add(M4A4_LURKER, 133)
			.add(M1911_INDIRECT, 133)
			.add(P90_MODULE, 133)
			.add(MAC10_VIOLET, 200)
			.add(DEAGLE_DEVI, 200)
			.add(M1911_OLDWEST, 200)
		);
	public static final CloudItemCase CASE_VECTORIZED = new CloudItemCase(2012, 4368, COAL, "Vectorized Case", false, CASE_KEY_VECTORIZED, POOL_KNIFE_AUTOTRONIC, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(VECTOR_EXTINCT, 8)
			.add(MAC10_ASMO, 15)
			.add(AK47_BLOODSPORT, 15)
			.add(G18_VULCAN, 54)
			.add(CZ75_NEONPAW, 54)
			.add(M249_COG_SYSTEM_SCHEME, 54)
			.add(M4A4_CYREX, 133)
			.add(VECTOR_ARCTIC, 133)
			.add(SCAR20_SHOCK, 133)
			.add(AWP_FOREST, 200)
			.add(DEAGLE_NAVY, 200)
			.add(G18_CANDY_APPLE, 200)
		);
	public static final CloudItemCase CASE_BIOMECHANIC = new CloudItemCase(2013, 4369, COAL, "Biomech Case", false, CASE_KEY_BIOMECHANIC, POOL_KNIFE_WATERMELON, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(P250_SEEYALATER, 8)
			.add(AWP_STEAMPUNK, 15)
			.add(M4A4_GOLDEN_COIL, 15)
			.add(G18_MOONRISE, 54)
			.add(TEC9_ECO, 54)
			.add(DEAGLE_MECHAINDUSTRIES, 54)
			.add(M4A4_DAYBREAK, 133)
			.add(FAMAS_AKARI, 133)
			.add(AK47_SHIKARI, 133)
			.add(VECTOR_CIRCUIT, 200)
			.add(G18_CIRCUIT, 200)
			.add(P250_ARCTIC, 200)
		);
	public static final CloudItemCase CASE_PHANTASMAGORIA = new CloudItemCase(2014, 4370, COAL, "Phantom Case", false, CASE_KEY_PHANTASMAGORIA, POOL_KNIFE_INVERTED, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 2, false)
			.add(AK47_CREEP, 2, false)
			.add(P90_GRIM, 8)
			.add(M4A4_PUMPKIN, 15)
			.add(MAC10_SPIDER, 15)
			.add(AWP_BONES, 54)
			.add(VECTOR_ORTHUS, 54)
			.add(NOVA_GHOST, 54)
			.add(MAC10_PUMPKIN, 133)
			.add(CZ75_GHOST, 133)
			.add(FAMAS_SPOOKY, 133)
			.add(G18_GHOUL, 200)
			.add(M249_GHOST, 200)
			.add(M1911_TUXEDO, 200)
		);
	public static final CloudItemCase CASE_SEPULCHER = new CloudItemCase(2015, 4371, COAL, "Sepulcher Case", false, CASE_KEY_SEPULCHER, POOL_KNIFE_INVERTED, true)
		.modifyContents(contents -> contents
			.add(KNIFE_TAC_DISPLAY, 4, false)
			.add(AK47_HAUNTED, 8)
			.add(MAG7_VAMPIRE, 15)
			.add(AWP_PUMPKIN, 15)
			.add(DEAGLE_GRIN, 54)
			.add(AK47_BONES, 54)
			.add(P250_HAUNTED, 54)
			.add(MAC10_SWARM, 133)
			.add(UMP45_EDIFICE, 133)
			.add(CZ75_AFTERLIFE, 133)
			.add(DRAGUNOV_GHOST, 200)
			.add(M1911_MUMMY, 200)
			.add(G18_GHOST, 200)
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
		return Util.randomInt(4) > 0 ? CloudItemCase.getRandomForDrop() : CloudItemGun.getRandomForDrop();
	}
}
