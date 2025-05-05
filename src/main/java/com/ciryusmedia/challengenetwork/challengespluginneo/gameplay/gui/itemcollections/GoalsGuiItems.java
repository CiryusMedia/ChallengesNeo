package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface GoalsGuiItems extends GeneralGuiItems {

    ItemStack goalFillerItem = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
    ItemStack goalLineFillerItem = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);

    default void initGoalGuiItems() {
        ItemMeta fillerMeta = goalFillerItem.getItemMeta();
        fillerMeta.setDisplayName("");
        fillerMeta.setCustomModelData(1);

        goalFillerItem.setItemMeta(fillerMeta);

        ItemMeta lineFillerMeta = goalLineFillerItem.getItemMeta();
        lineFillerMeta.setDisplayName("");
        lineFillerMeta.setCustomModelData(3);

        goalLineFillerItem.setItemMeta(fillerMeta);
    }

}
