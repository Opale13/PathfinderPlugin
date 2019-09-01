package com.ludovic.config;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.character.RoleEnum;
import com.ludovic.gui.stat.StatGui;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Config {
    private static FileConfiguration config;
    private static File file;

    /**
     * Init the config file
     */
    public static void initConfig() {
        File f = new File("plugins/PathfinderPlugin");
        if (!f.exists()) f.mkdirs();

        file = new File(f, "Pathfinder.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Load the player into players list
     * @param player The player connected
     */
    public static void loadPlayer(Player player) {
        Character character;
        String uuid = player.getUniqueId().toString();

        if (config.getString(uuid + ".name") == null) {
            character = new Character(1, 0, 0, player.getName(), RoleEnum.PLAYER);
            saveDataPlayer(player, character);
        }

        character = getDataPlayer(player);

        Main.players.put(uuid, character);
        saveConfig();
    }

    /**
     * Allows to change the role of one player
     * @param player Player target
     * @param role Role target
     */
    public static void changeRole(Player player, RoleEnum role) {
        String uuid = player.getUniqueId().toString();
        Character character = Main.players.get(uuid);
        character.setRole(role);

        Main.players.put(uuid, character);
        saveDataPlayer(player, character);
        saveConfig();

        StatGui.reloadScoreboard(player);
    }

    public static void changeInit(Player player, int init) {
        String uuid = player.getUniqueId().toString();
        Character character = Main.players.get(uuid);
        character.setInit(init);

        Main.players.put(uuid, character);
        saveDataPlayer(player, character);
        saveConfig();

        StatGui.reloadScoreboard(player);
    }



    /**
     * Delete a player into config file
     * @param player The player target
     */
    public static void deletePlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        Character character = Main.getPlayerCharacter(player);

        saveDataPlayer(player, character);
        saveConfig();

        Main.players.remove(uuid);
        // TODO update scoreboard
    }

    /**
     * Save the config file
     */
    public static void saveConfig(){
        try{
            config.save(file);
        } catch(IOException ioe){ioe.printStackTrace();}
    }

    /**
     * Reload the configuration file
     */
    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);

        for (Player player : Bukkit.getOnlinePlayers()) {
            loadPlayer(player);
            StatGui.reloadScoreboard(player);
        }
    }


    /**
     * Return the player character in the config file
     * @param player The player target
     * @return
     */
    private static Character getDataPlayer(Player player) {
        String uuid = player.getUniqueId().toString();

        String name = config.getString(uuid + ".name");
        int level = config.getInt(uuid + ".level");
        int life = config.getInt(uuid + ".life");
        int init = config.getInt(uuid + ".init");
        RoleEnum role = RoleEnum.valueOf(config.getString(uuid + ".role"));

        return new Character(level, life, init, name, role);
    }

    /**
     * Set the player character in the config file
     * @param player The player target
     * @param character The character at save
     */
    private static void saveDataPlayer(Player player, Character character) {
        String uuid = player.getUniqueId().toString();

        config.set(uuid + ".name", character.getName());
        config.set(uuid + ".level", character.getLevel());
        config.set(uuid + ".life", character.getLife());
        config.set(uuid + ".init", character.getInit());
        config.set(uuid + ".role", character.getRole().getName().toUpperCase());

        saveConfig();
    }
}
