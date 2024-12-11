package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import org.bukkit.plugin.Plugin;

public abstract class AChallengeListener {

    protected ChallengesPluginNeo instance;
    protected Plugin plugin;
    protected ChallengeTimer timer;
    protected Challenge challenge;

    public AChallengeListener(Challenge challenge) {
        this.instance = ChallengesPluginNeo.getInstance();
        this.plugin = ChallengesPluginNeo.getPlugin(ChallengesPluginNeo.class);
        this.timer = this.instance.getTimer();
        this.challenge = challenge;
    }
}
