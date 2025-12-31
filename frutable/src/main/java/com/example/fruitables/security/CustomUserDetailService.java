package com.example.fruitables.security;

import com.example.fruitables.models.User;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomUserDetailService implements  UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User findUser = userService.findByEmail(email);
        if (findUser == null) {
            throw new UsernameNotFoundException("İstifadəçi tapılmadı: " + email);
        }
        if (findUser.getRoles() == null || findUser.getRoles().isEmpty()) {
            throw new InternalAuthenticationServiceException("İstifadəçinin rolu yoxdur!");
        }
        return findUser;
    }
}
