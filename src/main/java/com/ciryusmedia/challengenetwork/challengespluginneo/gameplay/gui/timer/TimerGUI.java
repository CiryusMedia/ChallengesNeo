package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.Commands;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.AGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerGUI extends AGUIListener implements Listener, TimerGuiItems, Commands {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(colorInventory))
            player.chat(TIMER_CMD + " color");

        if (item.equals(startTimer))
            player.chat(TIMER_CMD + " resume");

        if (item.equals(stopTimer))
            player.chat(TIMER_CMD + " pause");

        if (item.equals(resetTimer))
            player.chat(TIMER_CMD + " reset");

        if (item.equals(visibleTimer) | item.equals(invisibleTimer))
            player.chat(TIMER_CMD + " display running");
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9 * 3, "Timer");
        updateInventory();
    }

    @Override
    public void updateInventory() {
        emptyInventoryItemFiller(inv, fillerItem, lineFillerItem);

        inv.setItem(11, colorInventory);
        if (plugin.getTimer().isRunning()) {
            inv.setItem(13, stopTimer);
        } else {
            inv.setItem(13, startTimer);
        }
        inv.setItem(22, resetTimer);
        if (plugin.getConfig().getBoolean(TIMER_VISIBLE_RUNNING))
            inv.setItem(15, visibleTimer);
        else inv.setItem(15, invisibleTimer);
        inv.setItem(inv.getSize() -1, exitItem);

    }

    public TimerGUI() {
        super(timerFillerItem, timerLineFillerItem);
        initInv();
    }
}
