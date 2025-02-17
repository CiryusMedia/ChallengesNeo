package com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.exceptions.DataNotInitializedException;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.console.DebugLevel;
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

public class ChallengeRandomisation {

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

            //randomMobLoottableMap.put(allEntity, randomDrops);
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
        Material randomItemMaterial = randomItem.getType();

        if (randomItemMaterial.equals(Material.ENCHANTED_BOOK)) {
            DEBUGGER.log(randomItemMaterial.name() + " Book", DebugLevel.LEVEL_5);
            Enchantment randomEnchantment = getRandomEnchantment();
            int level;
            level = getRandomEnchantmentLevel(randomEnchantment);
            randomItem.addUnsafeEnchantment(randomEnchantment, level);
        } else if (randomItemMaterial.equals(Material.POTION) || randomItemMaterial.equals(Material.LINGERING_POTION) || randomItemMaterial.equals(Material.SPLASH_POTION) || randomItemMaterial.equals(Material.TIPPED_ARROW)) {
            DEBUGGER.log(randomItemMaterial.name() + " Potion", DebugLevel.LEVEL_5);
            randomItem.setItemMeta(getRandomPotionMeta(randomItem));
        }

        DEBUGGER.log("Random " + randomItemMaterial.name(), DebugLevel.LEVEL_4);
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
        boolean isUnsafe = PLUGIN.getConfig().getBoolean("UnsafeRandomEnchantments");
        int level;
        if (isUnsafe) {
            level = getUnsafeEnchantmentLevel(PLUGIN.getConfig().getInt("UnsafeEnchantmentBounds"));
        } else {
            level = getSafeEnchantmentLevel(randomEnchantment);
        }
        return level;
    }

    public static int getSafeEnchantmentLevel(Enchantment enchantment) {
        return new Random().nextInt(enchantment.getMaxLevel()) + 1;
    }

    public static int getUnsafeEnchantmentLevel(int bounds) {
        return new Random().nextInt(bounds);
    }

    public static @NotNull PotionMeta getRandomPotionMeta(ItemStack randomItem) {
        boolean isUnsafe = PLUGIN.getConfig().getBoolean("UnsafeRandomPotions");
        PotionMeta randomPotionMeta = (PotionMeta) randomItem.getItemMeta();
        PotionType randomPotionType = getRandomPotionType();
        List<PotionEffect> randomPotionTypeEffects = randomPotionType.getPotionEffects();
        PotionEffect randomPotionEffect = !randomPotionTypeEffects.isEmpty() ? randomPotionTypeEffects.getFirst() : null;
        int duration = getPotionDuration(PLUGIN.getConfig().getInt("RandomPotionDuration"));
        int level;
        if (isUnsafe) {
            level = getUnsafePotionLevel(PLUGIN.getConfig().getInt("UnsafeRandomPotionBounds"));
        } else {
            level = getSafePotionLevel(randomPotionType);
        }

        PotionEffectType backupEffectType = PotionEffectType.SPEED; //Backup for when randomPotionTypeEffects is empty (for whatever reason)

        PotionEffect customRandomPotionEffect;

        if (randomPotionEffect != null) {
            customRandomPotionEffect = new PotionEffect(randomPotionEffect.getType(), duration, level);
        } else {
            customRandomPotionEffect = new PotionEffect(backupEffectType, duration, level);;
        }

        randomPotionMeta.clearCustomEffects();
        randomPotionMeta.addCustomEffect(customRandomPotionEffect, true);

        String customPotionItemName = "Potion of a random effect";
        if (randomItem.getType().equals(Material.TIPPED_ARROW)) {
            customPotionItemName = "Arrow of a random effect";
        }
        randomPotionMeta.setDisplayName(ChatColor.RESET + customPotionItemName);

        randomPotionMeta.setColor(customRandomPotionEffect.getType().getColor());
        return randomPotionMeta;
    }

    public static PotionType getRandomPotionType() {
        return PotionType.values()[new Random().nextInt(PotionType.values().length)];
    }

    public static int getUnsafePotionLevel(int bounds) {
        return new Random().nextInt(bounds);
    }

    public static int getSafePotionLevel(PotionType potionType) {
        return new Random().nextInt(potionType.getMaxLevel());
    }

    public static int getPotionDuration(int bounds) {
        return (new Random().nextInt(bounds) + 1) * 20 * 60;
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
