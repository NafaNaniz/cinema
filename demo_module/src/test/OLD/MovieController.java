//package com.example.demo_module.Controller;
//import com.example.demo_module.Models.model.Movie;
//import com.example.demo_module.Models.service.MovieService;
//import com.example.demo_module.Models.model.MyAppUser;
//import com.example.demo_module.Models.repository.MyAppUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.security.Principal;
//import java.util.Comparator;
//import java.util.List;
//
//@Controller
//public class MovieController {
//    @Autowired
//    private MyAppUserRepository repository;
//
//    @Autowired
//    private MovieService service;
//
//    @RequestMapping("/home/user")
//    public String userHome(Model model,
//                           Principal principal,
//                           @RequestParam(required = false) String keyword,
//                           @RequestParam(required = false, defaultValue = "movieTitle") String sortField,
//                           @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
//
//        String username = principal.getName();
//        MyAppUser currentUser = repository.findByUsername(username).orElseThrow(()->
//                new UsernameNotFoundException("Пользователь не найден"));
//        model.addAttribute("role", currentUser.getRole().getName());
//
//        List<Movie> listMovies = service.filterAndSortMovies(keyword, sortField, sortDirection);
//        model.addAttribute("listMovies", listMovies);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDirection", sortDirection);
//        return "user/homepage";
//    }
//
//
//    @RequestMapping("/home/admin")
//    public String adminHome(Model model,
//                            @RequestParam(required = false) String keyword,
//                            @RequestParam(required = false, defaultValue = "movieTitle") String sortField,
//                            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
//        List<Movie> listMovies = service.filterAndSortMovies(keyword, sortField, sortDirection);
//        model.addAttribute("listMovies", listMovies);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDirection", sortDirection);
//        return "admin/home";
//    }
//
//    @RequestMapping("/home/admin/new")
//    public String newMovie(Model model) {
//        model.addAttribute("movie", new Movie());
//        return "admin/new";
//    }
//
//    @PostMapping(value = "/home/admin/save")
//    public String saveMovie(@ModelAttribute("movie") Movie movie) {
//        service.save(movie);
//        return "redirect:/home/admin";
//    }
//
//    @RequestMapping("/home/admin/edit/{movieId}")
//    public String editMovie(@PathVariable Integer movieId, Model model) {
//        Movie movie = service.findById(movieId);
//        model.addAttribute("movie", movie);
//        return "admin/edit";
//    }
//
//    @RequestMapping("/home/admin/delete/{movieId}")
//    public String deleteMovie(@PathVariable Integer movieId) {
//        service.deleteById(movieId);
//        return "redirect:/home/admin";
//    }
//
//    @GetMapping("/home/user/about")
//    public String aboutUser(Model model) {
//        model.addAttribute("role", "USER");
//        return "about";
//    }
//
//    @GetMapping("/home/admin/about")
//    public String aboutAdmin(Model model) {
//        model.addAttribute("role", "ADMIN");
//        return "about";
//    }
//
//    @RequestMapping("/home/admin/analytics")
//    public String analytics(Model model) {
//        List<Movie> listMovies = service.findAllMovies();
//        model.addAttribute("numberOfMovies", listMovies.size());
//
//        Movie oldestMovie = listMovies.stream().min(Comparator.comparing(Movie::getReleaseDate)).orElse(null);
//        model.addAttribute("oldestMovie", oldestMovie);
//
//        Movie newestMovie = listMovies.stream().max(Comparator.comparing(Movie::getReleaseDate)).orElse(null);
//        model.addAttribute("newestMovie", newestMovie);
//
//        Integer averageDuration = (int) listMovies.stream().mapToInt(Movie::getDuration).average().orElse(0);
//        model.addAttribute("averageDuration", averageDuration);
//
//        return "admin/analytics";
//    }
//}
