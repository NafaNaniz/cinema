package com.example.movie_theatre.service;

import com.example.movie_theatre.model.Movie;
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

@Service
public class MyAppUserService implements UserDetailsService {
    @Autowired
    private MyAppUserRepository repository;

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
    public List<MyAppUser> findAllUsers(){
        return repository.findAll();
    }

    public MyAppUser findById(int id) {
        return repository.findById(id).get();
    }

    public void save(MyAppUser user) {
        repository.save(user);
    }
}
