package com.example.fruitables.dtos.product;

import com.example.fruitables.dtos.category.CategoryDto;
import com.example.fruitables.dtos.comment.CommentDashboardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDashboardDetailDto {
    private Long id;
    private String name;
    private String barcode;
    private BigDecimal price;
    private BigDecimal discount;
    private int stock;
    private String imageUrl;
    private String seoUrl;
    private String description;
    private String shortDescription;
    private CategoryDto category;
    private List<CommentDashboardDto> comments;
}
