package com.example.coffo.DTOs.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequestDTO {
    private String name;

    private String description;

    private String imageUrl;

    private Double price;
}
