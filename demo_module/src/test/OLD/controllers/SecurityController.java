//package com.example.demo_module.controllers;
//
//import com.example.demo_module.model.User;
//import com.example.demo_module.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Set;
//
//@Controller
//@RequestMapping("/auth")
//public class SecurityController {
//    @Autowired
//    UserService userService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @GetMapping("/signIn")
//    public String getSignIn(Model model) {
//        model.addAttribute("auth_user", new User());
//        return "signIn";
//    }
//
//    @PostMapping("/signIn")
//    public String postSingIn(Model model, @ModelAttribute("auth_user") User user, HttpServletRequest request) {
//        Authentication authentication;
//        try {
//            authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            user.getUsername(), user.getPassword()));
//        } catch (BadCredentialsException e) {
//            model.addAttribute("singIn_error", "Invalid username or password");
//            return "redirect:/auth/signIn";
//        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        HttpSession session = request.getSession(true);
//        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                SecurityContextHolder.getContext());
//        return "redirect:/home";
//    }
//
//    @GetMapping("/signUp")
//    public String getSignUp(Model model) {
//        model.addAttribute("new_user", new User());
//        return "signUp";
//    }
//
//    @PostMapping("/signUp")
//    public String postSignUp(@ModelAttribute("new_user") User user) {
//        if (userService.existsByUsername(user.getUsername())) {
//            return "/signUp";
//        }
//        String passEncoded = passwordEncoder.encode(user.getPassword());
//        user.setPassword(passEncoded);
//        if (user.getName().equals("Nafa")) {
//            user.setRoles(Set.of("ADMIN", "USER"));
//        }
//        user.setRoles(Set.of("USER"));
//        userService.save(user);
//        return "redirect:/signIn";
//    }
//}
