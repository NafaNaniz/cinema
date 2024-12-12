//package com.example.demo_module.service;
//
//import com.example.demo_module.model.User;
//import com.example.demo_module.repository.UserRepo;
//import com.example.demo_module.security.UsersDetailsCustom;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements UserDetailsService {
//    @Autowired
//    UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException(username));
//        return UsersDetailsCustom.build(user);
//    }
//
//    public boolean existsByUsername(String username) {
//        return userRepo.existsByUsername(username);
//    }
//
//    public void save(User user) {
//        userRepo.save(user);
//    }
//}
