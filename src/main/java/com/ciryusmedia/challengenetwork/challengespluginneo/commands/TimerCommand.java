package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ColorOutsourcing;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.Inventories;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Locale;

@SuppressWarnings({"ConstantValue", "DataFlowIssue"})
public class TimerCommand implements CommandExecutor {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();
    ColorOutsourcing clo = instance.getClo();
    Plugin plugin = ChallengesPluginNeo.getPlugin(ChallengesPluginNeo.class);
    ChallengeTimer timer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        timer = ChallengesPluginNeo.getInstance().getTimer();

        if ((sender instanceof Player player)) {

            if (args.length == 0) {
                instance.log("Opening timer gui for player " + player.getName(), Debuglevel.LEVEL_3);
                instance.updateInventories();
                player.openInventory(Inventories.timerGUI);
                return true;
            }

            if (args.length > 0 && !player.hasPermission("challenge.timer")) {
                player.sendMessage(Texts.PREFIX + Texts.NO_PERMISSION);
                return true;
            }
        } else if (args.length == 0) {
            sender.sendMessage(Texts.PREFIX + Texts.NOT_ENOUGH_ARGUMENTS);
            instance.log("Sender isn't a player and didn't provide enough args", Debuglevel.LEVEL_3);
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
                instance.log("Handling timer paused/stop command", Debuglevel.LEVEL_3);
                if (!timer.isRunning()) {
                    instance.log("Timer already paused", Debuglevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Timer is already paused!");
                    break;
                }

                instance.log("Timer paused", Debuglevel.LEVEL_3);
                timer.setRunning(false);
                sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.RED + "paused!");
                break;

            case "set":
                instance.log("Handling timer set command", Debuglevel.LEVEL_3);
                if (args.length != 2) {
                    instance.log("Not enough arguments for timer set command", Debuglevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Wrong number of arguments!");
                    break;
                }

                instance.log("Setting timer", Debuglevel.LEVEL_3);
                try {
                    timer.setTime(Integer.parseInt(args[1]));
                    instance.log("Timer successfully set", Debuglevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer has been set to " + ChatColor.AQUA + timer.getTime());
                } catch (NumberFormatException e) {
                    instance.log("Time is not a number", Debuglevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Time must be a number!");
                }

                break;

            case "load": {
                instance.log("Loading timer time", Debuglevel.LEVEL_3);
                timer.setTime(plugin.getConfig().getInt("Time"));
                sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer has been loaded!");
                break;
            }

            case "reset": {
                instance.log("Resetting timer", Debuglevel.LEVEL_3);
                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer has been reset!");
                break;
            }

            case "display": {
                instance.log("Handling timer display command", Debuglevel.LEVEL_3);
                if (args.length >= 2) {
                    displayHandler(sender, args);
                } else {
                    instance.log("Not enough arguments for timer display command", Debuglevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + Texts.INVALID_ARGUMENTS);
                }

                plugin.saveConfig();
                break;
            }

            case "color":
                instance.log("Handling timer color command", Debuglevel.LEVEL_3);
                if (args.length == 1 && sender instanceof Player player) {
                    instance.log("Opening inventory for player", Debuglevel.LEVEL_3);
                    player.openInventory(Inventories.timerColorGui);
                } else {
                    colorHandler(sender, args);
                }
                break;

            default:
                sender.sendMessage(Texts.PREFIX + Texts.INVALID_ARGUMENTS);
        }

        instance.updateInventories();

        return false;
    }

    private void displayHandler(CommandSender sender, String[] args) {
        instance.log("Enough args to handle", Debuglevel.LEVEL_3);
        switch (args[1].toLowerCase(Locale.ROOT)) {
            case "running":
                instance.log("Handling running timer visibility", Debuglevel.LEVEL_3);
                if (!plugin.getConfig().getBoolean("Visible")) {
                    plugin.getConfig().set("Visible", true);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.GREEN + "visible " + ChatColor.YELLOW + "when running");
                } else if (plugin.getConfig().getBoolean("Visible")) {
                    plugin.getConfig().set("Visible", false);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + ChatColor.RED + "invisible " + ChatColor.YELLOW + "when running");
                }
                break;
            case "paused":
                instance.log("Handling paused timer visibility", Debuglevel.LEVEL_3);
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
            if (colorType == null){
                instance.log("Invalid args", Debuglevel.LEVEL_3);
                sender.sendMessage(Texts.PREFIX + Texts.INVALID_ARGUMENTS);
            } else if (args.length == 2 && sender instanceof Player player) {
                if (colorType.equalsIgnoreCase("running")) {
                    instance.log("Opening timer running color gui for player", Debuglevel.LEVEL_3);
                    player.openInventory(Inventories.timerRunningColorGUI);
                } else {
                    instance.log("Opening timer paused color gui for player", Debuglevel.LEVEL_3);
                    player.openInventory(Inventories.timerPausedColorGUI);
                }
            } else if (args.length >= 3) {
                if (clo.isValidChatColor(args[2])) {
                    ChatColor color = ChatColor.valueOf(args[2].toUpperCase());
                    plugin.getConfig().set(colorType + "Color", args[2]);
                    plugin.saveConfig();

                    instance.log("Setting color", Debuglevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.YELLOW + "Timer is now " + color + args[2] + ChatColor.YELLOW + " when " + colorType.toLowerCase());
                } else {
                    instance.log("Arg is not a color", Debuglevel.LEVEL_3);
                    sender.sendMessage(Texts.PREFIX + ChatColor.RED + "Enter a valid color!");
                }
            }
        }
    }
}
