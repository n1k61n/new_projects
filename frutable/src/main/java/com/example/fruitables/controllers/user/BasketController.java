package com.example.fruitables.controllers.user;

import com.example.fruitables.dtos.cart.AddToCartDto;
import com.example.fruitables.dtos.cart.CartItemDto;
import com.example.fruitables.dtos.order.CartSummaryDTO;
import com.example.fruitables.payloads.results.Result;
import com.example.fruitables.services.CartService;
import com.example.fruitables.services.OrderService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/my-basket")
    @PreAuthorize("isAuthenticated()")
    public String myBasket(Principal principal, Model model){
        String email = principal.getName();

        List<CartItemDto> cartItems =   cartService.getCartItems(email);
        model.addAttribute("basket", cartItems);

        double subTotal = cartItems.stream().mapToDouble(CartItemDto::getItemPrice).sum();
        model.addAttribute("subtotal", subTotal);
        model.addAttribute("total", subTotal + 3.00);

        return "basket/my-basket";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String addToCart(Principal principal, AddToCartDto addToCartDto){
        if(principal == null){
            return "redirect:/login";
        }
        String email = principal.getName();
        Result result = cartService.addProductToCart(email, addToCartDto);

        return "redirect:/basket/my-basket";
    }



    @PostMapping("/delete")
    public String deleteItemFromCart(@RequestParam Long productId, Principal principal){
        String username = principal.getName();
        Result result = cartService.deleteItem(username, productId);
        return "redirect:/basket/my-basket";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId, @RequestParam String action, Principal principal) {
        String username = principal.getName();
        if("increase".equals(action)){
            cartService.increaseQuantity(username, productId);
        }else if("decrease".equals(action)){
            cartService.decreaseQuantity(username, productId);
        }
        return "redirect:/basket/my-basket";
    }


    @PostMapping("/checkout")
    public String proceedCheckout(@ModelAttribute("cartSummary") CartSummaryDTO summary, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        String userName = principal.getName();
        boolean result = orderService.createUserOrder(userName, summary);

        if(result)
            return "redirect:/basket/my-basket?success=true";
        else
            return "redirect:/basket/my-basket?error=true";
    }

}
