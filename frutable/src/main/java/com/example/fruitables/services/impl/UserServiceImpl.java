package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.auth.AuthResponseDto;
import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.models.Role;
import com.example.fruitables.models.User;
import com.example.fruitables.repositories.RoleRepository;
import com.example.fruitables.repositories.UserRepository;
import com.example.fruitables.services.EmailService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailService emailService;



    @Override
    public boolean registerUser(RegisterDto registerDto) {
        Optional<User> existingUser = userRepository.findByEmail(registerDto.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }
        User newUser = modelMapper.map(registerDto, User.class);
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        newUser.setPassword(encodedPassword);


        //yeni token teyin edirik;
//        String token = UUID.randomUUID().toString();

        String token = String.valueOf((int) (Math.random() * 1000000));
        newUser.setVerificationToken(token);

        Role userRole = new Role();
        if(userRepository.findById(1L).isEmpty()) {
            userRole.setName("ADMIN");
            newUser.setAccountNonExpired(true);
            newUser.setAccountNonLocked(true);
            newUser.setCredentialsNonExpired(true);
        }
        else {
            userRole.setName("USER");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        try {
            roleRepository.save(userRole);
            userRepository.save(newUser);
            emailService.sendEmail(newUser.getEmail(), token);
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
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean verifyUser(AuthResponseDto authResponseDto) {
        // Bazadan bu tokenə sahib istifadəçini tap
        Optional<User> userOpt = userRepository.findByEmail(authResponseDto.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getVerificationToken() != null && user.getVerificationToken().equals(authResponseDto.getOtp())) {
                user.setEnabled(true); // Hesabı aktivləşdir
                user.setVerificationToken(null); // Tokeni təmizlə (bir dəfəlik istifadə üçün)
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

}