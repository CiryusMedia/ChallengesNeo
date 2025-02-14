package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.system;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.ChallengeDebugger;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevel;
import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockBreakListener implements Listener {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    private static final ChallengeDebugger DEBUGGER = ChallengeDebugger.getDebugger();
    ChallengeTimer timer = plugin.getTimer();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!timer.isRunning()) {
            DEBUGGER.log("Cancelling block breaking", DebugLevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        if (!timer.isRunning()) {
            DEBUGGER.log("Cancelling leaves decaying", DebugLevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!timer.isRunning()) {
            DEBUGGER.log("Cancelling blocks exploding", DebugLevel.LEVEL_3);
            e.setYield(0);
        }
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent e) {
        if (!timer.isRunning()) {
            DEBUGGER.log("Cancelling block being destroyed", DebugLevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

}
