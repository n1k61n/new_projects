package com.example.fruitables.controllers;


import com.example.fruitables.dtos.auth.AuthResponseDto;
import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.services.UserService;
import jakarta.servlet.http.HttpSession;
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
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null ) {
            model.addAttribute("loginError", "Melumat sehvdir");
        }
        return "auth/login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("registerDto", registerDto);
            return "auth/register";
        }

        if(userService.isEmailExist(registerDto.getEmail())){
            result.rejectValue("email", "error.email", "Bu email artiq movcuddur");
            model.addAttribute("registerDto", registerDto);
            return "auth/register";
        }

        userService.registerUser(registerDto);
        session.setAttribute("pendingEmail", registerDto.getEmail());
        return "redirect:/verify-otp";
    }

    @GetMapping("/verify-otp")
    public String confirmRegistration(HttpSession session, Model model) {
        String email = (String) session.getAttribute("pendingEmail");
        if (email == null) {
            return "redirect:/register";
        }
        AuthResponseDto dto = new AuthResponseDto();
        dto.setEmail(email);
        model.addAttribute("authResponseDto", dto);
        return "auth/verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(AuthResponseDto authResponseDto) {
        // Логика проверки OTP
        log.info("Received OTP: {}", authResponseDto.getOtp());
        boolean isVerified = userService.verifyUser(authResponseDto);
        if (isVerified) {
            return "auth/login";
        } else {
            return "redirect:/login?error=invalid-token";
        }
    }

    @GetMapping("/forgot-password")
    public String forgot() {
        return "auth/forgot-password";
    }

}