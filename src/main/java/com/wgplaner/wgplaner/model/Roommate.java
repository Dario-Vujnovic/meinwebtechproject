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

}


