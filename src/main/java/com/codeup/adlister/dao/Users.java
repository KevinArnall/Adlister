package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

public interface Users {

    // Find a user by username
    User findByUsername(String username);

    // Insert a new user
    Long insert(User user);

    // Find user by id
    User findUserById(long id);
}
