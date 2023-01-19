package ui;

import model.TradeList;
import persistence.Reader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamsView extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 20;
    private static final String TEAM = "TEAM";
    private static final String TRADE = "TRADE";
    private static final String QUIT = "QUIT";


    private TradeApp tradeApp = new TradeApp();
    private List<TradeList> teams = new ArrayList<>();
    private TradeList tradeList = new TradeList();
    private int numTeam;
    private ListView listView;

    public TeamsView(int i) {
        super("This is a " + Integer.toString(i) + " team trade");
        this.setWindow();
        this.setBackgroundImage();
        this.setUpLabelsAndButtons(i);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        numTeam = i;
    }

    private void setWindow() {
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
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

    private void setUpLabelsAndButtons(int n) {
        JLabel selectOptionLabel = new JLabel("Please select a team: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);
        for (int i = 0; i < n; i++) {
            JButton viewListButton = new JButton("Team_" + Integer.toString(i));
            viewListButton.setBounds(BUTTON_POSITION, 40 + 38 * i, BUTTON_WIDTH, BUTTON_HEIGHT);
            add(viewListButton);
            viewListButton.setActionCommand(TEAM + Integer.toString(i));
            viewListButton.addActionListener(this);
            viewListButton.setForeground(Color.black);
        }
        addButton(TRADE,170);
//        JLabel tradeLabel = new JLabel("", JLabel.CENTER);
//        tradeLabel.setBounds(26, 130, 300, 20);
//        add(tradeLabel);
//        tradeLabel.setForeground(Color.white);
        addButton(QUIT,200);
    }

    public void addButton(String name, int position) {
        JButton tradeButton = new JButton(name);
        tradeButton.setBounds(BUTTON_POSITION, position, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(tradeButton);
        tradeButton.setActionCommand(name);
        tradeButton.addActionListener(this);
        tradeButton.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel tradeLabel = new JLabel("", JLabel.CENTER);
        tradeLabel.setBounds(26, 140, 300, 20);
        add(tradeLabel);
        tradeLabel.setForeground(Color.white);
        teams = tradeApp.creatTeams(numTeam);
        String action = e.getActionCommand();
        for (int i = 0; i < numTeam; i++) {
            if (action.equals(TEAM + Integer.toString(i))) {
                tradeList = teams.get(i);
                listView = new ListView(tradeList);
            } else if (action.equals(TRADE)) {
                for (int p = 0; p < numTeam; p++) {
                    Reader.load(teams.get(p),teams.get(p).getPath());
                }
                if (tradeApp.works(teams)) {
                    tradeLabel.setText("Trade works!");
                } else {
                    tradeLabel.setText("Trade don't work!");
                }
            } else if (action.equals(QUIT)) {
                dispose();
            }
        }
    }
}
