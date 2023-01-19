package ui;

import model.Player;
import model.Teams;
import model.TradeList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerView extends JFrame implements ActionListener {
    JTextField playerNameField;
    JTextField playerSalaryField;
    JTextField playerTeamField;
    ListView listView;
    TradeList tradeList;
    private static final String FINISH_ACTION = "FINISH_ACTION";

    public AddPlayerView(ListView listView, TradeList tradeList) {
        super("Add an Player");
        this.listView = listView;
        this.tradeList = tradeList;
        this.setWindow();

        this.setLabelsFieldsButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    private void setLabelsFieldsButtons() {
        extracted("Enter Player name: ", 48, 40, 400);

        playerNameField = new JTextField(30);
        playerNameField.setBounds(50, 60, 300, 20);
        add(playerNameField);

        extracted("Enter Player Salary", 50, 80, 600);

        playerSalaryField = new JTextField(30);
        playerSalaryField.setBounds(50, 100,300,20);
        add(playerSalaryField);

        extracted("Enter Player Team", 50, 120, 600);

        playerTeamField = new JTextField(30);
        playerTeamField.setBounds(50, 140,300,20);
        add(playerTeamField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(310,210,100,20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }

    private void extracted(String text, int x, int y, int width) {
        JLabel playerNameLabel = new JLabel(text);
        playerNameLabel.setBounds(x, y, width, 20);
        add(playerNameLabel);
        playerNameLabel.setForeground(Color.darkGray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            String name = playerNameField.getText();
            int salary = Integer.parseInt(playerSalaryField.getText());
            Teams team = Teams.valueOf(playerTeamField.getText());
            tradeList.addPlayer(new Player(name, salary,team));
            listView.dispose();
            new ListView(tradeList);
            dispose();
        }
    }
}
