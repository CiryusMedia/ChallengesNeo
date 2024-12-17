package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.event.entity.EntityDeathEvent;

public class RandomMobsLoottableListener extends ARandomEntitiesDeath {

    @Override
    public void handleRandomEntityDeathLogic(EntityDeathEvent event) {
        instance.log("Clearing drops", Debuglevel.LEVEL_3);
        event.getDrops().clear();
        instance.log("Replacing drops", Debuglevel.LEVEL_3);
        event.getDrops().addAll(rro.randomMobLoottableMap.get(event.getEntityType()));
        instance.log(event.getDrops().toString(), Debuglevel.LEVEL_4);
    }

    public RandomMobsLoottableListener(Challenge challenge) {
        super(challenge);
    }
}
