package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ColorWoolUtils;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiTimerColorItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"DataFlowIssue","deprecation"})
public interface TimerGuiItems extends GeneralGuiItems {

    GuiItemStack timerFillerItem = new GuiItemStack(Material.YELLOW_STAINED_GLASS_PANE, " ", CMD_FILLER);
    GuiItemStack timerLineFillerItem = new GuiItemStack(Material.YELLOW_STAINED_GLASS_PANE, " ", CMD_LINE_FILLER);

    //Timer Inv
    GuiItemStack checkTime = new GuiItemStack(Material.CLOCK, ChatColor.YELLOW + "Current time", "timer_check");
    GuiItemStack colorInventory = new GuiItemStack(Material.LIME_WOOL,ChatColor.YELLOW + "Color", "timer_color");
    GuiItemStack startTimer = new GuiItemStack(Material.EMERALD, ChatColor.GREEN + "Start timer", "timer_start");
    GuiItemStack stopTimer = new GuiItemStack(Material.REDSTONE, ChatColor.RED + "Stop timer", "timer_stop");
    GuiItemStack resetTimer = new GuiItemStack(Material.TNT, ChatColor.RED + "Reset timer", "timer_reset");
    GuiItemStack visibleTimer = new GuiItemStack(Material.LIME_DYE, ChatColor.GREEN + "Timer Visible", "timer_visible");
    GuiItemStack invisibleTimer = new GuiItemStack(Material.GRAY_DYE, ChatColor.GRAY + "Timer Invisible", "timer_invisible");

    //Timer Color Inv
    GuiItemStack timerRunningColor = new GuiItemStack(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString("RunningColor")),
            ChatColor.valueOf(plugin.getConfig().getString("RunningColor").toUpperCase()) + "Running Color");
    GuiItemStack timerPausedColor = new GuiItemStack(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString("PausedColor")),
            ChatColor.valueOf(plugin.getConfig().getString("PausedColor").toUpperCase()) + "Paused Color");

    //Timer Running Color Inv TODO: Change Custom Model data, so that the colors are based on the hexcodes instead of the nearest wool color. Extract into enum?
    List<GuiTimerColorItemStack> runningColorItems = new ArrayList<>();
    GuiTimerColorItemStack runningColorBlack = new GuiTimerColorItemStack(Material.BLACK_WOOL, ChatColor.BLACK + "Black", CMD_COLOR_WOOL, "black", true);
    GuiTimerColorItemStack runningColorDarkBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.DARK_BLUE + "Dark Blue", CMD_COLOR_WOOL, "dark_blue", true);
    GuiTimerColorItemStack runningColorDarkGreen = new GuiTimerColorItemStack(Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Dark Green", CMD_COLOR_WOOL, "dark_green", true);
    GuiTimerColorItemStack runningColorDarkAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.DARK_AQUA + "Dark Aqua", CMD_COLOR_WOOL, "dark_aqua", true);
    GuiTimerColorItemStack runningColorDarkRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Dark Red", CMD_COLOR_WOOL, "dark_red" , true);
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

    //Timer Paused Color Inv TODO: Change Custom Model data, so that the colors are based on the hexcodes instead of the nearest wool color. Extract into enum?
    List<GuiTimerColorItemStack> pausedColorItems = new ArrayList<>();
    GuiTimerColorItemStack pausedColorBlack = new GuiTimerColorItemStack(Material.BLACK_WOOL, ChatColor.BLACK + "Black", CMD_COLOR_WOOL, "black", false);
    GuiTimerColorItemStack pausedColorDarkBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.DARK_BLUE + "Dark Blue", CMD_COLOR_WOOL, "dark_blue", false);
    GuiTimerColorItemStack pausedColorDarkGreen = new GuiTimerColorItemStack(Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Dark Green", CMD_COLOR_WOOL, "dark_green", false);
    GuiTimerColorItemStack pausedColorDarkAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.DARK_AQUA + "Dark Aqua", CMD_COLOR_WOOL, "dark_aqua", false);
    GuiTimerColorItemStack pausedColorDarkRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Dark Red", CMD_COLOR_WOOL, "dark_red" , false);
    GuiTimerColorItemStack pausedColorDarkPurple = new GuiTimerColorItemStack(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Dark Purple", CMD_COLOR_WOOL, "dark_purple", false);
    GuiTimerColorItemStack pausedColorDarkGray = new GuiTimerColorItemStack(Material.GRAY_WOOL, ChatColor.DARK_GRAY + "Dark Gray", CMD_COLOR_WOOL, "dark_grey", false);
    GuiTimerColorItemStack pausedColorGold = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.GOLD + "Gold", CMD_COLOR_WOOL, "gold", false);
    GuiTimerColorItemStack pausedColorGray = new GuiTimerColorItemStack(Material.LIGHT_GRAY_WOOL, ChatColor.GRAY + "Gray", CMD_COLOR_WOOL, "gray", false);
    GuiTimerColorItemStack pausedColorBlue = new GuiTimerColorItemStack(Material.BLUE_WOOL, ChatColor.BLUE + "Blue", CMD_COLOR_WOOL, "blue", false);
    GuiTimerColorItemStack pausedColorGreen = new GuiTimerColorItemStack(Material.LIME_WOOL, ChatColor.GREEN + "Green", CMD_COLOR_WOOL, "green", false);
    GuiTimerColorItemStack pausedColorAqua = new GuiTimerColorItemStack(Material.CYAN_WOOL, ChatColor.AQUA + "Aqua", CMD_COLOR_WOOL, "aqua", false);
    GuiTimerColorItemStack pausedColorRed = new GuiTimerColorItemStack(Material.RED_WOOL, ChatColor.DARK_RED + "Red", CMD_COLOR_WOOL, "red", false);
    GuiTimerColorItemStack pausedColorLightPurple = new GuiTimerColorItemStack(Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE + "Purple", CMD_COLOR_WOOL, "purple", false);
    GuiTimerColorItemStack pausedColorYellow = new GuiTimerColorItemStack(Material.YELLOW_WOOL, ChatColor.YELLOW + "Yellow", CMD_COLOR_WOOL, "yellow", false);
    GuiTimerColorItemStack pausedColorWhite = new GuiTimerColorItemStack(Material.WHITE_WOOL, ChatColor.WHITE + "White", CMD_COLOR_WOOL, "white", false);

    static void initTimerGuiItems() {
        updateColors();
        initUpdateColorWoolBlocks();
    }

    static void updateColors() {
        //Timer Running Color
        timerRunningColor.setType(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString("RunningColor")));
        ItemMeta timerRunningColorMeta = timerRunningColor.getItemMeta();
        timerRunningColorMeta.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString("RunningColor").toUpperCase()) + "Running Color");

        timerRunningColor.setItemMeta(timerRunningColorMeta);

        //Timer Paused Color
        timerPausedColor.setType(ColorWoolUtils.colorStringToWool(plugin.getConfig().getString("PausedColor")));
        ItemMeta timerPausedColorMeta = timerPausedColor.getItemMeta();
        timerPausedColorMeta.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString("PausedColor").toUpperCase()) + "Paused Color");

        timerPausedColor.setItemMeta(timerPausedColorMeta);
    }

    static void initUpdateColorWoolBlocks() {
        initLists();

        updateRunningColorWoolBlocks();
        updatePausedColorWoolBlocks();
    }

    //TODO Not a nice way, but it's the way I chose (up to change, hopefully)
    static void updateRunningColorWoolBlocks() {
        ItemMeta runningColorBlackMeta = runningColorBlack.getItemMeta();
        runningColorBlackMeta.setDisplayName(ChatColor.BLACK + "Black");
        runningColorBlackMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("BLACK"));

        runningColorBlack.setItemMeta(runningColorBlackMeta);


        ItemMeta runningColorDarkBlueMeta = runningColorDarkBlue.getItemMeta();
        runningColorDarkBlueMeta.setDisplayName(ChatColor.DARK_BLUE + "Dark Blue");
        runningColorDarkBlueMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("DARK_BLUE"));

        runningColorDarkBlue.setItemMeta(runningColorDarkBlueMeta);


        ItemMeta runningColorDarkGreenMeta = runningColorDarkGreen.getItemMeta();
        runningColorDarkGreenMeta.setDisplayName(ChatColor.DARK_GREEN + "Dark Green");
        runningColorDarkGreenMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("DARK_GREEN"));

        runningColorDarkGreen.setItemMeta(runningColorDarkGreenMeta);


        ItemMeta runningColorDarkAquaMeta = runningColorDarkAqua.getItemMeta();
        runningColorDarkAquaMeta.setDisplayName(ChatColor.DARK_AQUA + "Dark Aqua");
        runningColorDarkAquaMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("DARK_AQUA"));

        runningColorDarkAqua.setItemMeta(runningColorDarkAquaMeta);


        ItemMeta runningColorDarkRedMeta = runningColorDarkRed.getItemMeta();
        runningColorDarkRedMeta.setDisplayName(ChatColor.DARK_RED + "Dark Red");
        runningColorDarkRedMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("DARK_RED"));

        runningColorDarkRed.setItemMeta(runningColorDarkRedMeta);


        ItemMeta runningColorDarkPurpleMeta = runningColorDarkPurple.getItemMeta();
        runningColorDarkPurpleMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dark Purple");
        runningColorDarkPurpleMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("DARK_PURPLE"));

        runningColorDarkPurple.setItemMeta(runningColorDarkPurpleMeta);


        ItemMeta runningColorGoldMeta = runningColorGold.getItemMeta();
        runningColorGoldMeta.setDisplayName(ChatColor.GOLD + "Gold");
        runningColorGoldMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("GOLD"));

        runningColorGold.setItemMeta(runningColorGoldMeta);


        ItemMeta runningColorGrayMeta = runningColorGray.getItemMeta();
        runningColorGrayMeta.setDisplayName(ChatColor.GRAY + "Gray");
        runningColorGrayMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("GRAY"));

        runningColorGray.setItemMeta(runningColorGrayMeta);


        ItemMeta runningColorDarkGrayMeta = runningColorDarkGray.getItemMeta();
        runningColorDarkGrayMeta.setDisplayName(ChatColor.DARK_GRAY + "Dark Gray");
        runningColorDarkGrayMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("DARK_GRAY"));

        runningColorDarkGray.setItemMeta(runningColorDarkGrayMeta);


        ItemMeta runningColorBlueMeta = runningColorBlue.getItemMeta();
        runningColorBlueMeta.setDisplayName(ChatColor.BLUE + "Blue");
        runningColorBlueMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("BLUE"));

        runningColorBlue.setItemMeta(runningColorBlueMeta);


        ItemMeta runningColorGreenMeta = runningColorGreen.getItemMeta();
        runningColorGreenMeta.setDisplayName(ChatColor.GREEN + "Green");
        runningColorGreenMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("GREEN"));

        runningColorGreen.setItemMeta(runningColorGreenMeta);


        ItemMeta runningColorAquaMeta = runningColorAqua.getItemMeta();
        runningColorAquaMeta.setDisplayName(ChatColor.AQUA + "Aqua");
        runningColorAquaMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("AQUA"));

        runningColorAqua.setItemMeta(runningColorAquaMeta);


        ItemMeta runningColorRedMeta = runningColorRed.getItemMeta();
        runningColorRedMeta.setDisplayName(ChatColor.RED + "Red");
        runningColorRedMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("RED"));

        runningColorRed.setItemMeta(runningColorRedMeta);


        ItemMeta runningColorLightPurpleMeta = runningColorLightPurple.getItemMeta();
        runningColorLightPurpleMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Light Purple");
        runningColorLightPurpleMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("LIGHT_PURPLE"));

        runningColorLightPurple.setItemMeta(runningColorLightPurpleMeta);


        ItemMeta runningColorYellowMeta = runningColorYellow.getItemMeta();
        runningColorYellowMeta.setDisplayName(ChatColor.YELLOW + "Yellow");
        runningColorYellowMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("YELLOW"));

        runningColorYellow.setItemMeta(runningColorYellowMeta);


        ItemMeta runningColorWhiteMeta = runningColorWhite.getItemMeta();
        runningColorWhiteMeta.setDisplayName(ChatColor.WHITE + "White");
        runningColorWhiteMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("RunningColor").equalsIgnoreCase("WHITE"));

        runningColorWhite.setItemMeta(runningColorWhiteMeta);
    }

    //TODO Not a nice way, but it's the way I chose (up to change, hopefully)
    static void updatePausedColorWoolBlocks() {
        ItemMeta pausedColorBlackMeta = pausedColorBlack.getItemMeta();
        pausedColorBlackMeta.setDisplayName(ChatColor.BLACK + "Black");
        pausedColorBlackMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("BLACK"));

        pausedColorBlack.setItemMeta(pausedColorBlackMeta);


        ItemMeta pausedColorDarkBlueMeta = pausedColorDarkBlue.getItemMeta();
        pausedColorDarkBlueMeta.setDisplayName(ChatColor.DARK_BLUE + "Dark Blue");
        pausedColorDarkBlueMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("DARK_BLUE"));

        pausedColorDarkBlue.setItemMeta(pausedColorDarkBlueMeta);


        ItemMeta pausedColorDarkGreenMeta = pausedColorDarkGreen.getItemMeta();
        pausedColorDarkGreenMeta.setDisplayName(ChatColor.DARK_GREEN + "Dark Green");
        pausedColorDarkGreenMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("DARK_GREEN"));

        pausedColorDarkGreen.setItemMeta(pausedColorDarkGreenMeta);


        ItemMeta pausedColorDarkAquaMeta = pausedColorDarkAqua.getItemMeta();
        pausedColorDarkAquaMeta.setDisplayName(ChatColor.DARK_AQUA + "Dark Aqua");
        pausedColorDarkAquaMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("DARK_AQUA"));

        pausedColorDarkAqua.setItemMeta(pausedColorDarkAquaMeta);


        ItemMeta pausedColorDarkRedMeta = pausedColorDarkRed.getItemMeta();
        pausedColorDarkRedMeta.setDisplayName(ChatColor.DARK_RED + "Dark Red");
        pausedColorDarkRedMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("DARK_RED"));

        pausedColorDarkRed.setItemMeta(pausedColorDarkRedMeta);


        ItemMeta pausedColorDarkPurpleMeta = pausedColorDarkPurple.getItemMeta();
        pausedColorDarkPurpleMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dark Purple");
        pausedColorDarkPurpleMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("DARK_PURPLE"));

        pausedColorDarkPurple.setItemMeta(pausedColorDarkPurpleMeta);


        ItemMeta pausedColorGoldMeta = pausedColorGold.getItemMeta();
        pausedColorGoldMeta.setDisplayName(ChatColor.GOLD + "Gold");
        pausedColorGoldMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("GOLD"));

        pausedColorGold.setItemMeta(pausedColorGoldMeta);


        ItemMeta pausedColorGrayMeta = pausedColorGray.getItemMeta();
        pausedColorGrayMeta.setDisplayName(ChatColor.GRAY + "Gray");
        pausedColorGrayMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("GRAY"));

        pausedColorGray.setItemMeta(pausedColorGrayMeta);


        ItemMeta pausedColorDarkGrayMeta = pausedColorDarkGray.getItemMeta();
        pausedColorDarkGrayMeta.setDisplayName(ChatColor.DARK_GRAY + "Dark Gray");
        pausedColorDarkGrayMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("DARK_GRAY"));

        pausedColorDarkGray.setItemMeta(pausedColorDarkGrayMeta);


        ItemMeta pausedColorBlueMeta = pausedColorBlue.getItemMeta();
        pausedColorBlueMeta.setDisplayName(ChatColor.BLUE + "Blue");
        pausedColorBlueMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("BLUE"));

        pausedColorBlue.setItemMeta(pausedColorBlueMeta);


        ItemMeta pausedColorGreenMeta = pausedColorGreen.getItemMeta();
        pausedColorGreenMeta.setDisplayName(ChatColor.GREEN + "Green");
        pausedColorGreenMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("GREEN"));

        pausedColorGreen.setItemMeta(pausedColorGreenMeta);


        ItemMeta pausedColorAquaMeta = pausedColorAqua.getItemMeta();
        pausedColorAquaMeta.setDisplayName(ChatColor.AQUA + "Aqua");
        pausedColorAquaMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("AQUA"));

        pausedColorAqua.setItemMeta(pausedColorAquaMeta);


        ItemMeta pausedColorRedMeta = pausedColorRed.getItemMeta();
        pausedColorRedMeta.setDisplayName(ChatColor.RED + "Red");
        pausedColorRedMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("RED"));

        pausedColorRed.setItemMeta(pausedColorRedMeta);


        ItemMeta pausedColorLightPurpleMeta = pausedColorLightPurple.getItemMeta();
        pausedColorLightPurpleMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Light Purple");
        pausedColorLightPurpleMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("LIGHT_PURPLE"));

        pausedColorLightPurple.setItemMeta(pausedColorLightPurpleMeta);


        ItemMeta pausedColorYellowMeta = pausedColorYellow.getItemMeta();
        pausedColorYellowMeta.setDisplayName(ChatColor.YELLOW + "Yellow");
        pausedColorYellowMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("YELLOW"));

        pausedColorYellow.setItemMeta(pausedColorYellowMeta);


        ItemMeta pausedColorWhiteMeta = pausedColorWhite.getItemMeta();
        pausedColorWhiteMeta.setDisplayName(ChatColor.WHITE + "White");
        pausedColorWhiteMeta.setEnchantmentGlintOverride(plugin.getConfig().getString("PausedColor").equalsIgnoreCase("WHITE"));

        pausedColorWhite.setItemMeta(pausedColorWhiteMeta);
    }

    static void initLists() {

        runningColorItems.clear();
        pausedColorItems.clear();

        runningColorItems.add(runningColorBlack);
        runningColorItems.add(runningColorDarkBlue);
        runningColorItems.add(runningColorDarkGreen);
        runningColorItems.add(runningColorDarkAqua);
        runningColorItems.add(runningColorDarkRed);
        runningColorItems.add(runningColorDarkPurple);
        runningColorItems.add(runningColorGold);
        runningColorItems.add(runningColorGray);
        runningColorItems.add(runningColorDarkGray);
        runningColorItems.add(runningColorBlue);
        runningColorItems.add(runningColorGreen);
        runningColorItems.add(runningColorAqua);
        runningColorItems.add(runningColorRed);
        runningColorItems.add(runningColorLightPurple);
        runningColorItems.add(runningColorYellow);
        runningColorItems.add(runningColorWhite);

        pausedColorItems.add(pausedColorBlack);
        pausedColorItems.add(pausedColorDarkBlue);
        pausedColorItems.add(pausedColorDarkGreen);
        pausedColorItems.add(pausedColorDarkAqua);
        pausedColorItems.add(pausedColorDarkRed);
        pausedColorItems.add(pausedColorDarkPurple);
        pausedColorItems.add(pausedColorGold);
        pausedColorItems.add(pausedColorGray);
        pausedColorItems.add(pausedColorDarkGray);
        pausedColorItems.add(pausedColorBlue);
        pausedColorItems.add(pausedColorGreen);
        pausedColorItems.add(pausedColorAqua);
        pausedColorItems.add(pausedColorRed);
        pausedColorItems.add(pausedColorLightPurple);
        pausedColorItems.add(pausedColorYellow);
        pausedColorItems.add(pausedColorWhite);

    }

}
