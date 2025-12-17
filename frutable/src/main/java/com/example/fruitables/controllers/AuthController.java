package com.example.fruitables.controllers;


import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j

@Controller
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("registerDto", registerDto);
            return "auth/register";
        }
        log.info(String.valueOf(registerDto));
        userService.registerUser(registerDto);
        model.addAttribute("msg", "User register successfully!");
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String forgot() {
        return "auth/forgot-password";
    }

    @GetMapping("/confirm")
    public String confirmRegistration(@RequestParam("token") String token) {
        boolean isVerified = userService.verifyUser(token);
        if (isVerified) {
            return "auth/registration-success";
        } else {
            return "redirect:/login?error=invalid-token";
        }
    }

}