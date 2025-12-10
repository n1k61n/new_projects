package com.example.coffo.repositories;

import com.example.coffo.models.MenuItem;
import com.example.coffo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
