package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.Commands;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class TimerColorGUI extends AGUIListener implements Listener, TimerGuiItems, Commands {

    public static final TimerColorGUI INST = new TimerColorGUI();

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(timerRunningColor))
            player.chat(TIMER_CMD + " color running");

        if (item.equals(timerPausedColor))
            player.chat(TIMER_CMD + " color paused");
    }

    @Override
    public void exitInventory(Player player) {
        player.openInventory(TimerGUI.INST.getInventory());
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

    public TimerColorGUI() {
        super(timerFillerItem, timerLineFillerItem);
        initInv();
    }
}
