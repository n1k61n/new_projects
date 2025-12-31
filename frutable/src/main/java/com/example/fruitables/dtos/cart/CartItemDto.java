package com.example.fruitables.dtos.cart;

import com.example.fruitables.dtos.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long id;
    private ProductDto product;
    private int quantity;

    private Double itemPrice = 0.0;

    public Double getItemPrice(){
        return product.getPrice() * quantity;
    }
}
