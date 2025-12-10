package com.example.fruitables.services;

import com.example.fruitables.dtos.auth.RegisterDto;

public interface UserService {
    boolean registerUser(RegisterDto registerDto);
}
