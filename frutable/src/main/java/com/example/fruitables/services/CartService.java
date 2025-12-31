package com.example.fruitables.services;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.dtos.cart.CartItemDto;
import com.example.fruitables.payloads.results.Result;

import java.util.List;

public interface CartService {
    Result addProductToCart(String email, AddToCartDto addToCartDto);

    Result deleteItem(String username, Long productId);

    Result increaseQuantity(String username, Long productId);

    Result decreaseQuantity(String username, Long productId);


    List<CartItemDto> getCartItems(String email);



}

