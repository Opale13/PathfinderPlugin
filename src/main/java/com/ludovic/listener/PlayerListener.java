package com.ludovic.listener;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.character.Mob;
import com.ludovic.config.Config;
import com.ludovic.gui.stat.StatGui;
import com.ludovic.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
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
        StatGui.updateScoreboard(event.getPlayer());

        Main.spellZone.put(event.getPlayer().getUniqueId().toString(), "s1");
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
        Character character = Main.players.get(event.getPlayer().getUniqueId().toString());
        event.setFormat("[" + character.getName() + "] " + event.getMessage());
    }

    @EventHandler
    private void playerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Character character = Main.players.get(player.getUniqueId().toString());

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = event.getItem();
            Block block = event.getClickedBlock();

            if (item.getType() == Material.ARMOR_STAND) {
                Utils.createArmorStand(player, block.getLocation(), character.getName(), character.getColor());

            } else if (item.getType() == Material.STICK) {
                Utils.spawnRange(player, block);

            } else {
                for (Mob mob : Main.mobList) {
                    String blockSet = mob.getBlockSet();

                    if (item.getType() == Material.valueOf(blockSet.toUpperCase())) {
                        mob.createArmorStand(player, block);
                        mob.setMobLocation(block.getLocation(), player.getLocation().getYaw());
                        event.setCancelled(true);
                    }
                }
            }
        }

    }

    @EventHandler
    private void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            for (Mob mob : Main.mobList) {
                Location entityLocalisation = event.getEntity().getLocation();
                Location mobLocalistation = mob.getMobLocation();

                if (entityLocalisation.equals(mobLocalistation)) {
                    // Take the block under the mob
                    Block block = event.getDamager().getWorld().getBlockAt(entityLocalisation.add(0,-1,0));
                    mob.removeArmorStand(block);
                    break;
                }
            }
        }
    }
}

