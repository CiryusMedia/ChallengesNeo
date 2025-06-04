package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.comp;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ConfigPaths;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ItemUtil;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum Comp implements ItemUtil, ConfigPaths { //Comp for Competitive and Composition :)
    COOP(true, "coop", "Co-op", Material.GOLDEN_APPLE, new String[]{}),
    FFA(false, "ffa", "Free for all", Material.DIAMOND_SWORD, new String[]{}),
//    TEAMS(false, "teams", "Teams", Material.IRON_SWORD, new String[]{}), //TODO I honestly don't want to implement this until version 3.0.0
    ;

    private static final ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeLogger LOGGER = ChallengeLogger.getLogger();

    private boolean enabled;
    public final String key;
    public final String path;
    public final String displayName;
    public final GuiItemStack item;
    public final List<String> description;

    public void updateItem() {
        updateItem(this.item, this.description, this.enabled, this.displayName);
    }

    public void updateEnabled() {
        enabled = plugin.getConfig().getBoolean(path);
        updateItem();
    }

    public static void updateAllEnabled() {
        Arrays.stream(values()).toList().forEach(Comp::updateEnabled);
    }

    public static boolean anyEnabled() {
        return Arrays.stream(values()).anyMatch(comp -> comp.enabled);
    }

    public static List<Comp> comps() {
        return List.of(values());
    }

    public static Comp getComp(String key) {
        return comps().stream().filter(c -> c.key.equals(key)).findFirst().orElse(null);
    }

    public static boolean isCoop() {
        return COOP.isEnabled();
    }

    Comp(boolean enabled, String key, String displayName, Material itemMaterial, String[] description) {
        this(enabled, key, displayName, new GuiItemStack(itemMaterial, displayName, key), description);
    }

    Comp(boolean enabled, String key, String displayName, GuiItemStack item, String[] description) {
        this.enabled = enabled;
        this.key = key;
        this.path = GOAL_PREFIX + key;
        this.displayName = displayName;
        this.item = item;
        this.description = Arrays.stream(description).toList();

        updateItem();
    }

    public void setEnabled(boolean enabled) {
        LOGGER.debug("Setting comp " + key + " to " + enabled, DebugLevel.LEVEL_3);
        plugin.getConfig().set(path, enabled);
        plugin.saveConfig();
        this.enabled = enabled;
        LOGGER.debug("Comp " + key + " is now " + enabled, DebugLevel.LEVEL_3);
        updateItem();
    }

    public boolean isEnabled() {
        return enabled;
    }


}
