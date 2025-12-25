package com.example.fruitables.dtos.cart;

import com.example.fruitables.models.Product;
import com.example.fruitables.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long id;
    private String name;
    private Product product;
    private User user;
    private Integer quantity;
    private Double price;
}
