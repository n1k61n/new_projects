package com.example.fruitables.repositories;

import com.example.fruitables.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRrepository extends JpaRepository<CartItem, Long> {
}
