package com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.synched;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import com.ciryusmedia.challengenetwork.challengespluginneo.challenges.ChallengeOld;
import com.ciryusmedia.challengenetwork.challengespluginneo.interfaces.Debuglevel;
import com.ciryusmedia.challengenetwork.challengespluginneo.listeners.challenges.AChallengeListener;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventorySyncListener extends AChallengeListener implements Listener {

    ChallengesPluginNeo instance = ChallengesPluginNeo.getChallengePlugin();

    @EventHandler
    public void onPlayerInventorySlotChange(PlayerInventorySlotChangeEvent e) {

        if (!challengeOld.isEnabled() || !timer.isRunning()) {
            return;
        }

        instance.log(e.getEventName(), Debuglevel.LEVEL_4);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.getInventory().setContents(e.getPlayer().getInventory().getContents());
            player.getInventory().setArmorContents(e.getPlayer().getInventory().getArmorContents());
        });
    }

    public InventorySyncListener(ChallengeOld challengeOld) {
        super(challengeOld);
    }
}
