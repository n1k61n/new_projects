package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.coupon.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/comment")
public class CommentController {

    @GetMapping
    public String index(Model model){
        return "dashboard/comment/index";
    }
}
