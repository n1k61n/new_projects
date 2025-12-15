package com.example.fruitables.services;

import com.example.fruitables.dtos.auth.RegisterDto;
<<<<<<< Updated upstream
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
=======
import com.example.fruitables.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;
>>>>>>> Stashed changes


public interface UserService {
    boolean registerUser(RegisterDto registerDto);
<<<<<<< Updated upstream
=======
    Optional<User> getUserByEmail( String email);
>>>>>>> Stashed changes
}
