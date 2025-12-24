package com.example.fruitables.controllers;


import com.example.fruitables.dtos.category.CategoryPinnedDto;
import com.example.fruitables.dtos.product.ProductPinnedDto;
import com.example.fruitables.dtos.product.ProductSliderDto;
import com.example.fruitables.services.CategoryService;
import com.example.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {


    private final ProductService productService;
    private final CategoryService categoryService;



    @GetMapping()
    public String home(Model model) {
        List<CategoryPinnedDto> categoryPinnedDtoList = categoryService.getPinnedCategory();
        model.addAttribute("categories", categoryPinnedDtoList);

        List<ProductSliderDto> productSliderDtoList = productService.getSilderProducts();
        model.addAttribute("sliderProducts", productSliderDtoList);

        List<ProductPinnedDto> allProducts = productService.getAllProducts();
        model.addAttribute("allProducts", allProducts);

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





    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }


    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


}
