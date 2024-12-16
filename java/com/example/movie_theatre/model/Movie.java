package com.example.movie_theatre.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * Represents a movie entity in the movie theatre system.
 * <p>
 * The class is annotated with {@link Entity} to be used as a JPA entity, meaning
 * instances of this class will be persisted to the database.
 * </p>
 */
@Setter
@Entity
public class Movie {

    /**
     * The unique identifier for the movie.
     * This value is auto-generated when a new movie is saved to the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    /**
     * The title of the movie.
     */
    @Getter
    private String movieTitle;

    /**
     * The genre of the movie.
     */
    @Getter
    private String genre;

    /**
     * The director of the movie.
     */
    @Getter
    private String director;

    /**
     * The release date of the movie.
     */
    @Getter
    private Date releaseDate;

    /**
     * A brief description of the movie.
     * The column definition is set to "text" to accommodate longer descriptions.
     */
    @Getter
    @Column(columnDefinition = "text")
    private String description;

    /**
     * The duration of the movie in minutes.
     */
    @Getter
    private int duration;

    /**
     * The URL to the poster image for the movie.
     */
    @Getter
    private String posterURL;

    /**
     * Returns the unique identifier of the movie.
     * @return the movie ID
     */
    public int getMovieId() {
        return movieId;
    }
}
