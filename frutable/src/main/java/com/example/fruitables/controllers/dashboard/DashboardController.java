package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.message.MessageDto;
import com.example.fruitables.dtos.message.MessageReadDto;
import com.example.fruitables.dtos.toolbar.UserProfileDto;
import com.example.fruitables.services.CouponService;
import com.example.fruitables.services.MessageService;
import com.example.fruitables.services.OrderService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final MessageService messageService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final CouponService couponService;

    @GetMapping
    public String index(Model model){
        long totalOrders = orderService.countTotalOrders();
        double totalRevenue = orderService.calculateTotalRevenue();
        long activeCoupons = couponService.countActiveCoupons();

        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("activeCoupons", activeCoupons);
        return "dashboard/index";
    }

    @GetMapping("/messages/read-and-redirect/{id}")
    public String readAndRedirect(@PathVariable Long id) {
        MessageDto messageDto = messageService.findByIdAndMarkAsRead(id);
        String gmailLink = "https://mail.google.com/mail/?view=cm&fs=1&to=" + messageDto.getEmail() +
                "&su=Cavab: Tecili" + "&body=" + messageDto.getMessage();
        return "redirect:" + gmailLink;
    }


    @GetMapping("/admin-profile")
    public String showProfile(Model model, Principal principal) {
        String email = principal.getName();
        UserProfileDto userProfileDto = userService.getUserProfile(email);
        model.addAttribute("adminProfile", userProfileDto);
        return "dashboard/admin-profile";
    }


    @PostMapping("/admin-profile")
    public String updateProfile(@ModelAttribute("adminProfile") UserProfileDto profileDto) {
        boolean  result = userService.updateProfile(profileDto);
        if(result)
            return "redirect:admin-profile?success";
        else
            return "redirect:admin-profile?error";
    }
}


