package com.ludovic;

import com.ludovic.command.DiceCmd;
import com.ludovic.command.RoleCmd;
import com.ludovic.config.Config;
import com.ludovic.listener.PlayerListener;
import com.ludovic.role.RoleEnum;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, RoleEnum> players = new HashMap<String, RoleEnum>();

    @Override
    public final void onLoad() {
        Config.initConfig();
    }

    @Override
    public final void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("roll").setExecutor(new DiceCmd());
        getCommand("setRole").setExecutor(new RoleCmd());
    }

    @Override
    public final void onDisable() {
        Config.saveConfig();
    }
}
