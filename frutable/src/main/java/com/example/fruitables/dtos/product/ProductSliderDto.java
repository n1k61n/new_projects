package com.example.fruitables.dtos.product;

import com.example.fruitables.dtos.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSliderDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private String imageUrl;
    private CategoryDto category;
    private String shortDescription;
}
