package com.example.movie_theatre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Represents a role in the movie theatre system.
 * <p>
 * The class is annotated with {@link Entity} to be used as a JPA entity, meaning
 * instances of this class will be persisted to the database.
 * </p>
 */
@Entity
@Data
public class MyAppRole {

    /**
     * The unique identifier for the role.
     * This value is auto-generated when a new role is saved to the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RoleId;

    /**
     * The name of the role (e.g., "USER", "ADMIN").
     */
    private String name;

    /**
     * A description of the role.
     * This can provide additional context for what the role represents.
     */
    private String description;
}
