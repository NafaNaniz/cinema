package com.example.movie_theatre.repository;

import com.example.movie_theatre.model.MyAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link MyAppUser} entities.
 * This interface extends {@link JpaRepository}, providing basic persistence methods.
 */
@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Integer> {

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to search for
     * @return an {@link Optional} containing the {@link MyAppUser} if found, otherwise empty
     */
    Optional<MyAppUser> findByUsername(String username);

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if a user with the specified username exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email to check
     * @return true if a user with the specified email exists, false otherwise
     */
    boolean existsByEmail(String email);
}
