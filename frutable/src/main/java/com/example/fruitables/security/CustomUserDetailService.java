package com.example.fruitables.security;



import com.example.fruitables.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.fruitables.models.User findUser = userRepository.findByEmail(username);
        if (findUser != null) {
            return  new org.springframework.security.core.userdetails.User(
                    findUser.getEmail(),
                    findUser.getPassword(),
                    findUser.isEnabled(),
                    findUser.isAccountNonExpired(),
                    findUser.isCredentialsNonExpired(),
                    findUser.isAccountNonLocked(),
                    findUser.getAuthorities()
            );
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
