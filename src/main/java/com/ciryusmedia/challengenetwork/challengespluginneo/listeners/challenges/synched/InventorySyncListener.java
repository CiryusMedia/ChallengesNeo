package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.synched;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class InventorySyncListener extends AChallengeListener implements Listener {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getInstance();

    @EventHandler
    public void onPlayerInventorySlotChange(PlayerInventorySlotChangeEvent e) {

        if (!challenge.isEnabled() || !timer.isRunning()) {
            return;
        }

        instance.log(e.getEventName(), Debuglevel.LEVEL_4);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.getInventory().setContents(e.getPlayer().getInventory().getContents());
            player.getInventory().setArmorContents(e.getPlayer().getInventory().getArmorContents());
        });
    }

    public InventorySyncListener(Challenge challenge) {
        super(challenge);
    }
}
