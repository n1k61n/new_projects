package com.example.fruitables.controllers;

import com.example.fruitables.dtos.auth.UserProfileDto;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        String email = principal.getName();
        UserProfileDto userProfileDto = userService.getUserProfile(email);

        model.addAttribute("user", userProfileDto);
        return "auth/profile";
    }

//    @PostMapping("/profile")
//    public String updateProfile(UserProfileDto userProfileDto){
//        boolean result = userService.updateProfile(userProfileDto);
//        if(result)
//            return "redirect:/profile";
//        else
//            return "redirect:/profile?status=error";
//    }


    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("adminProfile") UserProfileDto userProfileDto, RedirectAttributes redirectAttributes) {
        boolean result = userService.updateProfile(userProfileDto);

        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "Profiliniz uğurla yeniləndi!");
            return "redirect:/profile";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Yeniləmə zamanı xəta baş verdi.");
            return "redirect:/profile";
        }
    }
}