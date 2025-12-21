package com.example.fruitables.security;

import com.example.fruitables.models.User;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class CustomUserDetailService implements  UserDetailsService {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Əvvəlcə DTO-nu al
        User findUser = userService.findByEmail(email);

        // 2. Əgər DTO null-dırsa, ModelMapper-ə keçmədən xəta at
        if (findUser == null) {
            throw new UsernameNotFoundException("İstifadəçi tapılmadı: " + email);
        }

        // 3. İndi təhlükəsiz şəkildə map edə bilərsən
//        User findUser = modelMapper.map(user, User.class);

        // 4. Əvvəlki xətanın (No roles) qarşısını almaq üçün rolları yoxla
        if (findUser.getRoles() == null || findUser.getRoles().isEmpty()) {
            throw new InternalAuthenticationServiceException("İstifadəçinin rolu yoxdur!");
        }

        return findUser;
    }


}
