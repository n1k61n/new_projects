package com.example.fruitables.services;

import com.example.fruitables.dtos.auth.RegisterDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;


public interface UserService {
    boolean registerUser(RegisterDto registerDto);
}
