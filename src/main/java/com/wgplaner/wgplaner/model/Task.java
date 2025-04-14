package com.wgplaner.wgplaner.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean done;

    public Task() {}



    public Task(int id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = false;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public boolean isDone() { return done; }

    public void setDescription(String description) { this.description = description; }
    public void setDone(boolean done) { this.done = done; }
}

