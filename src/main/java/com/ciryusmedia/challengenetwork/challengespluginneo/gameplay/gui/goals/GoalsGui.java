package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.goals;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.GoalsGuiItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GoalsGui extends AGUIListener implements GoalsGuiItems {

    public GoalsGui() {
        super(goalLineFillerItem, goalFillerItem);
    }

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {

    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9*3, "Goals");
        initGoalGuiItems();
        updateInventory();
    }

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, goalFillerItem, goalLineFillerItem);

        inv.setItem(9 + 1, enderDragonDeathGoal);
        inv.setItem(9 + 3, allAdvancementsGoal);
        inv.setItem(inv.getSize() - 1, exitItem);
    }
}
