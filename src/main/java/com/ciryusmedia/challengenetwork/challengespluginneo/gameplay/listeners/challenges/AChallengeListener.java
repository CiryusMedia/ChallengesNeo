package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeDebugger;

public abstract class AChallengeListener {

    protected ChallengesPluginNeo plugin;
    protected static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();
    protected ChallengeTimer timer;
    protected Challenge challenge;

    public AChallengeListener(Challenge challenge) {
        this.plugin = ChallengesPluginNeo.getChallengePlugin();
        this.timer = this.plugin.getTimer();
        this.challenge = challenge;
    }
}
