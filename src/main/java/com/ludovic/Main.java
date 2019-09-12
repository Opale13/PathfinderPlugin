package com.ludovic;

import com.ludovic.character.Character;
import com.ludovic.character.Mob;
import com.ludovic.command.*;
import com.ludovic.config.Config;
import com.ludovic.dice.Dice;
import com.ludovic.gui.stat.StatGui;
import com.ludovic.listener.PlayerListener;
import com.ludovic.utils.ReadJson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, Character> players = new HashMap<String, Character>();
    public static List<Mob> mobList = new ArrayList<Mob>();
    public static Map<String, String> spellZone = new HashMap<String, String>();

    @Override
    public final void onEnable() {
        Config.initConfig();
        StatGui.initScoreboard();

        try {
            ReadJson.initJson();
            ReadJson.readJson();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("roll").setExecutor(new RollDiceCmd());
        getCommand("hroll").setExecutor(new RollDiceHiddenCmd());
        getCommand("init").setExecutor(new InitCmd());
        getCommand("life").setExecutor(new LifeCmd());
        getCommand("reloadConfig").setExecutor(new ReloadConfigCmd());
        getCommand("setColor").setExecutor(new SetColorCmd());
        getCommand("zone").setExecutor(new SpellZone());
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
