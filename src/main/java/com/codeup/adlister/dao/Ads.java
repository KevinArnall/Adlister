package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {

    // Get a list of all the ads
    List<Ad> all();

    // Get a list of ads made by a specific user
    List<Ad> getAdsByUserId(long id);

    // Insert a new ad and return the new ad's id
    Long insert(Ad ad);

    // Get an ad by id
    Ad getAdById(long id);

    // Get a list of ads that match a search term
    List<Ad> getAdsBySearchTerm(String search);

    // Get a list of ads based on category
    List<Ad> getAdsByCategory(String filter);

    // Edit an existing ad
    void edit(Ad ad);

    // Delete an existing ad
    void delete(long id);
}
