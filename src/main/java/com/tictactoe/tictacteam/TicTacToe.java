package com.tictactoe.tictacteam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel title_panel = new JPanel();
    JPanel butt_panel = new JPanel();
    JLabel text_field = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;


    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        text_field.setBackground(Color.BLACK);
        text_field.setForeground(Color.blue);
        text_field.setFont(new Font("Times", Font.BOLD, 75));
        text_field.setHorizontalAlignment(JLabel.CENTER);
        text_field.setText("TicTacToe");
        text_field.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        butt_panel.setLayout(new GridLayout(3, 3));
        butt_panel.setBackground(Color.gray);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            butt_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Times", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(text_field);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(butt_panel);

        firstTurn();
    }

    private void firstTurn() {

        if(random.nextInt(2) ==0) {
            player1_turn = true;
            text_field.setText("X turn");
        }
        else {
            player1_turn = false;
            text_field.setText("O turn");
        }
    }

    public void check() {
        if((buttons[0].getText()=="X") &&
                (buttons[1].getText()=="X") &&
                (buttons[2].getText()=="X")) {
            xWins(0,1,2);
        }

        if((buttons[3].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[5].getText()=="X")) {
            xWins(3,4,5);
        }
        if((buttons[6].getText()=="X") &&
                (buttons[7].getText()=="X") &&
                (buttons[8].getText()=="X")) {
            xWins(6,7,8);
        }
        if((buttons[0].getText()=="X") &&
                (buttons[3].getText()=="X") &&
                (buttons[6].getText()=="X")) {
            xWins(0,3,6);
        }
        if((buttons[1].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[7].getText()=="X")) {
            xWins(1,4,7);
        }
        if((buttons[2].getText()=="X") &&
                (buttons[5].getText()=="X") &&
                (buttons[8].getText()=="X")) {
            xWins(2,5,8);
        }
        if((buttons[0].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[8].getText()=="X")) {
            xWins(0,4,8);
        }
        if((buttons[2].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[6].getText()=="X")) {
            xWins(2,4,6);
        }

        if((buttons[0].getText()=="O") &&
                (buttons[1].getText()=="O") &&
                (buttons[2].getText()=="O")) {
            oWins(0,1,2);
        }

        if((buttons[3].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[5].getText()=="O")) {
            oWins(3,4,5);
        }
        if((buttons[6].getText()=="O") &&
                (buttons[7].getText()=="O") &&
                (buttons[8].getText()=="O")) {
            oWins(6,7,8);
        }
        if((buttons[0].getText()=="O") &&
                (buttons[3].getText()=="O") &&
                (buttons[6].getText()=="O")) {
            oWins(0,3,6);
        }
        if((buttons[1].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[7].getText()=="O")) {
            oWins(1,4,7);
        }
        if((buttons[2].getText()=="O") &&
                (buttons[5].getText()=="O") &&
                (buttons[8].getText()=="O")) {
            oWins(2,5,8);
        }
        if((buttons[0].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[8].getText()=="O")) {
            oWins(0,4,8);
        }
        if((buttons[2].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[6].getText()=="O")) {
            oWins(2,4,6);
        }
    }

    private void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i <9 ; i++) {
            buttons[i].setEnabled(false);
        }
        text_field.setText("X wins");
    }

    private void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i <9 ; i++) {
            buttons[i].setEnabled(false);
        }
        text_field.setText("O wins");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.blue);
                        buttons[i].setText("X");
                        player1_turn = false;
                        text_field.setText("O turn");
                        check();
                    }
                }
                else {
                    if(buttons[i].getText() =="") {
                        buttons[i].setForeground(Color.red);
                        buttons[i].setText("O");
                        player1_turn = true;
                        text_field.setText("X turn");
                        check();
                    }
                }
            }
        }
    }
}
