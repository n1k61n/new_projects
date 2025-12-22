package com.example.coffo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF qorumasını söndürmək (ehtiyaca görə)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Hər kəsə açıq
                        .requestMatchers("/dashboard/**").hasRole("ADMIN") // Yalnız Admin üçün
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll()) // Giriş formunu aktiv edir
                .logout(logout -> logout.permitAll()); // Çıxış imkanı verir

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
