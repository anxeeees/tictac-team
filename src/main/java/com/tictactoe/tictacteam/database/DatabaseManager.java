package com.tictactoe.tictacteam.database;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * DatabaseManager class handles database operations such as connecting to the database,
 * checking user existence, adding users, updating scores, and retrieving scores.
 * It uses JDBC to interact with the underlying database.
 *
 * @author Robert Čuda
 * @author Ester Stankovsá
 */
public class DatabaseManager {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find database.properties");
            }

            prop.load(input);
            URL = prop.getProperty("db.url");
            USERNAME = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a connection to the database.
     *
     * @return Connection object representing the connection to the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    /**
     * Checks if a user with the given username exists in the database.
     *
     * @param username The username to check for existence.
     * @return true if the user exists, false otherwise.
     */
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

    /**
     * Adds a new user to the database with the specified username and initial score of 0.
     *
     * @param username The username of the new user to add.
     */
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

    /**
     * Updates the score of the user with the given username in the database.
     *
     * @param username The username of the user whose score is to be updated.
     * @param score    The new score to set for the user.
     */
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

    /**
     * Retrieves the score of the user with the given username from the database.
     *
     * @param username The username of the user whose score is to be retrieved.
     * @return The score of the user, or 0 if the user does not exist in the database.
     */
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