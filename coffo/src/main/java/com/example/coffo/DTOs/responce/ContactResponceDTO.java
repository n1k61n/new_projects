package com.example.coffo.DTOs.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponceDTO {
    private Long id;

    private String name;

    private String email;

    private String message;

    private String phone;
}
