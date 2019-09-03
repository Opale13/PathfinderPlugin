package com.ludovic.utils;

import com.ludovic.Main;
import com.ludovic.character.Mob;
import org.bukkit.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ReadJson {
    private static String path = "plugins/PathfinderPlugin/mobs.json";
    private static JSONParser jsonParser = new JSONParser();
    private static FileReader json;

    public static void initJson() throws FileNotFoundException {
        File f = new File("plugins/PathfinderPlugin");
        if (!f.exists()) f.mkdirs();

        File file = new File(f, "mobs.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) { e.printStackTrace(); }

        }

        json = new FileReader(file);
    }

    public static void readJson() throws ParseException, IOException {
        JSONArray mobsList = (JSONArray) jsonParser.parse(json);

        for (Object o : mobsList) {
            JSONObject mob = (JSONObject) o;
            JSONArray rgb = (JSONArray) mob.get("color");

            int red = ((Long) rgb.get(0)).intValue();
            int green = ((Long) rgb.get(1)).intValue();
            int blue = ((Long) rgb.get(2)).intValue();

            Color color = Color.fromRGB(red, green, blue);

            Main.mobList.add(new Mob((String) mob.get("name"),
                    (String) mob.get("helmet"),
                    (String) mob.get("chestplate"),
                    (String) mob.get("leggings"),
                    (String) mob.get("boots"),
                    color,
                    (String) mob.get("blockSet")));
        }
    }


}
