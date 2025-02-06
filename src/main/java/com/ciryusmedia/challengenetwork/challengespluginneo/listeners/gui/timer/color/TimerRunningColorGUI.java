package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.timer.color;

import com.ciryusmedia.challengenetwork.challengespluginneo.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.Inventories;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerRunningColorGUI extends ATimerColorGui implements Listener, TimerGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (clo.isWool(item.getType())) {
            String colorName = ChatColor.stripColor(item.getItemMeta().getDisplayName().toLowerCase().replace(' ', '_'));
            player.chat("/timer color running " + colorName);
        }
    }

    @Override
    public void exitInventory(Player player) {
        player.openInventory(Inventories.timerColorGui);
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9 * 5, "Timer Running Color");
        updateInventory();
    }


    public TimerRunningColorGUI() {
        super(timerFillerItem, timerLineFillerItem);
        initInv();
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory timerRunningColorGUI) {
        inv = timerRunningColorGUI;
    }
}
