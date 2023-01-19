package persistence;

import model.Player;
import model.TradeList;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.util.ArrayList;

// Represents a writer that writes JSON representation of workroom to file
public class Saver {
    public static void save(TradeList tradeList, String path) {
        try {
            PrintWriter writer = new PrintWriter(path,"UTF-8");
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            for (Player i : tradeList.getList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", i.getName());
                jsonObject.put("salary", i.getSalary());
                jsonObject.put("team", i.getTeam());
                jsonObjects.add(jsonObject);
            }
            JSONArray jsonArray = new JSONArray(jsonObjects);
            writer.println(jsonArray.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Encountered IOException while saving todo list.");
        }
    }
}

