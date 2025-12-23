package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.product.ProductCreateDto;
import com.example.fruitables.dtos.product.ProductDashboardDto;
import com.example.fruitables.dtos.product.ProductSliderDto;
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
import java.util.Optional;

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
            Product product = modelMapper.map(productCreateDto, Product.class);
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
        try {
            Product product = modelMapper.map(productUpdateDto, Product.class);
            product.setId(id);
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    @Override
    public boolean removeCategory(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductSliderDto> getSilderProducts() {
        List<Product> products = productRepository.findBySliderTrueOrderBySliderIndexAsc();
        if(products.isEmpty()) return List.of();
        return products.stream().map(product -> modelMapper.map(product, ProductSliderDto.class)).toList();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", productId));
    }
}


