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

@Controller
public class AuthController {
    @Autowired
    MyAppUserRepository myAppUserRepository;

    @Autowired
    MyAppRoleRepository myAppRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/req/login")
    public String login() {
        return "login";
    }

    @GetMapping("/req/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping(value = "/req/signup", consumes = "application/json")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody MyAppUser user) {
        Map<String, String> response = new HashMap<>();
        try {
            if (myAppUserRepository.existsByUsername(user.getUsername())) {
                response.put("status", "error");
                response.put("message", "Имя пользователя уже занято");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            if (myAppUserRepository.existsByEmail(user.getEmail())) {
                response.put("status", "error");
                response.put("message", "Email уже используется");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(myAppRoleRepository.findByName("USER"));
            myAppUserRepository.save(user);
            response.put("status", "success");
            response.put("message", "Регистрация прошла успешно");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Произошла ошибка на сервере");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}