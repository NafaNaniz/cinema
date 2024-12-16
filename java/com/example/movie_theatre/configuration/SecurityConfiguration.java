package com.example.movie_theatre.configuration;

import com.example.movie_theatre.service.MyAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The {@link SecurityConfiguration} is a configuration class for Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private MyAppUserService appUserService;

    /**
     * Bean for providing a {@link UserDetailsService} that loads user data
     * during authentication.
     *
     * @return a {@link UserDetailsService} instance used to load user info from the database.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return appUserService;
    }

    /**
     * Bean for providing an {@link AuthenticationProvider} that uses a {@link DaoAuthenticationProvider}
     * for authentication. This provider uses the {@link UserDetailsService} and {@link PasswordEncoder}
     * to authenticate the user.
     *
     * @return an {@link AuthenticationProvider} configured with the user details service and password encoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Bean for configuring a {@link PasswordEncoder} to encode passwords using the BCrypt algorithm.
     *
     * @return a {@link PasswordEncoder} instance that uses BCrypt for password encoding.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for configuring the HTTP security settings for the application.
     *
     * @param httpSecurity the {@link HttpSecurity} object used to configure HTTP security.
     * @return a {@link SecurityFilterChain} instance that configures the HTTP security for the application.
     * @throws Exception if an error occurs.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpForm -> {
                    httpForm.loginPage("/req/login").permitAll();
                    httpForm.defaultSuccessUrl("/home/user", true);
                })
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/home/user/about").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
                    registry.requestMatchers("/home/admin/about").hasAuthority("ROLE_ADMIN");
                    registry.requestMatchers("/home/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
                    registry.requestMatchers("/home/admin/**").hasAuthority("ROLE_ADMIN");
                    registry.requestMatchers("/req/**").permitAll();
                })
                .build();
    }
}
