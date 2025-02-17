package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.listeners.challenges.AChallengeListener;
import org.bukkit.event.Listener;

public abstract class ARandomEntities extends AChallengeListener implements Listener {

    public ARandomEntities(Challenge challenge) {
        super(challenge);
    }
}
