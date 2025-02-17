package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui.itemcollections;

import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ColorOutsourcing;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DataFlowIssue")
public interface TimerGuiItems extends GeneralGuiItems {

    ItemStack timerFillerItem = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
    ItemStack timerLineFillerItem = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);

    //Timer Inv
    ItemStack checkTime = new ItemStack(Material.CLOCK);
    ItemStack colorInventory = new ItemStack(Material.LIME_WOOL);
    ItemStack startTimer = new ItemStack(Material.EMERALD);
    ItemStack stopTimer = new ItemStack(Material.REDSTONE);
    ItemStack resetTimer = new ItemStack(Material.TNT);
    ItemStack visibleTimer = new ItemStack(Material.LIME_DYE);
    ItemStack invisibleTimer = new ItemStack(Material.GRAY_DYE);

    //Timer Color Inv
    ItemStack timerRunningColor = new ItemStack(ColorOutsourcing.colorStringToWool(plugin.getConfig().getString("RunningColor")));
    ItemStack timerPausedColor = new ItemStack(ColorOutsourcing.colorStringToWool(plugin.getConfig().getString("PausedColor")));

    //Timer Running Color Inv
    List<ItemStack> runningColorItems = new ArrayList<>();
    ItemStack runningColorBlack = new ItemStack(Material.BLACK_WOOL);
    ItemStack runningColorDarkBlue = new ItemStack(Material.BLUE_WOOL);
    ItemStack runningColorDarkGreen = new ItemStack(Material.GREEN_WOOL);
    ItemStack runningColorDarkAqua = new ItemStack(Material.CYAN_WOOL);
    ItemStack runningColorDarkRed = new ItemStack(Material.RED_WOOL);
    ItemStack runningColorDarkPurple = new ItemStack(Material.PURPLE_WOOL);
    ItemStack runningColorGold = new ItemStack(Material.YELLOW_WOOL);
    ItemStack runningColorGray = new ItemStack(Material.LIGHT_GRAY_WOOL);
    ItemStack runningColorDarkGray = new ItemStack(Material.GRAY_WOOL);
    ItemStack runningColorBlue = new ItemStack(Material.BLUE_WOOL);
    ItemStack runningColorGreen = new ItemStack(Material.LIME_WOOL);
    ItemStack runningColorAqua = new ItemStack(Material.CYAN_WOOL);
    ItemStack runningColorRed = new ItemStack(Material.RED_WOOL);
    ItemStack runningColorLightPurple = new ItemStack(Material.MAGENTA_WOOL);
    ItemStack runningColorYellow = new ItemStack(Material.YELLOW_WOOL);
    ItemStack runningColorWhite = new ItemStack(Material.WHITE_WOOL);

    //Timer Paused Color Inv
    List<ItemStack> pausedColorItems = new ArrayList<>();
    ItemStack pausedColorBlack = new ItemStack(Material.BLACK_WOOL);
    ItemStack pausedColorDarkBlue = new ItemStack(Material.BLUE_WOOL);
    ItemStack pausedColorDarkGreen = new ItemStack(Material.GREEN_WOOL);
    ItemStack pausedColorDarkAqua = new ItemStack(Material.CYAN_WOOL);
    ItemStack pausedColorDarkRed = new ItemStack(Material.RED_WOOL);
    ItemStack pausedColorDarkPurple = new ItemStack(Material.PURPLE_WOOL);
    ItemStack pausedColorGold = new ItemStack(Material.YELLOW_WOOL);
    ItemStack pausedColorGray = new ItemStack(Material.LIGHT_GRAY_WOOL);
    ItemStack pausedColorDarkGray = new ItemStack(Material.GRAY_WOOL);
    ItemStack pausedColorBlue = new ItemStack(Material.BLUE_WOOL);
    ItemStack pausedColorGreen = new ItemStack(Material.LIME_WOOL);
    ItemStack pausedColorAqua = new ItemStack(Material.CYAN_WOOL);
    ItemStack pausedColorRed = new ItemStack(Material.RED_WOOL);
    ItemStack pausedColorLightPurple = new ItemStack(Material.MAGENTA_WOOL);
    ItemStack pausedColorYellow = new ItemStack(Material.YELLOW_WOOL);
    ItemStack pausedColorWhite = new ItemStack(Material.WHITE_WOOL);

    static void initTimerGuiItems() {

        updateColors();
        initUpdateColorWoolBlocks();

        //Filler item
        ItemMeta fillerMeta = timerFillerItem.getItemMeta();
        fillerMeta.setDisplayName(" ");
        fillerMeta.setCustomModelData(1); //Make the Item invisible in default resourcepack config

        timerFillerItem.setItemMeta(fillerMeta);

        //Line filler Item
        ItemMeta lineFillerMeta = timerLineFillerItem.getItemMeta();
        lineFillerMeta.setDisplayName(" ");
        lineFillerMeta.setCustomModelData(3); //Make the Item fill a row of 9 slots

        timerLineFillerItem.setItemMeta(lineFillerMeta);

        //Check Time Item
        ItemMeta checkTimeItemMeta = checkTime.getItemMeta();
        checkTimeItemMeta.setDisplayName(ChatColor.YELLOW + "Current time");

        checkTime.setItemMeta(checkTimeItemMeta);

        //Color Inventory
        ItemMeta colorInventoryMeta = colorInventory.getItemMeta();
        colorInventoryMeta.setDisplayName(ChatColor.YELLOW + "Colors");
        colorInventoryMeta.setCustomModelData(1);

        colorInventory.setItemMeta(colorInventoryMeta);

        //Start Timer
        ItemMeta startTimeMeta = startTimer.getItemMeta();
        startTimeMeta.setDisplayName(ChatColor.GREEN + "Start Timer");
        startTimeMeta.setCustomModelData(1); //Start Model

        startTimer.setItemMeta(startTimeMeta);

        //Stop Timer
        ItemMeta stopTimeMeta = stopTimer.getItemMeta();
        stopTimeMeta.setDisplayName(ChatColor.RED + "Stop Timer");
        stopTimeMeta.setCustomModelData(1); //Stop Model

        stopTimer.setItemMeta(stopTimeMeta);

        //Reset Timer
        ItemMeta resetTimerMeta = resetTimer.getItemMeta();
        resetTimerMeta.setDisplayName(ChatColor.RED + "Reset Timer");
        resetTimerMeta.setCustomModelData(1); //Reset Model

        resetTimer.setItemMeta(resetTimerMeta);

        //TImer Visible
        ItemMeta visibleTimerMeta = visibleTimer.getItemMeta();
        visibleTimerMeta.setDisplayName(ChatColor.GREEN + "Timer Visible");
        visibleTimerMeta.setCustomModelData(1); //Visible Model

        visibleTimer.setItemMeta(visibleTimerMeta);

        //Timer Invisible
        ItemMeta invisibleTimerMeta = invisibleTimer.getItemMeta();
        invisibleTimerMeta.setDisplayName(ChatColor.GRAY + "Timer Invisible");
        invisibleTimerMeta.setCustomModelData(1); //Invisible Model

        invisibleTimer.setItemMeta(invisibleTimerMeta);
    }

    static void updateColors() {
        //Timer Running Color
        timerRunningColor.setType(ColorOutsourcing.colorStringToWool(plugin.getConfig().getString("RunningColor")));
        ItemMeta timerRunningColorMeta = timerRunningColor.getItemMeta();
        timerRunningColorMeta.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString("RunningColor").toUpperCase()) + "Running Color");

        timerRunningColor.setItemMeta(timerRunningColorMeta);

        //Timer Paused Color
        timerPausedColor.setType(ColorOutsourcing.colorStringToWool(plugin.getConfig().getString("PausedColor")));
        ItemMeta timerPausedColorMeta = timerPausedColor.getItemMeta();
        timerPausedColorMeta.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString("PausedColor").toUpperCase()) + "Paused Color");

        timerPausedColor.setItemMeta(timerPausedColorMeta);
    }

    static void initUpdateColorWoolBlocks() {
        initLists();

        updateRunningColorWoolBlocks();
        updatePausedColorWoolBlocks();
    }

    //Not a nice way, but it's the way I chose (up to change, hopefully)
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

    //Not a nice way, but it's the way I chose (up to change, hopefully)
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
