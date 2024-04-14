package com.tictactoe.tictacteam.server;

import com.tictactoe.tictacteam.server.ClientHandler;
import com.tictactoe.tictacteam.server.ServerGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Administrator on 9/20/2017.
 */
public class Server
{
    private static ArrayList<Socket> listOfClients = new ArrayList<Socket>(2);
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Server()
    {
        try
        {
            serverSocket = new ServerSocket(5555);
            ServerGUI.setServerTextArea("Server Up And Running On Port 5555....\n");
            while(true)
            {
                clientSocket = serverSocket.accept();
                ServerGUI.setServerTextArea("connected waiting...\n");
                listOfClients.add(clientSocket);

                //see if there are 2 clients connected
                if(listOfClients.size() == 2)
                {
                    ServerGUI.setServerTextArea("both clients are now connected!\n");
                    ClientHandler clientHandler = new ClientHandler(listOfClients.get(0), listOfClients.get(1));

                    //create a new thread for client handler
                    Thread clientHandlerThread = new Thread(clientHandler);
                    clientHandlerThread.start();

                    //clear list of clients to allow more 2 player connections to be made
                    listOfClients.clear();
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
