package com.example.fruitables.repositories;

import com.example.fruitables.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRrepository extends JpaRepository<OrderItem, Long> {
}

