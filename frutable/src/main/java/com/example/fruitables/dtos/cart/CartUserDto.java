package com.example.fruitables.dtos.cart;


import com.example.fruitables.dtos.product.ProductCartDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartUserDto {
    private Long id;
    private int quantity;
    private ProductCartDto product;
    private Double totalPrice;

    public Double getTotalPrice(){
        return product.getPrice() * quantity;
    }
}
