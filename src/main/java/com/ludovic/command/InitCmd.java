package com.ludovic.command;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.character.RoleEnum;
import com.ludovic.config.Config;
import com.ludovic.dice.Dice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InitCmd implements CommandExecutor {
    private Dice dice;

    public InitCmd(Dice dice) {
        this.dice = dice;
    }

    /**
     * Compute the init /init 5
     * @param commandSender The player which run the command
     * @param command The run command
     * @param s idk
     * @param args The args of the command
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player && args.length > 0) {
            Player player = ((Player) commandSender).getPlayer();
            Character character = Main.players.get(player.getUniqueId().toString());
            int init = 0;

            try {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage(ChatColor.AQUA + "                 INIT                   ");
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage(character.getName() + " Roll init");
                Bukkit.broadcastMessage("");

                init = dice.computeDice(dice, "d20" + args[0]);
                Config.changeInit(player, init);

                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("Result: " + init);
                return true;

            } catch (Exception e) {
                commandSender.sendMessage(e.getMessage());
                Bukkit.broadcastMessage("");
                return false;

            } finally {
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage(ChatColor.AQUA + "                END INIT                ");
                Bukkit.broadcastMessage(ChatColor.GOLD + "----------------------------------------");
                Bukkit.broadcastMessage("");
            }


        } else {
            commandSender.sendMessage("/init <+/- mod>");
        }

        return true;
    }
}
