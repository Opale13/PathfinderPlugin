package com.ludovic.character;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Mob {
    private String name, blockSet, size;
    private Color color;

    public Mob(String name, String size, Color color, String blockSet) {
        this.name = name;
        this.blockSet = blockSet;
        this.color = color;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getBlockSet() {
        return blockSet;
    }

    public Color getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public void createArmorStand(Player player, Location location, String name, Color color) {
        Location position = location;

        if (size.equals("M")) {
            position = location.add(0.5,1, 0.5);
        } else if (size.equals("G")) {
            position = location.add(0,1, 0);
        }

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);

        ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestPlate.getItemMeta();
        chestplateMeta.setColor(color);
        chestPlate.setItemMeta(chestplateMeta);

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsMeta.setColor(color);
        leggings.setItemMeta(leggingsMeta);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(color);
        boots.setItemMeta(bootsMeta);

        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(position, EntityType.ARMOR_STAND);
        stand.setVisible(true);
        stand.setArms(true);
        stand.setHelmet(helmet);
        stand.setChestplate(chestPlate);
        stand.setLeggings(leggings);
        stand.setBoots(boots);
        stand.setGravity(false);
        stand.setCustomNameVisible(true);
        stand.setCustomName(name);
    }
}
