package com.example.fruitables.services;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.models.Cart;
import com.example.fruitables.payloads.results.Result;

public interface CartService {
    Result addProductToCart(String email, AddToCartDto addToCartDto);

    void deleteItem(String username, Long productId);

    void increaseQuantity(String username, Long productId);

    void decreaseQuantity(String username, Long productId);


}

