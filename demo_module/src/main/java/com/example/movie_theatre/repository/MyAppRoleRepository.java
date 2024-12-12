package com.example.movie_theatre.repository;

import com.example.movie_theatre.model.MyAppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyAppRoleRepository extends JpaRepository<MyAppRole, Integer> {
    MyAppRole findByName(String user);
}
