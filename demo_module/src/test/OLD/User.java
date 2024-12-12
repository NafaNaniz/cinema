//package com.example.demo_module.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@Entity
//@Table(name = "ApplicationUser")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer UserId;
//    private String username;
//    private String password;
//    private String name;
//    private String PhoneNumber;
//    @ElementCollection(fetch = FetchType.LAZY)
//    public Set<String> roles = new HashSet<>();
//
//}
