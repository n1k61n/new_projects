package com.example.fruitables.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
public class HomeController {


    @GetMapping(path = {"/", "/index"})
    public String home(){
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
