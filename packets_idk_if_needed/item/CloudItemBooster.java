package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

import java.util.ArrayList;

public class CloudItemBooster extends CloudItem {
    public CloudItemBooster(int par1, int par2, String par3) {
        super(par1, par2, par3);
        this.setFilterIT("booster");
    }

    public void addInformation(CloudItemStack par1, ArrayList<String> par2) {
        super.addInformation(par1, par2);
        par2.add("");
        par2.add("When Activated, you");
        par2.add("will earn " + this.getSuffix() + "!");
    }
}
