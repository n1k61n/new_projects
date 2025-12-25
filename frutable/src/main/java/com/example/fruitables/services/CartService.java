package com.example.fruitables.services;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.dtos.cart.CartItemDto;
import com.example.fruitables.models.Cart;
import com.example.fruitables.payloads.results.Result;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface CartService {
    Result addProductToCart(String email, AddToCartDto addToCartDto);

    void deleteItem(String username, Long productId);

    void increaseQuantity(String username, Long productId);

    void decreaseQuantity(String username, Long productId);


    List<CartItemDto> getCart(String email);
}

