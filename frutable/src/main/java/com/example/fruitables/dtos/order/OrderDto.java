package com.example.fruitables.dtos.order;

import com.example.fruitables.enums.OrderStatus;
import com.example.fruitables.models.OrderItem;
import com.example.fruitables.models.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private User user;
    private BigDecimal totalPrice;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    private LocalDate orderDate;
}
