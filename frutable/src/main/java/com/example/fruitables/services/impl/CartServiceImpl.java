package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.dtos.cart.CartItemDto;
import com.example.fruitables.dtos.product.ProductDto;
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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;


    @Override
    public Result addProductToCart(String email, AddToCartDto addToCartDto) {
        Cart findCart = cartRepository.findByProductId(addToCartDto.getProductId());

        if(findCart != null){
            findCart.setQuantity(addToCartDto.getQuantity() + findCart.getQuantity());
            cartRepository.save(findCart);
            return new SuccessResult(true);
        }

        User user = userService.findByEmail(email);
        ProductDto productDto = productService.getProductById(addToCartDto.getProductId());
        Product product = modelMapper.map(productDto, Product.class);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setProduct(product);
        cartRepository.save(cart);
        return new Result(true);
    }

    @Override
    public void deleteItem(String username, Long productId) {
    }

    @Override
    public void increaseQuantity(String username, Long productId) {

    }

    @Override
    public void decreaseQuantity(String username, Long productId) {

    }

    @Override
    public List<CartItemDto> getCart(String email) {
        User user = userService.findByEmail(email);
//        List<Cart> cartList = cartRepository.findByUser(user);
        return List.of();
    }


}

