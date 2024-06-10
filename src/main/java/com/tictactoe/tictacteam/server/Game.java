package com.tictactoe.tictacteam.server;

import com.tictactoe.tictacteam.database.DatabaseManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;

public class Game {
    // a board of 9 squares
    private Player[] board = {
            null, null, null,
            null, null, null,
            null, null, null};

    //current player
    Player currentPlayer;

    // winner
    public boolean hasWinner() {
        return
                (board[0] != null && board[0] == board[1] && board[0] == board[2])
                        ||(board[3] != null && board[3] == board[4] && board[3] == board[5])
                        ||(board[6] != null && board[6] == board[7] && board[6] == board[8])
                        ||(board[0] != null && board[0] == board[3] && board[0] == board[6])
                        ||(board[1] != null && board[1] == board[4] && board[1] == board[7])
                        ||(board[2] != null && board[2] == board[5] && board[2] == board[8])
                        ||(board[0] != null && board[0] == board[4] && board[0] == board[8])
                        ||(board[2] != null && board[2] == board[4] && board[2] == board[6]);
    }

    // no empty squares
    public boolean boardFilledUp() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                return false;
            }
        }
        return true;
    }
    // thread when player tries a move
    public synchronized boolean legalMove(int location, Player player) {
        if (player == currentPlayer && board[location] == null) {
            board[location] = currentPlayer;
            currentPlayer = currentPlayer.opponent;
            currentPlayer.otherPlayerMoved(location);
            return true;
        }
        return false;
    }

    class Player extends Thread {
        char mark;
        String username;
        Player opponent = null;
        Socket socket;
        BufferedReader input;
        PrintWriter output;

        // thread handler to initialize stream fields
        public Player(Socket socket, char mark, Player opponent) {
            this.socket = socket;
            this.mark = mark;
            this.opponent = opponent;
            try {
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);


                //making sure that the player's usernames are not the same
                while (true) {
                    this.username = input.readLine();
                    if (this.opponent != null && this.opponent.username.equals(this.username)) {
                        output.println("WRONG_USERNAME");
                    } else {
                        break;
                    }
                }

                if(DatabaseManager.userExists(username)) {
                    output.println("WELCOME_BACK" + " " + username + " " + DatabaseManager.getScore(username));
                } else {
                    DatabaseManager.addUser(username);
                    output.println("WELCOME_NEW" + " " + username);
                }
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);
                TicTacToeServer.logger.log(Level.SEVERE, "Player died: ", e);
            }
        }

        //Accepts notification of who the opponent is.
        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        //Handles the otherPlayerMoved message.
        public void otherPlayerMoved(int location) {
            output.println("OPPONENT_MOVED " + location);
            if(hasWinner()) {
                output.println("DEFEAT");
            } else if (boardFilledUp()) {
                output.println("TIE");
            } else {
                output.println("");
            }

        }

        public void run() {
            try {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

                // Tell the first player that it is his/her turn.
                if (mark == 'X') {
                    output.println("MESSAGE Your move");
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command.startsWith("MOVE")) {
                        int location = Integer.parseInt(command.substring(5));
                        if (legalMove(location, this)) {
                            output.println("VALID_MOVE");
                            if(hasWinner()) {
                                output.println("VICTORY");
                                DatabaseManager.updateScore(username, DatabaseManager.getScore(username) + 1);
                            } else if (boardFilledUp()) {
                                output.println("TIE");
                            } else {
                                output.println("");
                            }

                        } else {
                            output.println("MESSAGE ?");
                        }
                    } else if (command.startsWith("QUIT")) {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
                TicTacToeServer.logger.log(Level.SEVERE, "Player died: ", e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    TicTacToeServer.logger.log(Level.SEVERE, "Error closing socket", e);
                }
            }
        }

        @Override
        public String toString() {
            String oppo = opponent == null ? null : opponent.username;
            return "Player{" +
                    "username='" + username + '\'' +
                    ", opponent=" + oppo +
                    ", socket=" + socket +
                    '}';
        }
    }
}
