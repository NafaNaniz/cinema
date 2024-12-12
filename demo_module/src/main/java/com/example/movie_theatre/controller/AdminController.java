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
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private MovieService service;
    @Autowired
    private MyAppUserService userService;
    @Autowired
    MyAppRoleRepository myAppRoleRepository;

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

    @RequestMapping("/home/admin/new")
    public String newMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin/new";
    }

    @PostMapping(value = "/home/admin/save")
    public String saveMovie(@ModelAttribute("movie") Movie movie) {
        service.save(movie);
        return "redirect:/home/admin";
    }

    @RequestMapping("/home/admin/edit/{movieId}")
    public String editMovie(@PathVariable Integer movieId, Model model) {
        Movie movie = service.findById(movieId);
        model.addAttribute("movie", movie);
        return "admin/edit";
    }

    @RequestMapping("/home/admin/delete/{movieId}")
    public String deleteMovie(@PathVariable Integer movieId) {
        service.deleteById(movieId);
        return "redirect:/home/admin";
    }

    @GetMapping("/home/admin/about")
    public String aboutAdmin(Model model) {
        model.addAttribute("role", "ADMIN");
        return "about";
    }

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

    @RequestMapping("home/admin/users")
    public String roles(Model model) {
        List<MyAppUser> listUsers = userService.findAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "admin/users";
    }

    @RequestMapping("/home/admin/editRole/{userId}")
    public String editUserRole(@PathVariable Integer userId, Model model) {
        MyAppUser user = userService.findById(userId);
        List<MyAppRole> roles = myAppRoleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin/editRole";
    }

    @PostMapping(value = "/home/admin/saveRole")
    public String saveUserRole(@ModelAttribute("user") MyAppUser user) {
        MyAppUser existingUser = userService.findById(user.getUserId());
        user.setPassword(existingUser.getPassword());
        userService.save(user);
        return "redirect:/home/admin/users";
    }
}
