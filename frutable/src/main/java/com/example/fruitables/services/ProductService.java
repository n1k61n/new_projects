package com.example.fruitables.services;

import com.example.fruitables.dtos.product.*;
import com.example.fruitables.models.Product;

import java.util.List;

public interface ProductService {
    List<ProductDashboardDto> getDashboardProducts();

    boolean createProduct(ProductCreateDto productCreateDto);

    ProductUpdateDto getUpdateProduct(Long id);

    boolean updateProduct(Long id, ProductUpdateDto productUpdateDto);

    boolean removeCategory(Long id);

    List<ProductPinnedDto> getAllProducts();

    List<ProductSliderDto> getSilderProducts();

    ProductDto getProductById(Long productId);

    List<ProductDto> getRelatedProducts(Long categoryId, Long id);

    ProductDto getById(Long productId);
}

