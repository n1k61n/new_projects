package com.example.fruitables.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("Gözləyir"),
    PROCESSING("Hazırlanır"),
    SHIPPED("Yoldadır"),      // Xətanı aradan qaldıran sətir
    DELIVERED("Çatdırıldı"),
    COMPLETED("Tamamlandı"),
    CANCELLED("Ləğv edildi");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}