package com.ludovic.dice;

import net.minecraft.server.v1_14_R1.IPlayerFileData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dice {
    private String display;
    private Player player;
    private Random random = new Random();
    private List<Integer> diceRoll = new ArrayList<Integer>();
    private String dicePattern = "([0-9]{0,3})d([0-9]{1,3})([\\+-]?)([0-9]{1,3})?";

    /**
     * Constructor
     */
    public Dice(String display) {
        this.display = display;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int computeDice(Dice dice, String diceArgs) throws Exception {
        Pattern dicePattern = Pattern.compile(dice.getDicePattern());
        Matcher matcher = dicePattern.matcher(diceArgs);

        // Total generate diceRolls + mod
        int numberGenerate = 0;

        // Number od dice - #d
        int diceNumber = 1;

        if (matcher.matches()) {
            if (!matcher.group(1).equals("")) {
                diceNumber = Integer.parseInt(matcher.group(1));
            }

            // Roll the dice
            try {
                dice.rollDice(diceNumber, Integer.parseInt(matcher.group(2)));
            } catch (Exception e) {
                throw new Exception("Can't convert " + matcher.group(2) + " into number");
            }

            // Without mod like /roll 8d8
            if (matcher.group(3).equals("")) {
                numberGenerate = dice.computeMod(0, "+");

            // With mod like /roll 8d8+5
            } else {
                try {
                    numberGenerate = dice.computeMod(Integer.parseInt(matcher.group(4)), matcher.group(3));
                } catch (Exception e) { throw new Exception("Can't convert " + matcher.group(4) + " into number"); }
            }

        } else {
            throw new Exception("/roll (# of dice)d<# of faces>(+ or -)(mod)");
        }

        return numberGenerate;
    }

    /**
     * Compute a random number between 1 and the dice's number
     * @param diceNumber
     * @return
     */
    private void rollDice(int rollNumber, int diceNumber) {
        for (int i = 0; i < rollNumber; i++) {
            this.diceRoll.add(random.nextInt((diceNumber - 1) + 1) + 1);

            if (display.equals("broadcast")) {
                Bukkit.broadcastMessage("Roll " + "d" + diceNumber + ": " + this.diceRoll.get(i));

            } else {
                player.sendMessage("Roll " + "d" + diceNumber + ": " + this.diceRoll.get(i));
            }
        }
    }

    /**
     * Add or remove the mod at the rollDice
     * @param mod
     * @param sign
     * @return
     */
    private Integer computeMod(int mod, String sign) {
        int computeNumber = 0;

        for (int dice : this.diceRoll) {
            computeNumber += dice;
        }

        switch (sign) {
            case "+":
                if (display.equals("broadcast")) {
                    Bukkit.broadcastMessage("Add mod: " + computeNumber + " + " + mod);
                } else {
                    player.sendMessage("Add mod: " + computeNumber + " + " + mod);
                }

                computeNumber += mod;
                break;

            case "-":
                if (display.equals("broadcast")) {
                    Bukkit.broadcastMessage("Remove mod: " + computeNumber + " - " + mod);
                } else {
                    player.sendMessage("Add mod: " + computeNumber + " - " + mod);
                }

                computeNumber -= mod;
                break;
        }

        diceRoll.clear();
        return computeNumber;
    }

    /**
     * Get the dice pattern
     * @return
     */
    public String getDicePattern() {
        return dicePattern;
    }
}
