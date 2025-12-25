package com.example.coffo.services;

import com.example.coffo.DTOs.UserDTO.RegisterDto;
import com.example.coffo.payloads.RegisterPayload;
import jakarta.validation.Valid;

public interface UserService {
    RegisterPayload registerUser(RegisterDto registerDto);
}
