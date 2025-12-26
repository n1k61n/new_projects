package com.example.fruitables.services;

import com.example.fruitables.dtos.auth.AuthResponseDto;
import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.dtos.contact.ContactDto;
import com.example.fruitables.dtos.user.UserNameDto;
import com.example.fruitables.dtos.user.UserProfileDto;
import com.example.fruitables.models.User;
import com.example.fruitables.payloads.RegisterPayload;


public interface UserService {
    RegisterPayload registerUser(RegisterDto registerDto);
    UserProfileDto getUserProfile(String email);

    boolean verifyUser(AuthResponseDto authResponseDto);

    boolean isEmailExist(String email);

    boolean updateProfile(UserProfileDto profileDto);

    UserNameDto getCurrentAdmin();


    User findByEmail(String email);

    ContactDto getContact(String name);
}
