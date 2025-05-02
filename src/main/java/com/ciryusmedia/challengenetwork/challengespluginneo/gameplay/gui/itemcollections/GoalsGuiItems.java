package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface GoalsGuiItems extends GeneralGuiItems {

    ItemStack goalFillerItem = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
    ItemStack goalLineFillerItem = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
    ItemStack enderDragonDeathGoal = new ItemStack(Material.DRAGON_EGG);
    ItemStack allAdvancementsGoal = new ItemStack(Material.ENCHANTED_BOOK);

    default void initGoalGuiItems() {
        ItemMeta fillerMeta = goalFillerItem.getItemMeta();
        fillerMeta.setDisplayName("");
        fillerMeta.setCustomModelData(1);

        goalFillerItem.setItemMeta(fillerMeta);

        ItemMeta lineFillerMeta = goalLineFillerItem.getItemMeta();
        lineFillerMeta.setDisplayName("");
        lineFillerMeta.setCustomModelData(3);

        goalLineFillerItem.setItemMeta(fillerMeta);

        ItemMeta enderDragonDeathGoalMeta = enderDragonDeathGoal.getItemMeta();
        enderDragonDeathGoalMeta.setDisplayName("Kill the Ender Dragon");
        enderDragonDeathGoalMeta.setCustomModelData(1);

        enderDragonDeathGoal.setItemMeta(enderDragonDeathGoalMeta);

        ItemMeta advancementsGoalMeta = allAdvancementsGoal.getItemMeta();
        advancementsGoalMeta.setDisplayName("Get all advancements");
        advancementsGoalMeta.setCustomModelData(1);

        allAdvancementsGoal.setItemMeta(advancementsGoalMeta);
    }

}
