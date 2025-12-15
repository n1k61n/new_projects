package com.example.fruitables.services;

import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.models.User;

import java.util.Optional;



public interface UserService {
    boolean registerUser(RegisterDto registerDto);

    Optional<User> getUserByEmail( String email);

}
