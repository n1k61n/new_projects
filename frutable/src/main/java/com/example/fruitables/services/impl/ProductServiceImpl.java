package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.product.ProductCreateDto;
import com.example.fruitables.dtos.product.ProductDashboardDto;
import com.example.fruitables.dtos.product.ProductUpdateDto;
import com.example.fruitables.exceptions.ResourceNotFoundException;
import com.example.fruitables.models.Product;
import com.example.fruitables.repositories.ProductRepository;
import com.example.fruitables.services.CategoryService;
import com.example.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.spec.ECField;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;;

    @Override
    public List<ProductDashboardDto> getDashboardProducts() {
        List<Product> products =  productRepository.findAll();
        if(!products.isEmpty()){
            return products.stream().map(product -> modelMapper.map(product, ProductDashboardDto.class)).toList();
        }
        return List.of();
    }

    @Override
    public boolean createProduct(ProductCreateDto productCreateDto) {
        try {
            Product product = new Product();
            product.setName(productCreateDto.getName());
            product.setBarcode(productCreateDto.getBarcode());
            product.setPrice(productCreateDto.getPrice());
            product.setDiscount(productCreateDto.getDiscount());
            product.setStock(productCreateDto.getStock());
            product.setImageUrl(productCreateDto.getImageUrl());
            product.setDescription(productCreateDto.getDescription());
            product.setShortDescription(productCreateDto.getShortDescription());
            product.setCategory(categoryService.getCategoryById(productCreateDto.getCategoryId()));
            productRepository.save(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ProductUpdateDto getUpdateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
        return modelMapper.map(product, ProductUpdateDto.class);

    }

    @Override
    public boolean updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        if(productRepository.existsById(id)) {
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
            product.setName(productUpdateDto.getName());
            product.setBarcode(productUpdateDto.getBarcode());
            product.setPrice(productUpdateDto.getPrice());
            product.setDiscount(productUpdateDto.getDiscount());
            product.setStock(productUpdateDto.getStock());
            product.setImageUrl(productUpdateDto.getImageUrl());
            product.setDescription(productUpdateDto.getDescription());
            product.setShortDescription(productUpdateDto.getShortDescription());
            product.setCategory(categoryService.getCategoryById(productUpdateDto.getCategoryId()));
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCategory(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


