package com.ciryusmedia.challengenetwork.challengespluginneo.core.util;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public abstract class ColorWoolUtils {

    //Wool map
    public static final Map<String, Material> COLOR_WOOL_MAP = new HashMap<>(){{
        put("BLACK", Material.BLACK_WOOL);
        put("DARK_BLUE", Material.BLUE_WOOL);
        put("DARK_GREEN", Material.GREEN_WOOL);
        put("DARK_AQUA", Material.CYAN_WOOL);
        put("DARK_RED", Material.RED_WOOL);
        put("DARK_PURPLE", Material.PURPLE_WOOL);
        put("GOLD", Material.YELLOW_WOOL);
        put("GRAY", Material.LIGHT_GRAY_WOOL);
        put("DARK_GRAY", Material.GRAY_WOOL);
        put("BLUE", Material.BLUE_WOOL);
        put("GREEN", Material.LIME_WOOL);
        put("AQUA", Material.CYAN_WOOL);
        put("RED", Material.RED_WOOL);
        put("LIGHT_PURPLE", Material.MAGENTA_WOOL);
        put("YELLOW", Material.YELLOW_WOOL);
        put("WHITE", Material.WHITE_WOOL);
    }};

    //ChatColors: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
    //Wool Colors: White, Black; Red, Green, Yellow, Orange, Magenta, Cyan, Light_blue, Lime, Pink, Gray, Light_gray

    public static Material colorStringToWool(String colorString) {
        return isValidChatColor(colorString) ? COLOR_WOOL_MAP.get(colorString.toUpperCase()) : null;
    }

    public static boolean isWool(Material material) {
        return COLOR_WOOL_MAP.containsValue(material);
    }

    public static boolean isValidChatColor(String colorString) {
        return COLOR_WOOL_MAP.get(colorString.toUpperCase()) != null;
    }
}
