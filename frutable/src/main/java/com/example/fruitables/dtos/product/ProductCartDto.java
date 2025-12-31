package com.example.fruitables.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDto {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
}
