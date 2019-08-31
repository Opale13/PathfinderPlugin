package com.ludovic.gui.stat;

import com.ludovic.Main;
import com.ludovic.character.Character;
import com.ludovic.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class StatGui {
    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private static Objective objective = scoreboard.registerNewObjective("info_player", "dummy", ChatColor.GOLD + "Info player");
    private static Map<String, Score> scores = new HashMap<String, Score>();

    public StatGui() {
    }

    /**
     * Create the scoreboard on the server
     */
    public static void initScoreboard() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score title = objective.getScore(ChatColor.RESET + " Name - " +
                            ChatColor.RED + "Life" + ChatColor.RESET + " - " +
                            ChatColor.BLUE + "Init");
        title.setScore(11);
    }

    /**
     * When player join the server, the scoreboard add his information for the other players
     * @param player The player target
     */
    public static void updateScoreboard(Player player) {
        Character character = Main.getPlayerCharacter(player);
        String uuid = player.getUniqueId().toString();

        setScoreboard(uuid, character);
        scores.get(uuid).setScore(scores.size());

        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.setScoreboard(scoreboard);
        }
    }

    /**
     * Reload the scoreboard in changing the player character for all players connected
     * @param player The player target
     */
    public static void reloadScoreboard(Player player) {
        String uuid = player.getUniqueId().toString();
        Character character = Main.players.get(uuid);
        int scorePlace = scores.get(uuid).getScore();

        // Remove the score of the scoreboard
        for (String entry : scoreboard.getEntries()) {
            if (entry.equals(scores.get(uuid).getEntry())) {
                scoreboard.resetScores(entry);
                break;
            }
        }

        // Put the new score
        setScoreboard(uuid, character);
        Score score = scores.get(uuid);
        score.setScore(scorePlace);

        // Update the scoreboard for all players connected
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.setScoreboard(scoreboard);
        }
    }

    /**
     * Allow to set easily the character on the scoreboard
     * @param uuid Unique Id of the player target
     * @param character The character at set on the score
     */
    private static void setScoreboard(String uuid, Character character) {
        scores.put(uuid, objective.getScore(character.getRole().getColor() + character.getName() + ChatColor.RESET + " - " +
                ChatColor.RED + character.getLife() + ChatColor.RESET + " - " +
                ChatColor.BLUE + character.getInit()));
    }
 }
