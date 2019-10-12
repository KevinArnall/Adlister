package com.codeup.adlister.models;


import java.time.Duration;
import java.time.LocalDateTime;

public class Ad {

    private long id;
    private long userId;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime currentTime;

    public Ad(long id, long userId, String title, String description, LocalDateTime dateCreated) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.currentTime = LocalDateTime.now();
    }

    public Ad(long userId, String title, String description, LocalDateTime dateCreated) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public Duration getTimeSinceCreation() {
        return Duration.between(currentTime, dateCreated);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
