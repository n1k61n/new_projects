package com.example.fruitables.services;

import com.example.fruitables.dtos.order.CartSummaryDTO;
import com.example.fruitables.dtos.order.OrderDto;

import java.util.List;

public interface OrderService {
    long countTotalOrders();

    double calculateTotalRevenue();

    List<OrderDto> findAllOrders();

    boolean updateOrderStatusById(Long orderId , String newStatus);

    boolean createUserOrder(String userName, CartSummaryDTO summary);
}


