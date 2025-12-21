package com.example.fruitables.dtos.order;


import com.example.fruitables.enums.OrderStatus;
import com.example.fruitables.models.OrderItem;
import com.example.fruitables.models.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

//    <th>Sifariş ID</th>
    private Long id;
// <th>Müştəri</th>
    private User user;
// <th>Məbləğ</th>
    private BigDecimal totalPrice;

    private List<OrderItem> orderItems;
//  <th>Status</th>
    private OrderStatus status;
//
    private LocalDateTime orderDate;

}
