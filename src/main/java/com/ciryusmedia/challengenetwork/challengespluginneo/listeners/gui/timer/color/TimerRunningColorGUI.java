package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.timer.color;

import com.ciryusmedia.challengenetwork.challengespluginneo.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.gui.AGUIListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.Inventories;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("DataFlowIssue")
public class TimerRunningColorGUI extends AGUIListener implements Listener, TimerGuiItems {

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

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, timerFillerItem, timerLineFillerItem);
        TimerGuiItems.initUpdateColorWoolBlocks();

        int getColorPos = 0;
        for (int i = 0; i < inv.getSize() && getColorPos < runningColorItems.size(); i++) {
            if (!(i < 9 | i % 9 == 0 | i % 9 == 8)) {
                inv.setItem(i, runningColorItems.get(getColorPos));
                getColorPos++;
            }
        }

        inv.setItem(inv.getSize() - 1, exitItem);
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
