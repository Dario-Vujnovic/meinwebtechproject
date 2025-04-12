package com.wgplaner.wgplaner.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean done;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public boolean isDone() { return done; }

    public void setDescription(String description) { this.description = description; }
    public void setDone(boolean done) { this.done = done; }
}

