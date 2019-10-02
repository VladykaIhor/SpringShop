package com.controller;

import com.entity.Product;
import com.entity.User;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Objects;

@Controller
@SessionAttributes("user")
public class InitController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public InitController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String login(@AuthenticationPrincipal User user) {
        if (Objects.isNull(user)) {
            User test = new User("test", "test@test", "test", "ROLE_ADMIN");
            User user1 = new User("user", "yngwar95@gmail.com", "user", "ROLE_USER");
            userService.add(test);
            userService.add(user1);
            return "redirect:/login";
        } else if ("ROLE_ADMIN".equals(user.getRole())) {
            return "redirect:/admin/users";
        } else {
            productService.add(new Product("Guitar Pick", "1.25 mm", 5.0));
            productService.add(new Product("Strings", "10-52", 280.0));
            return "redirect:/user/products";
        }
    }

    @GetMapping(path = {"/login"})
    public String login() {
        return "login";
    }

    @GetMapping(path = {"/logout"})
    public String logout() {
        return "redirect:/";
    }
}