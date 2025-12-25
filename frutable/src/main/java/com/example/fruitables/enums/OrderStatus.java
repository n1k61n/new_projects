package com.example.fruitables.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("Gözləyir"),
    PROCESSING("Hazırlanır"),
    SHIPPED("Yoldadır"),
    DELIVERED("Çatdırıldı"),
    COMPLETED("Tamamlandı"),
    CANCELLED("Ləğv edildi");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}