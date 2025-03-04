package com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.synched;

import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.core.console.DebugLevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.gameplay.listeners.challenges.AChallengeListener;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventorySyncListener extends AChallengeListener implements Listener {

    @EventHandler
    public void onPlayerInventorySlotChange(PlayerInventorySlotChangeEvent e) {

        if (!challenge.enabled || !timer.isRunning()) {
            return;
        }

        DEBUGGER.log(e.getEventName(), DebugLevel.LEVEL_4);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.getInventory().setContents(e.getPlayer().getInventory().getContents());
            player.getInventory().setArmorContents(e.getPlayer().getInventory().getArmorContents());
        });
    }

    public InventorySyncListener(Challenge challenge) {
        super(challenge);
    }
}
