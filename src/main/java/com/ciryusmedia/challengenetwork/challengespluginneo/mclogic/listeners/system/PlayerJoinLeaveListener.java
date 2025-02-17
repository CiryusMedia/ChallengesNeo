package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.listeners.system;

import com.ciryusmedia.challengenetwork.challengespluginneo.ChallengesPluginNeo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeaveListener implements Listener {

    ChallengesPluginNeo plugin = ChallengesPluginNeo.getChallengePlugin();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getDisplayName();

        player.setScoreboard(plugin.getScoreboard());

        event.setJoinMessage(ChatColor.GREEN + "+ " + ChatColor.WHITE + playerName);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getDisplayName();

        event.setQuitMessage(ChatColor.RED + "- " + ChatColor.WHITE + playerName);
    }

}
