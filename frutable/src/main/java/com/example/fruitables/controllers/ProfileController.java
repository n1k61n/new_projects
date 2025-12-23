package com.example.fruitables.controllers;

import com.example.fruitables.dtos.auth.UserProfileDto;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        // Principal daxil olan istifadəçinin emailini saxlayır
        String email = principal.getName();
        UserProfileDto userProfileDto = userService.getUserProfile(email);

        model.addAttribute("user", userProfileDto);
        return "auth/profile";
    }
}