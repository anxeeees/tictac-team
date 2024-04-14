package com.tictactoe.tictacteam.client;

import com.tictactoe.tictacteam.client.ClientGUI;
import com.tictactoe.tictacteam.client.Gameplay;
import other.Message;
import other.MyListener;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import static com.tictactoe.tictacteam.client.ClientGUI.enableAllGameButtons;

/**
 * Created by Administrator on 9/20/2017.
 */
public class Client implements Observer
{
    private Socket clientSocket;
    public static String nameAlias;
    public static boolean newSession;
    private ObjectOutputStream oos;
    private MyListener clientListener;


    public Client()
    {

    }

    public void sendMsg()
    {
        Message message = new Message(nameAlias, ClientGUI.textField1.getText());

        try
        {
            oos.writeObject(message);
            ClientGUI.chatArea.append("Me: " + message.getMessage() + "\n");
            ClientGUI.textField1.setText("");
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public void makeMoveButtonPress(JButton button, int id)
    {
        Message msg = new Message("", button.getText(), button, id);
        try
        {
            oos.writeObject(msg);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void disconnect()
    {
        Gameplay.continueYesNo(oos, clientSocket, clientListener);
    }

    public void connect()
    {
        try
        {
            clientSocket = new Socket(ClientGUI.getIpAddressTextField(), ClientGUI.getPortNumTextField());

            newSession = true;
            if (newSession)
            {
                nameAlias = JOptionPane.showInputDialog("Enter Your Name");

                if (nameAlias.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "You must enter a name!");
                    return;
                }
                else if (!nameAlias.isEmpty())
                {
                    ClientGUI.connectButton.setEnabled(false);
                    oos = new ObjectOutputStream(clientSocket.getOutputStream());
                    ClientGUI.chatArea.append("Connected! waiting for player.....\n");

                    clientListener = new MyListener(clientSocket, this);
                    Thread thread1 = new Thread(clientListener);
                    thread1.start();

                }
            }



        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Message message = (Message) arg;
        String msg = message.getNameAlias() + ": " + message.getMessage();
        ClientGUI.chatArea.append(msg + "\n");

        if(message.getMessage().compareTo("you can start talking") == 0)
        {
            ClientGUI.sendButton.setEnabled(true);
            enableAllGameButtons();
        }
        else if(message.getMessage().compareTo("has disconnected.") == 0)
        {
            newSession = false;
            disconnect();
            connect();
            ClientGUI.disableAllGameButtons();
        }

        //TODO

        if (message.getMessage().equals("X"))
        {
            if (Gameplay.getPlayerTurn() == true)
            {
                if (message.getButtonID() == 1)
                {
                    ClientGUI.button1.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();

                }
                if (message.getButtonID() == 2)
                {
                    ClientGUI.button2.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 3)
                {
                    ClientGUI.button3.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 4)
                {
                    ClientGUI.button4.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 5)
                {
                    ClientGUI.button5.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 6)
                {
                    ClientGUI.button6.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 7)
                {
                    ClientGUI.button7.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 8)
                {
                    ClientGUI.button8.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 9)
                {
                    ClientGUI.button9.setText(message.getMessage());
                    Gameplay.setPlayerTurn(false);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
            }
        }
        if (message.getMessage().equals("O"))
        {
            if (Gameplay.getPlayerTurn() == false)
            {
                if (message.getButtonID() == 1)
                {
                    ClientGUI.button1.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 2)
                {
                    ClientGUI.button2.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 3)
                {
                    ClientGUI.button3.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 4)
                {
                    ClientGUI.button4.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 5)
                {
                    ClientGUI.button5.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 6)
                {
                    ClientGUI.button6.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 7)
                {
                    ClientGUI.button7.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 8)
                {
                    ClientGUI.button8.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
                if (message.getButtonID() == 9)
                {
                    ClientGUI.button9.setText(message.getMessage());
                    Gameplay.setPlayerTurn(true);

                    enableAllGameButtons();

                    ClientGUI.gameplay.checkForWinner();
                    disconnect();
                }
            }
        }
    }
}
