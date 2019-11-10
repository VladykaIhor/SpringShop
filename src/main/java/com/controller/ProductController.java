package com.controller;

import com.entity.Product;
import com.entity.User;
import com.service.CartService;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping(value = "/admin/products")
    public String getProductsPage(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @PostMapping(value = "/admin/products")
    public String registerProduct(@RequestParam("name") String name,
                                  @RequestParam("description") String description,
                                  @RequestParam("price") Double price,
                                  Model model) {
        if (Objects.nonNull(name) || Objects.nonNull(description) || Objects.nonNull(price)) {
            Product product = new Product(name, description, price);
            productService.add(product);
        } else {
            model.addAttribute("error", "Please fill all the fields correctly!");
            return "register_product";
        }
        return "redirect:/admin/products";
    }

    @PostMapping(value = "/admin/products/remove")
    public String removeProductButton(@RequestParam("id") Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping(path = {"/admin/products/edit"})
    public String editProductsPage(@RequestParam("id") Long id,
                                   Model model) {
        model.addAttribute("id", id);
        Optional<Product> productOptional = productService.getById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("oldName", productOptional.get().getName());
            model.addAttribute("oldDescription", productOptional.get().getDescription());
            model.addAttribute("oldPrice", productOptional.get().getPrice());
        }
        return "change_product";
    }

    @PostMapping(path = {"/admin/products/edit"})
    public String updateProduct(@RequestParam("id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") Double price) {
        productService.delete(id);
        productService.update(new Product(name, description, price));
        return "redirect:/admin/products";
    }


    @GetMapping(path = {"/user/products"})
    public String getAllProductsPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("cart", cartService.getLastCartByUser(user).isPresent() ?
                cartService.getLastCartByUser(user).get().getProducts() : Collections.emptyList());
        return "productsUser";
    }
}
