package com.example.movie_theatre.service;

import com.example.movie_theatre.model.MyAppUser;
import com.example.movie_theatre.repository.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that implements {@link UserDetailsService} for managing and retrieving {@link MyAppUser} entities.
 */
@Service
public class MyAppUserService implements UserDetailsService {

    @Autowired
    private MyAppUserRepository repository;

    /**
     * Loads a user by their username and returns a {@link UserDetails} object containing the user's credentials and roles.
     *
     * @param username the username of the user to load
     * @return a {@link UserDetails} object containing the user's credentials and roles
     * @throws UsernameNotFoundException if a user with the provided username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyAppUser> user = repository.findByUsername(username);
        if (user.isPresent()) {
            return User.builder()
                    .username(user.get().getUsername())
                    .password(user.get().getPassword())
                    .roles(user.get().getRole().getName())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all {@link MyAppUser} entities
     */
    public List<MyAppUser> findAllUsers() {
        return repository.findAll();
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return the {@link MyAppUser} entity with the specified ID
     */
    public MyAppUser findById(int id) {
        return repository.findById(id).get();
    }

    /**
     * Saves a {@link MyAppUser} entity to the repository.
     *
     * @param user the {@link MyAppUser} entity to save
     */
    public void save(MyAppUser user) {
        repository.save(user);
    }
}
