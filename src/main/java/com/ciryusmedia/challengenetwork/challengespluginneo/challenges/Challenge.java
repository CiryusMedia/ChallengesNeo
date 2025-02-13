package com.ciryusmedia.challengenetwork.challengespluginneo.challenges;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Challenge {

    //<editor-fold desc="Random Challenges" defaultstate="collapsed">
    RANDOM_BLOCKS_FULL(
            "RandomBlocksFull",
            "Random Blocks Full",
            ChallengeType.RANDOM,
            ChallengeSubtype.RANDOM_BLOCKS,
            Material.STONE,
            new String[]{"Makes every Blockdrop random,", "not with a pattern, just straight up random\n"}),
    RANDOM_BLOCKS_LOOTTABLE("RandomBlocksLoottable",
            "Random Blocks Loottable",
            ChallengeType.RANDOM,
            ChallengeSubtype.RANDOM_BLOCKS,
            Material.GRASS_BLOCK,
            new String[]{"Makes every Blockdrop random, but still", "with a pattern, making it predictably random\n"}),

    RANDOM_MOBS_FULL(
            "RandomMobsFull",
            "Random Mobs Full",
            ChallengeType.RANDOM,
            ChallengeSubtype.RANDOM_MOBS,
            Material.PUFFERFISH,
            new String[]{"Makes every Mobdrop random,", "not with a pattern, just straight up random\n"}),
    RANDOM_MOBS_LOOTTABLE(
            "RandomMobsLoottable",
            "Random Mobs Loottable",
            ChallengeType.RANDOM,
            ChallengeSubtype.RANDOM_MOBS,
            Material.SNIFFER_SPAWN_EGG,
            new String[]{"Makes every Mobdrop random, but still", "with a pattern, making it predictably random\n"}),
    //</editor-fold>

    //<editor-fold desc="Sync Challenges" defaultstate="collapsed">
    INVENTORY_SYNC(
            "InventorySync",
            "Inventory Sync",
            ChallengeType.SYNC,
            ChallengeSubtype.INVENTORY_SYNC,
            Material.CRAFTING_TABLE,
            new String[]{"Gives every player the same inventory", "This means that every item in your", "inventories is identical\n"});
    //</editor-fold>

    private final ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    public static final List<Challenge> challenges = Arrays.stream(Challenge.values()).toList();

    public final String name;
    public final String displayName;
    public final List<String> description;
    public final ChallengeType type;
    public final ChallengeSubtype subType;
    public final ItemStack menuItem;
    public List<String> itemDescription;
    public boolean enabled = false;

    public void updateMenuItem() {
        ItemMeta itemMeta = menuItem.getItemMeta();
        List<String> lore = new ArrayList<>(description);

        itemMeta.setEnchantmentGlintOverride(enabled);

        itemMeta.setDisplayName(enabled ? ChatColor.GREEN + displayName : ChatColor.RED + displayName);
        lore.add(displayName + " is currently " + (enabled ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled"));

        itemMeta.setLore(lore);

        menuItem.setItemMeta(itemMeta);
    }

    public void setEnabled(boolean enabled) {
        plugin.log("Setting challenge " + name + " to " + enabled, Debuglevel.LEVEL_3);
        plugin.getConfig().set(name, enabled);
        plugin.saveConfig();
        this.enabled = enabled;
        plugin.log("Challenge " + name + " is now " + enabled, Debuglevel.LEVEL_3);
        updateMenuItem();
    }

    public static Challenge getChallengeFromName(String name) {
        if (challenges.stream().anyMatch(challenge -> challenge.name.equalsIgnoreCase(name))) {
            return challenges.stream().filter(challenge -> challenge.name.equalsIgnoreCase(name)).findFirst().get();
        } else {
            return null;
        }
    }

    public static List<Challenge> getChallengesFromType(String type) {
        return getChallengesFromType(ChallengeType.valueOf(type));
    }

    public static List<Challenge> getChallengesFromType(ChallengeType type) {
        List<Challenge> validChallenges = new ArrayList<>();

        challenges.forEach(c -> {
            if (c.type.equals(type)) validChallenges.add(c);
        });

        return validChallenges;
    }

    public static List<Challenge> getChallengesFromSubtype(String subtype) {
        return getChallengesFromSubtype(ChallengeSubtype.valueOf(subtype.toUpperCase()));
    }

    public static List<Challenge> getChallengesFromSubtype(ChallengeSubtype subtype) {
        List<Challenge> challenges = new ArrayList<>();

        Arrays.stream(values()).toList().forEach(c -> {
            if (c.subType.name.equalsIgnoreCase(subtype.name)) challenges.add(c);
        });

        return challenges;
    }

    Challenge(String name, String displayName, ChallengeType type, ChallengeSubtype subType, Material menuMaterial, String[] description) {
        this(name, displayName, type, subType, new ItemStack(menuMaterial), description);
    }

    Challenge(String name, String displayName, ChallengeType type, ChallengeSubtype subType, ItemStack menuItem, String[] description) {
        this.name = name;
        this.displayName = displayName;
        this.description = Arrays.stream(description).toList();
        this.type = type;
        this.subType = subType;
        this.menuItem = menuItem;
        updateMenuItem();
    }
}
