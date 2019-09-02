package com.ludovic.listener;

import com.ludovic.Main;
import com.ludovic.config.Config;
import com.ludovic.character.RoleEnum;
import com.ludovic.gui.stat.StatGui;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

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
        RoleEnum role = Main.getPlayersRole(event.getPlayer());
        event.setFormat(role.getColor() + role.getPrefix() + " " + event.getPlayer().getName() + " " + ChatColor.RESET + event.getMessage());
    }

    @EventHandler
    private void playerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = event.getItem();
            Block block = event.getClickedBlock();

            if (item.getType() == Material.ARMOR_STAND) {
                ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
                helmetMeta.setColor(Color.AQUA);
                helmet.setItemMeta(helmetMeta);

                ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestPlate.getItemMeta();
                chestplateMeta.setColor(Color.AQUA);
                chestPlate.setItemMeta(chestplateMeta);

                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
                leggingsMeta.setColor(Color.AQUA);
                leggings.setItemMeta(leggingsMeta);

                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                bootsMeta.setColor(Color.AQUA);
                boots.setItemMeta(bootsMeta);

                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(block.getLocation().add(0.5,1, 0.5), EntityType.ARMOR_STAND);
                stand.setVisible(true);
                stand.setArms(true);
                stand.setHelmet(helmet);
                stand.setChestplate(chestPlate);
                stand.setLeggings(leggings);
                stand.setBoots(boots);
            }
        }


    }

}
