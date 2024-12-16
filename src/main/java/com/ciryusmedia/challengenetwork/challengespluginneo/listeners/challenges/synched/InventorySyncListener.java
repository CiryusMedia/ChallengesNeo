package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.synched;

import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.Challenge;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class InventorySyncListener extends AChallengeListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        //TODO Player clicks on an item in the inventory
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        //TODO Player drags items in his inventory
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent e) {
        //TODO Item gets damaged
    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent e) {
        //TODO Player drops item
    }

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent e) {
        //TODO Player picks up item
    }

    public InventorySyncListener(Challenge challenge) {
        super(challenge);
    }
}
