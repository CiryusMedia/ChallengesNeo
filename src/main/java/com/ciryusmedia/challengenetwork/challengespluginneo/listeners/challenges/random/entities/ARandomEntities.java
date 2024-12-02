package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.random.entities;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.outsourcing.ChallengeRandomisation;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class ARandomEntities implements Listener {

    ChallengesPluginNeo instance;
    ChallengeRandomisation rro;
    Plugin plugin;
    ChallengeTimer timer;
    Challenge challenge;

    public ARandomEntities(ChallengesPluginNeo instance, Plugin plugin, ChallengeTimer timer, Challenge challenge) {
        this.instance = instance;
        this.rro = instance.getRro();
        this.plugin = plugin;
        this.timer = timer;
        this.challenge = challenge;
    }
}
