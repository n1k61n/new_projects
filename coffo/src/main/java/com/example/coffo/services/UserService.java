package com.example.coffo.services;

import com.example.coffo.DTOs.UserDTO.RegisterDto;
import com.example.coffo.payloads.RegisterPayload;

public interface UserService {
    RegisterPayload registerUser(RegisterDto registerDto);
}
