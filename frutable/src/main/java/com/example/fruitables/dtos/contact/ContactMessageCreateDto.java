package com.example.fruitables.dtos.contact;


import lombok.Data;

@Data
public class ContactMessageCreateDto {
    private String name;
    private String email;
    private String message;
}