package com.codeup.adlister.models;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class Ad {

    private long id;
    private long userId;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private List<String> categories;

    public Ad(long id, long userId, String title, String description, LocalDateTime dateCreated, List<String> categories) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.categories = categories;
    }

    public Ad(long userId, String title, String description, LocalDateTime dateCreated, List<String> categories) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.categories = categories;
    }

    public Duration getTimeSinceCreation() {
        return Duration.between(dateCreated, LocalDateTime.now()).abs();
    }

    public long getDaysSinceCreation() {
        return getTimeSinceCreation().toDays();
    }

    public long getHoursSinceCreation() {
        return getTimeSinceCreation().toHours();
    }

    public long getMinutesSinceCreation() {
        return getTimeSinceCreation().toMinutes();
    }

    public long getSecondsSinceCreation() {
        return getTimeSinceCreation().getSeconds();
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

    public String getDayOfWeekCreated() {
        return dateCreated.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    public String getMonthCreated() {
        return dateCreated.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    public int getDayOfMonthCreated() {
        return dateCreated.getDayOfMonth();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
