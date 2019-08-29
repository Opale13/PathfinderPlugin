package com.ludovic.config;

import com.ludovic.Main;
import com.ludovic.role.Role;
import com.ludovic.role.RoleEnum;
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
     * @param player
     */
    public static void loadPlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        int idRole = config.getInt(uuid);

        Main.players.put(uuid, Role.getRoleById(idRole));
    }

    /**
     * Allows to change the role of one player
     * @param player
     * @param role
     */
    public static void changeRole(Player player, RoleEnum role) {
        String uuid = player.getUniqueId().toString();
        config.set(uuid, role.getId());
        Main.players.put(uuid, role);

        saveConfig();
    }

    /**
     * Allows to get the role of one player
     * @param player
     * @return
     */
    public static RoleEnum getPlayersRole(Player player) {
        String uuid = player.getUniqueId().toString();

        return Main.players.get(uuid);
    }

    /**
     * Delete a player into config file
     * @param player
     */
    public static void deletePlayer(Player player) {
        String uuid = player.getUniqueId().toString();
        Main.players.remove(uuid);
    }

    /**
     * Save the config file
     */
    public static void saveConfig(){
        try{
            config.save(file);
        } catch(IOException ioe){ioe.printStackTrace();}
    }
}
