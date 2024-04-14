package other;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 9/20/2017.
 */
public class MyListener extends Observable implements Runnable
{
    private int lisNumber;
    private Socket socket;
    private ObjectInputStream objectInputStream;


    public MyListener(Socket clientSocket, Observer o)
    {
        lisNumber = 0;
        this.socket = clientSocket;
        this.addObserver(o);
    }

    public MyListener(int listenerNum,Socket socket, Observer o)
    {
        this.lisNumber = listenerNum;
        this.socket = socket;
        this.addObserver(o);
    }

    public int getLisNumber()
    {
        return lisNumber;
    }

    @Override
    public void run()
    {
        try
        {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            while(true)
            {
                Object object = objectInputStream.readObject();
                setChanged();
                notifyObservers(object);

                Message message = (Message) object;

                if(message.getMessage().compareTo("has disconnected.") == 0)
                {
                    objectInputStream.close();
                    socket.close();
                }

            }
        }
        catch (SocketException e)
        {

        }
        catch (EOFException e)
        {

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
