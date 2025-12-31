package com.example.fruitables.dtos.order;

import com.example.fruitables.enums.OrderStatus;
import com.example.fruitables.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartSummaryDTO {
    private Double subTotal;
    private Double shipping;
    private Double total;
}