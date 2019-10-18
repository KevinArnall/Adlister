package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {

    private Connection connection;

    // Constructor
    public MySQLUsersDao(Config config) {
        try {
            // Register new connection to db with the driver
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            // New statement to find a user with a specific username
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1");
            // Insert username
            stmt.setString(1, username);

            // Execute query and return found user
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public User findUserById(long id) {
        try {
            // New statement to find a user with a specific user id
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            // Insert id
            stmt.setLong(1, id);

            // Execute query and return found user
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by id.", e);
        }
    }

    @Override
    public Long insert(User user) {
        try {
            // New query to insert a user into db
            String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
            // Prepare statement
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // Insert users details
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            // Execute update
            stmt.executeUpdate();

            // Get generated keys from query
            ResultSet rs = stmt.getGeneratedKeys();

            // Need this for some reason
            rs.next();

            // Return generated key
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        // If not found, return null
        if (!rs.next()) {
            return null;
        }

        // Create user from columns in the ResultSet
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }
}