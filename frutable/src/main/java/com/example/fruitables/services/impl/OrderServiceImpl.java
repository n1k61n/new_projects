package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.order.OrderDto;
import com.example.fruitables.enums.OrderStatus;
import com.example.fruitables.models.Order;
import com.example.fruitables.repositories.OrderRepository;
import com.example.fruitables.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public long countTotalOrders() {
        return orderRepository.count();
    }

    @Override
    public double calculateTotalRevenue() {
//        Double total = orderRepository.sumTotalPriceByStatus(OrderStatus.COMPLETED);
//        return total == null ? 0.0 : total;
        Double result = orderRepository.calculateTotalByStatus(OrderStatus.COMPLETED);
        return (result != null) ? result : 0.0;
    }

    @Override
    public List<OrderDto> findAllOrders() {
        if(orderRepository.findAll().isEmpty()){
            return List.of();
        }
        return orderRepository.findAll().stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
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
}

