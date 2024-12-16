package com.example.movie_theatre.repository;

import com.example.movie_theatre.model.MyAppRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on {@link MyAppRole} entities.
 * This interface extends {@link JpaRepository}, which provides basic persistence methods.
 */
public interface MyAppRoleRepository extends JpaRepository<MyAppRole, Integer> {

    /**
     * Finds a role by its name.
     *
     * @param user the name of the role to search for
     * @return the {@link MyAppRole} with the specified name, or null if no role is found
     */
    MyAppRole findByName(String user);
}
