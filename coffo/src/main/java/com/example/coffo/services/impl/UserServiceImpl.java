package com.example.coffo.services.impl;

import com.example.coffo.DTOs.UserDTO.RegisterDto;
import com.example.coffo.models.Role;
import com.example.coffo.models.User;
import com.example.coffo.payloads.RegisterPayload;
import com.example.coffo.repositories.RoleRepository;
import com.example.coffo.repositories.UserRepository;
import com.example.coffo.services.UserService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public RegisterPayload registerUser(RegisterDto registerDto) {
        User existUser = userRepository.findByEmail(registerDto.getEmail());
        if(existUser != null){
            return new RegisterPayload(null, existUser.getEmail(), 400, "Bu adinda istifadeci movcuddur");
        }

        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            return new RegisterPayload(null, registerDto.getEmail(), 400, "Sifreler eyni deyil");
        }

        String roleName = (userRepository.count() == 0) ? "ROLE_ADMIN" : "ROLE_USER";
        Role userRole = roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return roleRepository.save(newRole);
                });

        User newUser = modelMapper.map(registerDto, User.class);
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));



        roleRepository.save(userRole);
        userRepository.save(newUser);




        return new RegisterPayload(null, newUser.getEmail(), 200, "Ugurla qeydiyyatdan kecdiniz");
    }

}
