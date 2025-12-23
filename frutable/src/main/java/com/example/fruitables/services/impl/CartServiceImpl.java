package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.models.Cart;
import com.example.fruitables.models.Product;
import com.example.fruitables.models.User;
import com.example.fruitables.payloads.results.Result;
import com.example.fruitables.payloads.results.success.SuccessResult;
import com.example.fruitables.repositories.CartRepository;
import com.example.fruitables.services.CartService;
import com.example.fruitables.services.ProductService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;


    @Override
    public Result addToCart(String email, AddToCartDto addToCartDto) {
        Cart findCart = cartRepository.findByProductId(addToCartDto.getProductId());

        if(findCart != null){
            findCart.setQuantity(addToCartDto.getQuantity() + findCart.getQuantity());
            cartRepository.save(findCart);
            return new SuccessResult(true);
        }

        User user = userService.findByEmail(email);
        Product product = productService.getProductById(addToCartDto.getProductId());

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setProduct(product);
        cartRepository.save(cart);
        return new Result(true);
    }


}

