package com.example.fruitables.controllers.user;

import com.example.fruitables.dtos.comment.CommentDto;
import com.example.fruitables.dtos.product.ProductDto;
import com.example.fruitables.models.Product;
import com.example.fruitables.services.CommentService;
import com.example.fruitables.services.ProductService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/add-comment")
    public String addComment(@RequestParam Long productId, @RequestParam String content, Principal principal) {
        if (principal == null) return "redirect:/login";

        CommentDto comment = new CommentDto();
        comment.setComment(content);
        ProductDto productDto = productService.getById(productId);

        comment.setProduct(modelMapper.map(productDto, Product.class));
        comment.setUser(userService.findByEmail(principal.getName()));
        comment.setCreatedAt(LocalDateTime.now());

        boolean result = commentService.create(comment);

        return "redirect:/shop-detail/" + productId;
    }
}
