package com.ludovic.gui.stat;

import com.ludovic.character.Character;
import com.ludovic.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class StatGui {
    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private static Objective objective = scoreboard.registerNewObjective("info_player", "dummy", "Info player");
    private static Map<String,Score> scores = new HashMap<String, Score>();

    public StatGui() {
    }

    public static void initScoreboard() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public static void updateScoreboard(Player player) {
        Character character = Config.getPlayerCharachter(player);
        player.sendMessage(character.getName());
        String uuid = player.getUniqueId().toString();

        scores.put(uuid, objective.getScore(character.getName() + " Life "));
        scores.get(uuid).setScore(scores.size());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }
 }
