package model;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class TradeList {
    private final ArrayList<Player> tradeList = new ArrayList<>();
    protected String path = "./data/savedTl.json";

    public TradeList() {
    }

    // MODIFIES:tradeList
    // EFFECTS: add player to team
    public void addPlayer(Player player) {
        String teamString = "";
        Teams team = player.getTeam();
        teamString = team.name();
        Event event = new Event(player.getName()
                + " , $ " + player.getSalary() + " , " + teamString + "  is added.");
        EventLog.getInstance().logEvent(event);
        tradeList.add(player);
        //System.out.println("Player add to team successfully!");
    }


    // MODIFIES:tradeList
    // EFFECTS: remove a player from team
    public void removePlayer() {
        Event event = new Event(
                "The last player of tradelist  is removed.");
        EventLog.getInstance().logEvent(event);
        tradeList.remove(tradeList.size() - 1);
        //System.out.println("removed player from team successfully!");
    }


    // EFFECTS: return the size of team
    public int size() {
        return tradeList.size();
    }


    // EFFECTS: check if team 1 contains a player
    public boolean contains(Player player) {
        return tradeList.contains(player);
    }

    // EFFECTS: returns a list of Strings of players: salary in the tradelist
    public List<String> getPlayers() {
        List<String> names = new ArrayList<>();
        for (Player player : tradeList) {
            names.add(player.getName() + ": $" + Integer.toString(player.getSalary()));
        }
        return names;
    }

    //EFFECT: return the total salary of all players in tradelist
    public int getTotalSalary() {
        int total = 0;
        for (Player player : tradeList) {
            total += player.getSalary();
        }
        return total;
    }

    // return the tradelist
    public ArrayList<Player> getList() {
        return tradeList;
    }

    // delete all saved files related to this tradelist
    public void clearFile() {
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Encountered IOException while saving todo list.");
        }
    }

    // MODIFIES: this
    // EFFECT: set a new path for the tradelist
    public void setPath(String path) {
        this.path = path;
        System.out.println("Successful added path: " + path);
    }

    // EFFECT: return the path for the tradelist
    public String getPath() {
        return path;
    }

    // EFFECT: get the player from an index
    public Player getPlayer(int index) {
        return tradeList.get(index);
    }

    // EFFECT: print all the event logs
    public void printLog() {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }


}
