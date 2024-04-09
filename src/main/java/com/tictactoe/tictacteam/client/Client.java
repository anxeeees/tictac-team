package com.tictactoe.tictacteam.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to Tic Tac Toe Server.");

            System.out.println(in.readLine()); // Server's prompt for username
            String username = userInput.readLine().trim();
            out.println(username); // Send username to server

            // Receive server response and display message to user
            System.out.println(in.readLine());


            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println(serverResponse);

                if (serverResponse.startsWith("Game over")) {
                    break;
                }

                // Client's turn
                String userInputStr = userInput.readLine();
                out.println(userInputStr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}