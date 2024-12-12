package com.example.movie_theatre.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    @Getter
    private String movieTitle;
    @Getter
    private String genre;
    @Getter
    private String director;
    @Getter
    private Date releaseDate;
    @Getter
    @Column(columnDefinition = "text")
    private String description;
    @Getter
    private int duration;
    @Getter
    private String posterURL;

    public int getMovieId() {
        return movieId;
    }

}
