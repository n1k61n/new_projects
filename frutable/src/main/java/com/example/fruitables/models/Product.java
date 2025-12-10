package com.example.fruitables.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(unique = true, length = 10)
    private String barcode;
    @Column(precision = 12, scale = 2)
    private BigDecimal price;
    @Column(precision = 12, scale = 2)
    private BigDecimal discount;
    private int stock;
    private String imageUrl;
    private String seoUrl;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String shortDescription;

    @ManyToOne
    private Category category;

    @OneToMany
    private List<OrderItem> orderItems;

    @OneToMany
    private List<Comment> comments;
}
