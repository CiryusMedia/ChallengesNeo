package com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengesOutsourcing;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChallengeComplete implements TabCompleter {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ChallengesOutsourcing.checkInitialized();
        List<String> list = new ArrayList<>();

        switch (strings.length) {
            case 1:
                list.add("random");
                list.add("sync");
                break;
            case 2:
                plugin.log(strings[0] + " " + ChallengesOutsourcing.isValidType(strings[0]), Debuglevel.LEVEL_4);
                if (ChallengesOutsourcing.isValidType(strings[0])) {
                    ChallengesOutsourcing.getChallengesFromType(strings[0]).forEach(challenge -> {
                        list.add(challenge.getName());
                    });
                }
                break;
            case 3:
                list.add("true");
                list.add("false");
                list.add("on");
                list.add("off");
        }

        return list;
    }

}
