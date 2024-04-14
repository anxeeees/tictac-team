package com.tictactoe.tictacteam.server;

import other.Message;
import other.MyListener;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 9/20/2017.
 */
public class ClientHandler implements Observer, Runnable
{
    private static boolean isBothClientsConnected;

    private ObjectOutputStream client1OutStream;
    private ObjectOutputStream client2OutStream;

    private Socket client1Socket;
    private Socket client2Socket;

    private MyListener listener1;
    private MyListener listener2;

    public ClientHandler(Socket clientSocket1, Socket clientSocket2)
    {
        this.client1Socket = clientSocket1;
        this.client2Socket = clientSocket2;

        try
        {
            client1OutStream = new ObjectOutputStream(clientSocket1.getOutputStream());
            client2OutStream = new ObjectOutputStream(clientSocket2.getOutputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        listener1 = new MyListener(1, client1Socket, this);
        listener2 = new MyListener(2, client2Socket, this);

        Thread thread1 = new Thread(listener1);
        Thread thread2 = new Thread(listener2);
        thread1.start();
        thread2.start();


        try
        {
            Message message = new Message("Connected", "you can start talking");

            isBothClientsConnected = true;
            client1OutStream.writeObject(message);
            client2OutStream.writeObject(message);

            while (client1Socket.isConnected() && client2Socket.isConnected()) ;

            client1Socket.close();
            client2Socket.close();
            client1OutStream.close();
            client2OutStream.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        MyListener lis = (MyListener) o;
        Message message = (Message) arg;

        try
        {
            if (lis.getLisNumber() == 1)
            {
                client2OutStream.writeObject(message);
            }else
            {
                client1OutStream.writeObject(message);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public static boolean isIsBothClientsConnected()
    {
        return isBothClientsConnected;
    }
}
