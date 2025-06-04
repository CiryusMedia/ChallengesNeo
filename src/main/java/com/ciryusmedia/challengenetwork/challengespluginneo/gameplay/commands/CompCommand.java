package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.commands;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.Texts;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.comp.Comp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CompCommand extends AGuiCompatibleCommand implements Texts {

    public CompCommand() {
        super(ChallengesPluginNeo.compGUI);
    }

    @Override
    public void handleArgs1(CommandSender commandSender, String[] args) {
        Comp comp = Comp.getComp(args[0]);
        if (comp == null) {
            commandSender.sendMessage(PREFIX + INVALID_ARGUMENTS);
            return;
        }
        commandSender.sendMessage(PREFIX + comp.displayName + " is " +
                (comp.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
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
            Comp.comps().forEach(c -> c.setEnabled(false));
        }

        Comp comp = Comp.getComp(args[0]);

        comp.setEnabled(arg == 1);

        commandSender.sendMessage(PREFIX + comp.displayName + " is now " +
                (comp.isEnabled() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled")
        );
    }
}
