package com.example.fruitables.security;


import com.example.fruitables.models.User;
import com.example.fruitables.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByEmail(username);
        if (findUser != null) {
            org.springframework.security.core.userdetails.User loggedUser =  new org.springframework.security.core.userdetails.User(
                    findUser.getEmail(),
                    findUser.getPassword(),
                    findUser.isEnabled(),
                    findUser.isAccountNonExpired(),
                    findUser.isCredentialsNonExpired(),
                    findUser.isAccountNonLocked(),
                    findUser.getAuthorities()
            );
            return loggedUser;
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }



}
