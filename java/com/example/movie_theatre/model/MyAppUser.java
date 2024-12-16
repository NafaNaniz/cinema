package com.example.movie_theatre.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents a user in the movie theatre system.
 * <p>
 * The class is annotated with {@link Entity} to be used as a JPA entity.
 * </p>
 */
@Entity
@Data
public class MyAppUser {

    /**
     * The unique identifier for the user.
     * This value is auto-generated when a new user is saved to the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserId;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     * This value should be securely hashed before storing in the database.
     */
    private String password;

    /**
     * The role assigned to the user.
     * This relationship is fetched eagerly, meaning the role will be loaded alongside the user when queried.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private MyAppRole role;
}
