package com.wgplaner.wgplaner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String description;

    @Setter
    private boolean done;

    // Frist-Datum – wird als yyyy-MM-dd im JSON formatiert
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roommate_id")
    @JsonIgnoreProperties({"flat"})
    private Roommate roommate;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    @JsonIgnoreProperties({"roommates", "tasks"})
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

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public LocalDate getDueDate() {
        return dueDate;
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
