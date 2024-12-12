package com.example.movie_theatre.repository;

import com.example.movie_theatre.model.MyAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Integer> {

    Optional<MyAppUser> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
