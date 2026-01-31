package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import java.util.ArrayList;
import net.minecraft.util.EnumChatFormatting;

public class CloudItemCape extends CloudItem {
    public CloudItemCape(int par1, int par2, String par3) {
        super(par1, par2, par3);
        this.setFilterIT("cape");
        this.disableSelling();
        this.disableContracts();
        this.disableTrading();
        this.disableNameable();
    }

    public void addInformation(CloudItemStack par1, ArrayList<String> par2) {
        super.addInformation(par1, par2);
        par2.add("");
        par2.add(EnumChatFormatting.BOLD + "Author");
        par2.add(EnumChatFormatting.GRAY + this.getAuthor());
    }
}
