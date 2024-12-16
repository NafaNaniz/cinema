package com.example.movie_theatre.controller;

import com.example.movie_theatre.model.Movie;
import com.example.movie_theatre.model.MyAppRole;
import com.example.movie_theatre.model.MyAppUser;
import com.example.movie_theatre.repository.MyAppRoleRepository;
import com.example.movie_theatre.service.MovieService;
import com.example.movie_theatre.service.MyAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

/**
 * The {@link AdminController} class handles the requests and logic related to the
 * admin functionalities in the movie theatre application.
 */
@Controller
public class AdminController {

    @Autowired
    private MovieService service;

    @Autowired
    private MyAppUserService userService;

    @Autowired
    private MyAppRoleRepository myAppRoleRepository;

    /**
     * Displays the admin home page with a list of movies.
     *
     * @param model the model to pass attributes to the view
     * @param keyword the keyword to filter the movies (optional)
     * @param sortField the field by which the movies should be sorted (default is "movieTitle")
     * @param sortDirection the direction to sort the movies (default is "asc")
     * @return the view name for the admin home page
     */
    @RequestMapping("/home/admin")
    public String adminHome(Model model, @RequestParam(required = false) String keyword,
                            @RequestParam(required = false, defaultValue = "movieTitle") String sortField,
                            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
        List<Movie> listMovies = service.filterAndSortMovies(keyword, sortField, sortDirection);
        model.addAttribute("listMovies", listMovies);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        return "admin/home";
    }

    /**
     * Displays the page to create a new movie.
     *
     * @param model the model to pass attributes to the view
     * @return the view name for the new movie creation page
     */
    @RequestMapping("/home/admin/new")
    public String newMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin/new";
    }

    /**
     * Saves a new movie to the database.
     *
     * @param movie the movie to be saved
     * @return a redirect to the admin home page
     */
    @PostMapping(value = "/home/admin/save")
    public String saveMovie(@ModelAttribute("movie") Movie movie) {
        service.save(movie);
        return "redirect:/home/admin";
    }

    /**
     * Displays the page to edit an existing movie.
     *
     * @param movieId the ID of the movie to be edited
     * @param model the model to pass attributes to the view
     * @return the view name for the movie edit page
     */
    @RequestMapping("/home/admin/edit/{movieId}")
    public String editMovie(@PathVariable Integer movieId, Model model) {
        Movie movie = service.findById(movieId);
        model.addAttribute("movie", movie);
        return "admin/edit";
    }

    /**
     * Deletes a movie by its ID and redirects to the admin home page.
     *
     * @param movieId the ID of the movie to be deleted
     * @return a redirect to the admin home page
     */
    @RequestMapping("/home/admin/delete/{movieId}")
    public String deleteMovie(@PathVariable Integer movieId) {
        service.deleteById(movieId);
        return "redirect:/home/admin";
    }

    /**
     * Displays information about the author of application.
     *
     * @param model the model to pass attributes to the view
     * @return the view name for the about page
     */
    @GetMapping("/home/admin/about")
    public String aboutAdmin(Model model) {
        model.addAttribute("role", "ADMIN");
        return "about";
    }

    /**
     * Displays movie analytics: number of movies, the oldest
     * movie, the newest movie, and the average duration of movies.
     *
     * @param model the model to pass attributes to the view
     * @return the view name for the analytics page
     */
    @RequestMapping("/home/admin/analytics")
    public String analytics(Model model) {
        List<Movie> listMovies = service.findAllMovies();
        model.addAttribute("numberOfMovies", listMovies.size());
        Movie oldestMovie = listMovies.stream().min(Comparator.comparing(Movie::getReleaseDate)).orElse(null);
        model.addAttribute("oldestMovie", oldestMovie);
        Movie newestMovie = listMovies.stream().max(Comparator.comparing(Movie::getReleaseDate)).orElse(null);
        model.addAttribute("newestMovie", newestMovie);
        Integer averageDuration = (int) listMovies.stream().mapToInt(Movie::getDuration).average().orElse(0);
        model.addAttribute("averageDuration", averageDuration);
        return "admin/analytics";
    }

    /**
     * Displays the list of users in the system.
     *
     * @param model the model to pass attributes to the view
     * @return the view name for the user list page
     */
    @RequestMapping("home/admin/users")
    public String roles(Model model) {
        List<MyAppUser> listUsers = userService.findAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "admin/users";
    }

    /**
     * Displays the page to edit a user's role.
     *
     * @param userId the ID of the user whose role is to be edited
     * @param model the model to pass attributes to the view
     * @return the view name for the user role edit page
     */
    @RequestMapping("/home/admin/editRole/{userId}")
    public String editUserRole(@PathVariable Integer userId, Model model) {
        MyAppUser user = userService.findById(userId);
        List<MyAppRole> roles = myAppRoleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin/editRole";
    }

    /**
     * Saves the updated role of a user.
     *
     * @param user the user whose role has been updated
     * @return a redirect to the users list page
     */
    @PostMapping(value = "/home/admin/saveRole")
    public String saveUserRole(@ModelAttribute("user") MyAppUser user) {
        MyAppUser existingUser = userService.findById(user.getUserId());
        user.setPassword(existingUser.getPassword());
        userService.save(user);
        return "redirect:/home/admin/users";
    }
}
