package com.ludovic.utils;

import com.ludovic.Main;
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
            Main.mobList.add((String) mob.get("name"));
        }
    }


}
