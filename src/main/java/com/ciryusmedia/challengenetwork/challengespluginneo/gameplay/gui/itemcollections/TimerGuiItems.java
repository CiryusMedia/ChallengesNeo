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
    GuiTimerColorItemStack timerColorBlack = new GuiTimerColorItemStack(Material.BLACK_WOOL, ChatColor.BLACK + "Black", CMD_COLOR_WOOL, "black");
    GuiTimerColorItemStack timerColorDarkBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.DARK_BLUE + "Dark Blue", CMD_COLOR_WOOL, "dark_blue");
    GuiTimerColorItemStack timerColorDarkGreen = new GuiTimerColorItemStack(Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Dark Green", CMD_COLOR_WOOL, "dark_green");
    GuiTimerColorItemStack timerColorDarkAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.DARK_AQUA + "Dark Aqua", CMD_COLOR_WOOL, "dark_aqua");
    GuiTimerColorItemStack timerColorDarkRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Dark Red", CMD_COLOR_WOOL, "dark_red");
    GuiTimerColorItemStack timerColorDarkPurple = new GuiTimerColorItemStack(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Dark Purple", CMD_COLOR_WOOL, "dark_purple");
    GuiTimerColorItemStack timerColorDarkGray = new GuiTimerColorItemStack(Material.GRAY_WOOL, ChatColor.DARK_GRAY + "Dark Gray", CMD_COLOR_WOOL, "dark_grey");
    GuiTimerColorItemStack timerColorGold = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.GOLD + "Gold", CMD_COLOR_WOOL, "gold");
    GuiTimerColorItemStack timerColorGray = new GuiTimerColorItemStack(Material.LIGHT_GRAY_WOOL, ChatColor.GRAY + "Gray", CMD_COLOR_WOOL, "gray");
    GuiTimerColorItemStack timerColorBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.BLUE + "Blue", CMD_COLOR_WOOL, "blue");
    GuiTimerColorItemStack timerColorGreen = new GuiTimerColorItemStack(Material.LIME_WOOL, ChatColor.GREEN + "Green", CMD_COLOR_WOOL, "green");
    GuiTimerColorItemStack timerColorAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.AQUA + "Aqua", CMD_COLOR_WOOL, "aqua");
    GuiTimerColorItemStack timerColorRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Red", CMD_COLOR_WOOL, "red");
    GuiTimerColorItemStack timerColorLightPurple = new GuiTimerColorItemStack(Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE + "Purple", CMD_COLOR_WOOL, "purple");
    GuiTimerColorItemStack timerColorYellow = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.YELLOW + "Yellow", CMD_COLOR_WOOL, "yellow");
    GuiTimerColorItemStack timerColorWhite = new GuiTimerColorItemStack(Material.WHITE_WOOL, ChatColor.WHITE + "White", CMD_COLOR_WOOL, "white");
    List<GuiTimerColorItemStack> timerColorItems = new ArrayList<>() {{
        add(timerColorBlack);
        add(timerColorDarkBlue);
        add(timerColorDarkGreen);
        add(timerColorDarkAqua);
        add(timerColorDarkRed);
        add(timerColorDarkPurple);
        add(timerColorGold);
        add(timerColorGray);
        add(timerColorDarkGray);
        add(timerColorBlue);
        add(timerColorGreen);
        add(timerColorAqua);
        add(timerColorRed);
        add(timerColorLightPurple);
        add(timerColorYellow);
        add(timerColorWhite);
    }};

    //Timer Running Color Inv TODO: Change Custom Model data, so that the colors are based on the hexcodes instead of the nearest wool color. Extract into enum?
    GuiTimerColorItemStack runningColorBlack = new GuiTimerColorItemStack(Material.BLACK_WOOL, ChatColor.BLACK + "Black", CMD_COLOR_WOOL, "black", true);
    GuiTimerColorItemStack runningColorDarkBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.DARK_BLUE + "Dark Blue", CMD_COLOR_WOOL, "dark_blue", true);
    GuiTimerColorItemStack runningColorDarkGreen = new GuiTimerColorItemStack(Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Dark Green", CMD_COLOR_WOOL, "dark_green", true);
    GuiTimerColorItemStack runningColorDarkAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.DARK_AQUA + "Dark Aqua", CMD_COLOR_WOOL, "dark_aqua", true);
    GuiTimerColorItemStack runningColorDarkRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Dark Red", CMD_COLOR_WOOL, "dark_red", true);
    GuiTimerColorItemStack runningColorDarkPurple = new GuiTimerColorItemStack(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Dark Purple", CMD_COLOR_WOOL, "dark_purple", true);
    GuiTimerColorItemStack runningColorDarkGray = new GuiTimerColorItemStack(Material.GRAY_WOOL, ChatColor.DARK_GRAY + "Dark Gray", CMD_COLOR_WOOL, "dark_grey", true);
    GuiTimerColorItemStack runningColorGold = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.GOLD + "Gold", CMD_COLOR_WOOL, "gold", true);
    GuiTimerColorItemStack runningColorGray = new GuiTimerColorItemStack(Material.LIGHT_GRAY_WOOL, ChatColor.GRAY + "Gray", CMD_COLOR_WOOL, "gray", true);
    GuiTimerColorItemStack runningColorBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.BLUE + "Blue", CMD_COLOR_WOOL, "blue", true);
    GuiTimerColorItemStack runningColorGreen = new GuiTimerColorItemStack(Material.LIME_WOOL, ChatColor.GREEN + "Green", CMD_COLOR_WOOL, "green", true);
    GuiTimerColorItemStack runningColorAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.AQUA + "Aqua", CMD_COLOR_WOOL, "aqua", true);
    GuiTimerColorItemStack runningColorRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Red", CMD_COLOR_WOOL, "red", true);
    GuiTimerColorItemStack runningColorLightPurple = new GuiTimerColorItemStack(Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE + "Purple", CMD_COLOR_WOOL, "purple", true);
    GuiTimerColorItemStack runningColorYellow = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.YELLOW + "Yellow", CMD_COLOR_WOOL, "yellow", true);
    GuiTimerColorItemStack runningColorWhite = new GuiTimerColorItemStack(Material.WHITE_WOOL, ChatColor.WHITE + "White", CMD_COLOR_WOOL, "white", true);
    List<GuiTimerColorItemStack> runningColorItems = new ArrayList<>() {{
        add(runningColorBlack);
        add(runningColorDarkBlue);
        add(runningColorDarkGreen);
        add(runningColorDarkAqua);
        add(runningColorDarkRed);
        add(runningColorDarkPurple);
        add(runningColorGold);
        add(runningColorGray);
        add(runningColorDarkGray);
        add(runningColorBlue);
        add(runningColorGreen);
        add(runningColorAqua);
        add(runningColorRed);
        add(runningColorLightPurple);
        add(runningColorYellow);
        add(runningColorWhite);
    }};

    //Timer Paused Color Inv TODO: Change Custom Model data, so that the colors are based on the hexcodes instead of the nearest wool color. Extract into enum?
//    GuiTimerColorItemStack pausedColorBlack = new GuiTimerColorItemStack(Material.BLACK_WOOL, ChatColor.BLACK + "Black", CMD_COLOR_WOOL, "black", false);
//    GuiTimerColorItemStack pausedColorDarkBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.DARK_BLUE + "Dark Blue", CMD_COLOR_WOOL, "dark_blue", false);
//    GuiTimerColorItemStack pausedColorDarkGreen = new GuiTimerColorItemStack(Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Dark Green", CMD_COLOR_WOOL, "dark_green", false);
//    GuiTimerColorItemStack pausedColorDarkAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.DARK_AQUA + "Dark Aqua", CMD_COLOR_WOOL, "dark_aqua", false);
//    GuiTimerColorItemStack pausedColorDarkRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Dark Red", CMD_COLOR_WOOL, "dark_red", false);
//    GuiTimerColorItemStack pausedColorDarkPurple = new GuiTimerColorItemStack(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Dark Purple", CMD_COLOR_WOOL, "dark_purple", false);
//    GuiTimerColorItemStack pausedColorDarkGray = new GuiTimerColorItemStack(Material.GRAY_WOOL, ChatColor.DARK_GRAY + "Dark Gray", CMD_COLOR_WOOL, "dark_grey", false);
//    GuiTimerColorItemStack pausedColorGold = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.GOLD + "Gold", CMD_COLOR_WOOL, "gold", false);
//    GuiTimerColorItemStack pausedColorGray = new GuiTimerColorItemStack(Material.LIGHT_GRAY_WOOL, ChatColor.GRAY + "Gray", CMD_COLOR_WOOL, "gray", false);
//    GuiTimerColorItemStack pausedColorBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.BLUE + "Blue", CMD_COLOR_WOOL, "blue", false);
//    GuiTimerColorItemStack pausedColorGreen = new GuiTimerColorItemStack(Material.LIME_WOOL, ChatColor.GREEN + "Green", CMD_COLOR_WOOL, "green", false);
//    GuiTimerColorItemStack pausedColorAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.AQUA + "Aqua", CMD_COLOR_WOOL, "aqua", false);
//    GuiTimerColorItemStack pausedColorRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Red", CMD_COLOR_WOOL, "red", false);
//    GuiTimerColorItemStack pausedColorLightPurple = new GuiTimerColorItemStack(Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE + "Purple", CMD_COLOR_WOOL, "purple", false);
//    GuiTimerColorItemStack pausedColorYellow = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.YELLOW + "Yellow", CMD_COLOR_WOOL, "yellow", false);
//    GuiTimerColorItemStack pausedColorWhite = new GuiTimerColorItemStack(Material.WHITE_WOOL, ChatColor.WHITE + "White", CMD_COLOR_WOOL, "white", false);
//    List<GuiTimerColorItemStack> pausedColorItems = new ArrayList<>() {{
//        add(pausedColorBlack);
//        add(pausedColorDarkBlue);
//        add(pausedColorDarkGreen);
//        add(pausedColorDarkAqua);
//        add(pausedColorDarkRed);
//        add(pausedColorDarkPurple);
//        add(pausedColorGold);
//        add(pausedColorGray);
//        add(pausedColorDarkGray);
//        add(pausedColorBlue);
//        add(pausedColorGreen);
//        add(pausedColorAqua);
//        add(pausedColorRed);
//        add(pausedColorLightPurple);
//        add(pausedColorYellow);
//        add(pausedColorWhite);
//    }};

    static void initTimerGuiItems() {
        updateColors();
        initUpdateColorWoolBlocks();
    }

    static void updateColors() {
        //Timer Running Color
        timerRunningColor.setType(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString(TIMER_COLOR_RUNNING)));
        timerRunningColor.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString(TIMER_COLOR_RUNNING).toUpperCase()) + "Running Color");

        //Timer Paused Color
        timerPausedColor.setType(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString(TIMER_COLOR_PAUSED)));
        timerPausedColor.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString(TIMER_COLOR_PAUSED).toUpperCase()) + "Paused Color");
    }

    static void initUpdateColorWoolBlocks() {
        updateRunningColorWoolBlocks();
//        updatePausedColorWoolBlocks();
    }

    //TODO Far better than both ways, but the list of items seems unnecessary
    static void updateRunningColorWoolBlocks() {
        runningColorItems.forEach(item -> item.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_RUNNING).equalsIgnoreCase(item.key)));
    }

    //TODO Better than the old way, but still room for improvement
//    static void updatePausedColorWoolBlocks() {
//        pausedColorBlack.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("BLACK"));
//        pausedColorDarkBlue.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("DARK_BLUE"));
//        pausedColorDarkGreen.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("DARK_GREEN"));
//        pausedColorDarkAqua.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("DARK_AQUA"));
//        pausedColorDarkRed.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("DARK_RED"));
//        pausedColorDarkPurple.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("DARK_PURPLE"));
//        pausedColorGold.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("GOLD"));
//        pausedColorGray.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("GRAY"));
//        pausedColorDarkGray.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("DARK_GRAY"));
//        pausedColorBlue.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("BLUE"));
//        pausedColorGreen.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("GREEN"));
//        pausedColorAqua.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("AQUA"));
//        pausedColorRed.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("RED"));
//        pausedColorLightPurple.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("LIGHT_PURPLE"));
//        pausedColorYellow.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("YELLOW"));
//        pausedColorWhite.setEnchantmentGlint(plugin.getConfig().getString(TIMER_COLOR_PAUSED).equalsIgnoreCase("WHITE"));
//    }
}
