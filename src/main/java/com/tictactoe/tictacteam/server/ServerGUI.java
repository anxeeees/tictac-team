package com.tictactoe.tictacteam.server;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Huy Khoa Le
 */
public class ServerGUI extends JFrame {
    public ServerGUI() {
        initComponents();
        displayServerGUI();
        new Server();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        scrollPane2 = new JScrollPane();
        serverTextArea = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();

        //======== scrollPane2 ========
        {

            //---- serverTextArea ----
            serverTextArea.setEditable(false);
            scrollPane2.setViewportView(serverTextArea);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JScrollPane scrollPane2;
    private static JTextArea serverTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private void displayServerGUI()
    {
        this.setVisible(true);
        this.setTitle("Server GUI");
        this.setSize(500,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setServerTextArea(String msg)
    {
        serverTextArea.append(msg);
    }

    public static void main(String[] args)
    {
        new ServerGUI();

    }

}
