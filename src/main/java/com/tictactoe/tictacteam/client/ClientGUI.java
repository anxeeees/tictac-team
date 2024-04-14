package com.tictactoe.tictacteam.client;/*
 * Created by JFormDesigner on Mon Sep 18 16:26:57 MDT 2017
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Huy Khoa Le
 */
public class ClientGUI extends JFrame {
    private Client client;
    public static Gameplay gameplay;

    public ClientGUI() {
        initComponents();
        displayClientGUI();

    }

    private void connectButtonActionPerformed(ActionEvent e) {
        client = new Client();
        client.connect();
    }

    private void sendButtonActionPerformed(ActionEvent e) {
        client.sendMsg();
    }

    private void button1ActionPerformed(ActionEvent e) {

        if (button1.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button1.setText("X");
                client.makeMoveButtonPress(button1, 1);


                button1.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button1.setText("O");
                client.makeMoveButtonPress(button1, 1);

                button1.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }

    }

    private void button2ActionPerformed(ActionEvent e) {
        if (button2.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button2.setText("X");
                client.makeMoveButtonPress(button2, 2);

                button2.setEnabled(false);
                disableAllGameButtons();


                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button2.setText("O");
                client.makeMoveButtonPress(button2, 2);

                button2.setEnabled(false);
                disableAllGameButtons();


                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }

    }

    private void button3ActionPerformed(ActionEvent e) {
        if (button3.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button3.setText("X");
                client.makeMoveButtonPress(button3, 3);

                button3.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button3.setText("O");
                client.makeMoveButtonPress(button3, 3);

                button3.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }


    }

    private void button4ActionPerformed(ActionEvent e) {
        if (button4.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button4.setText("X");
                client.makeMoveButtonPress(button4, 4);

                button4.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button4.setText("O");
                client.makeMoveButtonPress(button4, 4);

                button4.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }


    }

    private void button5ActionPerformed(ActionEvent e) {
        if (button5.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button5.setText("X");
                client.makeMoveButtonPress(button5, 5);

                button5.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button5.setText("O");
                client.makeMoveButtonPress(button5, 5);

                button5.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }
    }

    private void button6ActionPerformed(ActionEvent e) {
        if (button6.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button6.setText("X");
                client.makeMoveButtonPress(button6, 6);

                button6.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button6.setText("O");
                client.makeMoveButtonPress(button6, 6);

                button6.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }
    }

    private void button7ActionPerformed(ActionEvent e) {
        if (button7.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button7.setText("X");
                client.makeMoveButtonPress(button7, 7);

                button7.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button7.setText("O");
                client.makeMoveButtonPress(button7, 7);

                button7.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }
    }

    private void button8ActionPerformed(ActionEvent e) {
        if (button8.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button8.setText("X");
                client.makeMoveButtonPress(button8, 8);

                button8.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button8.setText("O");
                client.makeMoveButtonPress(button8, 8);

                button8.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }
    }

    private void button9ActionPerformed(ActionEvent e) {
        if (button9.getText().equals("")) {

            if (Gameplay.getPlayerTurn() == true) {
                button9.setText("X");
                client.makeMoveButtonPress(button9, 9);

                button9.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(false);
            } else {
                button9.setText("O");
                client.makeMoveButtonPress(button9, 9);

                button9.setEnabled(false);
                disableAllGameButtons();

                gameplay.checkForWinner();
                client.disconnect();
                Gameplay.setPlayerTurn(true);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        connectButton = new JButton();
        ipAddressTextField = new JTextField();
        portNumTextField = new JTextField();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        scrollPane1 = new JScrollPane();
        chatArea = new JTextArea();
        textField1 = new JTextField();
        sendButton = new JButton();

        //======== this ========
        setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 18));
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Welcome To Huy, Le Tic Tac Toe!");
        label1.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        //---- label2 ----
        label2.setText("Enter The IP Address:");

        //---- label3 ----
        label3.setText("Enter The Port Number:");

        //---- connectButton ----
        connectButton.setText("Connect");
        connectButton.addActionListener(e -> connectButtonActionPerformed(e));

        //---- ipAddressTextField ----
        ipAddressTextField.setText("localhost");
        ipAddressTextField.setEditable(true);

        //---- portNumTextField ----
        portNumTextField.setText("5555");
        portNumTextField.setEditable(false);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(3, 3));

            //---- button1 ----
            button1.setEnabled(false);
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);

            //---- button2 ----
            button2.setEnabled(false);
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);

            //---- button3 ----
            button3.setEnabled(false);
            button3.addActionListener(e -> button3ActionPerformed(e));
            panel1.add(button3);

            //---- button4 ----
            button4.setEnabled(false);
            button4.addActionListener(e -> button4ActionPerformed(e));
            panel1.add(button4);

            //---- button5 ----
            button5.setEnabled(false);
            button5.addActionListener(e -> button5ActionPerformed(e));
            panel1.add(button5);

            //---- button6 ----
            button6.setEnabled(false);
            button6.addActionListener(e -> button6ActionPerformed(e));
            panel1.add(button6);

            //---- button7 ----
            button7.setEnabled(false);
            button7.addActionListener(e -> button7ActionPerformed(e));
            panel1.add(button7);

            //---- button8 ----
            button8.setEnabled(false);
            button8.addActionListener(e -> button8ActionPerformed(e));
            panel1.add(button8);

            //---- button9 ----
            button9.setEnabled(false);
            button9.addActionListener(e -> button9ActionPerformed(e));
            panel1.add(button9);
        }

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(chatArea);
        }

        //---- sendButton ----
        sendButton.setText("Send");
        sendButton.setEnabled(false);
        sendButton.addActionListener(e -> sendButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(167, 167, 167)
                                                .addComponent(label1))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                                        .addComponent(label2)
                                                                        .addComponent(label3))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                                        .addComponent(ipAddressTextField, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(portNumTextField, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(connectButton)))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 3, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(label1)
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(sendButton)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(ipAddressTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(label2))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(label3)
                                                                        .addComponent(portNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(connectButton))
                                                .addGap(24, 24, 24)
                                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    public static JButton connectButton;
    private static JTextField ipAddressTextField;
    private static JTextField portNumTextField;
    private JPanel panel1;
    public static JButton button1;
    public static JButton button2;
    public static JButton button3;
    public static JButton button4;
    public static JButton button5;
    public static JButton button6;
    public static JButton button7;
    public static JButton button8;
    public static JButton button9;
    private JScrollPane scrollPane1;
    public static JTextArea chatArea;
    public static JTextField textField1;
    public static JButton sendButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void displayClientGUI() {
        this.setVisible(true);
        this.setTitle("Client GUI");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static String getIpAddressTextField() {
        return ipAddressTextField.getText();
    }

    public static int getPortNumTextField() {
        int port;
        port = Integer.parseInt(portNumTextField.getText());
        return port;
    }

    public void initiateGameplayFunctionality() {
        gameplay = new Gameplay(button1, button2, button3, button4, button5, button6, button7, button8, button9);
    }

    public static void disableAllGameButtons() {
        ClientGUI.button1.setEnabled(false);
        ClientGUI.button2.setEnabled(false);
        ClientGUI.button3.setEnabled(false);
        ClientGUI.button4.setEnabled(false);
        ClientGUI.button5.setEnabled(false);
        ClientGUI.button6.setEnabled(false);
        ClientGUI.button7.setEnabled(false);
        ClientGUI.button8.setEnabled(false);
        ClientGUI.button9.setEnabled(false);
    }

    public static void enableAllGameButtons() {
        ClientGUI.button1.setEnabled(true);
        ClientGUI.button2.setEnabled(true);
        ClientGUI.button3.setEnabled(true);
        ClientGUI.button4.setEnabled(true);
        ClientGUI.button5.setEnabled(true);
        ClientGUI.button6.setEnabled(true);
        ClientGUI.button7.setEnabled(true);
        ClientGUI.button8.setEnabled(true);
        ClientGUI.button9.setEnabled(true);
    }


    public static void main(String[] args) {
        ClientGUI clientGUI = new ClientGUI();
        clientGUI.initiateGameplayFunctionality();
    }
}
