package com.example.coffo.services.impl;

import com.example.coffo.DTOs.UserDTO.RegisterDto;
import com.example.coffo.models.User;
import com.example.coffo.repositories.UserRepository;
import com.example.coffo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean registerUser(RegisterDto registerDto) {
        try {
            User existUser = userRepository.findByEmail(registerDto.getEmail());
            if(existUser != null){
                return false;
            }

            User newUser = modelMapper.map(registerDto, User.class);
            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
