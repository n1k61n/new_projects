package com.example.fruitables.config;


import com.example.fruitables.services.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomUserDetailService customUserDetailService;





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