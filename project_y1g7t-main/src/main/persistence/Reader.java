package persistence;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.*;

// Represents a reader that reads Tradelists from JSON data stored in file

// EFFECT: read the saved tradelist from JSON data
public class Reader {
    // EFFECT:
    public static void load(TradeList tradeList, String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (lines.size() == 0) {
                return;
            }
            String jsonArrayString = lines.get(0); // there is only 1 line in the file
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                int salary = jsonObject.getInt("salary");
                Teams team = jsonObject.getEnum(Teams.class, "team");
                tradeList.addPlayer(new Player(name, salary, team));
            }
        } catch (IOException e) {
            System.out.println("Encountered IOException while loading tradelist.");
        }
    }
}
//public class Reader1 {
//    private String source;
//
//    // EFFECTS: constructs reader to read from source file
//    public Reader1(String source) {
//        this.source = source;
//    }
//
//    // EFFECTS: reads workroom from file and returns it;
//    // throws IOException if an error occurs reading data from file
//    public TradeList read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseTradeList1(jsonObject);
//    }
//
//    // EFFECTS: reads source file as string and returns it
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return contentBuilder.toString();
//    }
//
//    // EFFECTS: parses tradelist1 from JSON object and returns it
//    private TradeList parseTradeList1(JSONObject jsonObject) {
//        String tradeList1 = jsonObject.getString("tradelist1");
//        TradeList tl = new TradeList();
//        addPlayers(tl, jsonObject);
//        return tl;
//    }
//
//    // MODIFIES: wr
//    // EFFECTS: parses thingies from JSON object and adds them to workroom
//    private void addPlayers(TradeList tl, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("tradelist1");
//        for (Object json : jsonArray) {
//            JSONObject nextPlayer = (JSONObject) json;
//            addPlayer(tl, nextPlayer);
//        }
//    }
//
//    // MODIFIES: wr
//    // EFFECTS: parses thingy from JSON object and adds it to workroom
//    private void addPlayer(TradeList tl, JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        int salary = jsonObject.getInt("salary");
//        Teams teams = Teams.valueOf(jsonObject.getString("team"));
//        Player player = new Player(name, salary, teams);
//        tl.addPlayer1(player);
//    }
//}

