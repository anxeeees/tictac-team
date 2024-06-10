package com.tictactoe.tictacteam.server;

import java.io.*;
import java.net.ServerSocket;
import java.util.logging.*;

public class TicTacToeServer {

    static final Logger logger = Logger.getLogger(TicTacToeServer.class.getName());
    private static FileHandler fileHandler;

    public static void main(String[] args) throws Exception {
        setupLogging();

        ServerSocket listener = new ServerSocket(8080);
        logger.log(Level.INFO, "Tic Tac Toe Server is Running");
        try {
            while (true) {
                Game game = new Game();
                System.out.println(1);
                Game.Player playerX = game.new Player(listener.accept(), 'X', null);
                System.out.println(playerX);
                Game.Player playerO = game.new Player(listener.accept(), 'O', playerX);
                System.out.println(playerO);
                System.out.println(2);
                playerX.setOpponent(playerO);
                System.out.println(playerX);
                System.out.println(playerO);
                System.out.println(3);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();
                System.out.println(4);
            }
        } finally {
            listener.close();
            System.out.println(5);
        }
    }

    private static void setupLogging() {
        try {
            // Configure logger to write to a file
            fileHandler = new FileHandler("server_log.txt");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            // Configure logger to write to console as well
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up logging", e);
        }
    }
}
