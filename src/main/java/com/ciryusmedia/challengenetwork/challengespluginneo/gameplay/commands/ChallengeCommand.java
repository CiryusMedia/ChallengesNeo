package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.InventoryCollection;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.ChallengeType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ChallengeCommand extends AGuiCompatibleCommand implements Texts {

    @Override
    public void handleArgs1(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args[0].equalsIgnoreCase(ChallengeType.RANDOM.name)) {
                player.openInventory(InventoryCollection.randomChallengesGUI);
            } else {
                player.sendMessage(PREFIX + NOT_ENOUGH_ARGUMENTS);
            }
        } else {
            sender.sendMessage(PREFIX + NOT_ENOUGH_ARGUMENTS);
        }
    }

    @Override
    public void handleArgs2(CommandSender sender, String[] args) {
        Challenge challenge = Challenge.getChallengeFromName(args[1]);
        if (challenge == null) {
            sender.sendMessage(PREFIX + INVALID_CHALLENGE);
        } else if (args.length == 3) {
            handleChallenge(challenge, args[2], sender);
        } else if (args.length == 2) {
            sender.sendMessage(PREFIX + challenge.displayName + " is " +
                    (challenge.enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
            );
        } else {
            sender.sendMessage(PREFIX + TOO_MANY_ARGUMENTS);
        }
    }

    public void handleChallenge(Challenge challenge, String argument, CommandSender sender) {
        int arg = getIntegerFromArg(argument);
        if (arg == -1) {
            sender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        if (arg == 1) {
            List<Challenge> challengesWithSameSuptype = Challenge.getChallengesFromSubtype(challenge.subType);
            if (!challengesWithSameSuptype.isEmpty()) {
                challengesWithSameSuptype.forEach(c -> c.setEnabled(false));
            }
        }

        challenge.setEnabled(arg == 1);

        sender.sendMessage(PREFIX + challenge.displayName + " is now " +
                (challenge.enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }

    public ChallengeCommand() {
        super(ChallengesPluginNeo.challengeGUI);
    }
}
