package com.example.movie_theatre.repository;

import com.example.movie_theatre.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link Movie} entities.
 * This interface extends {@link JpaRepository}, which provides basic persistence methods.
 */
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    /**
     * Finds movies whose titles contain the specified keyword (case-insensitive).
     * The results are sorted based on the specified {@link Sort} object.
     *
     * @param keyword the keyword to search for in movie titles
     * @param sort the sorting criteria for the result list
     * @return a list of movies that match the search criteria
     */
    List<Movie> findByMovieTitleContainingIgnoreCase(String keyword, Sort sort);
}
