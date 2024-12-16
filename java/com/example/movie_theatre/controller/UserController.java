package com.example.movie_theatre.controller;

import com.example.movie_theatre.model.Movie;
import com.example.movie_theatre.service.MovieService;
import com.example.movie_theatre.model.MyAppUser;
import com.example.movie_theatre.repository.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * The {@link UserController} class handles user-specific actions and displays the user homepage.
 */
@Controller
public class UserController {

    @Autowired
    private MovieService service;

    @Autowired
    private MyAppUserRepository repository;

    /**
     * Displays the user homepage with a list of movies that can be filtered and sorted.
     *
     * @param model the model object to which attributes are added for the view
     * @param principal the principal object containing information about the current authenticated user
     * @param keyword a search keyword for filtering movies (optional)
     * @param sortField the field to sort the movies by (optional, default is "movieTitle")
     * @param sortDirection the direction to sort the movies by (optional, default is "asc")
     * @return the view name for the user homepage
     * @throws UsernameNotFoundException if the user is not found in the repository
     */
    @RequestMapping("/home/user")
    public String userHome(Model model, Principal principal,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false, defaultValue = "movieTitle") String sortField,
                           @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
        String username = principal.getName();
        MyAppUser currentUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("role", currentUser.getRole().getName());
        List<Movie> listMovies = service.filterAndSortMovies(keyword, sortField, sortDirection);
        model.addAttribute("listMovies", listMovies);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        return "user/homepage";
    }

    /**
     * Displays the "About" page for users.
     * It adds a role attribute to the model indicating that the user is a "USER".
     *
     * @param model the model object to which attributes are added for the view
     * @return the view name for the "About" page
     */
    @GetMapping("/home/user/about")
    public String aboutUser(Model model) {
        model.addAttribute("role", "USER");
        return "about";
    }
}
