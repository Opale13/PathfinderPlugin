package com.ludovic.command;

import com.google.common.collect.Lists;
import com.ludovic.character.RoleEnum;
import com.ludovic.config.Config;
import com.ludovic.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class SetColorCmd implements CommandExecutor, TabCompleter {

    /**
     * Change the color of the character
     * @param commandSender The player which run the command
     * @param command The run command
     * @param s idk
     * @param args The args of the command
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            if (commandSender instanceof Player) {
                Player player = ((Player) commandSender).getPlayer();
                Config.changeColor(player, Utils.translateColor(args[0].toUpperCase()));
                commandSender.sendMessage("Your color has been set in " + args[0]);
            }

        } else {
            commandSender.sendMessage("/setColor <color>");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tabComplete = Lists.newArrayList();
        if(args.length == 1) {
            for(String color : Utils.getColors())
                if(color.toLowerCase().startsWith(args[0].toLowerCase()))
                    tabComplete.add(color);
            return tabComplete;
        }
        return null;
    }
}
