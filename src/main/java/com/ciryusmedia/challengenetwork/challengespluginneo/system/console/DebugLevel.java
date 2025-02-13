package com.ciryusmedia.challengenetwork.challengespluginneo.system.console;

import org.bukkit.ChatColor;

public enum DebugLevel {
    LEVEL_0(0, ChatColor.GREEN),
    LEVEL_1(1, ChatColor.GOLD),
    LEVEL_2(2, ChatColor.YELLOW),
    LEVEL_3(3, ChatColor.AQUA),
    LEVEL_4(4, ChatColor.BLUE),
    LEVEL_5(5, ChatColor.DARK_AQUA),;

    public final int level;
    public final ChatColor color;

    DebugLevel(int level, ChatColor color) {
        this.level = level;
        this.color = color;
    }
}
