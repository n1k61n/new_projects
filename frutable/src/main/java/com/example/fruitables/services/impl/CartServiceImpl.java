package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.dtos.cart.CartItemDto;
import com.example.fruitables.dtos.product.ProductDto;
import com.example.fruitables.enums.CartStatus;
import com.example.fruitables.models.Cart;
import com.example.fruitables.models.CartItem;
import com.example.fruitables.models.Product;
import com.example.fruitables.models.User;
import com.example.fruitables.payloads.results.Result;
import com.example.fruitables.payloads.results.error.ErrorResult;
import com.example.fruitables.payloads.results.success.SuccessResult;
import com.example.fruitables.repositories.CartItemRepository;
import com.example.fruitables.repositories.CartRepository;
import com.example.fruitables.services.CartService;
import com.example.fruitables.services.ProductService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public Result addProductToCart(String email, AddToCartDto addToCartDto) {
        try {
            User user = userService.findByEmail(email);
            Cart cart = cartRepository.findByUserAndStatus(user, CartStatus.OPEN)
                    .orElseGet(() -> {
                        Cart newCart = new Cart();
                        newCart.setUser(user);
                        newCart.setStatus(CartStatus.OPEN);
                        return cartRepository.save(newCart);
                    });

            Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), addToCartDto.getProductId());

            if (existingItem.isPresent()) {
                CartItem item = existingItem.get();
                item.setQuantity(item.getQuantity() + addToCartDto.getQuantity());
                cartItemRepository.save(item);
            } else {
                ProductDto productDto = productService.getProductById(addToCartDto.getProductId());
                Product product = modelMapper.map(productDto, Product.class);

                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(addToCartDto.getQuantity());
                cartItemRepository.save(newItem);
            }
            return new SuccessResult(true, "Mehsul sebete əlavə olundu.");
        } catch (Exception e) {
            return new ErrorResult(false, "Mehsul sebete əlavə edilmədi");
        }
    }

    @Override
    @Transactional
    public Result deleteItem(String username, Long productId) {
        User user = userService.findByEmail(username);
        Optional<Cart> cartOptional = cartRepository.findByUserAndStatus(user, CartStatus.OPEN);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Optional<CartItem> itemOptional = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
            itemOptional.ifPresent(cartItemRepository::delete);
            return new SuccessResult(true, "Mehsul ugurla silindi");
        }
        return new ErrorResult(false, "Mehsul silinmedi");
    }

    @Override
    @Transactional
    public Result increaseQuantity(String username, Long productId) {
        User user = userService.findByEmail(username);
        Optional<Cart> cartOptional = cartRepository.findByUserAndStatus(user, CartStatus.OPEN);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Optional<CartItem> itemOptional = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

            if (itemOptional.isPresent()) {
                CartItem item = itemOptional.get();
                item.setQuantity(item.getQuantity() + 1);
                cartItemRepository.save(item);
                return new SuccessResult(true, "Sayi artirilindi");
            }
        }
        return new ErrorResult(false, "Sayi artmadi");
    }

    @Override
    @Transactional
    public Result decreaseQuantity(String username, Long productId) {
        User user = userService.findByEmail(username);
        Optional<Cart> cartOptional = cartRepository.findByUserAndStatus(user, CartStatus.OPEN);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Optional<CartItem> itemOptional = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

            if (itemOptional.isPresent()) {
                CartItem item = itemOptional.get();
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    cartItemRepository.save(item);
                } else {
                    cartItemRepository.delete(item);
                }
                return new SuccessResult(true, "Sayi azaldi");
            }
        }
        return new ErrorResult(false, "Sayi azalmadi");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> getCartItems(String email) {
        User user = userService.findByEmail(email);
        Cart cart = cartRepository.findByUserAndStatus(user, CartStatus.OPEN).orElse(null);
        
        if (cart == null) {
            return new ArrayList<>();
        }

        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        return cartItems.stream()
                .map(item -> {
                    CartItemDto dto = new CartItemDto();
                    dto.setId(item.getId());
                    dto.setQuantity(item.getQuantity());
                    
                    // Manually map Product to ProductDto
                    Product product = item.getProduct();
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setPrice(product.getPrice() != null ? product.getPrice().doubleValue() : 0.0);
                    productDto.setImageUrl(product.getImageUrl());
                    // Map other fields as needed
                    
                    dto.setProduct(productDto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
