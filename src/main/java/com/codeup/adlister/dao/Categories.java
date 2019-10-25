package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Categories {

    // Get a list of categories with this ad id
    List<String> getCategoriesByAdId(long id);

    // Insert categories
    void insert(Ad ad);

    // Get the category's id number
    long getCategoryId(String category);

    // Delete the categories of an existing ad
    void delete(Ad ad);
}
