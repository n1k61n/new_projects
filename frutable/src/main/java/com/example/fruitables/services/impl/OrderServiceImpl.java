package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.order.CartSummaryDTO;
import com.example.fruitables.dtos.order.OrderDto;
import com.example.fruitables.enums.CartStatus;
import com.example.fruitables.enums.OrderStatus;
import com.example.fruitables.models.Cart;
import com.example.fruitables.models.Order;
import com.example.fruitables.models.User;
import com.example.fruitables.repositories.CartRepository;
import com.example.fruitables.repositories.OrderRepository;
import com.example.fruitables.services.OrderService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @Override
    public long countTotalOrders() {
        return orderRepository.count();
    }

    @Override
    public double calculateTotalRevenue() {
        Double result = orderRepository.calculateTotalByStatus(OrderStatus.COMPLETED);
        return (result != null) ? result : 0.0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAllOrders(Sort sort) {
        if (orderRepository.findAll().isEmpty()) {
            return List.of();
        }
        return orderRepository.findAll(sort).stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    @Override
    public boolean updateOrderStatusById(Long orderId, String newStatus) {
        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Sifariş tapılmadı: " + orderId));
            // 2. Statusu yeniləyirik
            order.setStatus(OrderStatus.valueOf(newStatus));

            // 3. Bazada yadda saxlayırıq
            orderRepository.save(order);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean createUserOrder(String userName, CartSummaryDTO summary) {
        User existUser = userService.findByEmail(userName);
        if (existUser != null) {
            Order order = new Order();
            order.setUser(existUser);
            order.setTotalPrice(summary.getTotal());
            order.setOrderDate(LocalDate.now());
            order.setStatus(OrderStatus.SHIPPED);
            orderRepository.save(order);

            Cart cart = cartRepository.findByUserAndStatus(existUser, CartStatus.OPEN).orElseThrow();
            cart.setStatus(CartStatus.COMPLETED);
            cartRepository.save(cart);
            return true;
        }
        return false;
    }

    @Override
    public long countOrderActive() {
        return orderRepository.count();
    }
}

