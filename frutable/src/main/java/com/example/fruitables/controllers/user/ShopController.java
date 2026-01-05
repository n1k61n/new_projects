package com.example.fruitables.controllers.user;

import com.example.fruitables.dtos.category.CategoryDto;
import com.example.fruitables.dtos.product.ProductDto;
import com.example.fruitables.models.User;
import com.example.fruitables.services.CategoryService;
import com.example.fruitables.services.ProductService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;


    @GetMapping("/shop-detail/{id}")
    @Transactional(readOnly = true)
    public String getProductDetail(@PathVariable("id") Long id, Model model, Principal principal) {
        ProductDto product = productService.getProductById(id);
        model.addAttribute("product", product);


        List<CategoryDto> categories = categoryService.getAllCategories();
        categories.forEach(cat -> cat.getProducts().size());
        model.addAttribute("categories", categories);

        List<ProductDto> relatedProducts = productService.getRelatedProducts(product.getCategoryId(), id);
        model.addAttribute("relatedProducts", relatedProducts);

        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("currentUser", user);
        }

        return "shop-detail";
    }
}
