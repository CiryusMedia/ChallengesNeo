package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class RandomMobsLoottableListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        instance.log("Clearing drops", Debuglevel.LEVEL_3);
        event.getDrops().clear();
        instance.log("Replacing drops", Debuglevel.LEVEL_3);
        event.getDrops().addAll(rro.randomMobLoottableMap.get(event.getEntityType()));
        instance.log(event.getDrops().toString(), Debuglevel.LEVEL_4);
    }

    public RandomMobsLoottableListener(ChallengesPluginNeo instance, Plugin plugin, ChallengeTimer timer, Challenge challenge) {
        super(instance, plugin, timer, challenge);
    }
}
