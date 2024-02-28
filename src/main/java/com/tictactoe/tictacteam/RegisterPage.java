package com.tictactoe.tictacteam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage implements ActionListener {
    JFrame frame;
    JLabel label_heading, label_username, label_pass, label_passcon;
    JTextField text_username;
    JPasswordField text_pass, text_passcon;
    JButton butt_signup, butt_cancel;
    Font fon1, fon2;


    public RegisterPage() {
        frame = new JFrame("Registration");
        frame.getContentPane().setBackground(Color.lightGray);

        fon1 = new Font("arial", Font.BOLD, 22);
        fon2 = new Font("arial", Font.BOLD, 14);

        label_heading = new JLabel("User registration");
        label_heading.setForeground(Color.blue);
        label_heading.setFont(fon1);
        label_heading.setBounds(110, 10, 250, 40);
        frame.add(label_heading);

        label_username = new JLabel("Username");
        label_username.setForeground(Color.blue);
        label_username.setFont(fon2);
        label_username.setBounds(20, 150, 120, 40);
        frame.add(label_username);

        text_username = new JTextField();
        text_username.setFont(fon2);
        text_username.setBounds(140, 150, 220, 30);
        frame.add(text_username);

        label_pass = new JLabel("Password");
        label_pass.setForeground(Color.blue);
        label_pass.setFont(fon2);
        label_pass.setBounds(20, 200, 100, 40);
        frame.add(label_pass);

        text_pass = new JPasswordField();
        text_pass.setFont(fon2);
        text_pass.setBounds(140, 210, 220, 30);
        frame.add(text_pass);

        label_pass = new JLabel("Confirm password");
        label_pass.setForeground(Color.blue);
        label_pass.setFont(fon2);
        label_pass.setBounds(20, 250, 100, 40);
        frame.add(label_pass);

        text_passcon = new JPasswordField();
        text_passcon.setFont(fon2);
        text_passcon.setBounds(140, 260, 220, 30);
        frame.add(text_passcon);

        butt_signup = new JButton("Signup");
        butt_signup.setFont(fon2);
        butt_signup.setBounds(145, 300, 100, 40);
        butt_signup.addActionListener(this);
        frame.add(butt_signup);

        butt_cancel = new JButton("Cancel");
        butt_cancel.setFont(fon2);
        butt_cancel.setBounds(255, 300, 100, 40);
        butt_cancel.addActionListener(this);
        frame.add(butt_cancel);

        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = text_username.getText();
        String pass = text_pass.getText();
        System.out.println(username+pass);

        if(e.getSource()== butt_signup) {
            Database database = new Database();
            String query = "insert into registraton" + username + pass;
            int ans = database.insert(query);
            if(ans>0) {
                JOptionPane.showMessageDialog(frame, "Registration succ");
                frame.dispose();
            }
        }
    }
}
