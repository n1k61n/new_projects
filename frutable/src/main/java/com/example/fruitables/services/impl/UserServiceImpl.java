package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.auth.AuthResponseDto;
import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.dtos.auth.UserNameDto;
import com.example.fruitables.dtos.auth.UserProfileDto;
import com.example.fruitables.models.Role;
import com.example.fruitables.models.User;
import com.example.fruitables.repositories.RoleRepository;
import com.example.fruitables.repositories.UserRepository;
import com.example.fruitables.services.EmailService;
import com.example.fruitables.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        // 1. İstifadəçi yoxlaması
        if (userRepository.findByEmail(registerDto.getEmail()) != null) {
            return false;
        }

        // 2. Map və şifrələmə
        User newUser = modelMapper.map(registerDto, User.class);
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));


//        yeni token teyin edirik;
//        String token = UUID.randomUUID().toString();

        String token = String.valueOf((int) (Math.random() * 1000000));
        newUser.setVerificationToken(token);
        Date date = new Date();
        newUser.setTokenExpiryDate(new Date(date.getTime() + 300000));

//        Role userRole = new Role();
//        if(userRepository.findById(1L).isEmpty()) {
//            userRole.setName("ROLE_ADMIN");
//        }
//        else {
//            userRole.setName("ROLE_USER");
//        }
//        Set<Role> roles = new HashSet<>();
//        roles.add(userRole);
//        newUser.setRoles(roles);

        // 4. Rol təyini
        String roleName = (userRepository.count() == 0) ? "ROLE_ADMIN" : "ROLE_USER";
        Role userRole = roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return roleRepository.save(newRole);
                });

        newUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));

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



    public UserProfileDto getUserProfile(String email) {
        User user = userRepository.findByEmail(email); // Və ya .orElseThrow()
        if (user == null) return null;
        return modelMapper.map(user, UserProfileDto.class);
    }

    public boolean verifyUser(AuthResponseDto authResponseDto) {
        // Bazadan bu tokenə sahib istifadəçini tap
        User userOpt = userRepository.findByEmail(authResponseDto.getEmail());

        if (userOpt != null) {
            User user = userOpt;
            if (user.getVerificationToken() != null && user.getVerificationToken().equals(authResponseDto.getOtp())) {
                user.setEnabled(true);
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                user.setVerificationToken(null);
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

    @Override
    @Transactional // Verilənlər bazası əməliyyatının bütövlüyü üçün vacibdir
    public boolean updateProfile(UserProfileDto profileDto) {
        try {
            User user = userRepository.findByEmail(profileDto.getEmail());

            if (user == null) {
                return false; // İstifadəçi tapılmasa xəta qaytar
            }

            // Ad və Soyad yeniləmə
            user.setFirstName(profileDto.getFirstName());
            user.setLastName(profileDto.getLastName());

            // Şifrə yoxlaması: Yalnız boş deyilsə və null deyilsə yenilə
            if (profileDto.getPassword() != null && !profileDto.getPassword().trim().isEmpty()) {
                user.setPassword(passwordEncoder.encode(profileDto.getPassword()));
            }

            // Şəkil yeniləmə
            if (profileDto.getImageUrl() != null && !profileDto.getImageUrl().isEmpty()) {
                user.setImageUrl(profileDto.getImageUrl());
            }

            userRepository.save(user);
            return true;
        } catch (Exception e) {
            // Loglamaq yaxşı olar:
            //log.error("Update error: ", e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserNameDto getCurrentAdmin() {
        User user =  userRepository.findByRoleName("ROLE_ADMIN");
        if(user == null) return null;
        return modelMapper.map(user, UserNameDto.class);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) return null;
        return user;
    }
}