package com.example.coffo.services;

import com.example.coffo.DTOs.UserDTO.RegisterDto;
import jakarta.validation.Valid;

public interface UserService {
    boolean registerUser(@Valid RegisterDto registerDto);
}
