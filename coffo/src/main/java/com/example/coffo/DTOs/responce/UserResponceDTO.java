package com.example.coffo.DTOs.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponceDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
}
