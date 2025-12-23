package com.example.fruitables.dtos.cart;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartDto {
    private Long productId;
    private Integer quantity;

}
