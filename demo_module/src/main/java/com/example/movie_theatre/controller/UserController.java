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

@Controller
public class UserController {

    @Autowired
    private MovieService service;

    @Autowired
    private MyAppUserRepository repository;

    @RequestMapping("/home/user")
    public String userHome(Model model, Principal principal,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false, defaultValue = "movieTitle") String sortField,
                           @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
        String username = principal.getName();
        MyAppUser currentUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        model.addAttribute("role", currentUser.getRole().getName());
        List<Movie> listMovies = service.filterAndSortMovies(keyword, sortField, sortDirection);
        model.addAttribute("listMovies", listMovies);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        return "user/homepage";
    }

    @GetMapping("/home/user/about")
    public String aboutUser(Model model) {
        model.addAttribute("role", "USER");
        return "about";
    }
}