package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import org.bukkit.event.Listener;

public abstract class ARandomEntities extends AChallengeListener implements Listener {

    ChallengeRandomisation rro;

    public ARandomEntities(Challenge challenge) {
        super(challenge);
        this.rro = plugin.getRro();
    }
}
