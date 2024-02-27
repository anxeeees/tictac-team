package com.tictactoe.tictacteam;

import java.sql.*;

public class Database {
Connection con;
Statement st;

ResultSet rows;
int val;

public Database() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection("jdbc:");
        if(con!=null) {
            System.out.println("Database is connected");
        }
        st = con.createStatement();
    } catch (Exception e) {
        e.printStackTrace();
    }

}

public int insert(String query) {
    try {
        val = st.executeUpdate(query);
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
    return val;
}
public ResultSet select(String query) {
    try {
        rows = st.executeQuery(query);
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
    return rows;
}

    public static void main(String[] args) {
        new Database();
    }

}
