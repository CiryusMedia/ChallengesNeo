package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ConfigPaths;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ItemUtil;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.gui.GuiItemStack;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Goal implements ItemUtil, ConfigPaths {
    //<editor-fold desc="Success" defaultstate="collapsed">
    KILL_ENDER_DRAGON(true, GoalType.SUCCESS,
            "kill_ender_dragon",
            "Kill Ender Dragon",
            Material.DRAGON_EGG,
            new String[]{"Beat the challenge by", "killing the Ender Dragon"}),
    GET_ALL_ADVANCEMENTS(false, GoalType.SUCCESS,
            "get_all_advancements",
            "Get all advancements",
            Material.ENCHANTED_BOOK,
            new String[]{"Beat the challenge by", "getting all advancements"}),
    //</editor-fold>

    //<editor-fold desc="Failure" defaultstate="collapsed">
    PLAYER_DEATH(true, GoalType.FAILURE,
            "player_death",
            "Player death",
            Material.TOTEM_OF_UNDYING,
            new String[]{"Fail the challenge because", "a player dies"}),
    ;
    //</editor-fold>

    private static final ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeLogger LOGGER = ChallengeLogger.getLogger();

    private boolean enabled;
    public final GoalType type;
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
        Arrays.stream(values()).toList().forEach(Goal::updateEnabled);
    }

    public static boolean anyEnabled(GoalType type) {
        return Arrays.stream(values()).anyMatch(goal -> goal.type == type && goal.enabled);
    }

    public static List<Goal> goals(GoalType type) {
        return goals().stream().filter(g -> g.type.equals(type)).collect(Collectors.toList());
    }

    public static List<Goal> goals() {
        return List.of(values());
    }

    public static Goal getGoal(String key) {
        return goals().stream().filter(g -> g.key.equals(key)).findFirst().orElse(null);
    }

    Goal(boolean enabled, GoalType type, String key, String displayName, Material itemMaterial, String[] description) {
        this(enabled, type, key, displayName, new GuiItemStack(itemMaterial, displayName, key), description);
    }

    Goal(boolean enabled, GoalType type, String key, String displayName, GuiItemStack item, String[] description) {
        this.enabled = enabled;
        this.type = type;
        this.key = key;
        this.path = GOAL_PREFIX + key;
        this.displayName = displayName;
        this.item = item;
        this.description = Arrays.stream(description).toList();

        updateItem();
    }

    public void setEnabled(boolean enabled) {
        LOGGER.debug("Setting goal " + key + " to " + enabled, DebugLevel.LEVEL_3);
        plugin.getConfig().set(path, enabled);
        plugin.saveConfig();
        this.enabled = enabled;
        LOGGER.debug("Goal " + key + " is now " + enabled, DebugLevel.LEVEL_3);
        updateItem();
    }

    public boolean isEnabled() {
        return enabled;
    }

}
