package com.ludovic;

import com.ludovic.character.Character;
import com.ludovic.command.DiceCmd;
import com.ludovic.command.RoleCmd;
import com.ludovic.config.Config;
import com.ludovic.gui.stat.StatGui;
import com.ludovic.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, Character> players = new HashMap<String, Character>();

    @Override
    public final void onLoad() {
        Config.initConfig();
    }

    @Override
    public final void onEnable() {
        StatGui.initScoreboard();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("roll").setExecutor(new DiceCmd());
        getCommand("setRole").setExecutor(new RoleCmd());
    }

    @Override
    public final void onDisable() {
        Config.saveConfig();
    }
}
