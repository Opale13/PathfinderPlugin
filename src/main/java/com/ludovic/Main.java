package com.ludovic;

import com.ludovic.character.Character;
import com.ludovic.character.RoleEnum;
import com.ludovic.command.RollDiceCmd;
import com.ludovic.command.ReloadConfigCmd;
import com.ludovic.command.SetRoleCmd;
import com.ludovic.config.Config;
import com.ludovic.gui.stat.StatGui;
import com.ludovic.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, Character> players = new HashMap<String, Character>();

    @Override
    public final void onLoad() {
    }

    @Override
    public final void onEnable() {
        Config.initConfig();
        StatGui.initScoreboard();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("roll").setExecutor(new RollDiceCmd());
        getCommand("setRole").setExecutor(new SetRoleCmd());
        getCommand("reloadConfig").setExecutor(new ReloadConfigCmd());
    }

    @Override
    public final void onDisable() {
        Config.saveConfig();
    }


    /**
     * Allows to get the role of one player
     * @param player Player target
     * @return
     */
    public static RoleEnum getPlayersRole(Player player) {
        return getPlayerCharacter(player).getRole();
    }

    /**
     * Return the character of the player
     * @param player Player target
     * @return
     */
    public static Character getPlayerCharacter(Player player) {
        String uuid = player.getUniqueId().toString();

        return Main.players.get(uuid);
    }
}
