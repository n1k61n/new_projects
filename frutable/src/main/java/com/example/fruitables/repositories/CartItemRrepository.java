package com.example.fruitables.repositories;

import com.example.fruitables.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRrepository extends JpaRepository<CartItem, Long> {
}
