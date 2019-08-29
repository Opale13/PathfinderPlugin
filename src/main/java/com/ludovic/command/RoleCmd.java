package com.ludovic.command;

import com.google.common.collect.Lists;
import com.ludovic.config.Config;
import com.ludovic.character.RoleEnum;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class RoleCmd implements CommandExecutor, TabCompleter {

    /**
     * Command to change the role off one player
     * @param commandSender
     * @param command
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            commandSender.sendMessage("The player was not found");
            return false;
        }

        RoleEnum role = null;
        try {
            role = RoleEnum.valueOf(args[1].toUpperCase());
        } catch (Exception e) {
            commandSender.sendMessage("Role was not found");
            return false;
        }

        Config.changeRole(target, role);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tabComplete = Lists.newArrayList();
        if(args.length == 2){
            for(RoleEnum role : RoleEnum.values())
                if(role.getName().toLowerCase().startsWith(args[1].toLowerCase()))
                    tabComplete.add(role.getName());
            return tabComplete;
        }
        return null;
    }
}
