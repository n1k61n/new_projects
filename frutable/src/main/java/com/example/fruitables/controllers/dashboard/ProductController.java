package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.category.CategoryDto;
import com.example.fruitables.dtos.product.ProductCreateDto;
import com.example.fruitables.dtos.product.ProductDashboardDto;
import com.example.fruitables.dtos.product.ProductUpdateDto;
import com.example.fruitables.services.CategoryService;
import com.example.fruitables.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String index(Model model){
        List<ProductDashboardDto> productDtoList = productService.getDashboardProducts();
        model.addAttribute("products", productDtoList);
        return "dashboard/product/index";
    }


    @GetMapping("/create")
    public String create(Model model){
        List<CategoryDto> categoryDtoList  = categoryService.getDashboardCategory();
        model.addAttribute("categories", categoryDtoList);
        return "dashboard/product/create";
    }

    @PostMapping("/create")
    public String create(ProductCreateDto productCreateDto){
        boolean result = productService.createProduct(productCreateDto);
        return "redirect:/dashboard/product";
    }


    @GetMapping("/update/{id}")
    public String update (@PathVariable Long id, Model model){
        ProductUpdateDto productUpdateDto = productService.getUpdateProduct(id);
        List<CategoryDto> categoryDtoList  = categoryService.getDashboardCategory();
        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("product", productUpdateDto);
        return "dashboard/product/update";
    }

    @PostMapping("/update/{id}")
    public String update (@PathVariable Long id, ProductUpdateDto productUpdateDto){
        productService.updateProduct(id, productUpdateDto);
        return "redirect:/dashboard/product";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boolean result = productService.removeCategory(id);
        return "redirect:/dashboard/category";
    }
}
