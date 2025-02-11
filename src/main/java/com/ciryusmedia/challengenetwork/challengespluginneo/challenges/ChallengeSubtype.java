package com.ciryusmedia.challengenetwork.challengespluginneo.challenges;

public enum ChallengeSubtype {
    //<editor-fold desc="Random" defaultstate="collapsed">
    RANDOM_BLOCKS(ChallengeType.RANDOM),
    RANDOM_MOBS(ChallengeType.RANDOM),

    //</editor-fold>

    //<editor-fold desc="Sync" defaultstate="collapsed">
    INVENTORY_SYNC(ChallengeType.SYNC);
    //<editor-fold>

    public final ChallengeType parentType;

    ChallengeSubtype(ChallengeType parentType) {
        this.parentType = parentType;
    }

}
