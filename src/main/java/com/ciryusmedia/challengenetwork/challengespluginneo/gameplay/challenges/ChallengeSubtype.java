package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges;

import java.util.Arrays;

public enum ChallengeSubtype {
    //<editor-fold desc="Random" defaultstate="collapsed">
    RANDOM_BLOCKS("randomBlocks", ChallengeType.RANDOM),
    RANDOM_MOBS("randomMobs", ChallengeType.RANDOM),

    //</editor-fold>

    //<editor-fold desc="Sync" defaultstate="collapsed">
    INVENTORY_SYNC("inventorySync", ChallengeType.SYNC);
    //<editor-fold>

    public final String name;
    public final ChallengeType parentType;

    public static boolean isValidSubtype(String type) {
        return Arrays.stream(values()).anyMatch(challengeType -> challengeType.name.equalsIgnoreCase(type));
    }

    ChallengeSubtype(String name, ChallengeType parentType) {
        this.name = name;
        this.parentType = parentType;
    }

}
