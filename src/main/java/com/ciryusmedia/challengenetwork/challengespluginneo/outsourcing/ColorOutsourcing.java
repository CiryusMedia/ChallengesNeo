package com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ColorOutsourcing {

    //Wool map
    public final Map<String, String> chatColorToWool = new HashMap<>(); //


    //ChatColors: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
    private final String[] woolColorNames = new String[] {"white", "black", "red", "green", "yellow", "orange", "magenta", "cyan", "light_blue", "lime", "pink", "gray", "light_gray"};

    public Material colorStringToWool(String colorString) {

        Material returnWool = Material.WHITE_WOOL;

        if (isValidChatColor(colorString)) {
            returnWool = Material.valueOf(chatColorToWool.get(colorString.toUpperCase()));
        }

        return returnWool;
    }

    public boolean isWool(Material material) {
        return chatColorToWool.containsValue(material.name());
    }

    public boolean isValidChatColor(String colorString) {
        return chatColorToWool.get(colorString.toUpperCase()) != null;
    }

    public void initChatColorToItemColorMap() {
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

    public ColorOutsourcing() {
        initChatColorToItemColorMap();
    }
}
