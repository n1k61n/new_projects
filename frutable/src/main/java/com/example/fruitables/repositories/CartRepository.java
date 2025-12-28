package com.example.fruitables.repositories;

import com.example.fruitables.enums.CartStatus;
import com.example.fruitables.models.Cart;
import com.example.fruitables.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndStatus(User user, CartStatus status);
    List<Cart> findByUserId(Long id);
}
