package com.example.fruitables.controllers;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.dtos.cart.CartItemDto;
import com.example.fruitables.payloads.results.Result;
import com.example.fruitables.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final CartService cartService;

    @GetMapping("/my-basket")
    @PreAuthorize("isAuthenticated()")
    public String myBasket(Principal principal, Model model){
        String email = principal.getName();

        List<CartItemDto> cartItems =   cartService.getCart(email);
        model.addAttribute("basket", cartItems);

        return "basket/my-basket";
    }

    @PostMapping("/add")
    public String addToCart(Principal principal, AddToCartDto addToCartDto){
        if(principal == null){
            return "redirect:/login";
        }
        String email = principal.getName();
        Result result = cartService.addProductToCart(email, addToCartDto);

        return "redirect:/basket/my-basket";
    }

    @PostMapping("/delete")
    public String deleteItemFromCart(@RequestParam Long productId, Principal principal){
        String username = principal.getName();
        cartService.deleteItem(username, productId);
        return "redirect:/basket/my-basket";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId, @RequestParam String action, Principal principal) {
        String username = principal.getName();
        if(action.equals("increase")){
            cartService.increaseQuantity(username, productId);
        }else if(action.equals("decrease")){
            cartService.decreaseQuantity(username, productId);
        }
        return "redirect:/basket/my-basket";
    }

}
