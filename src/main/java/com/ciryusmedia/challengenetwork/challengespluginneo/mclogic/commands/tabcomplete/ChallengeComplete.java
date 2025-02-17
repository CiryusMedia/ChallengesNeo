package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.commands.tabcomplete;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeType;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.DebugLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChallengeComplete implements TabCompleter {

    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();

        switch (strings.length) {
            case 1:
                list.add("random");
                list.add("sync");
                break;
            case 2:
                DEBUGGER.log(strings[0] + " " + ChallengeType.isValidType(strings[0]), DebugLevel.LEVEL_4);
                if (ChallengeType.isValidType(strings[0])) {
                    Challenge.getChallengesFromType(strings[0]).forEach(challenge -> {
                        list.add(challenge.name);
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
