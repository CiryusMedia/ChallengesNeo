package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.system;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.timer.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.ChallengeLogger;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockBreakListener implements Listener {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeLogger LOGGER = ChallengeLogger.getLogger();
    ChallengeTimer timer = plugin.getTimer();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!timer.isRunning()) {
            LOGGER.debug("Cancelling block breaking", DebugLevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        if (!timer.isRunning()) {
            LOGGER.debug("Cancelling leaves decaying", DebugLevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!timer.isRunning()) {
            LOGGER.debug("Cancelling blocks exploding", DebugLevel.LEVEL_3);
            e.setYield(0);
        }
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent e) {
        if (!timer.isRunning()) {
            LOGGER.debug("Cancelling block being destroyed", DebugLevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

}
