package com.example.fruitables.dtos.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
    private Long id;
    private String code;
    private BigDecimal discount;
    private Integer usageLimit;
    private int usageCount;
    private boolean active;
    private LocalDateTime expiryDate;
}

