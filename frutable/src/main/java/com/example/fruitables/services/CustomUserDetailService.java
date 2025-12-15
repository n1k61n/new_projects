package com.example.fruitables.services;

import com.example.fruitables.models.User;
import com.example.fruitables.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configuration
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {


    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User findUser = userRepository.findByEmail(email).orElse(null);
        if (findUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        if (findUser.getRoles() == null || findUser.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User has no roles assigned");
        }
        return new org.springframework.security.core.userdetails.User(
                findUser.getEmail(),
                findUser.getPassword(),
                findUser.isEnabled(),
                findUser.isAccountNonExpired(),
                findUser.isCredentialsNonExpired(),
                findUser.isAccountNonLocked(),
                findUser.getAuthorities());

    }

}
