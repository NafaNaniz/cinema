package com.example.movie_theatre.service;

import com.example.movie_theatre.model.Movie;
import com.example.movie_theatre.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepo repo;

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

    public void save(Movie movie) {
        repo.save(movie);
    }

    public Movie findById(int id) {
        return repo.findById(id).get();
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
    public List<Movie> findAllMovies(){
        return repo.findAll();
    }
}
