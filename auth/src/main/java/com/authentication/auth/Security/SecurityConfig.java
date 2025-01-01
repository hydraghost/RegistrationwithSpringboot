package com.authentication.auth.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests(registry -> {
                    registry
                            .requestMatchers("/api/auth/register", "/api/auth/hello").permitAll() // Publicly accessible
                            .anyRequest().authenticated(); // All other endpoints require authentication
                })
                .formLogin(httpForm -> {
                    httpForm
                            .loginPage("/api/auth/login") // Custom login endpoint
                            .permitAll();
                })
                .logout(httpLogout -> {
                    httpLogout
                            .logoutUrl("/api/auth/logout") // Custom logout endpoint
                            .permitAll();
                })
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
