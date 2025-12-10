package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.coupon.CouponCreateDto;
import com.example.fruitables.dtos.coupon.CouponDto;
import com.example.fruitables.dtos.coupon.CouponUpdateDto;
import com.example.fruitables.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/coupon")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public String index(Model model){
        List<CouponDto> couponDtoList = couponService.getDashboardCoupons();
        model.addAttribute("coupons", couponDtoList);
        return "dashboard/coupon/index";
    }


    @GetMapping("/create")
    public String create(){
        return "dashboard/coupon/create";
    }

    @PostMapping("/create")
    public String create(CouponCreateDto couponCreateDto){
        boolean result = couponService.createCoupon(couponCreateDto);
        return "redirect:/dashboard/coupon";
    }


    @GetMapping("/update/{id}")
    public String update (@PathVariable Long id, Model model){
        CouponUpdateDto couponUpdateDto = couponService.getUpdateCoupon(id);
        model.addAttribute("coupon", couponUpdateDto);
        return "dashboard/coupon/update";
    }

    @PostMapping("/update/{id}")
    public String update (@PathVariable Long id, CouponUpdateDto couponUpdateDto){
        couponService.updateCoupon(id, couponUpdateDto);
        return "redirect:/dashboard/coupon";
    }
}
