package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.timer;

import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.AGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerGUI extends AGUIListener implements Listener, TimerGuiItems {

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (item.equals(colorInventory))
            player.chat("/timer color");

        if (item.equals(startTimer))
            player.chat("/timer resume");

        if (item.equals(stopTimer))
            player.chat("/timer pause");

        if (item.equals(resetTimer))
            player.chat("/timer reset");

        if (item.equals(visibleTimer) | item.equals(invisibleTimer))
            player.chat("/timer display running");
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
        if (plugin.getConfig().getBoolean("Visible"))
            inv.setItem(15, visibleTimer);
        else inv.setItem(15, invisibleTimer);
        inv.setItem(inv.getSize() -1, exitItem);

    }

    public TimerGUI() {
        super(timerFillerItem, timerLineFillerItem);
        initInv();
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory timerInv) {
        inv = timerInv;
    }
}
