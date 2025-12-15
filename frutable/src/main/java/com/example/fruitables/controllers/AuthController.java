package com.example.fruitables.controllers;


import com.example.fruitables.dtos.auth.RegisterDto;
import com.example.fruitables.models.User;
import com.example.fruitables.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

<<<<<<< Updated upstream

=======
import javax.lang.model.element.ModuleElement;
import java.util.Objects;

@Slf4j
>>>>>>> Stashed changes
@Controller
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute RegisterDto registerDto, Model model){
        try {
            User user = userService.getUserByEmail(registerDto.getEmail()).orElse(null);
            if (Objects.equals(user.getPassword(), registerDto.getPassword())) {
                return "redirect:/dashboard";
            }
            model.addAttribute("error", "Invalid email or password");
            return "redirect:/login?error";
        }catch (Exception e){
            model.addAttribute("error", "Login failed. Please try again.");
            return "login";
        }
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

}