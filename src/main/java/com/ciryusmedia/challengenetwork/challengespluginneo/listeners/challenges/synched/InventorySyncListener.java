package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.synched;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.system.console.DebugLevelOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventorySyncListener extends AChallengeListener implements Listener {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getChallengePlugin();

    @EventHandler
    public void onPlayerInventorySlotChange(PlayerInventorySlotChangeEvent e) {

        if (!challenge.enabled || !timer.isRunning()) {
            return;
        }

        instance.log(e.getEventName(), DebugLevelOld.LEVEL_4);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.getInventory().setContents(e.getPlayer().getInventory().getContents());
            player.getInventory().setArmorContents(e.getPlayer().getInventory().getArmorContents());
        });
    }

    public InventorySyncListener(Challenge challenge) {
        super(challenge);
    }
}
