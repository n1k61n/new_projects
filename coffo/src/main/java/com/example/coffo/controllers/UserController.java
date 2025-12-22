package com.example.coffo.controllers;

import com.example.coffo.DTOs.UserDTO.RegisterDto;
import com.example.coffo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerDto") @Valid RegisterDto registerDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        return "redirect:/login";
    }
}
