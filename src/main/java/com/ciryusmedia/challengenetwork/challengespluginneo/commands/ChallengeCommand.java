package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengesOutsourcing;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.Inventories;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ChallengeCommand implements CommandExecutor, Texts {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        ChallengesOutsourcing.checkInitialized();

        if (strings.length == 0) {
            plugin.log("Handling challenge command for gui", Debuglevel.LEVEL_3);
            if (sender instanceof Player player) {
                if (player.hasPermission("challenge.challenges.view")) {
                    ChallengesPluginNeo.challengeGUI.updateInventory();
                    player.openInventory(Inventories.challengeGUI);
                    return true;
                } else {
                    player.sendMessage(PREFIX + NO_PERMISSION);
                    return true;
                }
            } else {
                sender.sendMessage(PREFIX + NOT_PLAYER);
                return true;
            }
        }

        switch (strings[0].toLowerCase()) {
            case "random":
                handleRandomChallenges(strings, sender);
                break;

            case "sync":
                if (strings.length == 1) {
                    sender.sendMessage(PREFIX + NOT_ENOUGH_ARGUMENTS);
                    break;
                } else {
                    ChallengeOld challengeOld = ChallengesOutsourcing.getChallengeFromName(strings[1]);
                    if (challengeOld == null) {
                        sender.sendMessage(PREFIX + INVALID_CHALLENGE);
                        break;
                    }
                    if (strings.length >= 3) {
                        handleChallenge(challengeOld, strings[2], sender);
                        break;
                    }
                    if (challengeOld.isEnabled()) {
                        sender.sendMessage(PREFIX + challengeOld.getDisplayName() + " is " + ChatColor.GREEN + "enabled");
                        break;
                    } else {
                        sender.sendMessage(PREFIX + challengeOld.getDisplayName() + " is " + ChatColor.RED + "disabled");
                        break;
                    }
                }
        }

        return true;
    }

    public void handleRandomChallenges(String[] args, CommandSender sender) {
        if (args.length == 1) {
            if (sender instanceof Player player) {
                switch (args[0].toLowerCase()) {
                    case "random":
                        player.openInventory(Inventories.randomChallengesGUI);
                }
            } else
                sender.sendMessage(PREFIX + NOT_PLAYER);
        } else {
            ChallengeOld challengeOld = ChallengesOutsourcing.getChallengeFromName(args[1]);
            if (challengeOld == null) {
                sender.sendMessage(PREFIX + INVALID_CHALLENGE);
            } else if (args.length >= 3) {
                handleChallenge(challengeOld, args[2], sender);
            } else {
                if (challengeOld.isEnabled()) {
                    sender.sendMessage(PREFIX + challengeOld.getDisplayName() + " is " + ChatColor.GREEN + "enabled");
                } else {
                    sender.sendMessage(PREFIX + challengeOld.getDisplayName() + " is " + ChatColor.RED + "disabled");
                }
            }
        }
    }

    public void handleChallenge(ChallengeOld challengeOld, String argument, CommandSender sender) {
        boolean arg;
        if (argument.equalsIgnoreCase("true") || argument.equalsIgnoreCase("on")) {
            arg = true;
        } else if (argument.equalsIgnoreCase("false") || argument.equalsIgnoreCase("off")) {
            arg = false;
        } else {
            sender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        if (challengeOld.hasSubtype() && arg) {
            List<ChallengeOld> challengesWithSameSuptybe = ChallengesOutsourcing.getChallengesFromSubtype(challengeOld.getSubType());
            if (!challengesWithSameSuptybe.isEmpty()) {
                challengesWithSameSuptybe.forEach(c -> c.setEnabled(false));
            }
        }

        challengeOld.setEnabled(arg);

        if (challengeOld.isEnabled()) {
            sender.sendMessage(PREFIX + challengeOld.getDisplayName() + " is now " + ChatColor.GREEN + "enabled");
        } else {
            sender.sendMessage(PREFIX + challengeOld.getDisplayName() + " is now " + ChatColor.RED + "disabled");
        }
    }
}
