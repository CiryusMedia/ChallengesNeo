package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import org.bukkit.event.Listener;

public abstract class ARandomEntities extends AChallengeListener implements Listener {

    public ARandomEntities(ChallengeOld challengeOld) {
        super(challengeOld);
    }
}
