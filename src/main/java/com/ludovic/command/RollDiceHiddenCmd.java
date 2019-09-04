package com.ludovic.command;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.dice.Dice;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RollDiceHiddenCmd implements CommandExecutor {
    private Dice dice = new Dice("player");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        int numberGenerate = 0;

        if (commandSender instanceof Player) {
            Player player = ((Player) commandSender).getPlayer();
            Character character = Main.players.get(player.getUniqueId().toString());
            dice.setPlayer(player);
            
            try {
                commandSender.sendMessage("");
                commandSender.sendMessage(ChatColor.GOLD + "----------------------------------------");
                commandSender.sendMessage(ChatColor.AQUA + "                ROLL HIDDEN             ");
                commandSender.sendMessage(ChatColor.GOLD + "----------------------------------------");
                commandSender.sendMessage(character.getName() + " Roll");
                commandSender.sendMessage("");

                if (args.length == 0) {
                    numberGenerate = dice.computeDice(dice, "d20");

                } else {
                    numberGenerate = dice.computeDice(dice, args[0]);
                }

                commandSender.sendMessage("");
                commandSender.sendMessage("Result: " + numberGenerate);

            } catch (Exception e) {
                commandSender.sendMessage(e.getMessage());
                commandSender.sendMessage("");
                return false;

            } finally {
                commandSender.sendMessage(ChatColor.GOLD + "----------------------------------------");
                commandSender.sendMessage(ChatColor.AQUA + "                END ROLL                ");
                commandSender.sendMessage(ChatColor.GOLD + "----------------------------------------");
                commandSender.sendMessage("");
            }

        }

        return true;
    }
}
