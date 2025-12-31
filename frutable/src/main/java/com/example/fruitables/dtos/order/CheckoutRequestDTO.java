package com.example.fruitables.dtos.order;

import java.util.List;

public record CheckoutRequestDTO(
        List<OrderItemDTO> items,
        String couponCode,
        String shippingCountry
) {}
