package dev.vuis.cccr.cloud.packets_idk_if_needed.item;

public class CloudItemKey extends CloudItem {
    public CloudItemKey(int par1, int par2, String par3) {
        super(par1, par2, par3);
        this.setFilterIT("key");
        this.disableContracts();
        this.disableTrading();
        this.disableNameable();
    }
}
