package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class ARandomEntities extends AChallengeListener implements Listener {

    ChallengesPluginNeo instance;
    ChallengeRandomisation rro;
    Plugin plugin;
    ChallengeTimer timer;
    Challenge challenge;

    public ARandomEntities(Challenge challenge) {
        super(challenge);
        this.rro = instance.getRro();
    }
}
