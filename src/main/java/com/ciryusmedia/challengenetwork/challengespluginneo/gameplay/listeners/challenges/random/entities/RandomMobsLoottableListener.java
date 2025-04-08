package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.util.RandomisationUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class RandomMobsLoottableListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        RandomisationUtils.checkInitialized();
        YamlConfiguration loottable = YamlConfiguration.loadConfiguration(plugin.getFileLoader().getRandomMobsLoottableConfigFile());
        LOGGER.debug("Drops: " + event.getDrops().size(), DebugLevel.LEVEL_4);
        LOGGER.debug("Clearing drops", DebugLevel.LEVEL_3);
        event.getDrops().clear();
        LOGGER.debug("Replacing drops", DebugLevel.LEVEL_3);
        event.getDrops().addAll((Collection<? extends ItemStack>) loottable.getList(event.getEntityType().name()));
        LOGGER.debug(event.getDrops().toString(), DebugLevel.LEVEL_4);
    }

    public RandomMobsLoottableListener(Challenge challenge) {
        super(challenge);
    }
}
