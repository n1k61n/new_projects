package com.example.fruitables.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/dashboard/**").hasAuthority("ADMIN");
                    auth.requestMatchers("/register/**").hasAuthority("USER");
                    auth.anyRequest().permitAll();
                })
                .formLogin((form) -> {
                    form.loginPage("/login");
                    form.defaultSuccessUrl("/dashboard", true);
                    form.failureUrl("/login?error=true");
                    form.usernameParameter("email");
                    form.passwordParameter("password");
                    form.permitAll();

                })
                 .logout(logout->{
                     logout.logoutUrl("/logout")
                             .logoutSuccessUrl("/login?logout=true")
                             .permitAll();
                 });
         return http.build();
    }
}