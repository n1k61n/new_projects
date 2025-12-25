package com.example.coffo.controllers;

import com.example.coffo.DTOs.UserDTO.RegisterDto;
import com.example.coffo.payloads.RegisterPayload;
import com.example.coffo.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Melumat sehvdir");
        }


        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(RegisterDto registerDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("registerDto", registerDto);
            return "/register";
        }

        RegisterPayload result = userService.registerUser(registerDto);
        if(result.getStatus() == 200){
            return "redirect:/login";
        }
        model.addAttribute("error", result.getMmessage());
        return "/register";
    }
}
