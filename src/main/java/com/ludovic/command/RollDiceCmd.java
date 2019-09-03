package com.ludovic.command;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.character.RoleEnum;
import com.ludovic.dice.Dice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RollDiceCmd implements CommandExecutor {
    private Dice dice;

    public RollDiceCmd(Dice dice) {
        this.dice = dice;
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
        Player player = Bukkit.getPlayer(commandSender.getName());
        Character character = Main.players.get(player.getUniqueId().toString());
        int numberGenerate = 0;

        if (commandSender instanceof Player) {
            try {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage(ChatColor.AQUA + "                 ROLL                   ");
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage(character.getName() + " Roll");
                Bukkit.broadcastMessage("");

                if (args.length == 0) {
                    numberGenerate = dice.computeDice(dice, "d20");

                } else {
                    numberGenerate = dice.computeDice(dice, args[0]);
                }

                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("Result: " + numberGenerate);
                return true;

            } catch (Exception e) {
                commandSender.sendMessage(e.getMessage());
                Bukkit.broadcastMessage("");
                return false;

            } finally {
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage(ChatColor.AQUA + "                END ROLL                ");
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage("");
            }

        } else {
            commandSender.sendMessage("/roll (# of dice)d<# of faces>(+ or -)(mod)");
        }

        return true;

    }
}
