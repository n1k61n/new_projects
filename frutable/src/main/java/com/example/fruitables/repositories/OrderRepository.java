package com.example.fruitables.repositories;

import com.example.fruitables.enums.OrderStatus;
import com.example.fruitables.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.status = :status")
    Double calculateTotalByStatus(@Param("status") OrderStatus status);

    @Query("SELECT count(o) FROM Order o WHERE o.status != 'COMPLETED'")
    long countActiveOrders();
}

