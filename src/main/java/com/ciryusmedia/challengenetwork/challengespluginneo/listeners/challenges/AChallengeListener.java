package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;

public abstract class AChallengeListener {

    protected ChallengesPluginNeo plugin;
    protected ChallengeTimer timer;
    protected Challenge challenge;

    public AChallengeListener(Challenge challenge) {
        this.plugin = ChallengesPluginNeo.getChallengePlugin();
        this.timer = this.plugin.getTimer();
        this.challenge = challenge;
    }
}
