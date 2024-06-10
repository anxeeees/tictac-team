package com.tictactoe.tictacteam.server;

import com.tictactoe.tictacteam.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;
/**
 * The TicTacToeServer class represents the server side of the Tic Tac Toe game.
 * It listens for incoming client connections and sets up new games for each pair of players.
 * This class initializes the server socket and manages the game sessions.
 *
 * @author Robert Čuda
 * @author Ester Stankovská
 */
public class TicTacToeServer {
    /**
     * The main method of the TicTacToeServer class.
     * It initializes the server socket and starts listening for incoming client connections.
     *
     * @param args Command line arguments (not used).
     * @throws Exception If an error occurs during server initialization.
     */
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8080);
        Log my_log = new Log("log_server.txt");

        try {
            my_log.logger.log(Level.INFO, "Tic Tac Toe Server is Running");

            while (true) {
                Game game = new Game(my_log);
                Game.Player playerX = game.new Player(listener.accept(), 'X', my_log, null);
                Game.Player playerO = game.new Player(listener.accept(), 'O', my_log, playerX);
                playerX.setOpponent(playerO);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();
            }
        } finally {
            listener.close();
        }
    }
}

