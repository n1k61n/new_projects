package com.example.fruitables.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;
    private Double price;
    private String imageUrl;
    private String shortDescription;
    private String description;
}
