package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.color;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands.Commands;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections.TimerGuiItems;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ColorWoolUtils;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.timer.TimerColorGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class TimerPausedColorGUI extends ATimerColorGui implements Listener, TimerGuiItems, Commands {

    public static final TimerPausedColorGUI INST = new TimerPausedColorGUI();

    @Override
    public void inventoryClickHandler(ItemStack item, Player player) {
        if (ColorWoolUtils.isWool(item.getType())) {
            String colorName = ChatColor.stripColor(item.getItemMeta().getDisplayName().toLowerCase().replace(' ', '_'));
            player.chat(TIMER_CMD + " color paused " + colorName);
        }
    }

    @Override
    public void exitInventory(Player player) {
        player.openInventory(TimerColorGUI.INST.getInventory());
    }

    @Override
    public void initInv() {
        inv = Bukkit.createInventory(null, 9 * 5, "Timer Paused Color");
        updateInventory();
    }

    public TimerPausedColorGUI() {
        super(timerFillerItem, timerLineFillerItem, false);
        initInv();
    }
}
