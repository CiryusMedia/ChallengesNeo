package com.ciryusmedia.challengenetwork.challengespluginneo.scoreboards;

import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

public class HealthScoreboard extends AScoreboards {

    public HealthScoreboard(Plugin plugin, Scoreboard scoreboard) {
        super(plugin, scoreboard);
    }

    @Override
    public void createScoreboard() {
        objective = scoreboard.registerNewObjective("health", "health", "Health");
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        objective.setRenderType(RenderType.HEARTS);
    }

    @Override
    protected void update() {

    }
}
