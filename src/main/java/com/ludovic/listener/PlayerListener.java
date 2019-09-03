package com.ludovic.listener;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.config.Config;
import com.ludovic.gui.stat.StatGui;
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
                ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
                helmetMeta.setColor(character.getColor());
                helmet.setItemMeta(helmetMeta);

                ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestPlate.getItemMeta();
                chestplateMeta.setColor(character.getColor());
                chestPlate.setItemMeta(chestplateMeta);

                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
                leggingsMeta.setColor(character.getColor());
                leggings.setItemMeta(leggingsMeta);

                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                bootsMeta.setColor(character.getColor());
                boots.setItemMeta(bootsMeta);

                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(block.getLocation().add(0.5,1, 0.5), EntityType.ARMOR_STAND);
                stand.setVisible(true);
                stand.setArms(true);
                stand.setHelmet(helmet);
                stand.setChestplate(chestPlate);
                stand.setLeggings(leggings);
                stand.setBoots(boots);
                stand.setCustomNameVisible(true);
                stand.setCustomName(character.getName());
            }
        }


    }

}
