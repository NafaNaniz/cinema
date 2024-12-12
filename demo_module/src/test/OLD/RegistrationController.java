//package com.example.demo_module.Controller;
//
//import com.example.demo_module.Models.repository.MyAppRoleRepository;
//import com.example.demo_module.Models.model.MyAppUser;
//import com.example.demo_module.Models.repository.MyAppUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RegistrationController {
//    @Autowired
//    MyAppUserRepository myAppUserRepository;
//
//    @Autowired
//    MyAppRoleRepository myAppRoleRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @PostMapping(value = "/req/signup", consumes = "application/json")
//    public MyAppUser createUser(@RequestBody MyAppUser user) {
//        System.out.println("Регистрация пользователя: " + user.getUsername());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(myAppRoleRepository.findByName("USER"));
//        return myAppUserRepository.save(user);
//    }
//}
