package com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.exceptions.DataNotInitializedException;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ColorOutsourcing {

    static ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static boolean isInitialized = false;

    //Wool map
    public static final Map<String, String> chatColorToWool = new HashMap<>(); //

    //ChatColors: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
    private static final String[] woolColorNames = new String[]{"white", "black", "red", "green", "yellow", "orange", "magenta", "cyan", "light_blue", "lime", "pink", "gray", "light_gray"};

    public static Material colorStringToWool(String colorString) {
        checkInitialized();

        Material returnWool = Material.WHITE_WOOL;

        if (isValidChatColor(colorString)) {
            returnWool = Material.valueOf(chatColorToWool.get(colorString.toUpperCase()));
        }

        return returnWool;
    }

    public static boolean isWool(Material material) {
        return chatColorToWool.containsValue(material.name());
    }

    public static boolean isValidChatColor(String colorString) {
        return chatColorToWool.get(colorString.toUpperCase()) != null;
    }

    public static void initChatColorToItemColorMap() {
        chatColorToWool.put("BLACK", "BLACK_WOOL");
        chatColorToWool.put("DARK_BLUE", "BLUE_WOOL");
        chatColorToWool.put("DARK_GREEN", "GREEN_WOOL");
        chatColorToWool.put("DARK_AQUA", "CYAN_WOOL");
        chatColorToWool.put("DARK_RED", "RED_WOOL");
        chatColorToWool.put("DARK_PURPLE", "PURPLE_WOOL");
        chatColorToWool.put("GOLD", "YELLOW_WOOL");
        chatColorToWool.put("GRAY", "LIGHT_GRAY_WOOL");
        chatColorToWool.put("DARK_GRAY", "GRAY_WOOL");
        chatColorToWool.put("BLUE", "BLUE_WOOL");
        chatColorToWool.put("GREEN", "LIME_WOOL");
        chatColorToWool.put("AQUA", "CYAN_WOOL");
        chatColorToWool.put("RED", "RED_WOOL");
        chatColorToWool.put("LIGHT_PURPLE", "MAGENTA_WOOL");
        chatColorToWool.put("YELLOW", "YELLOW_WOOL");
        chatColorToWool.put("WHITE", "WHITE_WOOL");
        //16 Colors
    }

    private static void checkInitialized() throws DataNotInitializedException {
        if (!isInitialized) {
            throw new DataNotInitializedException("ColorOutsourcing from ChallengesPluginNeo is not initialized!");
        }
    }

    public static void initColorOutsourcing() {
        plugin.log("Initiating clo", Debuglevel.LEVEL_2);
        initChatColorToItemColorMap();
        isInitialized = true;
    }
}
