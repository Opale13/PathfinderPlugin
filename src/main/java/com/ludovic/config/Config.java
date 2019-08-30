package com.ludovic.config;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.character.RoleEnum;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
     * @param player
     */
    public static void loadPlayer(Player player) {
        Character character;
        String uuid = player.getUniqueId().toString();

        if (config.getString(uuid + ".name") == null) {
            player.sendMessage("in if");
            character = new Character(1, 0, 0, player.getName(), RoleEnum.PLAYER);
            saveDataPlayer(player, character);
        }

        character = getDataPlayer(player);

        Main.players.put(uuid, character);
        saveConfig();
    }

    /**
     * Allows to change the role of one player
     * @param player
     * @param role
     */
    public static void changeRole(Player player, RoleEnum role) {
        String uuid = player.getUniqueId().toString();
        Character character = Main.players.get(uuid);
        character.setRole(role);

        Main.players.put(uuid, character);

        // TODO reload scoreboard
    }

    /**
     * Allows to get the role of one player
     * @param player
     * @return
     */
    public static RoleEnum getPlayersRole(Player player) {
        return getPlayerCharachter(player).getRole();
    }

    public static Character getPlayerCharachter(Player player) {
        String uuid = player.getUniqueId().toString();

        return Main.players.get(uuid);
    }

    /**
     * Delete a player into config file
     * @param player
     */
    public static void deletePlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        Character character = getPlayerCharachter(player);

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



    private static Character getDataPlayer(Player player) {
        String uuid = player.getUniqueId().toString();

        String name = config.getString(uuid + ".name");
        int level = config.getInt(uuid + ".level");
        int life = config.getInt(uuid + ".life");
        int init = config.getInt(uuid + ".init");
        RoleEnum role = RoleEnum.valueOf(config.getString(uuid + ".role"));

        return new Character(level, life, init, name, role);
    }

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
