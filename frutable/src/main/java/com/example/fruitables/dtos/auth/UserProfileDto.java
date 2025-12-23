package com.example.fruitables.dtos.auth;

import lombok.Data;

@Data
public class UserProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String imageUrl;
}
