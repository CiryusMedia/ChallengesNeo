package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.coop.Coop;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CoopCommand extends AGuiCompatibleCommand implements Texts {

    public CoopCommand() {
        super(ChallengesPluginNeo.coopGUI);
    }

    @Override
    public void handleArgs1(CommandSender commandSender, String[] args) {
        Coop coop = Coop.getCoop(args[0]);
        if (coop == null) {
            commandSender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        commandSender.sendMessage(PREFIX + coop.displayName + " is " +
                (coop.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }

    @Override
    public void handleArgs2(@NotNull CommandSender commandSender, String[] args) {
        int arg = getIntegerFromArg(args[1]);
        if (arg == -1) {
            commandSender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }

        if (arg == 1) {
            Coop.coops().forEach(c -> c.setEnabled(false));
        }

        Coop coop = Coop.getCoop(args[0]);

        coop.setEnabled(arg == 1);

        commandSender.sendMessage(PREFIX + coop.displayName + " is now " +
                (coop.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }
}
