package com.example.coffo.services;

import com.example.coffo.DTOs.responce.MenuItemResponceDTO;
import com.example.coffo.models.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItemResponceDTO> menuItems();
    MenuItemResponceDTO createMenuItem(MenuItemResponceDTO menuItemResponceDTO);
    MenuItemResponceDTO updateMenuItem(Long id, MenuItemResponceDTO menuItemResponceDTO);
    boolean deleteMenuItem(Long id);
    MenuItemResponceDTO getMenuItemById(Long id);
}
