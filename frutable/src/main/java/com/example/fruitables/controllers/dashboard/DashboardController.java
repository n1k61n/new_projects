package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.user.UserProfileDto;
import com.example.fruitables.models.Comment;
import com.example.fruitables.services.CommentService;
import com.example.fruitables.services.CouponService;
import com.example.fruitables.services.OrderService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;
    private final OrderService orderService;
    private final CouponService couponService;
    private final CommentService commentService;

    @GetMapping
    public String dashboard(Model model){
        long totalOrders = orderService.countTotalOrders();
        double totalRevenue = orderService.calculateTotalRevenue();
        long activeCoupons = couponService.countActiveCoupons();

        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("activeCoupons", activeCoupons);

        List<Double> monthlyEarnings = Arrays.asList(1000.0, 1500.0, 1200.0, 2500.0, 1800.0, 3203.0);
        List<Integer> categoryShares = Arrays.asList(45, 30, 25); // Meyvə, Tərəvəz, Digər

        model.addAttribute("earningsData", monthlyEarnings);
        model.addAttribute("categoryData", categoryShares);

        // Əvvəlki kartlar üçün saylar
        long commentCount = commentService.countComment();
        long orderCount = orderService.countOrderActive();
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("pendingComments", commentCount);

        return "dashboard/index";
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
