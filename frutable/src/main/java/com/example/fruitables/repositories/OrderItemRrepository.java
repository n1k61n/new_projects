package com.example.fruitables.repositories;

import com.example.fruitables.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRrepository extends JpaRepository<OrderItem, Long> {
}

