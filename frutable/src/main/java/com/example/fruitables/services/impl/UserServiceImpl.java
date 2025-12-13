package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.models.User;
import com.example.fruitables.repositories.UserRepository;
import com.example.fruitables.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean registerUser(RegisterDto registerDto) {
        Optional<User> existingUser = userRepository.findByEmail(registerDto.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }
        User newUser = modelMapper.map(registerDto, User.class);
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        newUser.setPassword(encodedPassword);
        try {
            userRepository.save(newUser);
            return true;
        } catch (DataIntegrityViolationException e) {
            System.err.println("Qeydiyyat zamanı məlumat bazası bütövlüyü pozuntusu: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Gözlənilməyən xəta: " + e.getMessage());
            return false;
        }
    }
}