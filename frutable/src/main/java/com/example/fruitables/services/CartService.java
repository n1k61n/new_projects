package com.example.fruitables.services;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.models.Cart;
import com.example.fruitables.payloads.results.Result;

public interface CartService {
    Result addToCart(String email, AddToCartDto addToCartDto);

}

