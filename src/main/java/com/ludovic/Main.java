package com.ludovic;

import com.ludovic.character.Character;
import com.ludovic.command.*;
import com.ludovic.config.Config;
import com.ludovic.dice.Dice;
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
    public final void onEnable() {
        Config.initConfig();
        StatGui.initScoreboard();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("roll").setExecutor(new RollDiceCmd());
        getCommand("hroll").setExecutor(new RollDiceHiddenCmd());
        getCommand("init").setExecutor(new InitCmd());
        getCommand("life").setExecutor(new LifeCmd());
        getCommand("reloadConfig").setExecutor(new ReloadConfigCmd());
        getCommand("setColor").setExecutor(new SetColorCmd());
    }

    @Override
    public final void onDisable() {
        Config.saveConfig();
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
