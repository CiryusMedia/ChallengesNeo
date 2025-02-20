package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ColorWoolUtils;
import com.ciryusmedia.challengenetwork.challengespluginneo.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.InventoryCollection;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.Texts;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

@SuppressWarnings({"ConstantValue"})
public class TimerCommand implements CommandExecutor {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();
    ChallengeTimer timer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        timer = plugin.getTimer();

        if ((sender instanceof Player player)) {

            if (args.length == 0) {
                DEBUGGER.log("Opening timer gui for player " + player.getName(), DebugLevel.LEVEL_3);
                plugin.updateInventories();
                player.openInventory(InventoryCollection.timerGUI);
                return true;
            }

            if (args.length > 0 && !player.hasPermission("challenge.timer")) {
                player.sendMessage(Texts.PREFIX + Texts.NO_PERMISSION);
                return true;
            }
        } else if (args.length == 0) {
            sender.sendMessage(Texts.PREFIX + Texts.NOT_ENOUGH_ARGUMENTS);
            DEBUGGER.log("Sender isn't a player and didn't provide enough args", DebugLevel.LEVEL_3);
            return true;
        }

        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "resume":
            case "start":
                if (timer.isRunning()) {
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Timer is already running!");
                    break;
                }

                timer.setRunning(true);
                sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.GREEN + "running!");
                break;

            case "pause":
            case "stop":
                DEBUGGER.log("Handling timer paused/stop command", DebugLevel.LEVEL_3);
                if (!timer.isRunning()) {
                    DEBUGGER.log("Timer already paused", DebugLevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Timer is already paused!");
                    break;
                }

                DEBUGGER.log("Timer paused", DebugLevel.LEVEL_3);
                timer.setRunning(false);
                sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.RED + "paused!");
                break;

            case "set":
                DEBUGGER.log("Handling timer set command", DebugLevel.LEVEL_3);
                if (args.length != 2) {
                    DEBUGGER.log("Not enough arguments for timer set command", DebugLevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Wrong number of arguments!");
                    break;
                }

                DEBUGGER.log("Setting timer", DebugLevel.LEVEL_3);
                try {
                    timer.setTime(Integer.parseInt(args[1]));
                    DEBUGGER.log("Timer successfully set", DebugLevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer has been set to " + ChatColor.AQUA + timer.getTime());
                } catch (NumberFormatException e) {
                    DEBUGGER.log("Time is not a number", DebugLevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Time must be a number!");
                }

                break;

            case "load": {
                DEBUGGER.log("Loading timer time", DebugLevel.LEVEL_3);
                timer.setTime(plugin.getConfig().getInt("Time"));
                sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer has been loaded!");
                break;
            }

            case "reset": {
                DEBUGGER.log("Resetting timer", DebugLevel.LEVEL_3);
                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer has been reset!");
                break;
            }

            case "display": {
                DEBUGGER.log("Handling timer display command", DebugLevel.LEVEL_3);
                if (args.length >= 2) {
                    displayHandler(sender, args);
                } else {
                    DEBUGGER.log("Not enough arguments for timer display command", DebugLevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + Texts.INVALID_ARGUMENTS);
                }

                plugin.saveConfig();
                break;
            }

            case "color":
                DEBUGGER.log("Handling timer color command", DebugLevel.LEVEL_3);
                if (args.length == 1 && sender instanceof Player player) {
                    DEBUGGER.log("Opening inventory for player", DebugLevel.LEVEL_3);
                    player.openInventory(InventoryCollection.timerColorGui);
                } else {
                    colorHandler(sender, args);
                }
                break;

            default:
                sender.sendMessage(Texts.PREFIX + Texts.INVALID_ARGUMENTS);
        }

        plugin.updateInventories();

        return false;
    }

    private void displayHandler(CommandSender sender, String[] args) {
        DEBUGGER.log("Enough args to handle", DebugLevel.LEVEL_3);
        switch (args[1].toLowerCase(Locale.ROOT)) {
            case "running":
                DEBUGGER.log("Handling running timer visibility", DebugLevel.LEVEL_3);
                if (!plugin.getConfig().getBoolean("Visible")) {
                    plugin.getConfig().set("Visible", true);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.GREEN + "visible " + ChatColor.YELLOW + "when running");
                } else if (plugin.getConfig().getBoolean("Visible")) {
                    plugin.getConfig().set("Visible", false);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.RED + "invisible " + ChatColor.YELLOW + "when running");
                }
                break;
            case "paused":
                DEBUGGER.log("Handling paused timer visibility", DebugLevel.LEVEL_3);
                if (!plugin.getConfig().getBoolean("ShowPaused")) {
                    plugin.getConfig().set("ShowPaused", true);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.GREEN + "visible " + ChatColor.YELLOW + "when paused");
                    break;
                } else if (plugin.getConfig().getBoolean("ShowPaused")) {
                    plugin.getConfig().set("ShowPaused", false);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.RED + "invisible " + ChatColor.YELLOW + "when paused");
                }
                break;
        }
    }

    private void colorHandler(CommandSender sender, String[] args) {
        String colorType;

        if (args.length >= 2) {
            colorType = args[1].equalsIgnoreCase("running") || args[1].equalsIgnoreCase("paused") ? StringUtils.capitalize(args[1].toLowerCase()) : null;
            if (colorType == null) {
                DEBUGGER.log("Invalid args", DebugLevel.LEVEL_3);
                sender.sendMessage(Texts.PREFIX + Texts.INVALID_ARGUMENTS);
            } else if (args.length == 2 && sender instanceof Player player) {
                if (colorType.equalsIgnoreCase("running")) {
                    DEBUGGER.log("Opening timer running color gui for player", DebugLevel.LEVEL_3);
                    player.openInventory(InventoryCollection.timerRunningColorGUI);
                } else {
                    DEBUGGER.log("Opening timer paused color gui for player", DebugLevel.LEVEL_3);
                    player.openInventory(InventoryCollection.timerPausedColorGUI);
                }
            } else if (args.length >= 3) {
                if (ColorWoolUtils.isValidChatColor(args[2])) {
                    ChatColor color = ChatColor.valueOf(args[2].toUpperCase());
                    plugin.getConfig().set(colorType + "Color", args[2]);
                    plugin.saveConfig();

                    DEBUGGER.log("Setting color", DebugLevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + color + args[2] + ChatColor.YELLOW + " when " + colorType.toLowerCase());
                } else {
                    DEBUGGER.log("Arg is not a color", DebugLevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Enter a valid color!");
                }
            }
        }
    }
}