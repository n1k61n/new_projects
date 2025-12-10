package com.example.coffo.controllers;

import com.example.coffo.services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping("/coffees")
    public String coffees() {
        return "coffees";
    }

}
