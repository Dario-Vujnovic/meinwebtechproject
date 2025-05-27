package com.wgplaner.wgplaner.model;

import jakarta.persistence.*;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import com.wgplaner.wgplaner.model.Flat;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    // Setter
    @Setter
    private String description;

    @Setter
    private boolean done;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roommate_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Roommate roommate;


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

}


