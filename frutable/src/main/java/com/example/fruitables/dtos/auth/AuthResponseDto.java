package com.example.fruitables.dtos.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {
    private String email;
    private String otp;
//    private long expiresIn;
}
