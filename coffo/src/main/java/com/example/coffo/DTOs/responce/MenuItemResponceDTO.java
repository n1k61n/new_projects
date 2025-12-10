package com.example.coffo.DTOs.responce;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponceDTO {
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private Double price;
}
