package com.example.agency.dtos.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponceDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String message;
}
