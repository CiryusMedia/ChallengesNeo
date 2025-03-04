package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.AChallengeListener;
import org.bukkit.event.Listener;

public abstract class ARandomEntities extends AChallengeListener implements Listener {

    public ARandomEntities(Challenge challenge) {
        super(challenge);
    }
}
