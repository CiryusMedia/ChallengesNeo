package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;

public abstract class AChallengeListener {

    protected ChallengesPluginNeo plugin;
    protected ChallengeTimer timer;
    protected ChallengeOld challengeOld;

    public AChallengeListener(ChallengeOld challengeOld) {
        this.plugin = ChallengesPluginNeo.getChallengePlugin();
        this.timer = this.plugin.getTimer();
        this.challengeOld = challengeOld;
    }
}
