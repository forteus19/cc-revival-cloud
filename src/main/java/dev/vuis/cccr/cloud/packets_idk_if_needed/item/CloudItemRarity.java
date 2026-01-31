package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.EnumChatFormatting;

public enum CloudItemRarity {
    DEFAULT(0, "Default", "0x56585C", -11118500, EnumChatFormatting.GRAY),
    TIER1(1, "Coal", "0x53738A", -11308150, EnumChatFormatting.DARK_AQUA),
    TIER2(2, "Iron", "0x0082dF", -16743713, EnumChatFormatting.AQUA),
    TIER3(3, "Lapis", "0x5549AD", -11187795, EnumChatFormatting.DARK_PURPLE),
    TIER4(4, "Gold", "0xAC43A1", -5487711, EnumChatFormatting.LIGHT_PURPLE),
    TIER5(5, "Diamond", "0xE73046", -1626042, EnumChatFormatting.RED),
    TIER6(6, "Obsidian", "0xFFC438", -15304, EnumChatFormatting.GOLD);

    private static final Map<Integer, CloudItemRarity> rarityMapping = new HashMap();
    public int rarityID = 0;
    public String rarityName;
    public String rarityColor;
    public int rarityColorInt;
    public EnumChatFormatting chatColor;

    private CloudItemRarity(int par1, String par2, String par3, int par4, EnumChatFormatting par5) {
        this.rarityID = par1;
        this.rarityName = par2;
        this.rarityColor = par3;
        this.rarityColorInt = par4;
        this.chatColor = par5;
    }

    public static CloudItemRarity getEnumFromID(int par1) {
        return (CloudItemRarity)rarityMapping.get(par1);
    }

    public int getColorInteger() {
        return Integer.parseInt(this.rarityColor.replace("0x", ""), 16);
    }

    static {
        CloudItemRarity[] var0 = values();
        int var1 = var0.length;
        CloudItemRarity[] arr$ = var0;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            CloudItemRarity var3 = arr$[i$];
            rarityMapping.put(var3.rarityID, var3);
        }

    }
}
