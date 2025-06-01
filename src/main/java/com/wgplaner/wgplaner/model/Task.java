package com.wgplaner.wgplaner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String description;

    @Setter
    private boolean done;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roommate_id")
    @JsonIgnoreProperties({"flat"}) // Verhindert Rückverweis auf Flat im Roommate
    private Roommate roommate;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    @JsonIgnoreProperties({"roommates", "tasks"}) // Verhindert Endlosschleife
    private Flat flat;

    public Task() {}

    public Task(Long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public Roommate getRoommate() {
        return roommate;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}
