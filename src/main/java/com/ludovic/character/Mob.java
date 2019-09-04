package com.ludovic.character;

import com.ludovic.utils.Utils;
import org.bukkit.Color;

public class Mob {
    private String name, helmet, chestPlate, leggings, boots, blockSet;
    private Color color;

    public Mob(String name, String helmet, String chestPlate, String leggings, String boots, Color color, String blockSet) {
        this.name = name;
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggings = leggings;
        this.boots = boots;
        this.blockSet = blockSet;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getHelmet() {
        return helmet;
    }

    public String getChestPlate() {
        return chestPlate;
    }

    public String getLeggings() {
        return leggings;
    }

    public String getBoots() {
        return boots;
    }

    public String getBlockSet() {
        return blockSet;
    }

    public Color getColor() {
        return color;
    }
}
