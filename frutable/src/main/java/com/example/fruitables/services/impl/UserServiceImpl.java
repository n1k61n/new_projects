package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.models.User;
import com.example.fruitables.repositories.UserRepository;
import com.example.fruitables.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}