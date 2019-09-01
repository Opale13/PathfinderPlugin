package com.ludovic.command;

import com.ludovic.Main;
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
        RoleEnum role = Main.players.get(player.getUniqueId().toString()).getRole();
        int numberGenerate = 0;

        try {
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage(ChatColor.AQUA + "                 ROLL                   ");
            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage(role.getColor() + role.getPrefix() + " " + player.getName() +
                    ChatColor.RESET + " Roll");
            Bukkit.broadcastMessage("");

            numberGenerate = dice.computeDice(dice, args[0]);

            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("Result: " + numberGenerate);
            return true;

        } catch (Exception e) {
            commandSender.sendMessage(e.getMessage());

            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("Error rolling");
            return false;

        } finally {
            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage(ChatColor.AQUA + "                END ROLL                ");
            Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
            Bukkit.broadcastMessage("");
        }

    }
}
