package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GuiItem {

    CHALLENGE_FILLER();

    public final ItemStack itemStack;
    public final String displayName;
    public final ItemType itemType;
    public final int customModelDataID;

    GuiItem() {
        this.itemStack =;
        this.displayName =;
        this.itemType =;

        if (itemType.CUSTOM_MODEL_DATA_ID == -1) {
            this.customModelDataID = customModelDataID;
        } else {
            this.customModelDataID = itemType.CUSTOM_MODEL_DATA_ID;
        }
    }
}
