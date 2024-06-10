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

/**
 * TicTacToeClient class represents a client for playing Tic Tac Toe game.
 * This class establishes a connection with the server, handles user input,
 * and displays the game interface.
 *
 * @author Robert Čuda
 * @author Ester Stankovská
 */

public class TicTacToeClient {

    private JFrame frame = new JFrame("Tic Tac Toe");
    private JLabel messageLabel = new JLabel("");
    private char currentPlayerMark = 'X';
    private boolean isMyTurn = false;  // Flag to check if it's the player's turn

    private Square[] board = new Square[9];
    private Square currentSquare;

    private static int PORT = 8080;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;


    Log my_log = new Log("log_client.txt");

    /**
     * Constructs a TicTacToeClient object with the specified server address and username.
     * Initializes the networking components, prompts the user for a username, and sets up the game interface.
     *
     * @param serverAddress The address of the server to connect to.
     * @param username      The username chosen by the player.
     * @throws Exception if there's an error during initialization or communication with the server.
     */
    public TicTacToeClient(String serverAddress, String username) throws Exception {
        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);


        // Log connection
        my_log.logger.log(Level.INFO, "Connected to server at " + serverAddress);

        // Send username to server
        while (true) {
            out.println(username);
            String response = in.readLine();
            my_log.logger.log(Level.INFO, "Received response after sending username: " + response);

            if (response.startsWith("WRONG_USERNAME")) {
                username = JOptionPane.showInputDialog(frame, "Enter your username different to that of your opponent:", "Username", JOptionPane.PLAIN_MESSAGE);
                my_log.logger.log(Level.INFO, "Username taken, prompt user to enter different username");
            } else {
                if (response.startsWith("WELCOME_NEW")) {
                    String[] parts = response.split(" ");
                    String welcomeMessage = "Welcome to tic tac toe " + parts[1] + "!";
                    JOptionPane.showMessageDialog(frame, welcomeMessage);
                    my_log.logger.log(Level.INFO, "Displayed WELCOME_NEW message: " + welcomeMessage);
                } else if (response.startsWith("WELCOME_BACK")) {
                    String[] parts = response.split(" ");
                    String welcomeMessage = "Welcome back " + parts[1] + "! Your current score is " + parts[2];
                    JOptionPane.showMessageDialog(frame, welcomeMessage);
                    my_log.logger.log(Level.INFO, "Displayed WELCOME_BACK message: " + welcomeMessage);
                }
                break;
            }
        }

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
                        isMyTurn = false;
                    }
                }
            });

            boardPanel.add(board[i]);
        }
        frame.getContentPane().add(boardPanel, "Center");
    }
    /**
     * Initiates the game logic. Handles incoming messages from the server and updates the game state accordingly.
     *
     * @throws Exception if there's an error during communication with the server.
     */
    public void play() throws Exception {
        String response;
        try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {
                char mark = response.charAt(8);
                currentPlayerMark = mark;
                frame.setTitle("Tic Tac Toe - Player " + mark);
                my_log.logger.log(Level.INFO, "Received WELCOME message: " + response);
                isMyTurn = (mark == 'X');
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
                        isMyTurn = true;
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
                        String messageContent = response.substring(8);
                        if (messageContent.contains("Your opponent left the game.")) {
                            break;
                        } else {
                            messageLabel.setText(messageContent);
                            my_log.logger.log(Level.INFO, "Received MESSAGE: " + messageContent);
                        }
                    }
                } else {
                    my_log.logger.log(Level.SEVERE, "Server connection lost.");
                    break;
                }
            }
        } finally {
            socket.close();
            my_log.logger.log(Level.INFO, "Socket closed");
        }
    }


    /**
     * Prompts the player whether they want to play again after the game ends.
     *
     * @return true if the player wants to play again, false otherwise.
     */
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
    /**
     * Represents a square in the Tic Tac Toe game board.
     *
     * @author Robert Čuda
     * @author Ester Stankovská
     */
    static class Square extends JPanel {
        JLabel label = new JLabel();

        public Square() {
            setBackground(Color.white);
            add(label);
        }

        /**
         * Sets the text content of the square.
         *
         * @param text The text to set in the square.
         */
        public void setText(String text) {
            label.setText(text);
        }
        /**
         * Checks if the square is empty.
         *
         * @return true if the square is empty, false otherwise.
         */
        public boolean isEmpty() {
            return label.getText().isEmpty();
        }
    }

    /**
     * The main method to start the Tic Tac Toe client.
     * Prompts the user for the server address and username, creates a client object,
     * and starts the game loop.
     *
     * @param args Command line arguments (not used).
     * @throws Exception if there's an error during client initialization or gameplay.
     */
    public static void main(String[] args) throws Exception {
        String serverAddress = (args.length == 0) ? "localhost" : args[0];

        while (true) {
            JFrame tempFrame = new JFrame();
            String username = JOptionPane.showInputDialog(tempFrame, "Enter your username:", "Username", JOptionPane.PLAIN_MESSAGE);
            tempFrame.dispose();

            TicTacToeClient client = new TicTacToeClient(serverAddress, username);
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