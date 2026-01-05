package com.example.fruitables.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(unique = true, length = 20)
    private String barcode;
    private Double price;
    @Column(precision = 12, scale = 2)
    private BigDecimal discount;
    private int stock;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "seo_url")
    private String seoUrl;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "short_description")
    private String shortDescription;

    @ManyToOne
    private Category category;

    @OneToMany
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Column(name = "is_pinned", columnDefinition = "boolean default false")
    private boolean pinned;
    @Column(name = "is_slider", columnDefinition = "boolean default false")
    private boolean slider;

    @Column(name = "slider_index", columnDefinition = "int default 0")
    private int sliderIndex;

}
