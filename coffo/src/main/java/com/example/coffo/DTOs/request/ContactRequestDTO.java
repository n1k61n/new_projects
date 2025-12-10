package com.example.coffo.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    private String name;

    private String email;

    private String message;

    private String phone;
}
