package com.example.fruitables.services;

import com.example.fruitables.dtos.coupon.CouponCreateDto;
import com.example.fruitables.dtos.coupon.CouponDto;
import com.example.fruitables.dtos.coupon.CouponUpdateDto;

import java.util.List;

public interface CouponService {
    List<CouponDto> getDashboardCoupons();

    boolean createCoupon(CouponCreateDto couponCreateDto);

    CouponUpdateDto getUpdateCoupon(Long id);

    boolean updateCoupon(Long id, CouponUpdateDto couponUpdateDto);
}

