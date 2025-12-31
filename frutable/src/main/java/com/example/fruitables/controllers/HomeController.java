package com.example.fruitables.controllers;


import com.example.fruitables.dtos.category.CategoryDto;
import com.example.fruitables.dtos.category.CategoryPinnedDto;
import com.example.fruitables.dtos.contact.ContactDto;
import com.example.fruitables.dtos.product.ProductPinnedDto;
import com.example.fruitables.dtos.product.ProductSliderDto;
import com.example.fruitables.models.User;
import com.example.fruitables.services.CategoryService;
import com.example.fruitables.services.ContactService;
import com.example.fruitables.services.ProductService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {


    private final ProductService productService;
    private final CategoryService categoryService;



    @GetMapping()
    public String home(Model model ) {
        List<CategoryPinnedDto> categoryPinnedDtoList = categoryService.getPinnedCategory();
        model.addAttribute("categories", categoryPinnedDtoList);

        List<ProductSliderDto> productSliderDtoList = productService.getSilderProducts();
        model.addAttribute("sliderProducts", productSliderDtoList);

        List<ProductPinnedDto> allProducts = productService.getAllProducts();
        model.addAttribute("allProducts", allProducts);

        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        List<ProductPinnedDto> products = productService.getAllProducts();
        List<CategoryDto> categories = categoryService.getAllCategoriesWithCount();
        List<ProductSliderDto> featuredProducts = productService.getSilderProducts();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("featuredProducts", featuredProducts);
        return "shop";
    }



}
