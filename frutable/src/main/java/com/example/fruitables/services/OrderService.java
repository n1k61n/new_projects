package com.example.fruitables.services;

import com.example.fruitables.dtos.order.OrderDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface OrderService {
    long countTotalOrders();

    double calculateTotalRevenue();

    List<OrderDto> findAllOrders();

    boolean updateOrderStatusById(Long orderId , String newStatus);

}


