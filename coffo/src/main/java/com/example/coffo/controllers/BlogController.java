package com.example.coffo.controllers;

import com.example.coffo.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.lang.model.element.ModuleElement;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    @GetMapping("/blog")
    public String blogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blog";
    }
}
