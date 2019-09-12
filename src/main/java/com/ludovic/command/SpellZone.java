package com.ludovic.command;

import com.ludovic.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpellZone implements CommandExecutor {

    /**
     * /zone s5
     * @param commandSender
     * @param command
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = ((Player) commandSender).getPlayer();
            String uuid = player.getUniqueId().toString();

            if (args.length > 0) {

                if (args[0].contains("s") || args[0].contains("l") || args[0].contains("c")) {
                    Main.spellZone.put(uuid, args[0]);
                    return true;

                } else {
                    commandSender.sendMessage("/zone <s | l | c><range>");
                    return false;
                }

            } else {
                commandSender.sendMessage("/zone <s | l | c><range>");
            }
        }

        return true;
    }
}
