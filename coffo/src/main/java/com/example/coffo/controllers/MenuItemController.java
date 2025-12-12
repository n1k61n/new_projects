package com.example.coffo.controllers;

import com.example.coffo.DTOs.responce.MenuItemResponceDTO;
import com.example.coffo.models.MenuItem;
import com.example.coffo.services.MenuItemService;
import com.example.coffo.utils.ListUtils;
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
        List<MenuItemResponceDTO> allItems = itemService.getAllItems();

        // Listi 4-4 hissələrə ayırırıq
        List<List<MenuItemResponceDTO>> groupedItems = ListUtils.splitList(allItems, 4);

        // Modelə qruplaşdırılmış listi əlavə edirik
        model.addAttribute("groupedItems", groupedItems);

        return "coffees";
    }

}
