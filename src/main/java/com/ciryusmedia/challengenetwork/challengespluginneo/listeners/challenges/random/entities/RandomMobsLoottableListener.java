package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class RandomMobsLoottableListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        YamlConfiguration config = instance.getConfigFromFile(instance.getRandomMobsLoottableConfigFile());
        instance.log("Drops: " + event.getDrops().size(), Debuglevel.LEVEL_4);
        instance.log("Clearing drops", Debuglevel.LEVEL_3);
        event.getDrops().clear();
        instance.log("Replacing drops", Debuglevel.LEVEL_3);
        event.getDrops().addAll((Collection<? extends ItemStack>) config.getList(event.getEntityType().name()));
        instance.log(event.getDrops().toString(), Debuglevel.LEVEL_4);
    }

    public RandomMobsLoottableListener(Challenge challenge) {
        super(challenge);
    }
}
