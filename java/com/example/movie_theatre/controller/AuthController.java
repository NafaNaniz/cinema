package com.example.movie_theatre.controller;

import com.example.movie_theatre.repository.MyAppRoleRepository;
import com.example.movie_theatre.model.MyAppUser;
import com.example.movie_theatre.repository.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@link AuthController} class handles authentication and user registration functionality
 * within the application.
 */
@Controller
public class AuthController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private MyAppRoleRepository myAppRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Displays the login page.
     *
     * @return the view name for the login page
     */
    @GetMapping("/req/login")
    public String login() {
        return "login";
    }

    /**
     * Displays the signup page.
     *
     * @return the view name for the signup page
     */
    @GetMapping("/req/signup")
    public String signup() {
        return "signup";
    }

    /**
     * Handles the user registration process by accepting the user details in JSON format.
     * It checks if the username or email is already in use and if so returns error messages.
     * If the user does not exist, it encodes the password, assigns a default "USER" role,
     * and saves the new user to the database.
     *
     * @param user the user details received in the request body
     * @return a ResponseEntity with a status and message indicating the result of the registration
     */
    @PostMapping(value = "/req/signup", consumes = "application/json")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody MyAppUser user) {
        Map<String, String> response = new HashMap<>();
        try {
            if (myAppUserRepository.existsByUsername(user.getUsername())) {
                response.put("status", "error");
                response.put("message", "Username is already taken");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            if (myAppUserRepository.existsByEmail(user.getEmail())) {
                response.put("status", "error");
                response.put("message", "Email is already in use");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(myAppRoleRepository.findByName("USER"));
            myAppUserRepository.save(user);

            response.put("status", "success");
            response.put("message", "Registration successful");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Server error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
