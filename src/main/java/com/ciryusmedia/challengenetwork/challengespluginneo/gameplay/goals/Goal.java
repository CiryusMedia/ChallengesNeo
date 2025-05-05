package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals;

import java.util.List;
import java.util.stream.Collectors;

public enum Goal {
    KILL_ENDER_DRAGON(true, GoalType.SUCCESS, "kill_ender_dragon", "Kill Ender Dragon"),
    GET_ALL_ADVANCEMENTS(false, GoalType.SUCCESS, "get_all_advancements", "Get all advancements"),

    PLAYER_DEATH(true, GoalType.FAILURE, "player_death", "Player death"),
    ;

    private boolean enabled;
    public final GoalType type;
    public final String key;
    public final String displayName;

    public static void enableGoal(Goal goal) {
        Goal.goals(goal.type).forEach(g -> {
            g.setEnabled(false);
        });
        goal.setEnabled(true);
    }

    public static void disableGoal(Goal goal) {
        goal.setEnabled(false);
    }

    public static List<Goal> goals(GoalType type) {
        return goals().stream().filter(g -> g.type.equals(type)).collect(Collectors.toList());
    }

    public static List<Goal> goals() {
        return List.of(values());
    }

    Goal(boolean enabled, GoalType type, String key, String displayName) {
        this.enabled = enabled;
        this.type = type;
        this.key = key;
        this.displayName = displayName;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
