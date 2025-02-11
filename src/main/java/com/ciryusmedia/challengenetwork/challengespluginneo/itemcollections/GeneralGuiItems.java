package com.ciryusmedia.challengenetwork.challengespluginneo.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface GeneralGuiItems {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    ItemStack defaultFillerItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    ItemStack defaultLineFillerItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

    ItemStack exitItem = new ItemStack(Material.BARRIER);

    default void emptyInventoryItemFiller(Inventory inventory, ItemStack fillerItem, ItemStack lineFillerItem) {
        for (int currentSlot = 0; currentSlot < inventory.getSize(); currentSlot++) {
            inventory.setItem(currentSlot, fillerItem);
            if (currentSlot % 9 == 0) {
                inventory.setItem(currentSlot, lineFillerItem);
            }
        }
    }

    public static void initGeneralGuiItems() {
        //Default filler item
        ItemMeta fillerMeta = defaultFillerItem.getItemMeta();
        fillerMeta.setDisplayName("");
        fillerMeta.setCustomModelData(1);

        defaultFillerItem.setItemMeta(fillerMeta);

        //Default line filler item
        ItemMeta lineFillerMeta = defaultFillerItem.getItemMeta();
        lineFillerMeta.setDisplayName("");
        lineFillerMeta.setCustomModelData(3);

        defaultLineFillerItem.setItemMeta(lineFillerMeta);

        //Exit Item
        ItemMeta exitMeta = exitItem.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit");
        exitMeta.setCustomModelData(1);

        exitItem.setItemMeta(exitMeta);
    }

}
