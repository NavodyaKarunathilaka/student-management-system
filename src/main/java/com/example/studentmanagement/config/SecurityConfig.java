package com.example.studentmanagement.config;

import com.example.studentmanagement.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // This class configures the security settings for the application.
    // It defines the security filter chain and password encoder beans.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        // Configures the HTTP security settings, including disabling CSRF,
        // setting session management to stateless, and defining authorization rules.
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Allows unauthenticated access to authentication endpoints.
                        .anyRequest().authenticated() // Requires authentication for all other endpoints.
                )
                .httpBasic(Customizer.withDefaults()) // Enables basic HTTP authentication.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Adds the JWT authentication filter before the username-password filter.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Configures the password encoder to use BCrypt hashing.
        return new BCryptPasswordEncoder();
    }
}