package com.wgplaner.wgplaner.model;

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
    private Flat flat;

}


