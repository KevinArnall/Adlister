package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {

    private Connection connection;

    public MySQLCategoriesDao(Config config) {
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
    public List<String> getCategoriesByAdId(long id) {
        try {
            // Prepare new statement to get the category names that are associated with this ad id
            PreparedStatement stmt = connection.prepareStatement("SELECT c.name FROM ad_cat JOIN categories c on ad_cat.cat_id = c.id WHERE ad_cat.ad_id = ?");
            stmt.setLong(1, id);

            // Execute
            ResultSet rs = stmt.executeQuery();

            return createCategoriesFromResults(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving categories by id");
        }
    }

    @Override
    public void insert(Ad ad) {
        try {
            // Loop through the list of categories
            for (String category : ad.getCategories()) {
                // Query to insert into association table
                String insertQuery = "INSERT INTO ad_cat(ad_id, cat_id) VALUES (?,?)";
                PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setLong(1, ad.getId());
                stmt.setLong(2, getCategoryId(category));

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting categories into db");
        }
    }

    @Override
    public void delete(long id) {
        try {
            // Query to delete from association table
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM ad_cat WHERE ad_id = ?");
            stmt.setLong(1, id);

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting categories into db");
        }
    }

    @Override
    public long getCategoryId(String category) {
        try {
            // Statement to get the id of a category
            PreparedStatement stmt = connection.prepareStatement("SELECT id FROM categories WHERE name = ?");
            stmt.setString(1, category);

            // Execute
            ResultSet rs = stmt.executeQuery();
            rs.next();

            // Return id
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting category id");
        }
    }

    private List<String> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<String> categories = new ArrayList<>();

        // Loop through each row in the ResultSet
        while (rs.next()) {
            categories.add(rs.getString("name"));
        }
        return categories;
    }
}
