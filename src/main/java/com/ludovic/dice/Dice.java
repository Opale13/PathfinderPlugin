package com.ludovic.dice;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {
    private Random random = new Random();
    private List<Integer> diceRoll = new ArrayList<Integer>();
    private String dicePattern = "([0-9]{0,3})d([0-9]{1,3})([\\+-]?)([0-9]{1,3})?";

    /**
     * Constructor
     */
    public Dice() {
    }

    /**
     * Compute a random number between 1 and the dice's number
     * @param diceNumber
     * @return
     */
    public void rollDice(int rollNumber, int diceNumber) {
        for (int i = 0; i < rollNumber; i++) {
            this.diceRoll.add(random.nextInt((diceNumber - 1) + 1) + 1);

            Bukkit.broadcastMessage("Roll " + "d" + diceNumber + ": " + this.diceRoll.get(i));
        }
    }

    /**
     * Add or remove the mod at the rollDice
     * @param mod
     * @param sign
     * @return
     */
    public Integer computeMod(int mod, String sign) {
        int computeNumber = 0;

        for (int dice : this.diceRoll) {
            computeNumber += dice;
        }

        switch (sign) {
            case "+":
                Bukkit.broadcastMessage("Add mod: " + computeNumber + " + " + mod);
                computeNumber += mod;
                break ;
            case "-":
                Bukkit.broadcastMessage("Remove mod: " + computeNumber + " - " + mod);
                computeNumber -= mod;
                break;
        }

        return computeNumber;
    }

    /**
     * Clear the dice list
     */
    public void clearDiceRoll() {
        diceRoll.clear();
    }

    /**
     * Get the dice pattern
     * @return
     */
    public String getDicePattern() {
        return dicePattern;
    }
}
