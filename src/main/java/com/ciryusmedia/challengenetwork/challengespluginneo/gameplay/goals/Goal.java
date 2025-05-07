package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Goal implements ItemUtil {
    //<editor-fold desc="Success" defaultstate="collapsed">
    KILL_ENDER_DRAGON(true, GoalType.SUCCESS,
            "kill_ender_dragon",
            "Kill Ender Dragon",
            new ItemStack(Material.DRAGON_EGG),
            new String[]{"Beat the challenge by", "killing the Ender Dragon"}),
    GET_ALL_ADVANCEMENTS(false, GoalType.SUCCESS,
            "get_all_advancements",
            "Get all advancements",
            new ItemStack(Material.ENCHANTED_BOOK),
            new String[]{"Beat the challenge by", "getting all advancements"}),
    //</editor-fold>

    //<editor-fold desc="Success" defaultstate="collapsed">
    PLAYER_DEATH(true, GoalType.FAILURE,
            "player_death",
            "Player death",
            new ItemStack(Material.PLAYER_HEAD),
            new String[]{"Fail the challenge because", "a player dies"}),
    ;
    //</editor-fold>

    private static final ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    private boolean enabled;
    public final GoalType type;
    public final String key;
    public final String displayName;
    public final ItemStack item;
    public final List<String> description;

    public void updateItem() { //TODO duplicated with same method in Challenge.java -> Extract?
        updateItem(this.item, this.description, this.enabled, this.displayName);
//        ItemMeta itemMeta = item.getItemMeta();
//        List<String> lore = new ArrayList<>(description);
//
//        itemMeta.setEnchantmentGlintOverride(enabled);
//
//        itemMeta.setDisplayName(enabled ? ChatColor.GREEN + displayName : ChatColor.RED + displayName);
//        lore.add(""); //Empty spacer line
//        lore.add(displayName + " is currently " + (enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled"));
//
//        itemMeta.setLore(lore);
//
//        item.setItemMeta(itemMeta);
    }

    public void updateEnabled() {
        enabled = plugin.getConfig().getBoolean(key);
        updateItem();
    }

    public void enable() {
        Goal.goals(type)
                .forEach(Goal::disableGoal);
        setEnabled(true);
        plugin.getConfig().set(key, true);
        plugin.saveConfig();
    }

    public void disable() {
        setEnabled(false);
        plugin.getConfig().set(key, false);
        plugin.saveConfig();
    }

    public static void updateAllEnabled() {
        Arrays.stream(values()).toList().forEach(Goal::updateEnabled);
    }

    public static boolean anyEnabled(GoalType type) {
        return Arrays.stream(values()).anyMatch(goal -> goal.type == type);
    }

    public static void enableGoal(Goal goal) {
        Goal.goals(goal.type).stream().filter(g -> g != goal).forEach(Goal::disableGoal);
        goal.setEnabled(true);
        plugin.getConfig().set(goal.key, true);
    }

    public static void disableGoal(Goal goal) {
        goal.setEnabled(false);
        plugin.getConfig().set(goal.key, false);
    }

    public static List<Goal> goals(GoalType type) {
        return goals().stream().filter(g -> g.type.equals(type)).collect(Collectors.toList());
    }

    public static List<Goal> goals() {
        return List.of(values());
    }

    Goal(boolean enabled, GoalType type, String key, String displayName, ItemStack item, String[] description) {
        this.enabled = enabled;
        this.type = type;
        this.key = key;
        this.displayName = displayName;
        this.item = item;
        this.description = Arrays.stream(description).toList();

        updateItem();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
