package com.controller;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.service.CartService;
import com.service.ProductService;
import com.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"user"})
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping(value = {"/user/products/add_to_cart"})
    public String addToCartButton(@AuthenticationPrincipal User user,
                                  @RequestParam("id") Long id, Model model) {
        Optional<Product> product = productService.getById(id);
        Optional<Cart> lastCartByUser = cartService.getLastCartByUser(user);
        if (lastCartByUser.isPresent()) {
            cartService.addProductToCart(lastCartByUser.get(), product.get());
        } else {
            ArrayList<Product> list = new ArrayList<>();
            list.add(product.get());
            Cart cart = new Cart(user, list);
            cartService.createCart(cart);
            model.addAttribute("count", cartService.getSizeOfACart(cart));
        }
        return "redirect:/user/products";
    }

    @PostMapping(value = {"/user/products/remove_from_cart"})
    public String removeFromCartButton(@AuthenticationPrincipal User user,
                                       @RequestParam("id") Long id, Model model) {
        Optional<Cart> lastCartByUser = cartService.getLastCartByUser(user);
        List<Product> products = productService.getAll().stream().filter(product -> product.getId() == id).collect(Collectors.toList());
        model.addAttribute("productInTheCart", cartService.getLastCartByUser(user).get().getProducts());
        return "redirect:/user/products";
    }
}
