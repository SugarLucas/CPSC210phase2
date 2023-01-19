package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 20;
    private static final String START_APPLICATION = "START_APPLICATION";
    private static final String QUIT_APP_ACTION = "QUIT_APP_ACTION";
    JTextField playeNum;
    private final TradeList tradeList = new TradeList();
    private TeamsView teamsView;

    public MainView() {
        super("NBA trade simulator Application");
        this.setWindow();
        this.setBackgroundImage();
        this.setUpLabelsAndButtons();
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
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

    private void setUpLabelsAndButtons() {
        JLabel selectOptionLabel = new JLabel("Please select an option: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.white);

        JLabel selectTeamLabel = new JLabel("Please select the number of teams: ", JLabel.CENTER);
        selectTeamLabel.setBounds(26, 70, 300, 20);
        add(selectTeamLabel);
        selectTeamLabel.setForeground(Color.white);

        playeNum = new JTextField(30);
        playeNum.setBounds(BUTTON_POSITION, 100, 300, 20);
        add(playeNum);

        JButton viewListButton = new JButton("Start");
        viewListButton.setBounds(BUTTON_POSITION, 40, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(viewListButton);
        viewListButton.setActionCommand(START_APPLICATION);
        viewListButton.addActionListener(this);
        viewListButton.setForeground(Color.black);


        JButton quitAppButton = new JButton("Quit Trade Simulator Application");
        quitAppButton.setBounds(BUTTON_POSITION, 240, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(quitAppButton);
        quitAppButton.setActionCommand(QUIT_APP_ACTION);
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(START_APPLICATION)) {
            int tls = Integer.parseInt(playeNum.getText());
            teamsView = new TeamsView(tls);
        } else if (action.equals(QUIT_APP_ACTION)) {
            teamsView.dispose();
            dispose();
            tradeList.printLog();
        }
    }
}
