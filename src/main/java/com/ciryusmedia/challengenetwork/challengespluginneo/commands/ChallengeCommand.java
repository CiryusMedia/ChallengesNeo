package com.ciryusmedia.challengenetwork.challengespluginneo.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
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

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();
    ChallengesOutsourcing coo = instance.getCho();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            instance.log("Handling challenge command for gui", Debuglevel.LEVEL_3);
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

            case "sync":
                if (strings.length == 1) {
                    sender.sendMessage(PREFIX + NOT_ENOUGH_ARGUMENTS);
                    break;
                } else {
                    Challenge challenge = coo.getChallengeFromName(strings[1]);
                    if (challenge == null) {
                        sender.sendMessage(PREFIX + INVALID_CHALLENGE);
                        break;
                    }
                    if (strings.length >= 3) {
                        handleChallenge(challenge, strings[2], sender);
                        break;
                    }
                    if (challenge.isEnabled()) {
                        sender.sendMessage(PREFIX + challenge.getDisplayName() + " is " + ChatColor.GREEN + "enabled");
                        break;
                    } else {
                        sender.sendMessage(PREFIX + challenge.getDisplayName() + " is " + ChatColor.RED + "disabled");
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
            Challenge challenge = coo.getChallengeFromName(args[1]);
            if (challenge == null) {
                sender.sendMessage(PREFIX + INVALID_CHALLENGE);
            } else if (args.length >= 3) {
                handleChallenge(challenge, args[2], sender);
            } else {
                if (challenge.isEnabled()) {
                    sender.sendMessage(PREFIX + challenge.getDisplayName() + " is " + ChatColor.GREEN + "enabled");
                } else {
                    sender.sendMessage(PREFIX + challenge.getDisplayName() + " is " + ChatColor.RED + "disabled");
                }
            }
        }
    }

    public void handleChallenge(Challenge challenge, String argument, CommandSender sender) {
        boolean arg;
        if (argument.equalsIgnoreCase("true") || argument.equalsIgnoreCase("on")) {
            arg = true;
        } else if (argument.equalsIgnoreCase("false") || argument.equalsIgnoreCase("off")) {
            arg = false;
        } else {
            sender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        if (challenge.hasSubtype() && arg) {
            List<Challenge> challengesWithSameSuptybe = coo.getChallengesFromSubtype(challenge.getSubType());
            if (!challengesWithSameSuptybe.isEmpty()) {
                challengesWithSameSuptybe.forEach(c -> c.setEnabled(false));
            }
        }

        challenge.setEnabled(arg);

        if (challenge.isEnabled()) {
            sender.sendMessage(PREFIX + challenge.getDisplayName() + " is now " + ChatColor.GREEN + "enabled");
        } else {
            sender.sendMessage(PREFIX + challenge.getDisplayName() + " is now " + ChatColor.RED + "disabled");
        }
    }
}
