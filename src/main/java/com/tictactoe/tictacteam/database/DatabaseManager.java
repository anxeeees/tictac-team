package com.tictactoe.tictacteam.database;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    static {
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            System.out.println(1);
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find database.properties");
            }
            // Load the properties file
            prop.load(input);
            System.out.println(2);
            // Get the property values
            URL = prop.getProperty("db.url");
            USERNAME = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
            System.out.println(3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public static boolean userExists(String username) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addUser(String username) {
        if (!userExists(username)) {
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, score) VALUES (?, 0)")) {
                statement.setString(1, username);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateScore(String username, int score) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET score = ? WHERE username = ?")) {
            statement.setInt(1, score);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getScore(String username) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT score FROM users WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("score");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
