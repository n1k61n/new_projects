package com.example.fruitables.controllers;

import com.example.fruitables.dtos.user.UserProfileDto;
import com.example.fruitables.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String email = principal.getName();
        UserProfileDto userProfileDto = userService.getUserProfile(email);

        model.addAttribute("userProfile", userProfileDto);
        return "auth/profile";
    }


    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("userProfile") UserProfileDto userProfileDto,
                                RedirectAttributes redirectAttributes) {

        // Şifrənin təkrarının yoxlanılması (opsional)
        if (userProfileDto.getPassword() != null && !userProfileDto.getPassword().equals(userProfileDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Şifrələr uyğun gəlmir!");
            return "redirect:/profile";
        }

        boolean result = userService.updateProfile(userProfileDto);

        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "Profiliniz uğurla yeniləndi!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Yeniləmə zamanı xəta baş verdi.");
        }
        return "redirect:/profile";
    }
}