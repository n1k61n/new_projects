package com.example.fruitables.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/dashboard/**").authenticated();
                    auth.anyRequest().permitAll();
                })
                .formLogin((form) -> {
                    form.loginPage("/login");
                });
         return http.build();
    }

}