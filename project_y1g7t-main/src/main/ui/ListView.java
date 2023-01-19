package ui;

import model.Player;
import model.TradeList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ListView extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable table;

    private TradeApp tradeApp = new TradeApp();

    private TradeList tradeList = new TradeList();


    private static final String ADD_PLAYER_ACTION = "ADD_PLAYER_ACTION";
    private static final String REMOVE_PLAYER_ACTION = "REMOVE_PLAYER_ACTION";
    private static final String LOAD_PLAYERS_ACTION = "LOAD_PLAYERS_ACTION";
    private static final String SAVE_PLAYERS_ACTION = "SAVE_PLAYERS_ACTION";
    private static final String EDIT_OTHER_TEAMS_ACTION = "EDIT_OTHER_TEAMS_ACTION";

    public ListView(TradeList tradeList) {
        this.tradeList = tradeList;
        this.setBackgroundImage();
        final String[] columnLabels = new String[] {
                "Name",
                "Salary",
                "Team"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {};
        table = new JTable(tableModel);
        this.populateTableRows();

        add(new JScrollPane(table));
        this.setButtons();
        setTitle("Team Roster");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void setBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File(
                    "src/main/ui/Image/nba-basketball-background-128497-573442-562392.jpg"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateTableRows() {
        for (int i = 0; i < tradeList.size(); i++) {
            Player player = tradeList.getPlayer(i);
            Object[] tableRow = new Object[] {
                    player.getName(), // name column
                    player.getSalary(), // salary column
                    player.getTeam().name() // team column
            };
            tableModel.addRow(tableRow);
        }
    }

    private void populateTableRows(TradeList tradeList) {
        for (int i = 0; i < tradeList.size(); i++) {
            Player player = tradeList.getPlayer(i);
            Object[] tableRow = new Object[] {
                    player.getName(), // name column
                    player.getSalary(), // salary column
                    player.getTeam().name() // team column
            };
            tableModel.addRow(tableRow);
        }
    }

    private void setButtons() {
        addButton("Add a new player",ADD_PLAYER_ACTION);
        addButton("remove a new player",REMOVE_PLAYER_ACTION);
        addButton("load the tradeslist",LOAD_PLAYERS_ACTION);
        addButton("save the tradeslist",SAVE_PLAYERS_ACTION);
        addButton("edit another team",EDIT_OTHER_TEAMS_ACTION);
    }

    public void addButton(String name, String command) {
        JButton editAnotherTeamButton = new JButton(name);
        add(editAnotherTeamButton);
        editAnotherTeamButton.setActionCommand(command);
        editAnotherTeamButton.addActionListener(this);
        editAnotherTeamButton.setForeground(Color.darkGray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel load = new JLabel("", JLabel.CENTER);
        load.setBounds(56, 40, 300, 20);
        add(load);
        load.setForeground(Color.black);
        JLabel save = new JLabel("", JLabel.CENTER);
        save.setBounds(56, 40, 300, 20);
        add(save);
        save.setForeground(Color.black);
        String action = e.getActionCommand();
        if (action.equals(ADD_PLAYER_ACTION)) {
            new AddPlayerView(this, tradeList);
        } else if (action.equals(REMOVE_PLAYER_ACTION)) {
            tableModel.removeRow(tradeList.size() - 1);
            tradeList.removePlayer();
        } else if (action.equals(LOAD_PLAYERS_ACTION)) {
            loadTradeList(load);
        } else if (action.equals(SAVE_PLAYERS_ACTION)) {
            tradeApp.saveTradelist(tradeList);
            save.setText("Saved players successfully");
        } else if (action.equals(EDIT_OTHER_TEAMS_ACTION)) {
            dispose();
        }
    }

    private void loadTradeList(JLabel load) {
        TradeList tl = new TradeList();
        tl = tradeApp.loadTradelist(tradeList);
        load.setText("Loaded players successfully");
        for (int i = 0; i < tl.size();i++) {
            Object[] tableRow = new Object[] {
                    tl.getPlayer(i).getName(), // name column
                    tl.getPlayer(i).getSalary(), // salary column
                    tl.getPlayer(i).getTeam().name() // team column
            };
            tableModel.addRow(tableRow);
        }
    }
}
