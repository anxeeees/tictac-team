package com.tictactoe.tictacteam.client;


import other.Message;
import other.MyListener;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 9/21/2017.
 */
public class Gameplay implements Serializable
{
    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private static boolean playerTurn;
    private static boolean playerXWon;
    private static boolean playerOWon;
    private static boolean tie;

    private ObjectOutputStream oos;
    private Socket socket;
    private MyListener clientListener;

    private final String pathTofile = "res/playerTurn.txt";
    private File file = new File(pathTofile);
    private DataOutputStream outPlayerTurnTxt;
    private DataInputStream inPlayerTurnTxt;

    public Gameplay(JButton btn1,
                    JButton btn2,
                    JButton btn3,
                    JButton btn4,
                    JButton btn5,
                    JButton btn6,
                    JButton btn7,
                    JButton btn8,
                    JButton btn9)
    {
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.btn4 = btn4;
        this.btn5 = btn5;
        this.btn6 = btn6;
        this.btn7 = btn7;
        this.btn8 = btn8;
        this.btn9 = btn9;

        tie = false;

        if (file.exists())
        {
            try
            {
                inPlayerTurnTxt = new DataInputStream(new FileInputStream(file));

                try
                {
                    boolean valueofBoolPlayerTurn = Boolean.parseBoolean(inPlayerTurnTxt.readUTF().trim());
                    playerTurn = valueofBoolPlayerTurn;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        } else
        {
            this.playerTurn = true;
        }

        this.playerXWon = false;
        this.playerOWon = false;

    }

    public static boolean getPlayerTurn()
    {
        return playerTurn;
    }

    public static void setPlayerTurn(boolean turnBool)
    {
        playerTurn = turnBool;
    }

    private void disbleAllGameButton()
    {
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
    }

    public void playerLeaving(ObjectOutputStream oos, Socket socket, MyListener clientListener)
    {
        this.oos = oos;
        this.socket = socket;
        this.clientListener = clientListener;

        try
        {
            oos.close();
            socket.close();
            clientListener = null;
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void continueYesNo(ObjectOutputStream oos, Socket socket, MyListener clientListener)
    {

        if ((playerOWon == true) || (playerXWon == true))
        {
            int clientResponse = JOptionPane.showConfirmDialog(null, "Do you want to continue playing?", "Confirmation", JOptionPane.YES_NO_OPTION);
            playerXWon = false;
            playerOWon = false;
            if (clientResponse == JOptionPane.YES_NO_OPTION)
            {
                ClientGUI.button1.setEnabled(true);
                ClientGUI.button2.setEnabled(true);
                ClientGUI.button3.setEnabled(true);
                ClientGUI.button4.setEnabled(true);
                ClientGUI.button5.setEnabled(true);
                ClientGUI.button6.setEnabled(true);
                ClientGUI.button7.setEnabled(true);
                ClientGUI.button8.setEnabled(true);
                ClientGUI.button9.setEnabled(true);

                ClientGUI.button1.setText("");
                ClientGUI.button2.setText("");
                ClientGUI.button3.setText("");
                ClientGUI.button4.setText("");
                ClientGUI.button5.setText("");
                ClientGUI.button6.setText("");
                ClientGUI.button7.setText("");
                ClientGUI.button8.setText("");
                ClientGUI.button9.setText("");
            }
            else
            {
                try
                {
                    Client.newSession = true;
                    Message message = new Message(Client.nameAlias, "has disconnected.");
                    oos.writeObject(message);

                    oos.close();
                    socket.close();
                    clientListener = null;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                ClientGUI.chatArea.append("Disconnected...");
                ClientGUI.connectButton.setEnabled(true);
                ClientGUI.sendButton.setEnabled(false);
                ClientGUI.button1.setEnabled(false);
                ClientGUI.button2.setEnabled(false);
                ClientGUI.button3.setEnabled(false);
                ClientGUI.button4.setEnabled(false);
                ClientGUI.button5.setEnabled(false);
                ClientGUI.button6.setEnabled(false);
                ClientGUI.button7.setEnabled(false);
                ClientGUI.button8.setEnabled(false);
                ClientGUI.button9.setEnabled(false);

                ClientGUI.button1.setText("");
                ClientGUI.button2.setText("");
                ClientGUI.button3.setText("");
                ClientGUI.button4.setText("");
                ClientGUI.button5.setText("");
                ClientGUI.button6.setText("");
                ClientGUI.button7.setText("");
                ClientGUI.button8.setText("");
                ClientGUI.button9.setText("");
            }

        }
        if ((playerOWon == false) || (playerXWon == false))
        {
            if (tie == true)
            {
                int clientResponse = JOptionPane.showConfirmDialog(null, "Do you want to continue playing?", "Confirmation", JOptionPane.YES_NO_OPTION);
                playerXWon = false;
                playerOWon = false;
                tie = false;

                if (clientResponse == JOptionPane.YES_NO_OPTION)
                {
                    ClientGUI.button1.setEnabled(true);
                    ClientGUI.button2.setEnabled(true);
                    ClientGUI.button3.setEnabled(true);
                    ClientGUI.button4.setEnabled(true);
                    ClientGUI.button5.setEnabled(true);
                    ClientGUI.button6.setEnabled(true);
                    ClientGUI.button7.setEnabled(true);
                    ClientGUI.button8.setEnabled(true);
                    ClientGUI.button9.setEnabled(true);

                    ClientGUI.button1.setText("");
                    ClientGUI.button2.setText("");
                    ClientGUI.button3.setText("");
                    ClientGUI.button4.setText("");
                    ClientGUI.button5.setText("");
                    ClientGUI.button6.setText("");
                    ClientGUI.button7.setText("");
                    ClientGUI.button8.setText("");
                    ClientGUI.button9.setText("");
                }
                else
                {
                    try
                    {
                        Client.newSession = true;
                        Message message = new Message(Client.nameAlias, "has disconnected.");
                        oos.writeObject(message);

                        oos.close();
                        socket.close();
                        clientListener = null;
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    ClientGUI.chatArea.append("Disconnected...");
                    ClientGUI.connectButton.setEnabled(true);
                    ClientGUI.sendButton.setEnabled(false);
                    ClientGUI.button1.setEnabled(false);
                    ClientGUI.button2.setEnabled(false);
                    ClientGUI.button3.setEnabled(false);
                    ClientGUI.button4.setEnabled(false);
                    ClientGUI.button5.setEnabled(false);
                    ClientGUI.button6.setEnabled(false);
                    ClientGUI.button7.setEnabled(false);
                    ClientGUI.button8.setEnabled(false);
                    ClientGUI.button9.setEnabled(false);

                    ClientGUI.button1.setText("");
                    ClientGUI.button2.setText("");
                    ClientGUI.button3.setText("");
                    ClientGUI.button4.setText("");
                    ClientGUI.button5.setText("");
                    ClientGUI.button6.setText("");
                    ClientGUI.button7.setText("");
                    ClientGUI.button8.setText("");
                    ClientGUI.button9.setText("");
                }
            }
        }
    }

    public void checkForWinner()
    {
        if (btn1.getText().toUpperCase().equals("X"))
        {
            if (btn4.getText().toUpperCase().equals("X"))
            {
                if (btn7.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }

        if (btn2.getText().toUpperCase().equals("X"))
        {
            if (btn5.getText().toUpperCase().equals("X"))
            {
                if (btn8.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }

        if (btn3.getText().toUpperCase().equals("X"))
        {
            if (btn6.getText().toUpperCase().equals("X"))
            {
                if (btn9.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;

                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }

        if (btn1.getText().toUpperCase().equals("X"))
        {
            if (btn2.getText().toUpperCase().equals("X"))
            {
                if (btn3.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;

                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }

        if (btn4.getText().toUpperCase().equals("X"))
        {
            if (btn5.getText().toUpperCase().equals("X"))
            {
                if (btn6.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }

        if (btn7.getText().toUpperCase().equals("X"))
        {
            if (btn8.getText().toUpperCase().equals("X"))
            {
                if (btn9.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }

        if (btn1.getText().toUpperCase().equals("X"))
        {
            if (btn5.getText().toUpperCase().equals("X"))
            {
                if (btn9.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }

        if (btn7.getText().toUpperCase().equals("X"))
        {
            if (btn5.getText().toUpperCase().equals("X"))
            {
                if (btn3.getText().toUpperCase().equals("X"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("false".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = true;
                    playerOWon = false;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player X WON");

                }
            }
        }
        ///////////////////////////////////////////////////////////////////////

        if (btn1.getText().toUpperCase().equals("O"))
        {
            if (btn4.getText().toUpperCase().equals("O"))
            {
                if (btn7.getText().toUpperCase().equals("O"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }

        if (btn2.getText().toUpperCase().equals("O"))
        {
            if (btn5.getText().toUpperCase().equals("O"))
            {
                if (btn8.getText().toUpperCase().equals("O"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }

        if (btn3.getText().toUpperCase().equals("O"))
        {
            if (btn6.getText().toUpperCase().equals("O"))
            {
                if (btn9.getText().toUpperCase().equals("O"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }

        if (btn1.getText().toUpperCase().equals("O"))
        {
            if (btn2.getText().toUpperCase().equals("O"))
            {
                if (btn3.getText().toUpperCase().equals("O"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }

        if (btn4.getText().toUpperCase().equals("O"))
        {
            if (btn5.getText().toUpperCase().equals("O"))
            {
                if (btn6.getText().toUpperCase().equals("O".trim()))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }

        if (btn7.getText().toUpperCase().equals("O"))
        {
            if (btn8.getText().toUpperCase().equals("O"))
            {
                if (btn9.getText().toUpperCase().equals("O"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }

        if (btn1.getText().toUpperCase().equals("O"))
        {
            if (btn5.getText().toUpperCase().equals("O"))
            {
                if (btn9.getText().toUpperCase().equals("O"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }

        if (btn7.getText().toUpperCase().equals("O"))
        {
            if (btn5.getText().toUpperCase().equals("O"))
            {
                if (btn3.getText().toUpperCase().equals("O"))
                {
                    try
                    {
                        outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                        outPlayerTurnTxt.writeUTF("true".trim());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    playerXWon = false;
                    playerOWon = true;
                    disbleAllGameButton();
                    JOptionPane.showMessageDialog(null, "Player O WON");

                }
            }
        }
        if ((btn1.getText().toUpperCase().equals("X") || btn1.getText().toUpperCase().equals("O")) &&
                (btn2.getText().toUpperCase().equals("X") || btn2.getText().toUpperCase().equals("O")) &&
                (btn3.getText().toUpperCase().equals("X") || btn3.getText().toUpperCase().equals("O")) &&
                (btn4.getText().toUpperCase().equals("X") || btn4.getText().toUpperCase().equals("O")) &&
                (btn5.getText().toUpperCase().equals("X") || btn5.getText().toUpperCase().equals("O")) &&
                (btn6.getText().toUpperCase().equals("X") || btn6.getText().toUpperCase().equals("O")) &&
                (btn7.getText().toUpperCase().equals("X") || btn7.getText().toUpperCase().equals("O")) &&
                (btn8.getText().toUpperCase().equals("X") || btn8.getText().toUpperCase().equals("O")) &&
                (btn9.getText().toUpperCase().equals("X") || btn9.getText().toUpperCase().equals("O"))
        )
        {
            if (playerXWon == false && playerOWon == false)
            {
                try
                {
                    outPlayerTurnTxt = new DataOutputStream(new FileOutputStream(file));
                    outPlayerTurnTxt.writeUTF("false".trim());
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                playerXWon = false;
                playerOWon = false;
                tie = true;
                disbleAllGameButton();
                JOptionPane.showMessageDialog(null, "ITS A TIE!");
            }
        }
    }

}

