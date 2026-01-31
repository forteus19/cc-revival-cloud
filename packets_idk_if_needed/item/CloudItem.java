package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import java.util.ArrayList;
import net.minecraft.util.EnumChatFormatting;

public class CloudItem {
    public static final CloudItem[] itemList = new CloudItem[8192];
    public static final CloudItem M4A4 = (new CloudItemGun(100, 4000, "M4A4")).setDefault().setFilterMT("rifle");
    public static final CloudItem M4A4_WAVES;
    public static final CloudItem M4A4_EVILDAIMYO;
    public static final CloudItem M4A4_CAKEY;
    public static final CloudItem M4A4_HOWL;
    public static final CloudItem M4A4_NERFED;
    public static final CloudItem M4A4_PUMPKIN;
    public static final CloudItem M4A4_PATRIOT;
    public static final CloudItem AK47;
    public static final CloudItem AK47_PROPAGANDA;
    public static final CloudItem AK47_ASMO;
    public static final CloudItem AK47_NEONREVOLUTION;
    public static final CloudItem AK47_HAUNTED;
    public static final CloudItem AK47_BLOODSPORT;
    public static final CloudItem AK47_FOX;
    public static final CloudItem FAMAS;
    public static final CloudItem FAMAS_AKARI;
    public static final CloudItem FAMAS_WARHAWK;
    public static final CloudItem FAMAS_SPOOKY;
    public static final CloudItem FNFAL;
    public static final CloudItem FNFAL_VAPORWAVE;
    public static final CloudItem FNFAL_ASMO;
    public static final CloudItem GALIL;
    public static final CloudItem M1911;
    public static final CloudItem M1911_DRAGON;
    public static final CloudItem M1911_TUXEDO;
    public static final CloudItem G18;
    public static final CloudItem G18_RACE;
    public static final CloudItem G18_HIGHROLLER;
    public static final CloudItem G18_DEEP_BLUE;
    public static final CloudItem TEC9;
    public static final CloudItem TEC9_QUAKORONIC;
    public static final CloudItem TEC9_ECO;
    public static final CloudItem TEC9_AQUARIUM;
    public static final CloudItem DEAGLE;
    public static final CloudItem DEAGLE_NIGHTRAZER;
    public static final CloudItem DEAGLE_DEADHORIZON;
    public static final CloudItem DEAGLE_GRIN;
    public static final CloudItem P250;
    public static final CloudItem P250_IRRADIATED;
    public static final CloudItem P250_SEEYALATER;
    public static final CloudItem P250_CITY;
    public static final CloudItem P250_BEAST;
    public static final CloudItem P90;
    public static final CloudItem P90_RUST;
    public static final CloudItem P90_KRAKEN;
    public static final CloudItem MAC10;
    public static final CloudItem MAC10_RACE;
    public static final CloudItem MAC10_PUMPKIN;
    public static final CloudItem VECTOR;
    public static final CloudItem VECTOR_CIRCUIT;
    public static final CloudItem VECTOR_BLOODYEQUATION;
    public static final CloudItem AWP;
    public static final CloudItem AWP_DRAGON;
    public static final CloudItem AWP_MANOWAR;
    public static final CloudItem AWP_ASMO;
    public static final CloudItem AWP_BONES;
    public static final CloudItem AWP_REDLINE;
    public static final CloudItem AWP_BOOM;
    public static final CloudItem DRAGUNOV;
    public static final CloudItem DRAGUNOV_SKY;
    public static final CloudItem DRAGUNOV_STEAMPUNK;
    public static final CloudItem SCAR20;
    public static final CloudItem SCAR20_CYREX;
    public static final CloudItem SCAR20_UNICORN;
    public static final CloudItem SSG08;
    public static final CloudItem KNIFE_TAC;
    public static final CloudItem KNIFE_TAC_DISPLAY;
    public static final CloudItem KNIFE_TAC_MARBLE;
    public static final CloudItem KNIFE_TAC_TIGER;
    public static final CloudItem KNIFE_TAC_STEEL;
    public static final CloudItem KNIFE_TAC_DOPPLAR;
    public static final CloudItem KNIFE_TAC_LORE;
    public static final CloudItem KNIFE_TAC_GAMMA;
    public static final CloudItem KNIFE_BAY;
    public static final CloudItem KNIFE_BAY_MARBLE;
    public static final CloudItem KNIFE_BAY_TIGER;
    public static final CloudItem KNIFE_BAY_STEEL;
    public static final CloudItem KNIFE_BAY_DOPPLAR;
    public static final CloudItem KNIFE_BAY_LORE;
    public static final CloudItem KNIFE_BAY_GAMMA;
    public static final CloudItem KNIFE_BAY_DRAGONLORE;
    public static final CloudItem KNIFE_KAR;
    public static final CloudItem KNIFE_KAR_MARBLE;
    public static final CloudItem KNIFE_KAR_TIGER;
    public static final CloudItem KNIFE_KAR_STEEL;
    public static final CloudItem KNIFE_KAR_DOPPLAR;
    public static final CloudItem KNIFE_KAR_LORE;
    public static final CloudItem KNIFE_KAR_GAMMA;
    public static final CloudItem KNIFE_KAR_EAGLE;
    public static final CloudItem KNIFE_BUTTERFLY;
    public static final CloudItem NOVA;
    public static final CloudItem NOVA_OXIDEBLAZE;
    public static final CloudItem NOVA_HYPER_BEAST;
    public static final CloudItem SAWEDOFF;
    public static final CloudItem SAWEDOFF_WASTELANDPRINCESS;
    public static final CloudItem SAWEDOFF_KRAKEN;
    public static final CloudItem SAWEDOFF_MIDNIGHT;
    public static final CloudItem MAG7;
    public static final CloudItem MAG7_ROYALFIRE;
    public static final CloudItem MAG7_VAMPIRE;
    public static final CloudItem M249;
    public static final CloudItem M249_NEON;
    public static final CloudItem M249_GHOST;
    public static final CloudItem CZ75;
    public static final CloudItem CZ75_PHANTOM;
    public static final CloudItem UMP45;
    public static final CloudItem UMP45_PRIMAL;
    public static final CloudItem UMP45_EDIFICE;
    public static final CloudItem FIVESEVEN;
    public static final CloudItem R8;
    public static final CloudItem R8_FADE;
    public static final CloudItem R8_GOLD;
    public static final CloudItem HAT_TOPHAT;
    public static final CloudItem HAT_CATHAT;
    public static final CloudItem HAT_HARDRED;
    public static final CloudItem HAT_HARDORANGE;
    public static final CloudItem HAT_HARDYELLOW;
    public static final CloudItem HAT_VIKING;
    public static final CloudItem HAT_FIREMAN;
    public static final CloudItem HAT_FIREMANCHIEF;
    public static final CloudItem HAT_TV;
    public static final CloudItem WC_ALPHA;
    public static final CloudItem WC_ALPHA2;
    public static final CloudItem WC_ALPHA3;
    public static final CloudItem HC_ALPHA1;
    public static final CloudItem WC_HALLOWEEN1;
    public static final CloudItem WC_EXO;
    public static final CloudItem TRADE_CONTRACT;
    public static final CloudItem NAME_TAG;
    public static final CloudItem COIN_BETA;
    public static final CloudItem COIN_ALPHA;
    public static final CloudItem COIN_DISCORD;
    public static final CloudItem COIN_SKINNER;
    public static final CloudItem COIN_DEV;
    public static final CloudItem COIN_STAFF;
    public static final CloudItem COIN_SUPPORTER;
    public static final CloudItem COIN_MODELER;
    public static final CloudItem COIN_PRESTIGE;
    public static final CloudItem COIN_ASSETDEV;
    public static final CloudItem COIN_FUZE;
    public static final CloudItem COIN_ALXTON;
    public static final CloudItem COIN_JEROME;
    public static final CloudItem COIN_PEDRO;
    public static final CloudItem COIN_EDGE;
    public static final CloudItem COIN_JABUTI;
    public static final CloudItem COIN_INEMAFOO;
    public static final CloudItem COIN_MAPPER;
    public static final CloudItem COIN_NITRO;
    public static final CloudItem COIN_BATTLEFIELDS;
    public static final CloudItem COIN_CCLEAGUE_BRONZE;
    public static final CloudItem COIN_CCLEAGUE_SILVER;
    public static final CloudItem COIN_CCLEAGUE_GOLD;
    public static final CloudItem BOOSTER_EXP_2;
    public static final CloudItem BOOSTER_EXP_3;
    public static final CloudItem BOOSTER_EMD_2;
    public static final CloudItem STICKER_CAMP;
    public static final CloudItem STICKER_CHICKEN_STRIKE;
    public static final CloudItem STICKER_DINKED;
    public static final CloudItem STICKER_KATO2014_IBUYPOWER;
    public static final CloudItem STICKER_KAWAIIKILLER;
    public static final CloudItem STICKER_LUCKY13;
    public static final CloudItem STICKER_HOWL;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_GIS;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_GRAVITY;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_KINGDEAD;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_PARADISE9;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_SEALTEAM6;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_SIKA;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_TBK;
    public static final CloudItem STICKER_CCL_2020_S1_CLAN_CCTN;
    public static final CloudItem SC_ALPHA;
    public static final CloudItem SC_CCL_2020_S1;
    public static final CloudItem CAPE_SUPPORTER;
    public static final CloudItem CAPE_COUNTERCRAFT;
    public static final CloudItem CAPE_DAWAE;
    public static final CloudItem CAPE_BOEHMOD;
    public static final CloudItem CAPE_POGCHAMP;
    public static final CloudItem CAPE_PEPEDANK;
    public static final CloudItem CAPE_MCGA;
    public static final CloudItem CAPE_PUMPKIN;
    public static final CloudItem CARD_DEFAULT;
    public static final CloudItem CARD_NATION_GB;
    public static final CloudItem CARD_NATION_USA;
    public static final CloudItem CARD_NATION_RUS;
    public static final CloudItem CARD_NATION_CAN;
    public static final CloudItem CARD_NATION_BR;
    public static final CloudItem CARD_NATION_AU;
    public static final CloudItem CARD_NATION_NZ;
    public static final CloudItem CARD_NATION_CN;
    public static final CloudItem CARD_NATION_HK;
    public static final CloudItem CARD_NATION_SCOT;
    public static final CloudItem CARD_NATION_ENG;
    public static final CloudItem CARD_NATION_IE;
    public static final CloudItem CARD_NATION_UA;
    public static final CloudItem CARD_NATION_FR;
    public static final CloudItem CARD_NATION_GER;
    public static final CloudItem CARD_NATION_JP;
    public static final CloudItem CARD_NATION_ES;
    public static final CloudItem CARD_CUSTOM_SLIME_GHOST;
    public static final CloudItem CARD_CUSTOM_FLOWER_WARFARE;
    public static final CloudItem CARD_CUSTOM_FLOWER_POWER;
    public static final CloudItem CARD_CUSTOM_CARTOON_BOOM;
    public static final CloudItem CARD_CUSTOM_CARTOON_HEY;
    public static final CloudItem CARD_CUSTOM_CARTOON_OH;
    public static final CloudItem CARD_CUSTOM_CARTOON_OOPS;
    public static final CloudItem CARD_CUSTOM_CARTOON_SMILE;
    public static final CloudItem CARD_CUSTOM_CARTOON_WOW;
    public static final CloudItem CARD_CUSTOM_CARTOON_WTF;
    public static final CloudItem CARD_CUSTOM_CHILI_PEPPER;
    public static final CloudItem CARD_CUSTOM_WITCH_KITTY;
    public static final CloudItem CARD_CUSTOM_PURPLE_NINJA;
    public static final CloudItem CARD_CUSTOM_GAME_OVER;
    public static final CloudItem CARD_CUSTOM_SKULL_PATRIOT;
    public static final CloudItem CARD_CUSTOM_GAMER_FLARE;
    public static final CloudItem CARD_CUSTOM_GREEN_VIP;
    public static final CloudItem CARD_CUSTOM_RANGER;
    public static final CloudItem CARD_CUSTOM_INSERTION;
    public static final CloudItem CARD_CUSTOM_SOLDIER_SILHOUETTE;
    public static final CloudItem CARD_NATION_BE;
    public static final CloudItem CASE_KEY_ALPHA;
    public static final CloudItem CASE_KEY_ALPHA2;
    public static final CloudItem CASE_KEY_ALPHA3;
    public static final CloudItem CASE_KEY_HAT;
    public static final CloudItem CASE_KEY_HALLOWEEN1;
    public static final CloudItem CASE_KEY_EXO;
    protected CloudItemRarity value;
    protected String filterInventoryType;
    protected String filterMarketType;
    protected String author;
    protected boolean isDefault;
    protected boolean isTradable;
    protected boolean isSellable;
    protected boolean isShowcaseable;
    protected boolean isNameable;
    protected boolean isContractable;
    protected String itemCollection;
    protected int tradeContractWeight;
    private int id;
    private int mcId;
    private String name;
    private String suffix;
    private String sticker0;
    private String sticker1;
    private String sticker2;

    public CloudItem(int ccID, int mcID, String itemName) {
        this.value = CloudItemRarity.DEFAULT;
        this.filterInventoryType = "-";
        this.filterMarketType = "-";
        this.author = "CounterCraft";
        this.isDefault = false;
        this.isTradable = true;
        this.isSellable = true;
        this.isShowcaseable = true;
        this.isNameable = true;
        this.isContractable = true;
        this.itemCollection = null;
        this.tradeContractWeight = 20;
        this.suffix = "none";
        this.sticker0 = "none";
        this.sticker1 = "none";
        this.sticker2 = "none";
        this.id = ccID;
        this.mcId = mcID + 256;
        this.name = itemName;
        itemList[ccID] = this;
    }

    public static CloudItem getCloudItemFromId(int par1) {
        CloudItem[] arr$ = itemList;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            CloudItem item = arr$[i$];
            if (item != null && item.id == par1) {
                return item;
            }
        }

        return null;
    }

    public void addInformation(CloudItemStack par1, ArrayList<String> par2) {
        par2.add(this.name);
        if (this.suffix != null && !this.suffix.equals("none")) {
            par2.add(this.value.chatColor + this.suffix);
        } else {
            par2.add("");
        }

        if (this.sticker0 != null && !this.sticker0.equals("none")) {
            par2.add("Sticker: " + this.value.chatColor + this.sticker0);
        }

        if (this.sticker1 != null && !this.sticker1.equals("none")) {
            par2.add("Sticker: " + this.value.chatColor + this.sticker1);
        }

        if (this.sticker2 != null && !this.sticker2.equals("none")) {
            par2.add("Sticker: " + this.value.chatColor + this.sticker2);
        }

        par2.add(this.value.chatColor + "" + EnumChatFormatting.ITALIC + this.value.rarityName);
    }

    public CloudItem setFilterIT(String par1) {
        this.filterInventoryType = par1;
        return this;
    }

    public CloudItem setFilterMT(String par1) {
        this.filterMarketType = par1;
        return this;
    }

    public CloudItem setDefault() {
        this.isDefault = true;
        return this;
    }

    public CloudItem disableTrading() {
        this.isTradable = false;
        return this;
    }

    public CloudItem disableSelling() {
        this.isSellable = false;
        return this;
    }

    public CloudItem disableShowcase() {
        this.isShowcaseable = false;
        return this;
    }

    public CloudItem disableNameable() {
        this.isNameable = false;
        return this;
    }

    public CloudItem disableContracts() {
        this.isContractable = false;
        return this;
    }

    public CloudItem setTradeupWeight(int par1) {
        this.tradeContractWeight = Math.min(par1, 20);
        return this;
    }

    public String getMarketType() {
        return this.filterMarketType;
    }

    public String getInventoryType() {
        return this.filterInventoryType;
    }

    public String getCollection() {
        return this.itemCollection;
    }

    public CloudItem setCollection(String par1) {
        this.itemCollection = par1;
        return this;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public CloudItem setSuffix(String par1) {
        this.suffix = par1;
        return this;
    }

    public CloudItem setSticker(String par1, int givenPosition) {
        switch (givenPosition) {
            case 0:
                this.sticker0 = par1;
                break;
            case 1:
                this.sticker1 = par1;
                break;
            case 2:
                this.sticker2 = par1;
        }

        return this;
    }

    public String getAuthor() {
        return this.author;
    }

    public CloudItem setAuthor(String par1) {
        this.author = par1;
        return this;
    }

    public boolean isTradable() {
        return this.isTradable;
    }

    public boolean isSellable() {
        return this.isSellable;
    }

    public boolean isShowcaseable() {
        return this.isShowcaseable;
    }

    public boolean isNameable() {
        return this.isNameable;
    }

    public boolean isContrableAble() {
        return this.isContractable;
    }

    public String getDisplayName() {
        String name = this.getName();
        String suffix = this.getSuffix();
        return !suffix.equals("none") ? name + " " + suffix : name;
    }

    public int getMCItemID() {
        return this.mcId;
    }

    public CloudItemRarity getValue() {
        return this.value;
    }

    public CloudItem setValue(CloudItemRarity par1) {
        this.value = par1;
        return this;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public String getSticker(int givenID) {
        switch (givenID) {
            case 0:
                return this.sticker0;
            case 1:
                return this.sticker1;
            case 2:
                return this.sticker2;
            default:
                return this.sticker0;
        }
    }

    static {
        M4A4_WAVES = (new CloudItemGun(101, 4000, "M4A4")).setAuthor("JasonPlayGame").setSuffix("Waves").setCollection("Alpha").setValue(CloudItemRarity.TIER2).setFilterMT("rifle");
        M4A4_EVILDAIMYO = (new CloudItemGun(102, 4000, "M4A4")).setAuthor("Nanta18").setSuffix("EvilDaimyo").setCollection("Alpha").setValue(CloudItemRarity.TIER4).setFilterMT("rifle");
        M4A4_CAKEY = (new CloudItemGun(103, 4000, "M4A4")).setAuthor("Icey_Eclipse").setSuffix("Cakey").setCollection("Alpha2").setValue(CloudItemRarity.TIER2).setFilterMT("rifle");
        M4A4_HOWL = (new CloudItemGun(104, 4000, "M4A4")).setAuthor("ABrokenHeart").setSuffix("Howl").setCollection("Alpha3").setValue(CloudItemRarity.TIER6).setFilterMT("rifle");
        M4A4_NERFED = (new CloudItemGun(105, 4000, "M4A4")).setAuthor("blockbuster4664").setSuffix("Nerfed").setCollection("Alpha3").setValue(CloudItemRarity.TIER2).setFilterMT("rifle");
        M4A4_PUMPKIN = (new CloudItemGun(106, 4000, "M4A4")).setAuthor("Luigi_MC").setSuffix("Pumpkin").setCollection("Halloween1").setValue(CloudItemRarity.TIER4).setFilterMT("rifle");
        M4A4_PATRIOT = (new CloudItemGun(107, 4000, "M4A4")).setAuthor("BreakingOrdinary").setSuffix("Patriot").setCollection("Exo").setValue(CloudItemRarity.TIER3).setFilterMT("rifle");
        AK47 = (new CloudItemGun(200, 4001, "AK47")).setDefault().setFilterMT("rifle");
        AK47_PROPAGANDA = (new CloudItemGun(201, 4001, "AK47")).setAuthor("ABrokenHeart").setSuffix("Propaganda").setCollection("Alpha").setValue(CloudItemRarity.TIER4).setFilterMT("rifle");
        AK47_ASMO = (new CloudItemGun(202, 4001, "AK47")).setAuthor("Tylerftw22").setSuffix("Asmo").setCollection("Alpha").setValue(CloudItemRarity.TIER5).setFilterMT("rifle");
        AK47_NEONREVOLUTION = (new CloudItemGun(203, 4001, "AK47")).setAuthor("BGcreeper101").setSuffix("Neon Revolution").setCollection("Alpha2").setValue(CloudItemRarity.TIER4).setFilterMT("rifle");
        AK47_HAUNTED = (new CloudItemGun(204, 4001, "AK47")).setAuthor("EV7").setSuffix("Haunted").setCollection("Halloween1").setValue(CloudItemRarity.TIER5).setFilterMT("rifle");
        AK47_BLOODSPORT = (new CloudItemGun(205, 4001, "AK47")).setAuthor("J0nks").setSuffix("Bloodsport").setCollection("Exo").setValue(CloudItemRarity.TIER2).setFilterMT("rifle");
        AK47_FOX = (new CloudItemGun(206, 4001, "AK47")).setAuthor("ABrokenHeart").setSuffix("Fox").setCollection("Exo").setValue(CloudItemRarity.TIER3).setFilterMT("rifle");
        FAMAS = (new CloudItemGun(300, 4002, "Famas")).setDefault().setFilterMT("rifle");
        FAMAS_AKARI = (new CloudItemGun(301, 4002, "Famas")).setAuthor("JasonPlayGame").setSuffix("Akari").setCollection("Alpha2").setValue(CloudItemRarity.TIER3).setFilterMT("rifle");
        FAMAS_WARHAWK = (new CloudItemGun(302, 4002, "Famas")).setAuthor("J0nks").setSuffix("Warhawk").setCollection("Alpha3").setValue(CloudItemRarity.TIER5).setFilterMT("rifle");
        FAMAS_SPOOKY = (new CloudItemGun(303, 4002, "Famas")).setAuthor("GonGic").setSuffix("Spooky").setCollection("Halloween1").setValue(CloudItemRarity.TIER3).setFilterMT("rifle");
        FNFAL = (new CloudItemGun(400, 4003, "FN FAL")).setDefault().setFilterMT("rifle");
        FNFAL_VAPORWAVE = (new CloudItemGun(401, 4003, "FN FAL")).setAuthor("RishFish").setSuffix("Vaporwave").setCollection("Alpha3").setValue(CloudItemRarity.TIER4).setFilterMT("rifle");
        FNFAL_ASMO = (new CloudItemGun(402, 4003, "FN FAL")).setAuthor("BreakingOrdinary").setSuffix("Asmo").setCollection("Exo").setValue(CloudItemRarity.TIER4).setFilterMT("rifle");
        GALIL = (new CloudItemGun(450, 4004, "Galil AR")).setDefault().setAuthor("Egglanoir").setFilterMT("rifle");
        M1911 = (new CloudItemGun(500, 4010, "M1911")).setDefault().setFilterMT("pistol");
        M1911_DRAGON = (new CloudItemGun(501, 4010, "M1911")).setAuthor("ABrokenHeart").setSuffix("Dragon").setCollection("Alpha3").setValue(CloudItemRarity.TIER3).setFilterMT("pistol");
        M1911_TUXEDO = (new CloudItemGun(502, 4010, "M1911")).setAuthor("FluffyFoxey").setSuffix("Tuxedo").setCollection("Halloween1").setValue(CloudItemRarity.TIER2).setFilterMT("pistol");
        G18 = (new CloudItemGun(600, 4011, "G18")).setDefault().setFilterMT("pistol");
        G18_RACE = (new CloudItemGun(601, 4011, "G18")).setAuthor("LauraDarkez").setSuffix("Race").setCollection("Alpha3").setValue(CloudItemRarity.TIER2).setFilterMT("pistol");
        G18_HIGHROLLER = (new CloudItemGun(602, 4011, "G18")).setAuthor("D3athClawPL").setSuffix("Highroller").setCollection("Halloween1").setValue(CloudItemRarity.TIER5).setFilterMT("pistol");
        G18_DEEP_BLUE = (new CloudItemGun(603, 4011, "G18")).setAuthor("FluffyFoxey").setSuffix("Deep Blue").setCollection("Halloween1").setValue(CloudItemRarity.TIER2).setFilterMT("pistol");
        TEC9 = (new CloudItemGun(650, 4014, "TEC9")).setDefault().setFilterMT("pistol");
        TEC9_QUAKORONIC = (new CloudItemGun(651, 4014, "TEC9")).setAuthor("BuLgUr").setSuffix("Quakoronic").setCollection("Alpha2").setValue(CloudItemRarity.TIER2).setFilterMT("pistol");
        TEC9_ECO = (new CloudItemGun(652, 4014, "TEC9")).setAuthor("iPeaceful").setSuffix("Eco").setCollection("Exo").setValue(CloudItemRarity.TIER2).setFilterMT("pistol");
        TEC9_AQUARIUM = (new CloudItemGun(653, 4014, "TEC9")).setAuthor("ABrokenHeart").setSuffix("Aquarium").setCollection("Exo").setValue(CloudItemRarity.TIER5).setFilterMT("pistol");
        DEAGLE = (new CloudItemGun(700, 4012, "Deagle")).setDefault().setFilterMT("pistol");
        DEAGLE_NIGHTRAZER = (new CloudItemGun(701, 4012, "Deagle")).setAuthor("BGcreeper101").setSuffix("NightRazer").setCollection("Alpha").setValue(CloudItemRarity.TIER3).setFilterMT("pistol");
        DEAGLE_DEADHORIZON = (new CloudItemGun(702, 4012, "Deagle")).setAuthor("Coghead").setSuffix("Dead Horizon").setCollection("Alpha3").setValue(CloudItemRarity.TIER4).setFilterMT("pistol");
        DEAGLE_GRIN = (new CloudItemGun(703, 4012, "Deagle")).setAuthor("NonGic").setSuffix("Grin").setCollection("Halloween1").setValue(CloudItemRarity.TIER3).setFilterMT("pistol");
        P250 = (new CloudItemGun(750, 4013, "P250")).setDefault().setFilterMT("pistol");
        P250_IRRADIATED = (new CloudItemGun(751, 4013, "P250")).setAuthor("RishFish").setSuffix("Irradiated").setCollection("Alpha").setValue(CloudItemRarity.TIER2).setFilterMT("pistol");
        P250_SEEYALATER = (new CloudItemGun(752, 4013, "P250")).setAuthor("ABrokenHeart").setSuffix("SeeYaLater").setCollection("Alpha2").setValue(CloudItemRarity.TIER5).setFilterMT("pistol");
        P250_CITY = (new CloudItemGun(753, 4013, "P250")).setAuthor("J0nks").setSuffix("City").setCollection("Alpha3").setValue(CloudItemRarity.TIER2).setFilterMT("pistol");
        P250_BEAST = (new CloudItemGun(754, 4013, "P250")).setAuthor("ABrokenHeart").setSuffix("Beast").setCollection("Exo").setValue(CloudItemRarity.TIER4).setFilterMT("pistol");
        P90 = (new CloudItemGun(800, 4020, "P90")).setDefault().setFilterMT("smg");
        P90_RUST = (new CloudItemGun(801, 4020, "P90")).setAuthor("Zoot").setSuffix("Rust").setCollection("Alpha").setValue(CloudItemRarity.TIER2).setFilterMT("smg");
        P90_KRAKEN = (new CloudItemGun(802, 4020, "P90")).setAuthor("ABrokenHeart").setSuffix("Kraken").setCollection("Alpha2").setValue(CloudItemRarity.TIER4).setFilterMT("smg");
        MAC10 = (new CloudItemGun(900, 4021, "MAC10")).setDefault().setFilterMT("smg");
        MAC10_RACE = (new CloudItemGun(901, 4021, "MAC10")).setAuthor("StolyLP").setSuffix("Race").setCollection("Alpha2").setValue(CloudItemRarity.TIER2).setFilterMT("smg");
        MAC10_PUMPKIN = (new CloudItemGun(902, 4021, "MAC10")).setAuthor("Nanta18").setSuffix("Pumpkin").setCollection("Halloween1").setValue(CloudItemRarity.TIER3).setFilterMT("smg");
        VECTOR = (new CloudItemGun(1000, 4022, "Vector")).setDefault().setFilterMT("smg");
        VECTOR_CIRCUIT = (new CloudItemGun(1001, 4022, "Vector")).setAuthor("Nanta18").setSuffix("Circuit").setCollection("Alpha1").setValue(CloudItemRarity.TIER2).setFilterMT("smg");
        VECTOR_BLOODYEQUATION = (new CloudItemGun(1002, 4022, "Vector")).setAuthor("Youpi").setSuffix("Bloody Equation").setCollection("Alpha3").setValue(CloudItemRarity.TIER2).setFilterMT("smg");
        AWP = (new CloudItemGun(1100, 4030, "AWP")).setDefault().setFilterMT("sniper");
        AWP_DRAGON = (new CloudItemGun(1101, 4030, "AWP")).setAuthor("ABrokenHeart").setSuffix("Dragon").setCollection("Alpha").setValue(CloudItemRarity.TIER6).setFilterMT("sniper");
        AWP_MANOWAR = (new CloudItemGun(1102, 4030, "AWP")).setAuthor("YCGamez").setSuffix("Man O War").setCollection("Alpha2").setValue(CloudItemRarity.TIER3).setFilterMT("sniper");
        AWP_ASMO = (new CloudItemGun(1103, 4030, "AWP")).setAuthor("Nanta18").setSuffix("Asmo").setCollection("Alpha3").setValue(CloudItemRarity.TIER5).setFilterMT("sniper");
        AWP_BONES = (new CloudItemGun(1104, 4030, "AWP")).setAuthor("Nanta18").setSuffix("Bones").setCollection("Halloween1").setValue(CloudItemRarity.TIER4).setFilterMT("sniper");
        AWP_REDLINE = (new CloudItemGun(1105, 4030, "AWP")).setAuthor("SandbergPhilip").setSuffix("Redline").setCollection("Exo").setValue(CloudItemRarity.TIER2).setFilterMT("sniper");
        AWP_BOOM = (new CloudItemGun(1106, 4030, "AWP")).setAuthor("ABrokenHeart").setSuffix("Boom").setCollection("Exo").setValue(CloudItemRarity.TIER4).setFilterMT("sniper");
        DRAGUNOV = (new CloudItemGun(1200, 4031, "Dragunov")).setDefault().setFilterMT("sniper");
        DRAGUNOV_SKY = (new CloudItemGun(1201, 4031, "Dragunov")).setAuthor("ABrokenHeart").setSuffix("Sky").setCollection("Alpha").setValue(CloudItemRarity.TIER2).setFilterMT("sniper");
        DRAGUNOV_STEAMPUNK = (new CloudItemGun(1202, 4031, "Dragunov")).setAuthor("Lehawk10").setSuffix("Steampunk").setCollection("Exo").setValue(CloudItemRarity.TIER3).setFilterMT("sniper");
        SCAR20 = (new CloudItemGun(1300, 4032, "Scar20")).setDefault().setFilterMT("sniper");
        SCAR20_CYREX = (new CloudItemGun(1301, 4032, "Scar20")).setAuthor("_Void_YT").setSuffix("Cyrex").setCollection("Alpha").setValue(CloudItemRarity.TIER3).setFilterMT("sniper");
        SCAR20_UNICORN = (new CloudItemGun(1302, 4032, "Scar20")).setAuthor("Overrkill").setSuffix("Unicorn").setCollection("Alpha2").setValue(CloudItemRarity.TIER5).setFilterMT("sniper");
        SSG08 = (new CloudItemGun(1400, 4033, "SSG-08")).setDefault().setAuthor("CreeprfighterYTC").setFilterMT("sniper");
        KNIFE_TAC = (new CloudItemKnife(1500, 4200, "Tac Knife")).setDefault().setFilterMT("knife");
        KNIFE_TAC_DISPLAY = (new CloudItemKnife(1501, 4201, "Special")).setSuffix("Item").setValue(CloudItemRarity.TIER6);
        KNIFE_TAC_MARBLE = (new CloudItemKnife(1502, 4202, "Tac Knife")).setSuffix("Marble").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_TAC_TIGER = (new CloudItemKnife(1503, 4203, "Tac Knife")).setSuffix("Tiger").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_TAC_STEEL = (new CloudItemKnife(1504, 4204, "Tac Knife")).setSuffix("Steel").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_TAC_DOPPLAR = (new CloudItemKnife(1505, 4205, "Tac Knife")).setSuffix("Doppler").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_TAC_LORE = (new CloudItemKnife(1506, 4206, "Tac Knife")).setSuffix("Lore").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_TAC_GAMMA = (new CloudItemKnife(1507, 4207, "Tac Knife")).setSuffix("Gamma").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY = (new CloudItemKnife(1510, 4210, "Bayonet")).setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY_MARBLE = (new CloudItemKnife(1511, 4211, "Bayonet")).setSuffix("Marble").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY_TIGER = (new CloudItemKnife(1512, 4212, "Bayonet")).setSuffix("Tiger").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY_STEEL = (new CloudItemKnife(1513, 4213, "Bayonet")).setSuffix("Steel").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY_DOPPLAR = (new CloudItemKnife(1514, 4214, "Bayonet")).setSuffix("Doppler").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY_LORE = (new CloudItemKnife(1515, 4215, "Bayonet")).setSuffix("Lore").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY_GAMMA = (new CloudItemKnife(1516, 4216, "Bayonet")).setSuffix("Gamma").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BAY_DRAGONLORE = (new CloudItemKnife(1517, 4217, "Bayonet")).setAuthor("strelski12").setSuffix("Dragonlore").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR = (new CloudItemKnife(1520, 4220, "Karambit")).setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR_MARBLE = (new CloudItemKnife(1521, 4221, "Karambit")).setSuffix("Marble").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR_TIGER = (new CloudItemKnife(1522, 4222, "Karambit")).setSuffix("Tiger").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR_STEEL = (new CloudItemKnife(1523, 4223, "Karambit")).setSuffix("Steel").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR_DOPPLAR = (new CloudItemKnife(1524, 4224, "Karambit")).setSuffix("Doppler").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR_LORE = (new CloudItemKnife(1525, 4225, "Karambit")).setSuffix("Lore").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR_GAMMA = (new CloudItemKnife(1526, 4226, "Karambit")).setSuffix("Gamma").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_KAR_EAGLE = (new CloudItemKnife(1527, 4227, "Karambit")).setSuffix("Eagle").setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        KNIFE_BUTTERFLY = (new CloudItemKnife(1530, 4230, "Butterfly")).setValue(CloudItemRarity.TIER5).setFilterMT("knife");
        NOVA = (new CloudItemGun(1600, 4040, "Nova")).setDefault().setAuthor("Lizardpile").setFilterMT("heavy");
        NOVA_OXIDEBLAZE = (new CloudItemGun(1601, 4040, "Nova")).setAuthor("Greemon").setSuffix("Oxide Blaze").setCollection("Alpha2").setValue(CloudItemRarity.TIER2).setFilterMT("heavy");
        NOVA_HYPER_BEAST = (new CloudItemGun(1602, 4040, "Nova")).setAuthor("ABrokenHeart").setSuffix("Hyper Beast").setCollection("Exo").setValue(CloudItemRarity.TIER5).setFilterMT("heavy");
        SAWEDOFF = (new CloudItemGun(1650, 4041, "Sawed-Off")).setDefault().setAuthor("Lizardpile").setFilterMT("heavy");
        SAWEDOFF_WASTELANDPRINCESS = (new CloudItemGun(1651, 4041, "Sawed-Off")).setAuthor("ABrokenHeart").setSuffix("WastelandPrincess").setCollection("Alpha").setValue(CloudItemRarity.TIER4).setFilterMT("heavy");
        SAWEDOFF_KRAKEN = (new CloudItemGun(1652, 4041, "Sawed-Off")).setAuthor("ABrokenHeart").setSuffix("Kraken").setCollection("Alpha2").setValue(CloudItemRarity.TIER3).setFilterMT("heavy");
        SAWEDOFF_MIDNIGHT = (new CloudItemGun(1653, 4041, "Sawed-Off")).setAuthor("_SquidMan_").setSuffix("Midnight").setCollection("Exo").setValue(CloudItemRarity.TIER2).setFilterMT("heavy");
        MAG7 = (new CloudItemGun(1700, 4042, "Mag-7")).setDefault().setAuthor("Lizardpile").setFilterMT("heavy");
        MAG7_ROYALFIRE = (new CloudItemGun(1701, 4042, "Mag-7")).setAuthor("Nanta18").setSuffix("Royal Fire").setCollection("Alpha").setValue(CloudItemRarity.TIER3).setFilterMT("heavy");
        MAG7_VAMPIRE = (new CloudItemGun(1702, 4042, "Mag-7")).setAuthor("Egglanoir").setSuffix("Vampire").setCollection("Alpha").setValue(CloudItemRarity.TIER4).setFilterMT("heavy");
        M249 = (new CloudItemGun(1750, 4043, "M249")).setDefault().setAuthor("Lizardpile").setFilterMT("heavy");
        M249_NEON = (new CloudItemGun(1751, 4043, "M249")).setAuthor("CogHead").setSuffix("Neon").setCollection("Alpha").setValue(CloudItemRarity.TIER3).setFilterMT("heavy");
        M249_GHOST = (new CloudItemGun(1752, 4043, "M249")).setAuthor("Egglanoir").setSuffix("Ghost").setCollection("Halloween1").setValue(CloudItemRarity.TIER2).setFilterMT("heavy");
        CZ75 = (new CloudItemGun(1800, 4015, "CZ75")).setDefault().setAuthor("CreeprfighterYTC").setFilterMT("pistol");
        CZ75_PHANTOM = (new CloudItemGun(1801, 4015, "CZ75")).setAuthor("Nanta18").setSuffix("Phantom").setCollection("Alpha3").setValue(CloudItemRarity.TIER4).setFilterMT("pistol");
        UMP45 = (new CloudItemGun(1850, 4023, "UMP-45")).setDefault().setAuthor("CreeprfighterYTC").setFilterMT("smg");
        UMP45_PRIMAL = (new CloudItemGun(1851, 4023, "UMP-45")).setAuthor("ABrokenHeart").setSuffix("Primal").setCollection("Alpha3").setValue(CloudItemRarity.TIER3).setFilterMT("smg");
        UMP45_EDIFICE = (new CloudItemGun(1852, 4023, "UMP-45")).setAuthor("EV7").setSuffix("Edifice").setCollection("Halloween1").setValue(CloudItemRarity.TIER2).setFilterMT("smg");
        FIVESEVEN = (new CloudItemGun(1815, 4016, "Fiveseven")).setDefault().setAuthor("ABrokenHeart").setFilterMT("pistol");
        R8 = (new CloudItemGun(1826, 4017, "R8")).setDefault().setAuthor("ABrokenHeart").setFilterMT("pistol");
        R8_FADE = (new CloudItemGun(1827, 4017, "R8")).setAuthor("JasonPlayGame").setSuffix("Fade").setCollection("Alpha").setValue(CloudItemRarity.TIER5).setFilterMT("pistol");
        R8_GOLD = (new CloudItemGun(1828, 4017, "R8")).setAuthor("LauraDarkez").setSuffix("Gold").setCollection("Alpha3").setValue(CloudItemRarity.TIER3).setFilterMT("pistol");
        HAT_TOPHAT = (new CloudItemHat(1900, 4400, "Top Hat")).setValue(CloudItemRarity.TIER3).setFilterMT("hat").disableContracts();
        HAT_CATHAT = (new CloudItemHat(1901, 4401, "Cat's Hat")).setValue(CloudItemRarity.TIER5).setFilterMT("hat").disableContracts();
        HAT_HARDRED = (new CloudItemHat(1902, 4402, "Hardhat")).setSuffix("Red").setValue(CloudItemRarity.TIER1).setFilterMT("hat").disableContracts();
        HAT_HARDORANGE = (new CloudItemHat(1903, 4403, "Hardhat")).setSuffix("Orange").setValue(CloudItemRarity.TIER1).setFilterMT("hat").disableContracts();
        HAT_HARDYELLOW = (new CloudItemHat(1904, 4404, "Hardhat")).setSuffix("Yellow").setValue(CloudItemRarity.TIER1).setFilterMT("hat").disableContracts();
        HAT_VIKING = (new CloudItemHat(1905, 4405, "Viking")).setSuffix("Helmet").setValue(CloudItemRarity.TIER4).setFilterMT("hat").disableContracts();
        HAT_FIREMAN = (new CloudItemHat(1906, 4406, "Fireman")).setSuffix("Hat").setValue(CloudItemRarity.TIER2).setFilterMT("hat").disableContracts();
        HAT_FIREMANCHIEF = (new CloudItemHat(1907, 4407, "Fire Chief")).setSuffix("Hat").setValue(CloudItemRarity.TIER2).setFilterMT("hat").disableContracts();
        HAT_TV = (new CloudItemHat(1908, 4408, "TV Hat")).setValue(CloudItemRarity.TIER3).disableContracts();
        WC_ALPHA = (new CloudItemCase(2000, 4100, "Alpha", 4500)).addContent(KNIFE_TAC_DISPLAY, 0).addContent(AWP_DRAGON, 0).addContent(AK47_ASMO, 1).addContent(R8_FADE, 1).addContent(SAWEDOFF_WASTELANDPRINCESS, 4).addContent(M4A4_EVILDAIMYO, 4).addContent(AK47_PROPAGANDA, 10).addContent(M249_NEON, 10).addContent(DEAGLE_NIGHTRAZER, 20).addContent(MAG7_ROYALFIRE, 40).addContent(SCAR20_CYREX, 60).addContent(P90_RUST, 80).addContent(VECTOR_CIRCUIT, 100).addContent(M4A4_WAVES, 110).addContent(DRAGUNOV_SKY, 110).addContent(P250_IRRADIATED, 110).setSuffix("Case").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        WC_ALPHA2 = (new CloudItemCase(2001, 4101, "Alpha 2", 4501)).addContent(KNIFE_TAC_DISPLAY, 0).addContent(SCAR20_UNICORN, 1).addContent(P250_SEEYALATER, 1).addContent(AK47_NEONREVOLUTION, 4).addContent(P90_KRAKEN, 4).addContent(FAMAS_AKARI, 10).addContent(SAWEDOFF_KRAKEN, 10).addContent(AWP_MANOWAR, 20).addContent(M4A4_CAKEY, 40).addContent(MAC10_RACE, 60).addContent(TEC9_QUAKORONIC, 80).addContent(NOVA_OXIDEBLAZE, 100).setSuffix("Case").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        WC_ALPHA3 = (new CloudItemCase(2002, 4102, "Alpha 3", 4502)).addContent(KNIFE_TAC_DISPLAY, 0).addContent(M4A4_HOWL, 1).addContent(AWP_ASMO, 1).addContent(FAMAS_WARHAWK, 4).addContent(FNFAL_VAPORWAVE, 4).addContent(CZ75_PHANTOM, 10).addContent(DEAGLE_DEADHORIZON, 10).addContent(R8_GOLD, 20).addContent(UMP45_PRIMAL, 40).addContent(M1911_DRAGON, 60).addContent(P250_CITY, 80).addContent(VECTOR_BLOODYEQUATION, 85).addContent(G18_RACE, 90).addContent(M4A4_NERFED, 100).setSuffix("Case").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        HC_ALPHA1 = (new CloudItemCase(2003, 4103, "Hats Alpha", 4503)).addContent(HAT_CATHAT, 6).addContent(HAT_VIKING, 15).addContent(HAT_TV, 30).addContent(HAT_TOPHAT, 30).addContent(HAT_FIREMAN, 80).addContent(HAT_FIREMANCHIEF, 80).addContent(HAT_HARDRED, 110).addContent(HAT_HARDORANGE, 110).addContent(HAT_HARDYELLOW, 110).setSuffix("Case").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        WC_HALLOWEEN1 = (new CloudItemCase(2004, 4104, "Halloween", 4504)).addContent(KNIFE_TAC_DISPLAY, 0).addContent(AK47_HAUNTED, 1).addContent(G18_HIGHROLLER, 1).addContent(AWP_BONES, 4).addContent(MAG7_VAMPIRE, 4).addContent(M4A4_PUMPKIN, 10).addContent(MAC10_PUMPKIN, 10).addContent(DEAGLE_GRIN, 20).addContent(FAMAS_SPOOKY, 40).addContent(G18_DEEP_BLUE, 60).addContent(M249_GHOST, 80).addContent(M1911_TUXEDO, 85).addContent(UMP45_EDIFICE, 100).setSuffix("Case").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        WC_EXO = (new CloudItemCase(2005, 4105, "Exo", 4505)).addContent(KNIFE_TAC_DISPLAY, 0).addContent(NOVA_HYPER_BEAST, 5).addContent(TEC9_AQUARIUM, 10).addContent(P250_BEAST, 20).addContent(FNFAL_ASMO, 30).addContent(AWP_BOOM, 40).addContent(DRAGUNOV_STEAMPUNK, 50).addContent(M4A4_PATRIOT, 50).addContent(AK47_FOX, 60).addContent(AWP_REDLINE, 70).addContent(AK47_BLOODSPORT, 80).addContent(TEC9_ECO, 90).addContent(SAWEDOFF_MIDNIGHT, 90).setSuffix("Case").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        TRADE_CONTRACT = (new CloudItem(2040, 4450, "Trade")).setSuffix("Contract").setDefault();
        NAME_TAG = (new CloudItem(2041, 4451, "Name Tag")).setSuffix("").setFilterMT("misc").setValue(CloudItemRarity.TIER1).disableNameable().disableShowcase().disableContracts();
        COIN_BETA = (new CloudItemCoin(2050, 4460, "Coin")).setSuffix("BETA Age").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_ALPHA = (new CloudItemCoin(2051, 4461, "Coin")).setSuffix("ALPHA Age").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_DISCORD = (new CloudItemCoin(2052, 4462, "Coin")).setSuffix("Discord").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_SKINNER = (new CloudItemCoin(2053, 4463, "Coin")).setSuffix("Skinning").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_DEV = (new CloudItemCoin(2054, 4464, "Coin")).setSuffix("Developer").setValue(CloudItemRarity.TIER6).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_STAFF = (new CloudItemCoin(2055, 4465, "Coin")).setSuffix("Staffing").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_SUPPORTER = (new CloudItemCoin(2056, 4466, " Coin")).setSuffix("Supporter").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_MODELER = (new CloudItemCoin(2057, 4467, "Coin")).setSuffix("Modelling").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_PRESTIGE = (new CloudItemCoin(2058, 4468, "Coin")).setSuffix("Prestige").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_ASSETDEV = (new CloudItemCoin(2059, 4469, "Coin")).setSuffix("Asset Dev").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_FUZE = (new CloudItemCoin(2060, 4470, "Coin")).setSuffix("Team Fuze").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_ALXTON = (new CloudItemCoin(2061, 4471, "Coin")).setSuffix("Team Alxton").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_JEROME = (new CloudItemCoin(2062, 4472, "Coin")).setSuffix("Team Jerome").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_PEDRO = (new CloudItemCoin(2063, 4473, "Coin")).setSuffix("Team Pedro").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_EDGE = (new CloudItemCoin(2064, 4474, "Coin")).setSuffix("Team CreepersEdge").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_JABUTI = (new CloudItemCoin(2065, 4475, "Coin")).setSuffix("Team Jabuti").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_INEMAFOO = (new CloudItemCoin(2066, 4476, "Coin")).setSuffix("Team Inemafoo").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_MAPPER = (new CloudItemCoin(2067, 4477, "Coin")).setSuffix("Mapper").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_NITRO = (new CloudItemCoin(2068, 4478, "Coin")).setSuffix("Nitro").setValue(CloudItemRarity.TIER6).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_BATTLEFIELDS = (new CloudItemCoin(2069, 4479, "Coin")).setSuffix("Battlefields").setValue(CloudItemRarity.TIER3).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_CCLEAGUE_BRONZE = (new CloudItemCoin(2070, 4480, "Coin")).setSuffix("CCL Bronze").setValue(CloudItemRarity.TIER6).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_CCLEAGUE_SILVER = (new CloudItemCoin(2071, 4481, "Coin")).setSuffix("CCL Silver").setValue(CloudItemRarity.TIER6).disableNameable().disableSelling().disableTrading().disableContracts();
        COIN_CCLEAGUE_GOLD = (new CloudItemCoin(2072, 4482, "Coin")).setSuffix("CCL Gold").setValue(CloudItemRarity.TIER6).disableNameable().disableSelling().disableTrading().disableContracts();
        BOOSTER_EXP_2 = (new CloudItemBooster(2100, 4500, "x2 EXP")).setSuffix("30m Boost").setValue(CloudItemRarity.TIER5).setCollection("Booster").disableNameable().disableSelling().disableTrading().disableContracts().disableShowcase();
        BOOSTER_EXP_3 = (new CloudItemBooster(2101, 4501, "x3 EXP")).setSuffix("30m Boost").setValue(CloudItemRarity.TIER5).setCollection("Booster").disableNameable().disableSelling().disableTrading().disableContracts().disableShowcase();
        BOOSTER_EMD_2 = (new CloudItemBooster(2102, 4502, "x2 Emerald")).setSuffix("30m Boost").setValue(CloudItemRarity.TIER5).setCollection("Booster").disableNameable().disableSelling().disableTrading().disableContracts().disableShowcase();
        STICKER_CAMP = (new CloudItemSticker(2200, 4550, "Sticker")).setStickerTextureName("stickercamper").setSuffix("Camper").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CHICKEN_STRIKE = (new CloudItemSticker(2201, 4551, "Sticker")).setStickerTextureName("stickerchickenstrike").setSuffix("Chicken Strike").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_DINKED = (new CloudItemSticker(2202, 4552, "Sticker")).setStickerTextureName("stickerdinked").setSuffix("Dinked").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_KATO2014_IBUYPOWER = (new CloudItemSticker(2203, 4553, "Sticker")).setStickerTextureName("stickeribuypowerkatowice2014").setSuffix("iBuyPower (Katowice 2014)").setValue(CloudItemRarity.TIER3).setFilterMT("sticker");
        STICKER_KAWAIIKILLER = (new CloudItemSticker(2204, 4554, "Sticker")).setStickerTextureName("stickerkawaiikiller").setSuffix("Kawaii Killer").setValue(CloudItemRarity.TIER3).setFilterMT("sticker");
        STICKER_LUCKY13 = (new CloudItemSticker(2205, 4555, "Sticker")).setStickerTextureName("stickerlucky13").setSuffix("Lucky 13").setValue(CloudItemRarity.TIER4).setFilterMT("sticker");
        STICKER_HOWL = (new CloudItemSticker(2206, 4556, "Sticker")).setStickerTextureName("stickerhowl").setSuffix("Howl").setValue(CloudItemRarity.TIER5).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_GIS = (new CloudItemSticker(2207, 4557, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_gis").setSuffix("GIS").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_GRAVITY = (new CloudItemSticker(2208, 4558, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_gravity").setSuffix("Gravity").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_KINGDEAD = (new CloudItemSticker(2209, 4559, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_kingdead").setSuffix("KingDead").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_PARADISE9 = (new CloudItemSticker(2210, 4560, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_paradise9").setSuffix("Paradise 9").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_SEALTEAM6 = (new CloudItemSticker(2211, 4561, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_sealteam6").setSuffix("SEAL Team 6").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_SIKA = (new CloudItemSticker(2212, 4562, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_sika").setSuffix("SIKA").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_TBK = (new CloudItemSticker(2213, 4563, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_tbk").setSuffix("TBK").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        STICKER_CCL_2020_S1_CLAN_CCTN = (new CloudItemSticker(2214, 4564, "Sticker")).setStickerTextureName("sticker_ccl_2020_s1_clan_cctn").setSuffix("CCTN").setCollection("CCL 2020 S1").setValue(CloudItemRarity.TIER2).setFilterMT("sticker");
        SC_ALPHA = (new CloudItemStickerCapsule(3000, 4600, "Alpha")).setCostToOpen(100).addContent(STICKER_CAMP, 80).addContent(STICKER_CHICKEN_STRIKE, 80).addContent(STICKER_DINKED, 80).addContent(STICKER_KATO2014_IBUYPOWER, 15).addContent(STICKER_KAWAIIKILLER, 15).addContent(STICKER_LUCKY13, 5).addContent(STICKER_HOWL, 3).setSuffix("Capsule").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        SC_CCL_2020_S1 = (new CloudItemStickerCapsule(3001, 4601, "CCL '20 S1")).setCostToOpen(100).addContent(STICKER_CCL_2020_S1_CLAN_GIS, 80).addContent(STICKER_CCL_2020_S1_CLAN_GRAVITY, 80).addContent(STICKER_CCL_2020_S1_CLAN_KINGDEAD, 80).addContent(STICKER_CCL_2020_S1_CLAN_PARADISE9, 80).addContent(STICKER_CCL_2020_S1_CLAN_SEALTEAM6, 80).addContent(STICKER_CCL_2020_S1_CLAN_SIKA, 80).addContent(STICKER_CCL_2020_S1_CLAN_CCTN, 80).addContent(STICKER_CCL_2020_S1_CLAN_TBK, 80).setSuffix("Capsule").setValue(CloudItemRarity.TIER1).setFilterMT("case").disableNameable().disableShowcase().disableContracts();
        CAPE_SUPPORTER = (new CloudItemCape(3100, 4700, "Cape")).setSuffix("Supporter").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CAPE_COUNTERCRAFT = (new CloudItemCape(3101, 4701, "Cape")).setSuffix("CounterCraft").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CAPE_DAWAE = (new CloudItemCape(3102, 4702, "Cape")).setSuffix("DaWae").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CAPE_BOEHMOD = (new CloudItemCape(3103, 4703, "Cape")).setSuffix("BoehMod").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CAPE_POGCHAMP = (new CloudItemCape(3104, 4704, "Cape")).setSuffix("PogChamp").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CAPE_PEPEDANK = (new CloudItemCape(3105, 4705, "Cape")).setSuffix("PepeDank").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CAPE_MCGA = (new CloudItemCape(3106, 4706, "Cape")).setSuffix("MCGA").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CAPE_PUMPKIN = (new CloudItemCape(3107, 4707, "Cape")).setSuffix("Pumpkin").setValue(CloudItemRarity.TIER4).setFilterMT("cape");
        CARD_DEFAULT = (new CloudItemCallingCard(4000, 5000, "Card")).setSuffix("Default").setDefault();
        CARD_NATION_GB = (new CloudItemCallingCard(4001, 5000, "Card")).setSuffix("GB").setDefault();
        CARD_NATION_USA = (new CloudItemCallingCard(4002, 5000, "Card")).setSuffix("USA").setDefault();
        CARD_NATION_RUS = (new CloudItemCallingCard(4003, 5000, "Card")).setSuffix("RUS").setDefault();
        CARD_NATION_CAN = (new CloudItemCallingCard(4004, 5000, "Card")).setSuffix("CAN").setDefault();
        CARD_NATION_BR = (new CloudItemCallingCard(4005, 5000, "Card")).setSuffix("BR").setDefault();
        CARD_NATION_AU = (new CloudItemCallingCard(4006, 5000, "Card")).setSuffix("AU").setDefault();
        CARD_NATION_NZ = (new CloudItemCallingCard(4007, 5000, "Card")).setSuffix("NZ").setDefault();
        CARD_NATION_CN = (new CloudItemCallingCard(4008, 5000, "Card")).setSuffix("CN").setDefault();
        CARD_NATION_HK = (new CloudItemCallingCard(4009, 5000, "Card")).setSuffix("HK").setDefault();
        CARD_NATION_SCOT = (new CloudItemCallingCard(4010, 5000, "Card")).setSuffix("SCOT").setDefault();
        CARD_NATION_ENG = (new CloudItemCallingCard(4011, 5000, "Card")).setSuffix("ENG").setDefault();
        CARD_NATION_IE = (new CloudItemCallingCard(4012, 5000, "Card")).setSuffix("IE").setDefault();
        CARD_NATION_UA = (new CloudItemCallingCard(4013, 5000, "Card")).setSuffix("UA").setDefault();
        CARD_NATION_FR = (new CloudItemCallingCard(4014, 5000, "Card")).setSuffix("FR").setDefault();
        CARD_NATION_GER = (new CloudItemCallingCard(4015, 5000, "Card")).setSuffix("GER").setDefault();
        CARD_NATION_JP = (new CloudItemCallingCard(4016, 5000, "Card")).setSuffix("JP").setDefault();
        CARD_NATION_ES = (new CloudItemCallingCard(4017, 5000, "Card")).setSuffix("ES").setDefault();
        CARD_CUSTOM_SLIME_GHOST = (new CloudItemCallingCard(4018, 5000, "Card")).setSuffix("Slime Ghost").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_FLOWER_WARFARE = (new CloudItemCallingCard(4019, 5000, "Card")).setSuffix("Flower Warfare").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_FLOWER_POWER = (new CloudItemCallingCard(4020, 5000, "Card")).setSuffix("Flower Power").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CARTOON_BOOM = (new CloudItemCallingCard(4021, 5000, "Card")).setSuffix("Cartoon Boom").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CARTOON_HEY = (new CloudItemCallingCard(4022, 5000, "Card")).setSuffix("Cartoon Hey").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CARTOON_OH = (new CloudItemCallingCard(4023, 5000, "Card")).setSuffix("Cartoon Oh").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CARTOON_OOPS = (new CloudItemCallingCard(4024, 5000, "Card")).setSuffix("Cartoon Oops").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CARTOON_SMILE = (new CloudItemCallingCard(4025, 5000, "Card")).setSuffix("Cartoon Smile").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CARTOON_WOW = (new CloudItemCallingCard(4026, 5000, "Card")).setSuffix("Cartoon Wow").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CARTOON_WTF = (new CloudItemCallingCard(4027, 5000, "Card")).setSuffix("Cartoon WTF").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_CHILI_PEPPER = (new CloudItemCallingCard(4028, 5000, "Card")).setSuffix("Chili Pepper").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_WITCH_KITTY = (new CloudItemCallingCard(4029, 5000, "Card")).setSuffix("Witch Kitty").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_PURPLE_NINJA = (new CloudItemCallingCard(4030, 5000, "Card")).setSuffix("Purple Ninja").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_GAME_OVER = (new CloudItemCallingCard(4031, 5000, "Card")).setSuffix("Game Over").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_SKULL_PATRIOT = (new CloudItemCallingCard(4032, 5000, "Card")).setSuffix("Skull Patriot").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_GAMER_FLARE = (new CloudItemCallingCard(4033, 5000, "Card")).setSuffix("Gamer Flare").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_GREEN_VIP = (new CloudItemCallingCard(4034, 5000, "Card")).setSuffix("Green VIP").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_RANGER = (new CloudItemCallingCard(4035, 5000, "Card")).setSuffix("Ranger").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_INSERTION = (new CloudItemCallingCard(4036, 5000, "Card")).setSuffix("Insertion").setValue(CloudItemRarity.TIER3);
        CARD_CUSTOM_SOLDIER_SILHOUETTE = (new CloudItemCallingCard(4037, 5000, "Card")).setSuffix("Soldier Silhouette").setValue(CloudItemRarity.TIER3);
        CARD_NATION_BE = (new CloudItemCallingCard(4038, 5000, "Card")).setSuffix("BE").setDefault();
        CASE_KEY_ALPHA = (new CloudItemKey(4500, 5500, "Key")).setSuffix("Alpha").setValue(CloudItemRarity.TIER1);
        CASE_KEY_ALPHA2 = (new CloudItemKey(4501, 5501, "Key")).setSuffix("Alpha 2").setValue(CloudItemRarity.TIER1);
        CASE_KEY_ALPHA3 = (new CloudItemKey(4502, 5502, "Key")).setSuffix("Alpha 3").setValue(CloudItemRarity.TIER1);
        CASE_KEY_HAT = (new CloudItemKey(4503, 5503, "Key")).setSuffix("Hats 1").setValue(CloudItemRarity.TIER1);
        CASE_KEY_HALLOWEEN1 = (new CloudItemKey(4504, 5504, "Key")).setSuffix("Halloween 1").setValue(CloudItemRarity.TIER1);
        CASE_KEY_EXO = (new CloudItemKey(4505, 5505, "Key")).setSuffix("Exo").setValue(CloudItemRarity.TIER1);
    }
}
