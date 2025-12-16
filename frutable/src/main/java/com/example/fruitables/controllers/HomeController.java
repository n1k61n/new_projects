package com.example.fruitables.controllers;


import com.example.fruitables.dtos.product.ProductDashboardDto;
import com.example.fruitables.models.Product;
import com.example.fruitables.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {


    private final ProductRepository productRepository;


    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "index";
    }


    @GetMapping("/chackout")
    public String chackout(){
        return "chackout";
    }

    @GetMapping("/testimonial")
    public String testimonial(){
        return "testimonial";
    }


    @GetMapping("/404")
    public String error(){
        return "404";
    }



    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }


    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }

    @GetMapping("/shop-detail")
    public String shopDetail(){
        return "shop-detail";
    }
}
