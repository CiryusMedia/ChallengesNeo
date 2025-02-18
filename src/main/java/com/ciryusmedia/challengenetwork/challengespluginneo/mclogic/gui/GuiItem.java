package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public enum GuiItem {

    //<editor-fold desc="Filler and Constant defaults" defaultstate="collapsed">
    DEFAULT_FILLER(Material.GRAY_STAINED_GLASS_PANE, " ", ItemType.FILLER),
    DEFAULT_LINE_FILLER(Material.GRAY_STAINED_GLASS_PANE, " ", ItemType.LINE_FILLER),
    CHALLENGE_FILLER(Material.RED_STAINED_GLASS_PANE, " ", ItemType.FILLER),
    CHALLENGE_LINE_FILLER(Material.RED_STAINED_GLASS_PANE, " ", ItemType.LINE_FILLER),

    EXIT(Material.BARRIER, ChatColor.RED + "Exit", ItemType.EXIT),
    //</editor-fold>

    //<editor-fold desc="Challenge" defaultstate="collapsed">
    RANDOM_CHALLENGES(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, ChatColor.LIGHT_PURPLE + "Random Challenges", ItemType.MENU, 4),

    ;
    //</editor-fold>

    public final ItemStack itemStack;
    public final String displayName;
    public final ItemType itemType;
    public final int customModelDataID;

    GuiItem(Material material, String displayName, ItemType itemType, int customModelDataID) {
        this(new ItemStack(material), displayName, itemType, customModelDataID);
    }

    GuiItem(Material material, String displayName, ItemType itemType) {
        this(new ItemStack(material), displayName, itemType, itemType.CUSTOM_MODEL_DATA_ID);
    }

    GuiItem(ItemStack itemStack, String displayName, ItemType itemType, int customModelDataID) {
        this.itemStack = itemStack;
        this.displayName = displayName;
        this.itemType = itemType;
        this.customModelDataID = customModelDataID;
    }
}
