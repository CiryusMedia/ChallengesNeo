package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.timer;

import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.Inventories;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerColorInvGUI extends AGUIListener implements Listener, TimerGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(timerRunningColor))
            player.chat("/timer color running");

        if (item.equals(timerPausedColor))
            player.chat("/timer color paused");
    }

    @Override
    public void exitInventory(Player player) {
        player.openInventory(Inventories.timerGUI);
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9 * 3, "Timer Color");
        updateInventory();
    }

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, timerFillerItem, timerLineFillerItem);

        inv.setItem(12, timerRunningColor);
        inv.setItem(14, timerPausedColor);
        inv.setItem(inv.getSize() -1, exitItem);
    }

    public TimerColorInvGUI() {
        super(timerFillerItem, timerLineFillerItem);
        initInv();
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory timerColorInv) {
        inv = timerColorInv;
    }
}
