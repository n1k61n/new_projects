package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.models.User;
import com.example.fruitables.repositories.UserRepository;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean registerUser(RegisterDto registerDto) {
        try {
            User findUser = userRepository.findByEmail(registerDto.getEmail());
            if (findUser == null) {
                User user = new User();
                user.setEmail(registerDto.getEmail());
                user.setName(registerDto.getName());
                user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
                userRepository.save(user);
                return true;
            }
            return false;
        }catch (Exception e) {
            return false;
        }
    }
}
