package com.ciryusmedia.challengenetwork.challengespluginneo.core.console;

import org.bukkit.ChatColor;

import static org.bukkit.Bukkit.getServer;

public class ChallengeLogger {

    private static final ChallengeLogger LOGGER = new ChallengeLogger();

    private static String messagePrefix = "";
    private static int debugLevel = 0;

    public void debug(String message, int level) {
        debug(message, DebugLevel.getDebugLevel(level));
    }

    public void debug(String message, DebugLevel level) {
        ChatColor color = level.color;
        if (level.level <= debugLevel) {
            getServer().getConsoleSender().sendMessage(messagePrefix + color + message);
        }
    }

    public void log(String message) {
        debug(message, DebugLevel.LEVEL_0);
    }

    public void error(String message) {
        debug(ChatColor.RED + message, DebugLevel.LEVEL_0);
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

    public ChallengeLogger() {

    }

    public static ChallengeLogger getLogger() {
        return LOGGER;
    }
}
