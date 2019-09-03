package com.ludovic.character;

import org.bukkit.Color;

public class Character {
    private int level, life, init;
    private String name;
    private Color color;

    public Character(int level, int life, int init, String name, Color color) {
        this.level = level;
        this.life = life;
        this.init = init;
        this.name = name;
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
