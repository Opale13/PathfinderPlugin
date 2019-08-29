package com.ludovic.role;

import org.bukkit.ChatColor;

public enum RoleEnum {
    PLAYER(0, "Player", ChatColor.WHITE),
    MJ(1, "MJ", ChatColor.GOLD),
    BARBARIAN(2, "Barbarian", ChatColor.DARK_RED),
    BARD(3, "Bard", ChatColor.RED),
    CHAMPION(4, "Champion", ChatColor.BLACK),
    CLERIC(5, "Cleric", ChatColor.YELLOW),
    DRUID(6, "Druid", ChatColor.DARK_GREEN),
    FIGHTER(7, "Fighter", ChatColor.DARK_GRAY),
    MONK(8, "Monk", ChatColor.GRAY),
    RANGER(9, "Ranger",ChatColor.GREEN),
    ROGUE(10, "Rogue",ChatColor.BLUE),
    SORCERER(11, "Sorcerer",ChatColor.DARK_BLUE),
    WIZARD(12, "Wizard", ChatColor.AQUA);


    private int id;
    private String prefix, name;
    private ChatColor color;

    RoleEnum(int id, String name, ChatColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.prefix = "[" + this.name + "]";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public ChatColor getColor() {
        return color;
    }
}
