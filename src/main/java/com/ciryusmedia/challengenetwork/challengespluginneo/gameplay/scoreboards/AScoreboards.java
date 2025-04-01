package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.scoreboards;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public abstract class AScoreboards {

    protected final Plugin plugin;
    protected final Scoreboard scoreboard;

    protected Objective objective;
    protected BukkitRunnable updater;

    public abstract void createScoreboard();

    public void startUpdater() {
        updater = new BukkitRunnable() {
            public void run() {
                update();
            }
        };
        updater.runTaskTimer(plugin, 0, 5);
    }

    public void stopUpdater() {
        if (updater != null) {
            updater.cancel();
        }
    }

    protected abstract void update();

    public AScoreboards(Plugin plugin, Scoreboard scoreboard) {
        this.plugin = plugin;
        this.scoreboard = scoreboard;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }
}
