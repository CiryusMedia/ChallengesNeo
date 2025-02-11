package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public abstract class ARandomEntitiesDeath extends ARandomEntities {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!challengeOld.isEnabled() || !timer.isRunning() || event.getEntityType().equals(EntityType.BLAZE))
            return;



        plugin.log(event.getEventName(), Debuglevel.LEVEL_3);

        handleRandomEntityDeathLogic(event);
    }

    public abstract void handleRandomEntityDeathLogic(EntityDeathEvent event);

    public ARandomEntitiesDeath(ChallengeOld challengeOld) {
        super(challengeOld);
    }
}
