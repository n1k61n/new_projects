package com.example.fruitables.repositories;

import com.example.fruitables.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRrepository extends JpaRepository<Order, Long> {
}

