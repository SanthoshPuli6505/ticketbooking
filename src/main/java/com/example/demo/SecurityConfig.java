package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for simplicity (forms)
            .csrf(csrf -> csrf.disable())

            // AUTHORIZE REQUESTS
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/error",                 // ✅ IMPORTANT
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

                .requestMatchers("/ticket").authenticated()
                .requestMatchers("/create", "/update").hasRole("USER")
                .requestMatchers("/delete", "/viewbyid", "/viewall").hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            // LOGIN CONFIG
            .formLogin(form -> form
                .defaultSuccessUrl("/ticket", true)
                .permitAll()
            )

            // LOGOUT CONFIG
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            );

        return http.build();
    }

    // ================= USERS =================
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
