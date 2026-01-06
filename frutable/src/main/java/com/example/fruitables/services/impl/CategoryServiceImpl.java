package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.category.*;
import com.example.fruitables.exceptions.ResourceNotFoundException;
import com.example.fruitables.models.Category;
import com.example.fruitables.repositories.CategoryRepository;
import com.example.fruitables.services.CategoryService;


import jakarta.transaction.Transactional;
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
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    @Transactional
    public boolean createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        category.setId(null);
        categoryRepository.save(category);
        return true;
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

    @Override
    public List<CategoryPinnedDto> getPinnedCategory() {
        List<Category> categories = categoryRepository.findByPinnedTrue();
        if(!categories.isEmpty()){
            return categories.stream().map(category -> modelMapper.map(category, CategoryPinnedDto.class)).toList();
        }
        return List.of();
    }

    @Override
    public  List<CategoryDto> getAllCategoriesWithCount() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryCountLists = categoryRepository.findAll();
        if (!categoryCountLists.isEmpty()) {
            return categoryCountLists.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        }
        return List.of();
    }
}

