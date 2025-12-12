package com.example.coffo.services.impl;

import com.example.coffo.DTOs.responce.MenuItemResponceDTO;
import com.example.coffo.models.MenuItem;
import com.example.coffo.repositories.MemuItemRepository;
import com.example.coffo.services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MemuItemRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<MenuItemResponceDTO> menuItems() {
        return repository.findAll().stream().map(menuItem ->
                modelMapper.map(menuItem, MenuItemResponceDTO.class)).toList();
    }

    @Override
    public MenuItemResponceDTO createMenuItem(MenuItemResponceDTO menuItemResponceDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setDescription(menuItemResponceDTO.getDescription());
        menuItem.setImageUrl(menuItemResponceDTO.getImageUrl());
        menuItem.setName(menuItemResponceDTO.getName());
        MenuItem savedMenuItem = repository.save(menuItem);
        return modelMapper.map(savedMenuItem, MenuItemResponceDTO.class);
    }

    @Override
    public MenuItemResponceDTO updateMenuItem(Long id, MenuItemResponceDTO menuItemResponceDTO) {
        MenuItem existingMenuItem = repository.findById(id).orElse(null);
        if (existingMenuItem == null) {
            return null;
        }
        existingMenuItem.setDescription(menuItemResponceDTO.getDescription());
        existingMenuItem.setImageUrl(menuItemResponceDTO.getImageUrl());
        existingMenuItem.setName(menuItemResponceDTO.getName());
        MenuItem savedMenuItem = repository.save(existingMenuItem);
        return modelMapper.map(savedMenuItem, MenuItemResponceDTO.class);
    }

    @Override
    public boolean deleteMenuItem(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public MenuItemResponceDTO getMenuItemById(Long id) {
        MenuItem menuItem = repository.findById(id).orElse(null);
        if(menuItem == null){
            return null;
        }
        return modelMapper.map(menuItem, MenuItemResponceDTO.class);
    }

    @Override
    public List<MenuItemResponceDTO> getAllItems() {
        return repository.findAll().stream().map(menuItem ->
                modelMapper.map(menuItem, MenuItemResponceDTO.class)).toList();
    }
}
