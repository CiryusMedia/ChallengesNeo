package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevelOld;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public abstract class ARandomEntitiesDeath extends ARandomEntities {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!challenge.enabled || !timer.isRunning() || event.getEntityType().equals(EntityType.BLAZE))
            return;



        plugin.log(event.getEventName(), DebugLevelOld.LEVEL_3);

        handleRandomEntityDeathLogic(event);
    }

    public abstract void handleRandomEntityDeathLogic(EntityDeathEvent event);

    public ARandomEntitiesDeath(Challenge challenge) {
        super(challenge);
    }
}
