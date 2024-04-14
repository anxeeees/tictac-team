package other;

import javax.swing.*;
import java.io.Serializable;

/**
 * Created by Administrator on 9/20/2017.
 */
public class Message implements Serializable
{
    private static final long serialVersionUID = -8286413926708233541L;
    private String nameAlias;
    private String message;
    private JButton button;
    private int buttonID;

    public Message(String nameAlias, String msg)
    {
        this.nameAlias = nameAlias;
        this.message = msg;
    }

    public Message(String nameAlias, String msg, JButton button, int buttonID)
    {
        this.nameAlias = nameAlias;
        this.message = msg;
        this.button = button;
        this.buttonID = buttonID;
    }

    public int getButtonID()
    {
        return buttonID;
    }

    public void setButtonID(int buttonID)
    {
        this.buttonID = buttonID;
    }

    public JButton getButton()
    {
        return button;
    }

    public void setButton(JButton button)
    {
        this.button = button;
    }

    public String getNameAlias()
    {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias)
    {
        this.nameAlias = nameAlias;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "nameAlias='" + nameAlias + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
