package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.goal;

import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.goals.Goal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EnderdragonDeathListener extends AGoal implements Listener {

    public static final EnderdragonDeathListener INST = new EnderdragonDeathListener();
    private static final ChallengeLogger LOGGER = ChallengeLogger.getLogger();

    @EventHandler
    public void onDragonDeath(EntityDeathEvent event) {
        if (! goal.isEnabled()) return;
        if (!(event.getEntity() instanceof EnderDragon)) return;

        if (timer.isRunning()) {
            beatRun(event.getEntity().getKiller());
            return;
        }
    }

    public EnderdragonDeathListener() {
        goal = Goal.KILL_ENDER_DRAGON;
    }
}
