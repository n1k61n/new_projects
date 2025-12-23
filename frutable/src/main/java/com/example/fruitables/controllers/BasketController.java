package com.example.fruitables.controllers;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.payloads.results.Result;
import com.example.fruitables.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final CartService cartService;

    @GetMapping("/my-basket")
    @PreAuthorize("isAuthenticated()")
    public String myBasket(Principal principal){
        String email = principal.getName();


        return "basket/my-basket";
    }


    @PostMapping("/add")
    public String addToCart(Principal principal, AddToCartDto addToCartDto){
        String email = principal.getName();
        Result result = cartService.addToCart(email, addToCartDto);

        return "redirect:/basket/my-basket";
    }

}
