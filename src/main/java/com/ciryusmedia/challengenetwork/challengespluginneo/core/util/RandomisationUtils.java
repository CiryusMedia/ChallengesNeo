package com.ciryusmedia.challengenetwork.challengespluginneo.core.util;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.exceptions.DataNotInitializedException;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

public abstract class RandomisationUtils {

    private static final ChallengesPluginNeo PLUGIN = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();

    private static boolean isInitialized = false;

    public static Material[] allBlocks = Arrays.stream(Material.values()).filter(Material::isBlock).toArray(Material[]::new);

    public static EntityType[] allEntities = EntityType.values();

    public static void initRandomLoottable() {

        DEBUGGER.log("Setting default config for challenge randomisation stuff", DebugLevel.LEVEL_3);
        if (!PLUGIN.getConfig().contains("UnsafeRandomEnchantments"))
            PLUGIN.getConfig().set("UnsafeRandomEnchantments", true);

        if (!PLUGIN.getConfig().contains("UnsafeEnchantmentBounds"))
            PLUGIN.getConfig().set("UnsafeEnchantmentBounds", 100);

        DEBUGGER.log("Saving config", DebugLevel.LEVEL_3);
        PLUGIN.saveConfig();

        initRandomBlockLoottable();

        initRandomMobLoottable();
    }

    private static void initRandomBlockLoottable() {
        DEBUGGER.log("Initiating block loottable", DebugLevel.LEVEL_3);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(PLUGIN.getFileLoader().getRandomBlocksLoottableConfigFile());
        for (Material allBlock : allBlocks) {
            DEBUGGER.log(allBlock.name(), DebugLevel.LEVEL_4);
            ItemStack randomItem = getRandomItem();
            if (config.contains(allBlock.name()) && config.getItemStack(allBlock.name()) != null) {
                DEBUGGER.log("Item in loottable file found", DebugLevel.LEVEL_4);
                randomItem = config.getItemStack(allBlock.name());
            } else {
                DEBUGGER.log("Item in loottable file not found", DebugLevel.LEVEL_4);
            }

            config.set(allBlock.name(), randomItem);
            try {
                config.save(PLUGIN.getFileLoader().getRandomBlocksLoottableConfigFile());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private static void initRandomMobLoottable() {
        DEBUGGER.log("Initiating mob loottable", DebugLevel.LEVEL_3);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(PLUGIN.getFileLoader().getRandomMobsLoottableConfigFile());
        for (EntityType allEntity : allEntities) {
            DEBUGGER.log(allEntity.name(), DebugLevel.LEVEL_4);
            List<ItemStack> randomDrops = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                Random r = new Random();

                if (i == 0 || r.nextInt(20) == 0) { //Every mob gets a random item and with a chance of 1:20 (5%) another item, up to 4
                    ItemStack randomItem = getRandomItem();
                    randomItem.setAmount(r.nextInt(2) + 1);

                    randomDrops.add(randomItem);
                }
            }


            if (config.contains(allEntity.name()) && config.getList(allEntity.name()) != null) {
                List<?> rmlconfigList = config.getList(allEntity.name());
                try {
                    randomDrops = (List<ItemStack>) rmlconfigList;
                    DEBUGGER.log("Loottable list file found", DebugLevel.LEVEL_4);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            } else {
                DEBUGGER.log("Loottable list file not found", DebugLevel.LEVEL_4);
            }

            config.set(allEntity.name(), randomDrops);
            try {
                config.save(PLUGIN.getFileLoader().getRandomMobsLoottableConfigFile());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static @NotNull ItemStack getRandomItem() {
        ItemStack randomItem = new ItemStack(getRandomItemMaterial());

        if (randomItem.getType().equals(Material.ENCHANTED_BOOK)) {
            DEBUGGER.log(randomItem.getType().name() + " Book", DebugLevel.LEVEL_5);
            Enchantment randomEnchantment = getRandomEnchantment();
            randomItem.addUnsafeEnchantment(randomEnchantment, getRandomEnchantmentLevel(randomEnchantment));
        } else if (randomItem.getType().equals(Material.POTION) || randomItem.getType().equals(Material.LINGERING_POTION) || randomItem.getType().equals(Material.SPLASH_POTION) || randomItem.getType().equals(Material.TIPPED_ARROW)) {
            DEBUGGER.log(randomItem.getType().name() + " Potion", DebugLevel.LEVEL_5);
            randomItem.setItemMeta(getRandomPotionMeta(randomItem));
        }

        DEBUGGER.log("Random " + randomItem.getType().name(), DebugLevel.LEVEL_4);
        return randomItem;
    }

    public static Material getRandomItemMaterial() {
        Random random = new Random();
        Material material;
        Material[] itemMaterials = Arrays.stream(Material.values()).filter(Material::isItem).toArray(Material[]::new);

        material = itemMaterials[random.nextInt(itemMaterials.length)];

        return material;
    }

    public static Enchantment getRandomEnchantment() {
        return Enchantment.values()[new Random().nextInt(Enchantment.values().length)];
    }

    public static int getRandomEnchantmentLevel(Enchantment randomEnchantment) {
        if (PLUGIN.getConfig().getBoolean("UnsafeRandomEnchantments")) {
            return getUnsafeLevel(PLUGIN.getConfig().getInt("UnsafeEnchantmentBounds"));
        } else {
            return getSafeEnchantmentLevel(randomEnchantment);
        }
    }

    public static @NotNull PotionMeta getRandomPotionMeta(ItemStack randomItem) {
        PotionEffectType backupEffectType = PotionEffectType.SPEED; //Backup for when randomPotionTypeEffects is empty (for whatever reason)

        PotionMeta randomPotionMeta = (PotionMeta) randomItem.getItemMeta();
        PotionType randomPotionType = getRandomPotionType();
        List<PotionEffect> randomPotionTypeEffects = randomPotionType.getPotionEffects();
        PotionEffect randomPotionEffect = !randomPotionTypeEffects.isEmpty() ? randomPotionTypeEffects.getFirst() : null;
        int duration = (new Random().nextInt(PLUGIN.getConfig().getInt("RandomPotionDuration")) + 1) * 20 * 60;
        int level =  PLUGIN.getConfig().getBoolean("UnsafeRandomPotions") ? getUnsafeLevel(PLUGIN.getConfig().getInt("UnsafeRandomPotionBounds")) : getSafePotionLevel(randomPotionType);

        PotionEffect customRandomPotionEffect = new PotionEffect(randomPotionEffect != null ? randomPotionEffect.getType() : backupEffectType, duration, level);

        randomPotionMeta.clearCustomEffects();
        randomPotionMeta.addCustomEffect(customRandomPotionEffect, true);

        String customPotionItemName = randomItem.getType().equals(Material.TIPPED_ARROW) ? "Arrow of a random effect" : "Potion of a random effect";
        randomPotionMeta.setDisplayName(ChatColor.RESET + customPotionItemName);

        randomPotionMeta.setColor(customRandomPotionEffect.getType().getColor());
        return randomPotionMeta;
    }

    public static PotionType getRandomPotionType() {
        return PotionType.values()[new Random().nextInt(PotionType.values().length)];
    }

    public static int getSafeEnchantmentLevel(Enchantment enchantment) {
        return new Random().nextInt(enchantment.getMaxLevel()) + 1;
    }

    public static int getSafePotionLevel(PotionType potionType) {
        return new Random().nextInt(potionType.getMaxLevel());
    }

    public static int getUnsafeLevel(int bounds) {
        return new Random().nextInt(bounds);
    }

    public static void checkInitialized() throws DataNotInitializedException {
        if (!isInitialized) {
            throw new DataNotInitializedException("ColorOutsourcing from ChallengesPluginNeo is not initialized!");
        }
    }

    public static void initRandomisation() {
        DEBUGGER.log("Initiating rro", DebugLevel.LEVEL_2);
        initRandomLoottable();
        isInitialized = true;
    }
}
