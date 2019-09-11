package com.ludovic.character;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Mob {
    private String name, helmet, blockSet, size;
    private Color color;
    private Location mobLocation;
    private String blockMaterial;

    public Mob(String name, String helmet, String size, Color color, String blockSet) {
        this.name = name;
        this.blockSet = blockSet;
        this.color = color;
        this.helmet = helmet;
        this.size = size;
    }

    public String getBlockSet() {
        return blockSet;
    }

    public Location getMobLocation() {
        return mobLocation;
    }

    public void setMobLocation(Location mobLocation) {
        this.mobLocation = mobLocation;
        this.mobLocation.add(0,1,0);
    }


    public void createArmorStand(Player player, Block block) {
        float standYaw = player.getLocation().getYaw() + 180;
        this.blockMaterial = block.getType().name();

        Location blockLocation = block.getLocation();
        Location position = new Location(player.getWorld(), blockLocation.getX(), blockLocation.getY(), blockLocation.getZ());

        if (size.equals("M")) {
            position.add(0.5,1, 0.5);
        } else if (size.equals("G")) {
            position.add(0,1, 0);

            Block rightBottomBlock = block;
            Block leftBottomBlock = block.getRelative(-1, 0, 0);
            Block rightTopBlock = block.getRelative(0, 0, -1);
            Block leftTopBlock = block.getRelative(-1, 0, -1);

            rightBottomBlock.setType(Material.WHITE_WOOL);
            leftBottomBlock.setType(Material.WHITE_WOOL);
            rightTopBlock.setType(Material.WHITE_WOOL);
            leftTopBlock.setType(Material.WHITE_WOOL);
        }

        position.setYaw(standYaw);

        ItemStack helmet = new ItemStack(Material.valueOf(this.helmet));
        if (this.helmet.equals("LEATHER_HELMET")) {
            LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
            helmetMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
        }


        ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestPlate.getItemMeta();
        chestplateMeta.setColor(this.color);
        chestPlate.setItemMeta(chestplateMeta);

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsMeta.setColor(this.color);
        leggings.setItemMeta(leggingsMeta);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(this.color);
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
        stand.setCustomName(this.name + " [" + this.size + "]");
        stand.setBasePlate(false);
    }

    public void removeArmorStand(Block block) {
        block.setType(Material.valueOf(this.blockMaterial));
        block.getRelative(-1, 0, 0).setType(Material.valueOf(this.blockMaterial));
        block.getRelative(0, 0, -1).setType(Material.valueOf(this.blockMaterial));
        block.getRelative(-1, 0, -1).setType(Material.valueOf(this.blockMaterial));
    }
}
