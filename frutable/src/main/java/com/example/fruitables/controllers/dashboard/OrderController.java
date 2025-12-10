package com.example.fruitables.controllers.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/order")
public class OrderController {


    @GetMapping
    public String order(Model model){
        return "dashboard/order/orders";
    }
    @GetMapping("/create")
    public String create(){
        return "dashboard/order/create";
    }
    @GetMapping("/update")
    public String update(){
        return "dashboard/order/update";
    }
    @GetMapping("/delete")
    public String delete(){
        return "dashboard/order/delete";
    }
    @GetMapping("/details")
    public String details(){
        return "dashboard/order/details";
    }


}

