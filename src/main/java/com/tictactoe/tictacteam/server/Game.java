package com.tictactoe.tictacteam.server;

import com.tictactoe.tictacteam.Log;
import com.tictactoe.tictacteam.database.DatabaseManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;

/**
 * The Game class represents a single game of Tic Tac Toe being played between two players.
 * It manages the game state, including the game board, player moves, and determining the winner.
 * This class also handles communication between players via sockets.
 *
 * @author Robert Čuda
 * @author Ester Stankovsá
 */
public class Game {
    private boolean isGameReady = false;
    private final Log my_log;
    private Player[] board = {
            null, null, null,
            null, null, null,
            null, null, null};

    Player currentPlayer;

    /**
     * Constructs a new Game object with the specified log.
     *
     * @param my_log The Log object used for logging messages.
     */
    public Game(Log my_log) {
        this.my_log = my_log;
    }

    /**
     * Checks if there is a winner in the current game state.
     *
     * @return true if there is a winner, false otherwise.
     */
    public boolean hasWinner() {
        return (board[0] != null && board[0] == board[1] && board[0] == board[2])
                || (board[3] != null && board[3] == board[4] && board[3] == board[5])
                || (board[6] != null && board[6] == board[7] && board[6] == board[8])
                || (board[0] != null && board[0] == board[3] && board[0] == board[6])
                || (board[1] != null && board[1] == board[4] && board[1] == board[7])
                || (board[2] != null && board[2] == board[5] && board[2] == board[8])
                || (board[0] != null && board[0] == board[4] && board[0] == board[8])
                || (board[2] != null && board[2] == board[4] && board[2] == board[6]);
    }

     /**
     * Checks if the game board is completely filled up with player marks.
     *
     * @return true if the board is filled up, false otherwise.
     */
    public boolean boardFilledUp() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a move at the given location is legal for the current player.
     * A move is legal if the game is ready, it's the current player's turn, and the specified location is empty.
     *
     * @param location The location where the player wants to make a move.
     * @param player   The player attempting the move.
     * @return true if the move is legal, false otherwise.
     */
    public synchronized boolean legalMove(int location, Player player) {
        if (isGameReady && player == currentPlayer && board[location] == null) {
            board[location] = currentPlayer;
            currentPlayer = currentPlayer.opponent;
            currentPlayer.otherPlayerMoved(location);
            return true;
        }
        return false;
    }
    /**
     * The Player class represents a player participating in a Tic Tac Toe game.
     * It handles communication with the player via sockets and processes their moves.
     */
    class Player extends Thread {
        char mark;
        Player opponent;
        String username;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        private final Log my_log;

        /**
         * Constructs a new Player object with the specified socket, mark, log, and opponent.
         *
         * @param socket   The socket connected to the player.
         * @param mark     The mark (X or O) assigned to the player.
         * @param my_log   The Log object used for logging messages.
         * @param opponent The opponent Player object.
         */
        public Player(Socket socket, char mark, Log my_log, Player opponent) {
            this.socket = socket;
            this.mark = mark;
            this.my_log = my_log;
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
                    my_log.logger.log(Level.INFO, "Sent WELCOME_BACK message to " + username);
                } else {
                    DatabaseManager.addUser(username);
                    output.println("WELCOME_NEW" + " " + username);
                    my_log.logger.log(Level.INFO, "Sent WELCOME_NEW message to " + username);
                }
                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
                my_log.logger.log(Level.INFO, "Sent 'Waiting for opponent to connect' message to " + username);
            } catch (IOException e) {
                my_log.logger.log(Level.INFO, "Tic Tac Toe Server is Running");
            }
        }

        //Accepts notification of who the opponent is.
        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        /**
         * Notifies the player about the opponent's move.
         *
         * @param location The location where the opponent made the move.
         */
        public void otherPlayerMoved(int location) {
            output.println("OPPONENT_MOVED " + location);
            output.println(
                    hasWinner() ? "DEFEAT" : boardFilledUp() ? "TIE" : "");
        }

        /**
         * Runs the player's thread, handling communication with the client and processing moves.
         * The thread is started after all players are connected.
         */
        public void run() {
            try {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

                // Tell the first player that it is his/her turn.
                if (mark == 'X') {
                    output.println("MESSAGE Your move");
                    isGameReady = true;
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command == null) {
                        return;
                    }
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
                my_log.logger.log(Level.SEVERE, "Player died: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                if (opponent != null) {
                    opponent.output.println("OPPONENT_LEFT");
                    opponent.output.println("MESSAGE Your opponent left the game. Do you want to play a new game? (YES/NO)");
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
