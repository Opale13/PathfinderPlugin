package com.ludovic.gui.stat;

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
    private static Map<String,Score> scores = new HashMap<String, Score>();

    public StatGui() {
    }

    public static void initScoreboard() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score title = objective.getScore(ChatColor.RESET + " Name - " +
                            ChatColor.RED + "Life - " +
                            ChatColor.BLUE + "Init");
        title.setScore(11);
    }

    public static void updateScoreboard(Player player) {
        Character character = Config.getPlayerCharacter(player);
        String uuid = player.getUniqueId().toString();

        scores.put(uuid, objective.getScore(character.getRole().getColor() + character.getName() + " - " +
                                            ChatColor.RED + character.getLife() + " - " +
                                            ChatColor.BLUE + character.getInit()));

        scores.get(uuid).setScore(scores.size());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }
 }
