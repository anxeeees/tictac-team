package com.tictactoe.tictacteam.server;

import com.tictactoe.tictacteam.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class TicTacToeServer {
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

