package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.system;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.ChallengeTimer;
import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockBreakListener implements Listener {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();
    ChallengeTimer timer = plugin.getTimer();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!timer.isRunning()) {
            plugin.log("Cancelling block breaking", Debuglevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        if (!timer.isRunning()) {
            plugin.log("Cancelling leaves decaying", Debuglevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!timer.isRunning()) {
            plugin.log("Cancelling blocks exploding", Debuglevel.LEVEL_3);
            e.setYield(0);
        }
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent e) {
        if (!timer.isRunning()) {
            plugin.log("Cancelling block being destroyed", Debuglevel.LEVEL_3);
            e.setCancelled(true);
        }
    }

}
