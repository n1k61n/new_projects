package com.example.coffo.DTOs.responce;

import com.example.coffo.models.MenuItem;
import com.example.coffo.models.Order;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponceDTO {
    private Long id;

    private String productName;

    private Order order;
    private Integer quantity;
    private Double price;

    private MenuItem menuItem;
}
