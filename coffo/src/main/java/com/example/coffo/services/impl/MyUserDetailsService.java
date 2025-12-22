package com.example.coffo.services.impl;


import com.example.coffo.models.User;
import com.example.coffo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("İstifadəçi tapılmadı: " + username);
        }

//        org.springframework.security.core.userdetails.User loggedUser = new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
//        );

//        User findUser = userService.findByEmail(email);
//        if (findUser == null) {
//            throw new UsernameNotFoundException("İstifadəçi tapılmadı: " + email);
//        }
//        if (findUser.getRoles() == null || findUser.getRoles().isEmpty()) {
//            throw new InternalAuthenticationServiceException("İstifadəçinin rolu yoxdur!");
//        }
//        return findUser;

        return modelMapper.map(user, UserDetails.class);

    }
}
