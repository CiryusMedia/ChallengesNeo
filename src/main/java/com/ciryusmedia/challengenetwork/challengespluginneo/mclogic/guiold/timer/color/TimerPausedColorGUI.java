package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.guiold.timer.color;

import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.guiold.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ColorWoolUtils;
import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.InventoryCollection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerPausedColorGUI extends ATimerColorGui implements Listener, TimerGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (ColorWoolUtils.isWool(item.getType())) {
            String colorName = ChatColor.stripColor(item.getItemMeta().getDisplayName().toLowerCase().replace(' ', '_'));
            player.chat("/timer color paused " + colorName);
        }
    }

    @Override
    public void exitInventory(Player player) {
        player.openInventory(InventoryCollection.timerColorGui);
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9 * 5, "Timer Paused Color");
        updateInventory();
    }

    public TimerPausedColorGUI() {
        super(timerFillerItem, timerLineFillerItem);
        initInv();
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory timerPausedColorGUI) {
        inv = timerPausedColorGUI;
    }
}
