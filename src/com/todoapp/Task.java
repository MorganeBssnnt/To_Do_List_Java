package com.todoapp;

import java.util.Date;

public class Task {
    private String title;
    private String description;
    private Date dateAdded;

    // Constructeur
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.dateAdded = new Date(); // La date actuelle
    }

    // Getter pour le titre
    public String getTitle() {
        return title;
    }

    // Setter pour le titre
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter pour la description
    public String getDescription() {
        return description;
    }

    // Setter pour la description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getters et Setters
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

}
