package com.example.fruitables.services;

import com.example.fruitables.dtos.auth.AuthResponseDto;
import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.dtos.auth.UserNameDto;
import com.example.fruitables.dtos.auth.UserProfileDto;
import com.example.fruitables.models.User;


public interface UserService {
    boolean registerUser(RegisterDto registerDto);
    UserProfileDto getUserProfile(String email);

    boolean verifyUser(AuthResponseDto authResponseDto);

    boolean isEmailExist(String email);

    boolean updateProfile(UserProfileDto profileDto);

    UserNameDto getCurrentAdmin();


    User findByEmail(String email);
}
