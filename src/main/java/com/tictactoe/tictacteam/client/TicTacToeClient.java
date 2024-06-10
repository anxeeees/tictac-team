package com.tictactoe.tictacteam.client;

import com.tictactoe.tictacteam.Log;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.util.logging.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeClient {

    private JFrame frame = new JFrame("Tic Tac Toe");
    private JLabel messageLabel = new JLabel("");
    private char currentPlayerMark = 'X';
    private boolean isMyTurn = false;  // Flag to check if it's the player's turn

    private Square[] board = new Square[9];
    private Square currentSquare;

    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;


    Log my_log = new Log("log_client.txt");

    public TicTacToeClient(String serverAddress) throws Exception {
        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Log connection
        my_log.logger.log(Level.INFO, "Connected to server at " + serverAddress);

        // Layout GUI
        messageLabel.setBackground(Color.lightGray);
        frame.getContentPane().add(messageLabel, "South");

        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(new GridLayout(3, 3, 2, 2));
        for (int i = 0; i < board.length; i++) {
            final int j = i;
            board[i] = new Square();
            board[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (isMyTurn && board[j].isEmpty()) {
                        currentSquare = board[j];
                        out.println("MOVE " + j);
                        my_log.logger.log(Level.INFO, "Sent MOVE command to server: MOVE " + j);
                        isMyTurn = false; // Disable further moves until it's player's turn again
                    }
                }
            });
            boardPanel.add(board[i]);
        }
        frame.getContentPane().add(boardPanel, "Center");
    }

    public void play() throws Exception {
        String response;
        try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {
                char mark = response.charAt(8);
                currentPlayerMark = mark; // Set player's mark according to server message
                frame.setTitle("Tic Tac Toe - Player " + mark);
                my_log.logger.log(Level.INFO, "Received WELCOME message: " + response);
                isMyTurn = (mark == 'X'); // 'X' starts the game
            }
            while (true) {
                response = in.readLine();
                if (response != null) {
                    if (response.startsWith("VALID_MOVE")) {
                        messageLabel.setText("Valid move, please wait");
                        currentSquare.setText(String.valueOf(currentPlayerMark));
                        currentSquare.repaint();
                        my_log.logger.log(Level.INFO, "Received VALID_MOVE message: " + response);
                    } else if (response.startsWith("OPPONENT_MOVED")) {
                        int loc = Integer.parseInt(response.substring(15));
                        board[loc].setText(String.valueOf((currentPlayerMark == 'X' ? 'O' : 'X')));
                        board[loc].repaint();
                        messageLabel.setText("Opponent moved, your turn");
                        my_log.logger.log(Level.INFO, "Received OPPONENT_MOVED message: " + response);
                        isMyTurn = true; // Enable player's turn
                    } else if (response.startsWith("VICTORY")) {
                        messageLabel.setText("You win");
                        my_log.logger.log(Level.INFO, "Received VICTORY message: " + response);
                        break;
                    } else if (response.startsWith("DEFEAT")) {
                        messageLabel.setText("You lose");
                        my_log.logger.log(Level.INFO, "Received DEFEAT message: " + response);
                        break;
                    } else if (response.startsWith("TIE")) {
                        messageLabel.setText("You tied");
                        my_log.logger.log(Level.INFO, "Received TIE message: " + response);
                        break;
                    } else if (response.startsWith("MESSAGE")) {
                        messageLabel.setText(response.substring(8));
                        my_log.logger.log(Level.INFO, "Received MESSAGE: " + response.substring(8));
                    }
                } else {
                    my_log.logger.log(Level.SEVERE, "Server connection lost.");
                    break;
                }
            }
            out.println("QUIT");
            my_log.logger.log(Level.INFO, "Sent QUIT command to server");
        } finally {
            socket.close();
            my_log.logger.log(Level.INFO, "Socket closed");
        }
    }

    private boolean wantsToPlayAgain() {
        int response = JOptionPane.showConfirmDialog(frame,
                "Want to play again?",
                "Tic Tac Toe is Fun Fun Fun",
                JOptionPane.YES_NO_OPTION);
        boolean playAgain = response == JOptionPane.YES_OPTION;
        my_log.logger.log(Level.INFO, "Player chose to " + (playAgain ? "play again" : "not play again"));
        frame.dispose();
        return playAgain;
    }

    static class Square extends JPanel {
        JLabel label = new JLabel();

        public Square() {
            setBackground(Color.white);
            add(label);
        }

        public void setText(String text) {
            label.setText(text);
        }

        public boolean isEmpty() {
            return label.getText().isEmpty();
        }
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[0];
            TicTacToeClient client = new TicTacToeClient(serverAddress);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setSize(240, 160);
            client.frame.setVisible(true);
            client.frame.setResizable(false);
            client.play();
            if (!client.wantsToPlayAgain()) {
                break;
            }
        }
    }
}