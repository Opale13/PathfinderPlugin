package com.ludovic.utils;

import org.bukkit.Color;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final List<String> colors = new ArrayList<String>() {
        {
            add("white");
            add("silver");
            add("gray");
            add("black");
            add("red");
            add("maroon");
            add("yellow");
            add("olive");
            add("lime");
            add("green");
            add("aqua");
            add("teal");
            add("blue");
            add("navy");
            add("fuchsia");
            add("purple");
            add("orange");
        }
    };

    public static Color translateColor(String name) {
        switch (name) {
            case "SILVER":
                return Color.SILVER;

            case "GRAY":
                return Color.GRAY;

            case "BLACK":
                return Color.BLACK;

            case "RED":
                return Color.RED;

            case "MAROON":
                return Color.MAROON;

            case "YELLOW":
                return Color.YELLOW;

            case "OLIVE":
                return Color.OLIVE;

            case "LIME":
                return Color.LIME;

            case "GREEN":
                return Color.GREEN;

            case "AQUA":
                return Color.AQUA;

            case "TEAL":
                return Color.TEAL;

            case "BLUE":
                return Color.BLUE;

            case "NAVY":
                return Color.NAVY;

            case "FUCHSIA":
                return Color.FUCHSIA;

            case "PURPLE":
                return Color.PURPLE;

            case "ORANGE":
                return Color.ORANGE;
            default:
                return Color.WHITE;
        }
    }

    public static List<String> getColors() {
        return colors;
    }
}
