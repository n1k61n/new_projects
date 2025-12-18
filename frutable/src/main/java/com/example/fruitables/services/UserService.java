package com.example.fruitables.services;

import com.example.fruitables.dtos.auth.AuthResponseDto;
import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;



public interface UserService {
    boolean registerUser(RegisterDto registerDto);
    Optional<User> findByEmail(String email);

    boolean verifyUser(AuthResponseDto authResponseDto);

    boolean isEmailExist(String email);
}
