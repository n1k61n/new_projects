package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.category.CategoryCreateDto;
import com.example.fruitables.dtos.category.CategoryDto;
import com.example.fruitables.dtos.category.CategoryUpdateDto;
import com.example.fruitables.exceptions.ResourceNotFoundException;
import com.example.fruitables.models.Category;
import com.example.fruitables.repositories.CategoryRepository;
import com.example.fruitables.services.CategoryService;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getDashboardCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories.stream()
                    .map(category -> modelMapper.map(category, CategoryDto.class))
                    .collect(Collectors.toList()); // Stream nəticəsini List-ə çeviririk
        }
        return List.of();
    }

    @Override
    public boolean createCategory(CategoryCreateDto categoryCreateDto) {
        try{
            Category category =  modelMapper.map(categoryCreateDto, Category.class);
            categoryRepository.save(category);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public CategoryUpdateDto getUpdateCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            return modelMapper.map(category, CategoryUpdateDto.class);
        }
        return new CategoryUpdateDto();
    }

    @Override
    public boolean updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        try{
            Category category =  modelMapper.map(categoryUpdateDto, Category.class);
            category.setId(id);
            categoryRepository.save(category);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean removeCategory(Long id) {
        try{
            Category category =  categoryRepository.findById(id).orElse(new Category());
            categoryRepository.delete(category);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
    }
}

