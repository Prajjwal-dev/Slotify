package com.slotify.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(
            JWTUtil jwtUtil,
            JWTRequestFilter jwtRequestFilter
    ) {
        this.jwtUtil = jwtUtil;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        // Public
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()

                        // Provider only
                        .requestMatchers("/api/provider/**").hasAuthority("ROLE_PROVIDER")
                        .requestMatchers("/api/services/**").hasAuthority("ROLE_PROVIDER")
                        .requestMatchers("/api/availability/**").hasAuthority("ROLE_PROVIDER")

                        // Customer and Provider both can access bookings
                        .requestMatchers("/api/bookings/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_PROVIDER")

                        .requestMatchers("/api/slots/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_PROVIDER")

                        // Admin only
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")

                        // Everything else needs authentication
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtRequestFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}