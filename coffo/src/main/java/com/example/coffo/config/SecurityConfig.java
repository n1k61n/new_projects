package com.example.coffo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/*", "/front/**").permitAll()
                        .requestMatchers("/static/dashboard/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> {
                    form.loginPage("/login");
                    form.loginProcessingUrl("/perform_login");
                    form.defaultSuccessUrl("/home", true);
                    form.permitAll();
                })
                .logout(logout -> logout.permitAll());
        return http.build();
    }

}
