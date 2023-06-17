package com.akhil.controller;

import com.akhil.entity.User;
import com.akhil.service.UserValidationService;
import com.akhil.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserValidationService userValidationService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        if(userValidationService.incomeValidation(user)) {
            userService.saveUser(user);
            return "redirect:/users";
        }
        model.addAttribute("errorMessage", "Invalid User or Password");
        return "new_user";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit_user";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") User user, Model model) {
        if (userValidationService.incomeValidation(user)) {
            userService.deleteUser(id);
            userService.updateUser(user);
            return "redirect:/users";
        }
        model.addAttribute("user", userService.getUser(id));
        return "edit_user";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
