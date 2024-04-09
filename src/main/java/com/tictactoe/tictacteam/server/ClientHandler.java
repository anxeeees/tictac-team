package com.tictactoe.tictacteam.server;

import java.io.*;
import java.net.*;
import com.tictactoe.tictacteam.database.DatabaseManager;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Prompt client for username
            out.println("Enter your username:");

            // Receive username input from client
            String username = in.readLine().trim();

            // Check if username exists in the database
            if (!DatabaseManager.userExists(username)) {
                // If username does not exist, add it to the database
                DatabaseManager.addUser(username);
                out.println("Username added to the database.");
            } else {
                out.println("Welcome back, " + username + "!");
            }
            out.println("Welcome to Tic Tac Toe!");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Handle client input
                System.out.println("Received from client: " + inputLine);

                // Example of broadcasting message to all clients
                Server.broadcast("Client " + clientSocket + ": " + inputLine);

                // Example of sending a specific message to this client
                // out.println("Server received: " + inputLine);

                // Exit condition
                if (inputLine.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Server.removeClient(this);
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendMessage(String message) {
        out.println(message);
    }
}