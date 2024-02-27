package com.tictactoe.tictacteam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage implements ActionListener {
    JFrame frame;
    JLabel label_heading, label_username, label_password;
    JTextField text_username;
    JPasswordField text_password;
    JButton button_log, button_cancel, button_reg;

    JButton button;

    Font fon1, fon2;


    public LoginPage() {
        frame = new JFrame("Login page");
        frame.getContentPane().setBackground(Color.BLUE);

        fon1 = new Font("Times", Font.BOLD, 24);
        fon2 = new Font("Verdana", Font.BOLD, 14);

        label_heading = new JLabel("Login credentials");
        label_heading.setForeground(Color.GRAY);
        label_heading.setFont(fon1);
        label_heading.setBounds(120, 10, 250, 30);
        frame.add(label_heading);

        label_username = new JLabel("Username");
        label_username.setForeground(Color.yellow);
        label_username.setFont(fon2);
        label_username.setBounds(50, 50, 100, 40);
        frame.add(label_username);

        text_username = new JTextField();
        text_username.setFont(fon2);
        text_username.setBounds(155, 60, 220, 30);
        frame.add(text_username);

        label_password = new JLabel("Password");
        label_password.setForeground(Color.red);
        label_password.setFont(fon2);
        label_password.setBounds(50, 100, 100, 40);
        frame.add(label_password);


        text_password = new JPasswordField();
        text_password.setFont(fon2);
        text_password.setBounds(155, 110, 220, 30);
        frame.add(text_password);

        button_log = new JButton("Login");
        button_log.setFont(fon2);
        button_log.setBounds(155, 170, 100, 40);
        button_log.addActionListener(this);
        frame.add(button_log);

        button_cancel = new JButton("Cancel");
        button_cancel.setFont(fon2);
        button_cancel.setBounds(260, 170, 100, 40);
        frame.add(button_cancel);

        button_reg = new JButton("Sign up");
        button_reg.setForeground(Color.BLUE);
        button_reg.setFont(fon2);
        button_reg.setBounds(320, 250, 100, 30);
        button_reg.addActionListener(this);
        frame.add(button_reg);

        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new LoginPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button_reg) {
            new RegisterPage();
        }
        if(e.getSource()== button_log) {
            String username = text_username.getText();
            String password = text_password.getText();
            String query = "select from registration where username" + username + "password =" + password;
            Database db = new Database();
            ResultSet resultSet = db.select(query);

            try {
                if(resultSet.next()) {
                    JOptionPane.showMessageDialog(frame, "Login succ");
                    new TicTacToe();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "invalid username or pass");
                }

            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
