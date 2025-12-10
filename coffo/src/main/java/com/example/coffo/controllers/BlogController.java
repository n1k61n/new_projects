package com.example.coffo.controllers;

import com.example.coffo.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
