package com.ludovic.command;

import com.ludovic.Main;
import com.ludovic.character.RoleEnum;
import com.ludovic.character.Character;
import com.ludovic.dice.Dice;
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
    private Dice dice;

    public RollDiceCmd() {
        dice = new Dice();
    }

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
        dice.clearDiceRoll();

        Pattern dicePattern = Pattern.compile(dice.getDicePattern());
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

            // Roll the dice
            try {
                dice.rollDice(diceNumber, Integer.parseInt(matcher.group(2)));
            } catch (ValueConversionException e) { commandSender .sendMessage("Can't convert " + matcher.group(2) + " into number"); }

            // Without mod like /roll 8d8
            if (matcher.group(3).equals("")) {
                numberGenerate = dice.computeMod(0, "+");

            // With mod like /roll 8d8+5
            } else {
                try {
                    numberGenerate = dice.computeMod(Integer.parseInt(matcher.group(4)), matcher.group(3));
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
}
