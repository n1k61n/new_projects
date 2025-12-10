package com.example.agency.dtos.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactCreateDTO {
    private String name;
    private String email;
    private String phone;
    private String message;
}
