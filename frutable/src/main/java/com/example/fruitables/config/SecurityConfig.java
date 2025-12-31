package com.example.fruitables.config;

import com.example.fruitables.security.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private  CustomUserDetailService userDetailService;
    private  final PasswordEncoder passwordEncoder;



    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            var authorities = authentication.getAuthorities();

            // Rolun adını yoxlayırıq (Spring RolePrefix olaraq ROLE_ istifadə edir)
            String redirectUrl = authorities.stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                    ? "/dashboard"  // Adminsə bura
                    : "/";     // Usersə (və ya başqa rolsa) bura

            response.sendRedirect(redirectUrl);
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // API və JWT üçün disable edilir
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/dashboard/**").hasRole("ADMIN"); // Bazada ROLE_ADMIN olmalıdır
                    auth.requestMatchers( "/front/**", "/*").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/verify-otp")
                        .defaultSuccessUrl("/", false)
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(customSuccessHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }
}