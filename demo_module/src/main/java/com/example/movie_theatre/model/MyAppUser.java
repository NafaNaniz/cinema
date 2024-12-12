package com.example.movie_theatre.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MyAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserId;
    private String username;
    private String email;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private MyAppRole role;
}
