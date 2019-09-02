package com.ludovic.command;

import com.ludovic.Main;
import com.ludovic.character.RoleEnum;
import com.ludovic.config.Config;
import com.ludovic.dice.Dice;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LifeCmd implements CommandExecutor {

    /**
     * Change the life of the players /life +5
     *
     * @param commandSender The player which run the command
     * @param command       The run command
     * @param s             idk
     * @param args          The args of the command
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Pattern pattern = Pattern.compile("([\\+-]?)([0-9]{1,3})");
        Matcher matcher = pattern.matcher(args[0]);

        if (commandSender instanceof Player && args.length == 1) {
            Player player = ((Player) commandSender).getPlayer();

            if (matcher.matches()) {

                Config.changeLife(player, Integer.parseInt(matcher.group(2)), matcher.group(1));

            } else {
                commandSender.sendMessage("/life <+/- mod>");
            }
        }

        return true;

    }
}