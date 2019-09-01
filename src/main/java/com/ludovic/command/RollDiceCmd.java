package com.ludovic.command;

import com.ludovic.Main;
import com.ludovic.character.RoleEnum;
import com.ludovic.character.Character;
import joptsimple.ValueConversionException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RollDiceCmd implements CommandExecutor {
    private Random random = new Random();
    private List<Integer> diceRoll = new ArrayList<Integer>();

    /**
     * When someone role a dice, we return the value
     * @param commandSender The player which run the command
     * @param command The run command
     * @param s idk
     * @param args The args of the command
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        this.diceRoll.clear();

        String pattern = "([0-9]{0,3})d([0-9]{1,3})([\\+-]?)([0-9]{1,3})?";
        Pattern dicePattern = Pattern.compile(pattern);
        Matcher matcher = dicePattern.matcher(args[0]);

        Player player = Bukkit.getPlayer(commandSender.getName());
        RoleEnum role = Main.players.get(player.getUniqueId().toString()).getRole();

        // total of the roll
        int numberGenerate = 0;

        //number of dice
        int diceNumber = 1;

        if (matcher.matches()) {
            // Get number of dice
            if (!matcher.group(1).equals("")) {
                diceNumber = Integer.parseInt(matcher.group(1));
            }

            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage(ChatColor.AQUA + "                 ROLL                   ");
            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage(role.getColor() + role.getPrefix() + " " + player.getName() +
                                    ChatColor.RESET + " Roll");
            Bukkit.broadcastMessage("");

            try {
                rollDice(diceNumber, Integer.parseInt(matcher.group(2)));
            } catch (ValueConversionException e) { commandSender .sendMessage("Can't convert " + matcher.group(2) + " into number"); }


            // Without mod like /roll 8d8
            if (matcher.group(3).equals("")) {
                numberGenerate = computeDice(0, "+");

            // With mod like /roll 8d8+5
            } else {
                try {
                    numberGenerate = computeDice(Integer.parseInt(matcher.group(4)), matcher.group(3));
                } catch (ValueConversionException e) { commandSender .sendMessage("Can't convert " + matcher.group(2) + " into number"); }
            }

            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("Result: " + numberGenerate);

            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage(ChatColor.AQUA + "                END ROLL                ");
            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage("");

        } else {
            commandSender.sendMessage("/roll (# of dice)d<# of faces>(+ or -)(mod)");

            return false;
        }

        return true;
    }


    /**
     * Compute a random number between 1 and the dice's number
     * @param diceNumber
     * @return
     */
    private void rollDice(int rollNumber, int diceNumber) {
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
    private Integer computeDice(int mod, String sign) {
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
}
