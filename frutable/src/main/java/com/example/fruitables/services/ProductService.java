package com.example.fruitables.services;

import com.example.fruitables.dtos.product.ProductCreateDto;
import com.example.fruitables.dtos.product.ProductDashboardDto;
import com.example.fruitables.dtos.product.ProductSliderDto;
import com.example.fruitables.dtos.product.ProductUpdateDto;
import com.example.fruitables.models.Product;

import java.util.List;

public interface ProductService {
    List<ProductDashboardDto> getDashboardProducts();

    boolean createProduct(ProductCreateDto productCreateDto);

    ProductUpdateDto getUpdateProduct(Long id);

    boolean updateProduct(Long id, ProductUpdateDto productUpdateDto);

    boolean removeCategory(Long id);

    List<Product> getAllProducts();

    List<ProductSliderDto> getSilderProducts();

    Product getProductById(Long productId);
}

