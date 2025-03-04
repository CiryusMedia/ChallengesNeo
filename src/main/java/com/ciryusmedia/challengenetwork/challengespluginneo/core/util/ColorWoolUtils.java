package com.ciryusmedia.challengenetwork.challengespluginneo.core.util;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public abstract class ColorWoolUtils {

    //Wool map
    public static final Map<String, Material> COLOT_WOOL_MAP = new HashMap<>(){{
        COLOT_WOOL_MAP.put("BLACK", Material.BLACK_WOOL);
        COLOT_WOOL_MAP.put("DARK_BLUE", Material.BLUE_WOOL);
        COLOT_WOOL_MAP.put("DARK_GREEN", Material.GREEN_WOOL);
        COLOT_WOOL_MAP.put("DARK_AQUA", Material.CYAN_WOOL);
        COLOT_WOOL_MAP.put("DARK_RED", Material.RED_WOOL);
        COLOT_WOOL_MAP.put("DARK_PURPLE", Material.PURPLE_WOOL);
        COLOT_WOOL_MAP.put("GOLD", Material.YELLOW_WOOL);
        COLOT_WOOL_MAP.put("GRAY", Material.LIGHT_GRAY_WOOL);
        COLOT_WOOL_MAP.put("DARK_GRAY", Material.GRAY_WOOL);
        COLOT_WOOL_MAP.put("BLUE", Material.BLUE_WOOL);
        COLOT_WOOL_MAP.put("GREEN", Material.LIME_WOOL);
        COLOT_WOOL_MAP.put("AQUA", Material.CYAN_WOOL);
        COLOT_WOOL_MAP.put("RED", Material.RED_WOOL);
        COLOT_WOOL_MAP.put("LIGHT_PURPLE", Material.MAGENTA_WOOL);
        COLOT_WOOL_MAP.put("YELLOW", Material.YELLOW_WOOL);
        COLOT_WOOL_MAP.put("WHITE", Material.WHITE_WOOL);
    }};

    //ChatColors: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
    private static final String[] woolColorNames = new String[]{"white", "black", "red", "green", "yellow", "orange", "magenta", "cyan", "light_blue", "lime", "pink", "gray", "light_gray"};

    public static Material colorStringToWool(String colorString) {

        Material returnWool = Material.WHITE_WOOL;

        if (isValidChatColor(colorString)) {
            returnWool = COLOT_WOOL_MAP.get(colorString.toUpperCase());
        }

        return returnWool;
    }

    public static boolean isWool(Material material) {
        return COLOT_WOOL_MAP.containsValue(material);
    }

    public static boolean isValidChatColor(String colorString) {
        return COLOT_WOOL_MAP.get(colorString.toUpperCase()) != null;
    }
}
