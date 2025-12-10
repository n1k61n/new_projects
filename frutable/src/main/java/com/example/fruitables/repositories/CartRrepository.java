package com.example.fruitables.repositories;

import com.example.fruitables.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRrepository extends JpaRepository<Cart, Long> {
}

