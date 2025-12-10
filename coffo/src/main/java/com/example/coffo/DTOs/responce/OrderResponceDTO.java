package com.example.coffo.DTOs.responce;

import com.example.coffo.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponceDTO {
    private Long id;

    private String customerName;

    private String customerEmail;

    private String customerPhone;
    private String address;

    private Double totalPrice;

    private LocalDateTime orderDate;

    private User user;
}
