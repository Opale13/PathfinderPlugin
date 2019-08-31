package com.ludovic.command;

import com.ludovic.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCmd implements CommandExecutor {

    /**
     * Reload the configuration file
     * @param commandSender The player which run the command
     * @param command The run command
     * @param s idk
     * @param args The args of the command
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Config.reloadConfig();

        return true;
    }
}
