package com.example.fruitables.services;

import com.example.fruitables.dtos.category.CategoryCreateDto;
import com.example.fruitables.dtos.category.CategoryDto;
import com.example.fruitables.dtos.category.CategoryUpdateDto;
import com.example.fruitables.models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getDashboardCategory();

    boolean createCategory(CategoryCreateDto categoryCreateDto);

    CategoryUpdateDto getUpdateCategory(Long id);

    boolean updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);

    boolean removeCategory(Long id);

    Category getCategoryById(Long categoryId);
}
