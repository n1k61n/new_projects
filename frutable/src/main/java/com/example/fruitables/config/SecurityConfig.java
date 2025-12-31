package com.example.fruitables.config;

import com.example.fruitables.security.CustomUserDetailService;
import com.example.fruitables.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private  CustomUserDetailService userDetailService;
    private  final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;



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
                    auth.requestMatchers("/front/**", "/*").permitAll();
                    auth.anyRequest().authenticated();
                })
                // Əgər həm JWT, həm Form Login varsa, tam Stateless etmək Form Login-i sındıra bilər.
                // Amma sırf API-dirsə, STATELESS qalmalıdır və formLogin silinməlidir.
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // JWT filtri
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login") // Formun "action" hissəsi bura baxmalıdır
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
                        .deleteCookies("JSESSIONID", "JWT_TOKEN_COOKIE_NAME") // Əgər tokeni cookie-də saxlayırsınızsa
                        .permitAll()
                );

        return http.build();
    }
}