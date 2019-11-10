package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostRemove;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String allUsersPage(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "users";
    }

    @PostMapping(path = {"/users"})
    public String allUsersPageBack(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "users";
    }

    @PostMapping(path = {"/remove/user"})
    public String deleteUser(@RequestParam("id") Long id) {
        userService.remove(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("login") String login,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("repeatPassword") String repeatPassword,
                           @RequestParam("role") String role, Model model) {
        if (password.equals(repeatPassword) && login != null) {
            User user = new User(login, email, password, role);
            userService.add(user);
        } else {
            model.addAttribute("Incorrect input", "Please enter valid values");
            return "/register";
        }
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/edit/user")
    public String getUserEditPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("oldLogin", userService.getUserById(id).get().getLogin());
        model.addAttribute("oldPassword", userService.getUserById(id).get().getPassword());
        model.addAttribute("oldEmail", userService.getUserById(id).get().getEmail());
        return "change_user";
    }

    @PostMapping(value = "/edit/user")
    public String editUser(@RequestParam("id") Long id,
                           @RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("role") String role) {
        userService.remove(id);
        userService.add(new User(login, email, password, role));
        return "redirect:/admin/users";
    }
}
