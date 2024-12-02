package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public abstract class ARandomEntitiesDeath extends ARandomEntities {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!challenge.isEnabled() || !timer.isRunning() || event.getEntityType().equals(EntityType.BLAZE))
            return;

        instance.log(event.getEventName(), Debuglevel.LEVEL_3);

        handleRandomEntityDeathLogic(event);
    }

    public abstract void handleRandomEntityDeathLogic(EntityDeathEvent event);

    public ARandomEntitiesDeath(ChallengesPluginNeo instance, Plugin plugin, ChallengeTimer timer, Challenge challenge) {
        super(instance, plugin, timer, challenge);
    }
}
