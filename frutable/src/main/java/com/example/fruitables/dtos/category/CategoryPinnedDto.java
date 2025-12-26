package com.example.fruitables.dtos.category;


import com.example.fruitables.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPinnedDto {
    private Long id;
    private String name;
    private boolean pinned;

    private List<Product> products = new ArrayList<>();
}
