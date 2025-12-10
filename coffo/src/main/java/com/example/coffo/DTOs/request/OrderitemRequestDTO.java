package com.example.coffo.DTOs.request;

import com.example.coffo.models.MenuItem;
import com.example.coffo.models.Order;
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
public class OrderitemRequestDTO {
    private String productName;

    private Order order;
    private Integer quantity;
    private Double price;


    private MenuItem menuItem;

}
