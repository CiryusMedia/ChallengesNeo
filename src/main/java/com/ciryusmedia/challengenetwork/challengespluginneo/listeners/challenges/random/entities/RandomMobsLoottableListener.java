package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class RandomMobsLoottableListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        ChallengeRandomisation.checkInitialized();
        YamlConfiguration loottable = YamlConfiguration.loadConfiguration(plugin.getRandomMobsLoottableConfigFile());
        plugin.log("Drops: " + event.getDrops().size(), Debuglevel.LEVEL_4);
        plugin.log("Clearing drops", Debuglevel.LEVEL_3);
        event.getDrops().clear();
        plugin.log("Replacing drops", Debuglevel.LEVEL_3);
        event.getDrops().addAll((Collection<? extends ItemStack>) loottable.getList(event.getEntityType().name()));
        plugin.log(event.getDrops().toString(), Debuglevel.LEVEL_4);
    }

    public RandomMobsLoottableListener(ChallengeOld challengeOld) {
        super(challengeOld);
    }
}
