package com.ciryusmedia.challengenetwork.challengespluginneo.console;

import org.bukkit.ChatColor;

import static org.bukkit.Bukkit.getServer;

public class ChallengeDebugger {

    private static final ChallengeDebugger DEBUGGER = new ChallengeDebugger();

    private static String messagePrefix = "";
    private static int debugLevel = 0;

    public void log(String message, int level) {
        log(message, DebugLevel.getDebugLevel(level));
    }

    public void log(String message, DebugLevel level) {
        ChatColor color = level.color;
        if (level.level <= debugLevel) {
            getServer().getConsoleSender().sendMessage(messagePrefix + color + message);
        }
    }

    /**
     * Sets the message prefix of debug messages to your chosen prefix
     * @param prefix A message prefix
     */
    public void setMessagePrefix(String prefix) {
        messagePrefix = prefix;
    }

    /**
     * Sets the level of debug, how "deep" the debug messages should go
     * @param level DebugLevel
     */
    public void setDebugLevel(int level) {
        debugLevel = level;
    }

    public void setDebugLevel(DebugLevel level) {
        debugLevel = level.level;
    }

    public String getMessagePrefix() {
        return messagePrefix;
    }

    public int getDebugLevel() {
        return debugLevel;
    }

    public ChallengeDebugger() {

    }

    public static ChallengeDebugger getDebugger() {
        return DEBUGGER;
    }
}
