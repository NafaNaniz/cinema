package com.example.movie_theatre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MyAppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RoleId;

    private String name;
    private String description;

}

