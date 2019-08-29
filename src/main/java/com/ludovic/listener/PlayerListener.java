package com.ludovic.listener;

import com.ludovic.config.Config;
import com.ludovic.role.RoleEnum;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    public PlayerListener() {
    }

    /**
     * When the player join
     * @param event
     */
    @EventHandler
    private void playerJoin(PlayerJoinEvent event) {
        Config.loadPlayer(event.getPlayer());
    }

    /**
     * When the player connects
     * @param event
     */
    @EventHandler
    private void playerQuit(PlayerQuitEvent event){
        Config.deletePlayer(event.getPlayer());
    }

    /**
     * When the player talks
     * @param event
     */
    @EventHandler
    private void playerChat(AsyncPlayerChatEvent event){
        RoleEnum role = Config.getPlayersRole(event.getPlayer());
        event.setFormat(role.getColor() + role.getPrefix() + " " + event.getPlayer().getName() + " " + ChatColor.RESET + event.getMessage());
    }

}