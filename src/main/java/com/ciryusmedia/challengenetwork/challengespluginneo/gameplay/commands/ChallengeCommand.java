package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.InventoryCollection;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ChallengeCommand implements CommandExecutor, Texts {

    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            DEBUGGER.log("Handling challenge command for gui", DebugLevel.LEVEL_3);
            if (sender instanceof Player player) {
                if (player.hasPermission("challenge.challenges.view")) {
                    ChallengesPluginNeo.challengeGUI.updateInventory();
                    player.openInventory(InventoryCollection.challengeGUI);
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
                    Challenge challenge = Challenge.getChallengeFromName(strings[1]);
                    if (challenge == null) {
                        sender.sendMessage(PREFIX + INVALID_CHALLENGE);
                        break;
                    }
                    if (strings.length >= 3) {
                        handleChallenge(challenge, strings[2], sender);
                        break;
                    }
                    if (challenge.enabled) {
                        sender.sendMessage(PREFIX + challenge.displayName + " is " + ChatColor.GREEN + "enabled");
                        break;
                    } else {
                        sender.sendMessage(PREFIX + challenge.displayName + " is " + ChatColor.RED + "disabled");
                        break;
                    }
                }
        }

        return true;
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
        if (arg) {
            List<Challenge> challengesWithSameSuptybe = Challenge.getChallengesFromSubtype(challenge.subType);
            if (!challengesWithSameSuptybe.isEmpty()) {
                challengesWithSameSuptybe.forEach(c -> c.setEnabled(false));
            }
        }

        challenge.setEnabled(arg);

        sender.sendMessage(PREFIX + challenge.displayName + " is now " +
                (challenge.enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }

    public void handleRandomChallenges(String[] args, CommandSender sender) {
        if (args.length == 1) {
            if (sender instanceof Player player) {
                switch (args[0].toLowerCase()) {
                    case "random":
                        player.openInventory(InventoryCollection.randomChallengesGUI);
                }
            } else
                sender.sendMessage(PREFIX + NOT_PLAYER);
        } else {
            Challenge challenge = Challenge.getChallengeFromName(args[1]);
            if (challenge == null) {
                sender.sendMessage(PREFIX + INVALID_CHALLENGE);
            } else if (args.length >= 3) {
                handleChallenge(challenge, args[2], sender);
            } else {
                sender.sendMessage(PREFIX + challenge.displayName + " is now " +
                        (challenge.enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
                );
            }
        }
    }
}
