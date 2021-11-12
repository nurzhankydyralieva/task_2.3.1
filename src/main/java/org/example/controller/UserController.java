package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String getUserList(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users/users_list";
    }

    @GetMapping("/new")
    public String getNewUser(@ModelAttribute("user") User user) {
        return "/users/create";
    }

    @PostMapping()
    public String postNewUser(@ModelAttribute("user") User user) {
        service.createUser(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String patchUser(@ModelAttribute("user") User user,
                            @PathVariable("id") Long id) {
        service.updateUser(id, user);
        return "redirect:/users";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", service.getUserById(id));
        return "users/user";
    }
    @GetMapping("{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", service.getUserById(id));
        return "users/edit";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
        return "redirect:/users";
    }
}
