package com.wgplaner.wgplaner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Roommate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    @JsonIgnoreProperties({"roommates", "tasks"}) // Verhindert endlose Rückverweise auf Flat
    private Flat flat;

}
