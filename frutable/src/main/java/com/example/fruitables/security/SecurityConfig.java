package com.example.fruitables.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> auth
                        // Dashboard içindəki statik resursları açırıq
                        .requestMatchers(
                                "/dashboard/vendor/**",
                                "/dashboard/css/**",
                                "/dashboard/js/**",
                                "/dashboard/img/**"
                        ).permitAll()

                        // Dashboard səhifələrinin özünü qoruyuruq
                        .requestMatchers("/dashboard/**").authenticated()

                        // Qalan hər şey açıq
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard/", true)
                );

        return http.build();
    }


}


