package com.example.fruitables.models;

import com.example.fruitables.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
