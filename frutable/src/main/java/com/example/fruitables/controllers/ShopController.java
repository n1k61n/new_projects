package com.example.fruitables.controllers;

import com.example.fruitables.dtos.product.ProductSliderDto;
import com.example.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;

    @GetMapping("/shop-detail")
    public String shopDetail(Model model){
        List<ProductSliderDto> productSliderDtoList = productService.getSilderProducts();
        model.addAttribute("sliderProducts", productSliderDtoList);
        return "shop-detail";
    }
}
