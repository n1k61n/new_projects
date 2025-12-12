package com.example.coffo.controllers;


import com.example.coffo.DTOs.responce.MenuItemResponceDTO;
import com.example.coffo.services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService itemService;



    @GetMapping("/coffees")
    public String getMenuPage(Model model) {
        List<List<MenuItemResponceDTO>> groupedItems = itemService.getAllItems(4);
        model.addAttribute("groupedItems", groupedItems);
        return "coffees";
    }

}
