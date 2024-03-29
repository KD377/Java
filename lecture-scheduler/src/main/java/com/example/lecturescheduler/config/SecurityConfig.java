package com.example.lecturescheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/classrooms/**").permitAll()
                        .requestMatchers("/api/groups/**").permitAll()
                        .requestMatchers("/api/instructors/**").permitAll()
                        .requestMatchers("/api/subjects/**").permitAll()// Zezwalaj na anonimowy dostęp do endpointów "/api/classrooms"
                        .anyRequest().authenticated()) // Wymaga uwierzytelnienia dla innych żądań
                .httpBasic(withDefaults()); // Umożliwia uwierzytelnienie za pomocą HTTP Basic Authentication
        return http.build();
    }
}
