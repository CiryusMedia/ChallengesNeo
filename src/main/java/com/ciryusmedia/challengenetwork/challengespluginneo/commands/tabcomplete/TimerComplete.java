package com.ciryusmedia.challengenetwork.challengespluginneo.commands.tabcomplete;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ColorOutsourcing;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TimerComplete implements TabCompleter {


    private final ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 1) {
            List<String> args = new ArrayList<>();
            args.add("color");
            args.add("display");
            args.add("load");
            args.add("pause");
            args.add("reset");
            args.add("resume");
            args.add("set");
            args.add("start");
            args.add("stop");

            return args;
        } else if (strings.length == 2) {
            List<String> args = new ArrayList<>();
            if (strings[0].equalsIgnoreCase("display")) {
                args.add("paused");
                args.add("running");
            }

            if (strings[0].equalsIgnoreCase("color")) {
                args.add("paused");
                args.add("running");
            }

            return args;
        } else if (strings.length == 3) {
            List<String> args = new ArrayList<>();
            if (strings[0].equalsIgnoreCase("color")) {
                args.addAll(ColorOutsourcing.chatColorToWool.keySet());
            }

            return args;
        }

        return null;
    }
}
