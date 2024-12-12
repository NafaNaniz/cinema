package com.example.movie_theatre.repository;

import com.example.movie_theatre.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    List<Movie> findByMovieTitleContainingIgnoreCase(String keyword, Sort sort);
}
