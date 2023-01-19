package ui;


import model.Player;
import model.Teams;
import model.TradeList;
import persistence.Reader;
import persistence.Saver;

import java.util.*;

public class TradeApp {
    private final Scanner scanner = new Scanner(System.in);
    private TradeList tradeList = new TradeList();
    private static String path = "./data/savedTradeLists";


    // Additional functions like view roster of a team will be implemented in phase 2
    private static final String selectOperationString =
            "----------------------------------------------------------------------   \n"
                    + "Please select an option: \n"
                    + "[1] Add a player to team \n"
                    + "[2] Remove a player from team  \n"
                    + "[3] See the roster of the team \n"
                    + "[4] Edit another team \n"
                    + "[5] Load the roster \n"
                    + "[6] Save the roster \n"
                    + "[7] See if the trade works \n"
                    + "[8] Quit \n";

    private static final String selectTeam =
            "Please select which team you want to modify by inputting an integer.";

    public TradeApp() {
    }

    // EFFECTS: run the entire program if the operation is not quit
    public void run() {
        TradeList tlSelected;
        String command;
        List<TradeList> teams = creatTeams();
        tlSelected = selectTradeList(teams);
        int n;
        while (true) {
            System.out.println(selectOperationString);
            command = scanner.nextLine();
            if (command.equals("8")) {
                break;
            } else if (command.equals("7")) {
                if (works(teams)) {
                    System.out.println("The trade works!");
                } else {
                    System.out.println("The trade don't work!");
                }
            } else if (command.equals("4")) {
                tlSelected = selectTradeList(teams);
            } else {
                performOperation(tlSelected, command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    private TradeList selectTradeList(List<TradeList> teams) {
        TradeList tlSelected;
        int n = selectNumTeam();
        tlSelected = teams.get(n);
        setPath(n);
        return tlSelected;
    }

    public int selectNumTeam() {
        System.out.println(selectTeam);
        return scanner.nextInt();
    }

    public void setPath(int i) {
        path = path + Integer.toString(i) + ".json";
    }

    public List<TradeList> creatTeams() {
        int n;
        TradeList tl;
        List<TradeList> teams = new ArrayList<>();
        System.out.println("Selected how many teams you want in the trade: ");
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            tl = new TradeList();
            teams.add(tl);
            tl.setPath(path + Integer.toString(i) + ".json");
        }
//        for (int i = 0; i < n; i++) {
//            // teams.get(i).setPath(PATH + Integer.toString(i) + ".json");
//            // Reader.load(teams.get(i), (PATH + Integer.toString(i) + ".json"));
//        }

        return teams;
    }

    public List<TradeList> creatTeams(int n) {
        TradeList tl;
        List<TradeList> teams = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tl = new TradeList();
            teams.add(tl);
            tl.setPath(path + Integer.toString(i) + ".json");
        }
        return teams;
    }

    // MODIFIES: tradeList
    // EFFECTS: perform certain operation according to the input
    private void performOperation(TradeList tl, String operation) {
        if (operation.equals("1")) {
            tl.addPlayer(createPlayer());
        }
        if (operation.equals("2")) {
            tl.removePlayer();
        }
        if (operation.equals("3")) {
            System.out.println(tl.getPlayers());
        }
        if (operation.equals("4")) {
            System.out.println("");
        }
        if (operation.equals("5")) {
            System.out.println(path);
            Reader.load(tl, path);
        }
        if (operation.equals("6")) {
            Saver.save(tl, path);
            System.out.println("Save successful!");
        } else {
            System.out.println("Please select the right operation according to menu! ");
        }
    }


    // EFFECTS: creates a new player
    private Player createPlayer() {
        System.out.println("Enter player name: ");
        String name = scanner.nextLine();
        System.out.println("Enter player salary: ");
        int salary = scanner.nextInt();
        Teams teams = readTeams();
        Player player = new Player(name, salary, teams);
        return player;
    }


    private Teams readTeams() {
        System.out.println("Enter player team: ");

        int menuLabel = 1;
        for (Teams c : Teams.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }

        int menuSelection = scanner.nextInt();
        return Teams.values()[menuSelection - 1];
    }


    public boolean works(List map) {
        int total1;
        int total2;
        List<Integer> allSalary = new ArrayList<>();
        TradeList tl = new TradeList();
        boolean works;
        for (int i = 0; i < map.size(); i++) {
            tl = (TradeList) map.get(i);
            allSalary.add(tl.getTotalSalary());
        }
        for (int i = 0; i < allSalary.size() - 1; i++) {
            total1 = allSalary.get(i);
            total2 = allSalary.get(i + 1);
            if ((0.8 > ((float) total1 / total2))) {
                return false;
            } else if ((float) total1 / total2 > 1.25) {
                return false;
            }
        }
        return true;

    }

    public TradeList loadTradelist(TradeList tradeList) {
        Reader.load(tradeList, tradeList.getPath());
        System.out.println("Load successfully!");
        return tradeList;
    }

    public void saveTradelist(TradeList tradeList) {
        Saver.save(tradeList, tradeList.getPath());
        System.out.println("Save successfully!");
    }

}
