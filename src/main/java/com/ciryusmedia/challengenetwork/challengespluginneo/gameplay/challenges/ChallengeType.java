package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges;

import java.util.Arrays;

public enum ChallengeType {
    RANDOM("random"),
    SYNC("sync");

    public final String name;

    public static boolean isValidType(String type) {
        return Arrays.stream(values()).anyMatch(challengeType -> challengeType.name.equalsIgnoreCase(type));
    }

    ChallengeType(String name) {
        this.name = name;
    }
}
