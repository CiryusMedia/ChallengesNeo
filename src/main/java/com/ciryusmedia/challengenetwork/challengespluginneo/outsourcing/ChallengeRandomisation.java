package com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

public class ChallengeRandomisation {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();
    Plugin plugin = ChallengesPluginNeo.getPlugin(ChallengesPluginNeo.class);
//    FileConfiguration randomBlocksLoottableConfig = instance.getRandomBlocksLoottableConfig();
//    FileConfiguration randomMobsLoottableConfig = instance.getRandomMobsLoottableConfig();

    public Material[] allBlocks = Arrays.stream(Material.values()).filter(Material::isBlock).toArray(Material[]::new);
    //public Map<Material, ItemStack> randomBlockLoottableMap = new HashMap<>();

    public EntityType[] allEntities = EntityType.values();
    //public Map<EntityType, List<ItemStack>> randomMobLoottableMap = new HashMap<>();

    public void initRandomLoottable() {

        instance.log("Setting default config for challenge randomisation stuff", Debuglevel.LEVEL_3);
        if (!plugin.getConfig().contains("UnsafeRandomEnchantments"))
            plugin.getConfig().set("UnsafeRandomEnchantments", true);

        if (!plugin.getConfig().contains("UnsafeEnchantmentBounds"))
            plugin.getConfig().set("UnsafeEnchantmentBounds", 100);

        instance.log("Saving config", Debuglevel.LEVEL_3);
        plugin.saveConfig();

        initRandomBlockLoottable();

        initRandomMobLoottable();
    }

    private void initRandomBlockLoottable() {
        instance.log("Initiating block loottable", Debuglevel.LEVEL_3);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(instance.getRandomBlocksLoottableConfigFile());
        for (Material allBlock : allBlocks) {
            instance.log(allBlock.name(), Debuglevel.LEVEL_4);
            ItemStack randomItem = getRandomItem();
            if (config.contains(allBlock.name()) && config.getItemStack(allBlock.name()) != null) {
                instance.log("Item in loottable file found", Debuglevel.LEVEL_4);
                randomItem = config.getItemStack(allBlock.name());
            } else {
                instance.log("Item in loottable file not found", Debuglevel.LEVEL_4);
            }

            //randomBlockLoottableMap.put(allBlock, randomItem);
            config.set(allBlock.name(), randomItem);
            try {
                config.save(instance.getRandomBlocksLoottableConfigFile());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void initRandomMobLoottable() {
        instance.log("Initiating mob loottable", Debuglevel.LEVEL_3);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(instance.getRandomMobsLoottableConfigFile());
        for (EntityType allEntity : allEntities) {
            instance.log(allEntity.name(), Debuglevel.LEVEL_4);
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
                    instance.log("Loottable list file found", Debuglevel.LEVEL_4);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            } else {
                instance.log("Loottable list file not found", Debuglevel.LEVEL_4);
            }

            //randomMobLoottableMap.put(allEntity, randomDrops);
            config.set(allEntity.name(), randomDrops);
            instance.saveRandomMobsLoottableConfig();
            try {
                config.save(instance.getRandomMobsLoottableConfigFile());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public @NotNull ItemStack getRandomItem() {
        ItemStack randomItem = new ItemStack(getRandomItemMaterial());
        Material randomItemMaterial = randomItem.getType();

        if (randomItemMaterial.equals(Material.ENCHANTED_BOOK)) {
            instance.log(randomItemMaterial.name() + " Book", Debuglevel.LEVEL_5);
            Enchantment randomEnchantment = getRandomEnchantment();
            int level;
            level = getRandomEnchantmentLevel(randomEnchantment);
            randomItem.addUnsafeEnchantment(randomEnchantment, level);
        } else if (randomItemMaterial.equals(Material.POTION) || randomItemMaterial.equals(Material.LINGERING_POTION) || randomItemMaterial.equals(Material.SPLASH_POTION) || randomItemMaterial.equals(Material.TIPPED_ARROW)) {
            instance.log(randomItemMaterial.name() + " Potion", Debuglevel.LEVEL_5);
            randomItem.setItemMeta(getRandomPotionMeta(randomItem));
        }

        instance.log("Random " + randomItemMaterial.name(), Debuglevel.LEVEL_4);
        return randomItem;
    }

    public Material getRandomItemMaterial() {
        Random random = new Random();
        Material material;
        Material[] itemMaterials = Arrays.stream(Material.values()).filter(Material::isItem).toArray(Material[]::new);

        material = itemMaterials[random.nextInt(itemMaterials.length)];

        return material;
    }

    public Enchantment getRandomEnchantment() {
        return Enchantment.values()[new Random().nextInt(Enchantment.values().length)];
    }

    public int getRandomEnchantmentLevel(Enchantment randomEnchantment) {
        boolean isUnsafe = plugin.getConfig().getBoolean("UnsafeRandomEnchantments");
        int level;
        if (isUnsafe) {
            level = getUnsafeEnchantmentLevel(plugin.getConfig().getInt("UnsafeEnchantmentBounds"));
        } else {
            level = getSafeEnchantmentLevel(randomEnchantment);
        }
        return level;
    }

    public int getSafeEnchantmentLevel(Enchantment enchantment) {
        return new Random().nextInt(enchantment.getMaxLevel()) + 1;
    }

    public int getUnsafeEnchantmentLevel(int bounds) {
        return new Random().nextInt(bounds);
    }

    public @NotNull PotionMeta getRandomPotionMeta(ItemStack randomItem) {
        //TODO This shit fucks shit up.
        // Should now really be fixed, currently large scale testing
        boolean isUnsafe = plugin.getConfig().getBoolean("UnsafeRandomPotions");
        PotionMeta randomPotionMeta = (PotionMeta) randomItem.getItemMeta();
        PotionType randomPotionType = getRandomPotionType();
        List<PotionEffect> randomPotionTypeEffects = randomPotionType.getPotionEffects();
        PotionEffect randomPotionEffect = !randomPotionTypeEffects.isEmpty() ? randomPotionTypeEffects.getFirst() : null;
//        PotionEffectType randomPotionEffectType = null;
//        while (randomPotionEffectType == null) { //TODO Rewrite to non-deprecation (Done?)
//            randomPotionEffectType = randomPotionType.getEffectType();
//        }
        int duration = getPotionDuration(plugin.getConfig().getInt("RandomPotionDuration"));
        int level;
        if (isUnsafe) {
            level = getUnsafePotionLevel(plugin.getConfig().getInt("UnsafeRandomPotionBounds"));
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

//        randomPotionMeta.setColor(randomPotionEffectType.getColor());
        randomPotionMeta.setColor(customRandomPotionEffect.getType().getColor());
        return randomPotionMeta;
    }

    public PotionType getRandomPotionType() {
        return PotionType.values()[new Random().nextInt(PotionType.values().length)];
    }

    public int getUnsafePotionLevel(int bounds) {
        return new Random().nextInt(bounds);
    }

    public int getSafePotionLevel(PotionType potionType) {
        return new Random().nextInt(potionType.getMaxLevel());
    }

    public int getPotionDuration(int bounds) {
        return (new Random().nextInt(bounds) + 1) * 20 * 60;
    }

    public ChallengeRandomisation() {
        initRandomLoottable();
        //plugin.getServer().getAsyncScheduler().runNow(plugin, task -> initRandomLoottable());
    }
}
