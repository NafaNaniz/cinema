package com.example.movie_theatre.service;

import com.example.movie_theatre.model.Movie;
import com.example.movie_theatre.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing {@link Movie} entities.
 */
@Service
public class MovieService {

    @Autowired
    private MovieRepo repo;

    /**
     * Filters and sorts movies based on the given keyword, sort field, and sort direction.
     * If a keyword is provided, the method performs a case-insensitive search on the movie title.
     * If no keyword is provided, it returns all movies sorted by the specified field and direction.
     *
     * @param keyword the keyword to search for in the movie title, or null to skip filtering
     * @param sortField the field to sort the movies by
     * @param sortDirection the direction to sort by (either "asc" for ascending or "desc" for descending)
     * @return a list of movies filtered and sorted based on the provided criteria
     */
    public List<Movie> filterAndSortMovies(String keyword, String sortField, String sortDirection) {
        Sort.Direction direction;
        if (sortDirection.equalsIgnoreCase("asc")) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }
        Sort sort = Sort.by(direction, sortField);

        if (keyword != null && !keyword.isEmpty()) {
            return repo.findByMovieTitleContainingIgnoreCase(keyword, sort);
        } else {
            return repo.findAll(sort);
        }
    }

    /**
     * Saves a {@link Movie} entity to the repository.
     *
     * @param movie the movie to be saved
     */
    public void save(Movie movie) {
        repo.save(movie);
    }

    /**
     * Finds a movie by its ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the movie with the specified ID
     */
    public Movie findById(int id) {
        return repo.findById(id).get();
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to be deleted
     */
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    /**
     * Retrieves all movies from the repository.
     *
     * @return a list of all movies
     */
    public List<Movie> findAllMovies() {
        return repo.findAll();
    }
}
