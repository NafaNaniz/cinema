//package com.example.demo_module.security;
//
//import com.example.demo_module.model.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Set;
//
//@AllArgsConstructor
//public class UsersDetailsCustom implements UserDetails {
//    private String username;
//    private String password;
//    @Getter
//    private Set<String> roles;
//
//    public static UsersDetailsCustom build(User user) {
//        return new UsersDetailsCustom(
//                user.getUsername(),
//                user.getPassword(),
//                user.getRoles()
//        );
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.getRoles().stream()
//                .map(SimpleGrantedAuthority::new)
//                .toList();
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//}
