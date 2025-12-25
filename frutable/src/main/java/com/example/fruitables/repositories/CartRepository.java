package com.example.fruitables.repositories;

import com.example.fruitables.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByProductId(Long productId);

    List<Cart> findByUserId(Long id);
}

