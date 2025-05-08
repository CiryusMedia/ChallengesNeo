package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public interface GoalsGuiItems extends GeneralGuiItems {

    ItemStack goalFillerItem = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
    ItemStack goalLineFillerItem = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
    ItemStack goalExit = new ItemStack(Material.BARRIER);

    ItemMeta goalExitDefaultMeta = goalExit.getItemMeta();
    ItemMeta goalExitWarningMeta = goalExit.getItemMeta();

    default void initGoalGuiItems() {
        ItemMeta fillerMeta = goalFillerItem.getItemMeta();
        fillerMeta.setDisplayName("");
        fillerMeta.setCustomModelData(1);

        goalFillerItem.setItemMeta(fillerMeta);

        ItemMeta lineFillerMeta = goalLineFillerItem.getItemMeta();
        lineFillerMeta.setDisplayName("");
        lineFillerMeta.setCustomModelData(3);

        goalLineFillerItem.setItemMeta(fillerMeta);

        goalExitDefaultMeta.setDisplayName(ChatColor.RED + "Exit");
        goalExitDefaultMeta.setCustomModelData(1);

        goalExit.setItemMeta(goalExitDefaultMeta);

        goalExitWarningMeta.setDisplayName(ChatColor.RED + "Exit");
        List<String> gewLore = new ArrayList<>();
        gewLore.add(ChatColor.RED + "WARNING: There aren't any goals active!");
        goalExitWarningMeta.setLore(gewLore);
        goalExitWarningMeta.setCustomModelData(2);
    }

}
