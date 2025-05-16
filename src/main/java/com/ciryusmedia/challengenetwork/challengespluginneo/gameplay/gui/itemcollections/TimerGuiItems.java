package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ColorWoolUtils;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiTimerColorItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"DataFlowIssue", "deprecation"})
public interface TimerGuiItems extends GeneralGuiItems {

    GuiItemStack timerFillerItem = new GuiItemStack(Material.YELLOW_STAINED_GLASS_PANE, " ", CMD_FILLER);
    GuiItemStack timerLineFillerItem = new GuiItemStack(Material.YELLOW_STAINED_GLASS_PANE, " ", CMD_LINE_FILLER);

    //Timer Inv
    GuiItemStack checkTime = new GuiItemStack(Material.CLOCK, ChatColor.YELLOW + "Current time", "timer_check");
    GuiItemStack colorInventory = new GuiItemStack(Material.LIME_WOOL, ChatColor.YELLOW + "Color", "timer_color");
    GuiItemStack startTimer = new GuiItemStack(Material.EMERALD, ChatColor.GREEN + "Start timer", "timer_start");
    GuiItemStack stopTimer = new GuiItemStack(Material.REDSTONE, ChatColor.RED + "Stop timer", "timer_stop");
    GuiItemStack resetTimer = new GuiItemStack(Material.TNT, ChatColor.RED + "Reset timer", "timer_reset");
    GuiItemStack visibleTimer = new GuiItemStack(Material.LIME_DYE, ChatColor.GREEN + "Timer Visible", "timer_visible");
    GuiItemStack invisibleTimer = new GuiItemStack(Material.GRAY_DYE, ChatColor.GRAY + "Timer Invisible", "timer_invisible");

    //Timer Color Inv
    GuiItemStack timerRunningColor = new GuiItemStack(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString(TIMER_COLOR_RUNNING)),
            ChatColor.valueOf(plugin.getConfig().getString(TIMER_COLOR_RUNNING).toUpperCase()) + "Running Color");
    GuiItemStack timerPausedColor = new GuiItemStack(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString(TIMER_COLOR_PAUSED)),
            ChatColor.valueOf(plugin.getConfig().getString(TIMER_COLOR_PAUSED).toUpperCase()) + "Paused Color");

    //Timer Color Items
    List<GuiTimerColorItemStack> timerColorItems = new ArrayList<>() {{
        add(new GuiTimerColorItemStack(Material.BLACK_WOOL, ChatColor.BLACK + "Black", CMD_COLOR_WOOL, "black"));
        add(new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.DARK_BLUE + "Dark Blue", CMD_COLOR_WOOL, "dark_blue"));
        add(new GuiTimerColorItemStack(Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Dark Green", CMD_COLOR_WOOL, "dark_green"));
        add(new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.DARK_AQUA + "Dark Aqua", CMD_COLOR_WOOL, "dark_aqua"));
        add(new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Dark Red", CMD_COLOR_WOOL, "dark_red"));
        add(new GuiTimerColorItemStack(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Dark Purple", CMD_COLOR_WOOL, "dark_purple"));
        add(new GuiTimerColorItemStack(Material.GRAY_WOOL, ChatColor.DARK_GRAY + "Dark Gray", CMD_COLOR_WOOL, "dark_grey"));
        add(new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.GOLD + "Gold", CMD_COLOR_WOOL, "gold"));
        add(new GuiTimerColorItemStack(Material.LIGHT_GRAY_WOOL, ChatColor.GRAY + "Gray", CMD_COLOR_WOOL, "gray"));
        add(new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.BLUE + "Blue", CMD_COLOR_WOOL, "blue"));
        add(new GuiTimerColorItemStack(Material.LIME_WOOL, ChatColor.GREEN + "Green", CMD_COLOR_WOOL, "green"));
        add(new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.AQUA + "Aqua", CMD_COLOR_WOOL, "aqua"));
        add(new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Red", CMD_COLOR_WOOL, "red"));
        add(new GuiTimerColorItemStack(Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE + "Purple", CMD_COLOR_WOOL, "purple"));
        add(new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.YELLOW + "Yellow", CMD_COLOR_WOOL, "yellow"));
        add(new GuiTimerColorItemStack(Material.WHITE_WOOL, ChatColor.WHITE + "White", CMD_COLOR_WOOL, "white"));
    }};

    static void updateColors() {
        //Timer Running Color
        timerRunningColor.setType(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString(TIMER_COLOR_RUNNING)));
        timerRunningColor.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString(TIMER_COLOR_RUNNING).toUpperCase()) + "Running Color");

        //Timer Paused Color
        timerPausedColor.setType(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString(TIMER_COLOR_PAUSED)));
        timerPausedColor.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString(TIMER_COLOR_PAUSED).toUpperCase()) + "Paused Color");
    }
}
